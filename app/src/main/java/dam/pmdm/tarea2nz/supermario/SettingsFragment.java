package dam.pmdm.tarea2nz.supermario;


import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import dam.pmdm.tarea2nz.R;

/**
 * Fragmento de configuración que permite al usuario cambiar las preferencias de la aplicación.
 * Incluye un interruptor para cambiar el idioma de la aplicación entre español e inglés.
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    /**
     * Carga las preferencias desde un archivo XML y configura el comportamiento del interruptor de idioma.
     *
     * @param savedInstanceState Estado previamente guardado del fragmento, si existe.
     * @param rootKey            La clave raíz del fragmento de preferencias, si se usa una jerarquía de preferencias anidadas.
     */
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {

        // Cargar las preferencias desde el archivo XML de recursos
        setPreferencesFromResource(R.xml.preferences, rootKey);

        // Obtener referencia al SwitchPreferenceCompat de cambio de idioma
        SwitchPreferenceCompat switchLanguage = findPreference("switch_language");

        // Configurar el estado inicial del Switch basándose en las preferencias compartidas
        if (switchLanguage != null) {
            // Obtener las preferencias compartidas para recuperar el estado actual del interruptor
            SharedPreferences prefs = getPreferenceManager().getSharedPreferences();
            boolean isSpanish = prefs.getBoolean("switch_language", true); // Se asume que el idioma predeterminado es español
            switchLanguage.setChecked(isSpanish);

            /*
              Configurar un listener para manejar los cambios en el estado del interruptor de idioma.
              Cuando se cambia el estado, se actualiza el idioma de la aplicación y se reinicia la actividad.
             */
            switchLanguage.setOnPreferenceChangeListener((preference, newValue) -> {
                boolean isChecked = (Boolean) newValue; // Obtener el nuevo estado como un valor booleano

                // Cambiar el idioma de la aplicación según el estado del interruptor
                if (isChecked) {
                    LocaleHelper.setLocale(getActivity(), "es"); // Cambiar a español
                } else {
                    LocaleHelper.setLocale(getActivity(), "en"); // Cambiar a inglés
                }

                // Reiniciar la actividad para aplicar los cambios de idioma
                getActivity().recreate();

                // Indicar que el cambio de preferencia fue manejado con éxito
                return true;
            });
        }
    }
}