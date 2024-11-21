package dam.pmdm.tarea2nz.supermario;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import java.util.Locale;
import dam.pmdm.tarea2nz.R;
import dam.pmdm.tarea2nz.databinding.ActivityMainBinding;

/**
 * Actividad principal que gestiona la interfaz de usuario y la navegación entre fragmentos.
 * Incluye la configuración del idioma, la navegación con un menú lateral (drawer) y la gestión de eventos.
 */
public class MainActivity extends AppCompatActivity {

    private NavController navController;  // Controlador de navegación.
    private ActionBarDrawerToggle toggle; // Toggle para el menú lateral (drawer).
    private ActivityMainBinding binding;  // ViewBinding para la actividad principal.
    private AppBarConfiguration appBarConfiguration;  // Configuración de la barra de aplicación.

    /**
     * Método llamado al crear la actividad. Inicializa la configuración de la interfaz y la navegación.
     *
     * @param savedInstanceState El estado guardado de la actividad si existe.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Establecer el idioma antes de cargar la actividad.
        String lang = PreferencesHelper.getSavedLanguage(this);
        setLocale(lang); // Aplicar idioma desde SharedPreferences

        // Inicialización de ViewBinding para acceder a los componentes de la interfaz.
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configuración de la Toolbar (barra de herramientas).
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        // Inicializar el NavController para gestionar la navegación entre fragmentos.
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = NavHostFragment.findNavController(navHostFragment);
        }

        // Configurar la AppBarConfiguration para gestionar la barra de acción.
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
                .setOpenableLayout(binding.drawerLayout)
                .build();

        // Vincular el NavController con el ActionBar (para mostrar el título y los botones de navegación).
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // Configurar el NavigationView para vincularlo con el NavController.
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Configurar el toggle del menú lateral (drawer).
        configureToggleMenu();

        // Configurar la navegación de los elementos del menú lateral.
        configureNavigation();

        // Agregar un listener para cambiar el contenido basado en la navegación entre fragmentos.
        navController.addOnDestinationChangedListener(this::onChangeView);
    }

    /**
     * Cambia el idioma de la aplicación según el código de idioma proporcionado.
     *
     * @param languageCode El código del idioma (por ejemplo, "es" o "en").
     */
    void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode); // Crear un objeto Locale con el código del idioma.
        Locale.setDefault(locale); // Establecer el idioma como predeterminado.

        // Configurar los recursos para que utilicen el idioma especificado.
        Configuration config = new Configuration();
        config.setLocale(locale);

        // Actualizar la configuración de los recursos con el nuevo idioma.
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        getApplicationContext().createConfigurationContext(config);
    }

    /**
     * Actualiza la vista del fragmento actual cuando cambia el idioma.
     *
     * Se realiza un "detach" y "attach" del fragmento actual para refrescar la vista después de cambiar el idioma.
     */
    void updateLanguageView() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (currentFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .detach(currentFragment)  // Desacoplar el fragmento actual.
                    .attach(currentFragment)  // Volver a acoplar el fragmento para refrescar la vista.
                    .commit();
        }
    }

    /**
     * Cambia el comportamiento de la barra de acción (ActionBar) según el fragmento actual.
     *
     * @param navController El controlador de navegación.
     * @param navDestination El destino de navegación actual.
     * @param bundle Los datos de la navegación.
     */
    private void onChangeView(NavController navController, NavDestination navDestination, Bundle bundle) {
        if (getSupportActionBar() == null) return;  // Validación de seguridad.

        // Si estamos en el fragmento de detalles del personaje, cambiamos el comportamiento de la barra de acción.
        if (navDestination.getId() == R.id.characterDetailFragment) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Mostrar el ícono de "Volver atrás".
            toggleDrawerButton(false); // Cambiar al comportamiento de "Volver atrás".
        } else {
            // Mostrar el ícono del menú lateral cuando estamos en cualquier otro fragmento.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toggleDrawerButton(true); // Cambiar al comportamiento de "Menú lateral".
        }
    }

    /**
     * Alterna entre el botón del menú lateral y el botón de "Volver atrás".
     *
     * @param enableDrawer True para habilitar el menú lateral, false para habilitar el botón de "Volver atrás".
     */
    private void toggleDrawerButton(boolean enableDrawer) {
        if (enableDrawer) {
            toggle.setDrawerIndicatorEnabled(true);  // Habilitar el ícono del menú lateral.
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED); // Desbloquear el drawer.
        } else {
            toggle.setDrawerIndicatorEnabled(false);  // Deshabilitar el ícono del menú lateral.
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED); // Bloquear el drawer.

            // Configurar el botón de atrás (nav up) para navegar hacia atrás.
            toggle.setToolbarNavigationClickListener(v -> {
                NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
                navController.navigateUp();  // Navegar hacia el fragmento anterior.
            });
        }
    }

    /**
     * Configura el menú de opciones en la Toolbar.
     *
     * @param menu El menú de opciones de la actividad.
     * @return True si el menú fue inflado correctamente.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);  // Inflar el menú en la Toolbar.
        return true;
    }

    /**
     * Configura el toggle del menú lateral (drawer).
     * Este método configura el ActionBarDrawerToggle que gestiona la apertura y cierre del drawer.
     */
    private void configureToggleMenu() {
        toggle = new ActionBarDrawerToggle(
                this,
                binding.drawerLayout,
                binding.toolbar,
                R.string.open_drawer,
                R.string.close_drawer
        );
        binding.drawerLayout.addDrawerListener(toggle);  // Vincular el toggle con el DrawerLayout.
        toggle.syncState();  // Sincronizar el estado del toggle.
    }

    /**
     * Configura la navegación en el menú lateral (drawer).
     * Este método maneja la selección de elementos en el menú lateral y realiza la navegación a los fragmentos correspondientes.
     */
    private void configureNavigation() {
        binding.navView.setNavigationItemSelectedListener(menuItem -> {
            int itemId = menuItem.getItemId();

            if (itemId == R.id.nav_home) {
                navController.navigate(R.id.characterListFragment);  // Navegar a la lista de personajes.
            } else if (itemId == R.id.nav_settings) {
                // Dejamos vacio porque se navega al fragmento desde  siguente item
            } else if (itemId == R.id.nav_language) {
                navController.navigate(R.id.settingsFragment);  // Navegar a la configuración de idioma.
            }

            // Cerrar el menú lateral después de seleccionar una opción.
            binding.drawerLayout.closeDrawers();
            return true;  // Indicar que el evento fue manejado correctamente.
        });
    }

    /**
     * Maneja los clics en las opciones del menú de la Toolbar.
     *
     * @param item El elemento del menú seleccionado.
     * @return True si el evento es manejado correctamente.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Si el ítem es del tipo de botón de menú lateral, se maneja aquí.
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Si el ítem es "Acerca de...", mostrar un diálogo.
        if (item.getItemId() == R.id.action_about) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogViewStyle);
            builder.setTitle(R.string.about_title)
                    .setMessage(R.string.about_message)
                    .setIcon(R.mipmap.ic_mario_launcher)
                    .setPositiveButton(getString(R.string.ok_button), null)
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);  // Si no es ningún ítem conocido, llamamos al super.
    }

    /**
     * Maneja la acción de navegación hacia arriba (botón de retroceder).
     *
     * @return True si la acción fue manejada correctamente.
     */
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    /**
     * Maneja la acción cuando un personaje es seleccionado.
     * Navega al fragmento de detalles del personaje con los datos del personaje seleccionado.
     *
     * @param currentCharacter El personaje seleccionado.
     * @param view La vista en la que se hizo el clic.
     */
    public void characterClicked(Character currentCharacter, View view) {
        // Empaquetar los datos del personaje en un Bundle para la navegación.
        Bundle bundle = new Bundle();
        bundle.putString("name", currentCharacter.getName());
        bundle.putInt("image", currentCharacter.getImage());
        bundle.putString("description", currentCharacter.getDescription());
        bundle.putString("abilities", currentCharacter.getAbilities());

        // Mostrar un mensaje Toast con el nombre del personaje seleccionado.
        Toast.makeText(this, getString(R.string.character_selected, currentCharacter.getName()), Toast.LENGTH_SHORT).show();

        // Navegar al fragmento de detalles del personaje.
        Navigation.findNavController(view).navigate(R.id.characterDetailFragment, bundle);
    }
}





