package dam.pmdm.tarea2nz.supermario;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import dam.pmdm.tarea2nz.R;
import dam.pmdm.tarea2nz.databinding.ActivityMainBinding;



public class MainActivity extends AppCompatActivity {

    private NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Inicialización de ViewBinding.
       ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

        // Asignar la raíz del layout al contenido de la actividad.
        setContentView(binding.getRoot());


        //Configura el NavController
        navController=Navigation.findNavController(this,R.id.nav_host_fragment);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController);


    }




//Metodo para manejar el click en un personaje
    public void characterClicked(Character currentCharacter, View view) {


// Crear un Bundle para pasar los datos al CharacterDetailFragment
        Bundle bundle = new Bundle();
        bundle.putString("name", currentCharacter.getName());
        bundle.putInt("image", currentCharacter.getImage());
        bundle.putString("description", currentCharacter.getDescription());
        bundle.putString("abilities", currentCharacter.getAbilities());

        // Mostrar Toast al abrir la pantalla de detalles
        Toast.makeText(this, "Se ha seleccionado el personaje " + currentCharacter.getName(), Toast.LENGTH_SHORT).show();

        Navigation.findNavController(view).navigate(R.id.action_characterListFragment_to_characterDetailFragment, bundle);
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Utiliza el método navigateUp del NavController
        return navController.navigateUp() || super.onSupportNavigateUp();
    }



}