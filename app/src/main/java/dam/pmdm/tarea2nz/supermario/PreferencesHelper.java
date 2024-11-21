package dam.pmdm.tarea2nz.supermario;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Clase utilitaria para gestionar la configuración de preferencias, como el idioma, en SharedPreferences.
 * Permite guardar y obtener el idioma seleccionado por el usuario.
 */
public class PreferencesHelper {

    /** Clave utilizada para almacenar y recuperar el idioma desde SharedPreferences. */
    private static final String LANGUAGE_KEY = "language";

    /**
     * Guarda el idioma seleccionado por el usuario en SharedPreferences.
     * Este método permite almacenar el código del idioma en las preferencias de la aplicación.
     *
     * @param context El contexto de la aplicación, necesario para acceder a SharedPreferences.
     * @param languageCode El código del idioma (por ejemplo, "es" para español, "en" para inglés).
     */
    public static void saveLanguage(Context context, String languageCode) {
        // Obtener las preferencias en modo privado
        SharedPreferences preferences = context.getSharedPreferences(LANGUAGE_KEY, Context.MODE_PRIVATE);

        // Crear un editor para guardar el valor en SharedPreferences
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(LANGUAGE_KEY, languageCode); // Guardar el código del idioma
        editor.apply(); // Aplicar los cambios
    }

    /**
     * Obtiene el idioma almacenado en SharedPreferences.
     * Si no se ha guardado ningún idioma, devuelve "es" por defecto.
     *
     * @param context El contexto de la aplicación, necesario para acceder a SharedPreferences.
     * @return El código del idioma almacenado, o "es" si no se ha guardado ningún valor.
     */
    public static String getSavedLanguage(Context context) {
        // Obtener las preferencias en modo privado
        SharedPreferences preferences = context.getSharedPreferences(LANGUAGE_KEY, Context.MODE_PRIVATE);

        // Devolver el idioma almacenado, "es" (español) como valor por defecto
        return preferences.getString(LANGUAGE_KEY, "es");
    }
}
