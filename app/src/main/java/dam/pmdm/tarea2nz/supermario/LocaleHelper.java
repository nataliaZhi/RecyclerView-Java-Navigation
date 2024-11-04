package dam.pmdm.tarea2nz.supermario;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import java.util.Locale;

public class LocaleHelper {

private static final String LANGUAGE_KEY = "language"; // Clave para almacenar el idioma

// Método para establecer y guardar el idioma
public static void setLocale(Context context, String language) {
    saveLanguage(context, language); // Guardar el idioma en SharedPreferences
    updateResources(context, language); // Actualizar la configuración de recursos
}

// Método privado para guardar el idioma en SharedPreferences
private static void saveLanguage(Context context, String language) {
    SharedPreferences prefs = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = prefs.edit();
    editor.putString(LANGUAGE_KEY, language); // Almacenar el idioma elegido
    editor.apply(); // Aplicar cambios
}

// Método privado para obtener el idioma almacenado
public static String getLanguage(Context context) {
    SharedPreferences prefs = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE);
    return prefs.getString(LANGUAGE_KEY, "es"); // Idioma predeterminado es español
}

// Método privado para actualizar la configuración de recursos según el idioma
private static void updateResources(Context context, String language) {
    Locale locale = new Locale(language);
    Locale.setDefault(locale); // Establecer el nuevo locale por defecto

    Resources resources = context.getResources();
    Configuration configuration = resources.getConfiguration();
    configuration.setLocale(locale); // Actualizar la configuración con el nuevo locale

    // Crear un nuevo contexto con la configuración actualizada
    context = context.createConfigurationContext(configuration);
}
}