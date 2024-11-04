package dam.pmdm.tarea2nz.supermario;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import dam.pmdm.tarea2nz.R;
import dam.pmdm.tarea2nz.databinding.ActivityMainBinding;



public class MainActivity extends AppCompatActivity {

    // Controlador de navegación para manejar la navegación entre fragmentos.
    private NavController navController;

    private ActionBarDrawerToggle toggle;

    private ActivityMainBinding binding;

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Aplica el idioma predeterminado guardado
        LocaleHelper.setLocale(this, LocaleHelper.getLanguage(this));

        super.onCreate(savedInstanceState);


        // Inicialización de ViewBinding.
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        // Asigna la raíz del layout al contenido de la actividad.
        setContentView(binding.getRoot());



//        // Configura el NavController para gestionar la navegación entre fragmentos.
//        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController);
        // Configurar la Toolbar
        Toolbar toolbar= binding.toolbar;
        setSupportActionBar(toolbar);


        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            navController = NavHostFragment.findNavController(navHostFragment);
        }

        // Configurar AppBarConfiguration
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.characterListFragment //
        ).setOpenableLayout(binding.drawerLayout).build();

        // Vincular el NavController con el Toolbar
       NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);




//
//        // Obtener el NavController desde el NavHostFragment
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.nav_host_fragment);
//        navController = navHostFragment.getNavController();

        // Configurar menú toggle
        configureToggleMenu();

        // Configurar la navegación
        configureNavigation();

        // Configurar el botón de "atrás"
        configureOnBackPressed();

        // Configurar el icono del menú en la ActionBar
       if (getSupportActionBar() != null) {
          getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      }

    }

    private void configureOnBackPressed() {
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    // Cierra el menú lateral si está abierto
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                } else if (navController.getCurrentDestination() != null &&
                        navController.getCurrentDestination().getId() == R.id.characterDetailFragment) {
                    // Navega de regreso al fragmento de lista de personajes si estás en el fragmento de detalles
                    navController.navigate(R.id.characterListFragment);
                } else {
                    // Comportamiento predeterminado de navegación
                    setEnabled(false); // Deshabilitar el callback para evitar un bucle infinito
                    getOnBackPressedDispatcher().onBackPressed(); // Llamada al manejador de "atrás"
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }



    private void configureToggleMenu() {
        // Configurar el ActionBarDrawerToggle
        toggle = new ActionBarDrawerToggle(
                this,
                binding.drawerLayout,
                binding.toolbar,
                R.string.open_drawer,
                R.string.close_drawer
        );
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void configureNavigation() {
        // Vincular el NavController con el NavigationView
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Manejar la selección de elementos del menú
        binding.navView.setNavigationItemSelectedListener(menuItem -> {
            int itemId = menuItem.getItemId();

            // Verificar qué elemento del menú fue seleccionado
            if (itemId == R.id.nav_home) {
                navController.navigate(R.id.characterListFragment);
            } else if (itemId == R.id.nav_settings) {
                navController.navigate(R.id.settingsFragment);
            } else if (itemId == R.id.nav_language) {

                navController.navigate(R.id.settingsFragment);
            }

            // Cerrar el menú lateral (Drawer) después de la selección
            binding.drawerLayout.closeDrawers();
            return true; // Indicar que el evento fue manejado correctamente
        });
    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Manejar clics en el icono del menú de navegacion
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Verificar si se seleccionó la opción "Acerca de..."
        if (item.getItemId() == R.id.action_about) {
            // Mostrar el diálogo "Acerca de..."
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Acerca de")
                    .setMessage("Aplicación desarrollada por Natalia Zhilyakova.\n Versión 1.0")
                    .setIcon(R.mipmap.ic_launcher)
                    .setPositiveButton("OK", null)
                    .show();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }



    public void characterClicked(Character currentCharacter, View view) {

        // Crear un Bundle para pasar los datos al CharacterDetailFragment.
        Bundle bundle = new Bundle();
        bundle.putString("name", currentCharacter.getName());
        bundle.putInt("image", currentCharacter.getImage());
        bundle.putString("description", currentCharacter.getDescription());
        bundle.putString("abilities", currentCharacter.getAbilities());

        // Muestra un Toast con el nombre del personaje seleccionado.
        Toast.makeText(this, "Se ha seleccionado el personaje " + currentCharacter.getName(), Toast.LENGTH_SHORT).show();

        // Navega al fragmento de detalles del personaje.
        Navigation.findNavController(view).navigate(R.id.characterDetailFragment, bundle);
    }






    @Override
    public boolean onSupportNavigateUp() {

        // Manejar la navegación hacia arriba
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();

    }
    }







