plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "dam.pmdm.tarea2nz"
    compileSdk = 34

    defaultConfig {
        applicationId = "dam.pmdm.tarea2nz"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

}


dependencies {
    // appcompat: proporciona compatibilidad hacia atrás para componentes y estilos de Android modernos,
    // incluyendo el ActionBar, temas, widgets, y otros elementos de UI en versiones antiguas de Android.
    implementation(libs.appcompat)

    // activity-ktx: extensión de Activity con características de Kotlin, simplifica el manejo de actividades
    // en Android. Proporciona funciones y características que facilitan el uso de actividades y compatibilidad
    // con fragmentos y otras APIs modernas de Android.
    implementation(libs.activity)

    // constraintlayout: permite un diseño flexible y eficiente en pantallas de Android.
    // Facilita la creación de interfaces complejas con menos anidación y más control de alineación y posición
    // de elementos, optimizando el rendimiento.
    implementation(libs.constraintlayout)

    // drawerlayout: permite el uso de un panel lateral deslizante (Drawer) para navegación.
    // Ideal para menús de navegación de aplicaciones con múltiples secciones o páginas.
    implementation(libs.drawerlayout)

    // material: contiene componentes de UI siguiendo las guías de Material Design,
    // como botones, menús, cuadros de diálogo y otras vistas que mejoran la experiencia visual.
    implementation(libs.material)

    // navigation-fragment y navigation-ui: manejan la navegación entre fragmentos en la app.
    // navigation-fragment permite el uso de NavHost para una navegación estructurada,
    // mientras que navigation-ui simplifica el control de la UI al navegar.
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation (libs.fragment.ktx)

    // recyclerview: permite mostrar grandes listas de datos de manera eficiente,
    // reciclando vistas y mejorando el rendimiento al mostrar contenido en scroll.
    implementation(libs.recyclerview)

    // cardview: ofrece un contenedor con bordes redondeados y sombra, usado comúnmente en diseños de listas
    // para dar estilo a elementos en forma de tarjeta.
    implementation(libs.cardview)

    // preference: simplifica la creación de pantallas de configuración y almacenamiento de preferencias de usuario.
    // Permite gestionar las preferencias con un diseño predefinido para opciones como switches y listas.
    implementation(libs.preference)

    // core-splashscreen: facilita la implementación de una pantalla de inicio o "splash screen" en la app,
    // asegurando una transición fluida al cargar el contenido principal.
    implementation(libs.core.splashscreen)

    // Dependencias de prueba:

    // junit: biblioteca principal para realizar pruebas unitarias en Java.
    // Permite escribir y ejecutar tests para verificar que el código funcione correctamente.
    testImplementation(libs.junit)

    // ext-junit: extensión de JUnit para pruebas en Android, permite pruebas instrumentadas en dispositivos o emuladores.
    androidTestImplementation(libs.ext.junit)

    // espresso-core: framework de pruebas de UI en Android. Facilita la simulación e interacción
    // con la interfaz de usuario para verificar el comportamiento de los elementos.
    androidTestImplementation(libs.espresso.core)
}



