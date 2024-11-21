package dam.pmdm.tarea2nz.supermario;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import dam.pmdm.tarea2nz.R;


/**
 * Fragmento que permite la configuración de la aplicación, en particular el cambio de idioma
 * utilizando un SwitchPreference. Este fragmento lee y guarda la preferencia de idioma en SharedPreferences.
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    /**
     * Este método se ejecuta al crear las preferencias del fragmento. Carga las preferencias desde
     * un archivo XML y configura un listener para el cambio de idioma.
     *
     * @param savedInstanceState El estado guardado del fragmento (si lo hubiera).
     * @param rootKey La clave raíz para las preferencias (normalmente puede ser null).
     */
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {

        // Cargar las preferencias desde el archivo XML de recursos
        setPreferencesFromResource(R.xml.preferences, rootKey);

        // Referencia al SwitchPreferenceCompat que permite cambiar el idioma
        SwitchPreferenceCompat switchLanguage = findPreference("switch_language");

        if (switchLanguage != null) {
            // Recuperar el idioma actual desde SharedPreferences
            String currentLanguage = PreferencesHelper.getSavedLanguage(requireContext());
            boolean isSpanish = currentLanguage.equals("es");

            // Configurar el estado inicial del switch (activo si el idioma es español)
            switchLanguage.setChecked(isSpanish);

            // Configurar un listener para manejar el cambio de estado del switch
            switchLanguage.setOnPreferenceChangeListener((preference, newValue) -> {
                boolean isChecked = (Boolean) newValue;
                String newLanguage = isChecked ? "es" : "en"; // Si el switch está activado, el idioma será español

                // Guardar el nuevo idioma en SharedPreferences
                PreferencesHelper.saveLanguage(requireContext(), newLanguage);

                // Recrear la actividad para que se apliquen los cambios de idioma
                requireActivity().recreate();

                // Cambiar el idioma de la actividad principal y actualizar el fragmento
                if (getActivity() instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.setLocale(newLanguage);  // Cambiar la configuración regional
                    mainActivity.updateLanguageView();   // Actualizar la vista con el nuevo idioma
                }

                return true;
            });
        }
    }

    /**
     * Se ejecuta cuando el fragmento se inicia, estableciendo el título de la barra de acciones (ActionBar).
     */
    @Override
    public void onStart() {
        super.onStart();

        // Cambia el título del ActionBar cuando el fragmento está visible
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.settings);
        }
    }
}