package dam.pmdm.tarea2nz.supermario;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import dam.pmdm.tarea2nz.R;
import dam.pmdm.tarea2nz.databinding.CharacterDetailFragmentBinding;

/**
 * Fragmento que muestra los detalles de un personaje, incluyendo su nombre, imagen,
 * descripción y habilidades. Este fragmento recibe los datos del personaje a través de los argumentos.
 */
public class CharacterDetailFragment extends Fragment {

    private CharacterDetailFragmentBinding binding;

    /**
     * Método que infla el layout del fragmento utilizando ViewBinding.
     *
     * @param inflater El objeto LayoutInflater utilizado para inflar la vista.
     * @param container El contenedor al que se adjuntará la vista inflada.
     * @param savedInstanceState El estado guardado del fragmento, si existe.
     * @return La vista inflada que representa el fragmento.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflar el layout utilizando el binding
        binding = CharacterDetailFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Método que se ejecuta después de que la vista ha sido creada. En este método se obtienen los datos
     * del personaje desde los argumentos del fragmento y se asignan a los componentes de la vista.
     *
     * @param view La vista raíz que representa el fragmento.
     * @param savedInstanceState El estado guardado del fragmento.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Verificar si existen argumentos para recuperar los datos del personaje
        if (getArguments() != null) {
            String name = getArguments().getString("name");
            int image = getArguments().getInt("image");
            String description = getArguments().getString("description");
            String abilities = getArguments().getString("abilities");

            // Asignar los datos a los componentes de la vista
            binding.name.setText(name);
            binding.image.setImageResource(image);
            binding.description.setText(description);
            binding.abilities.setText(abilities);
        }
    }

    /**
     * Método que se ejecuta cuando el fragmento está en estado activo. Este método
     * cambia el título de la ActionBar para que refleje el título de los detalles del personaje.
     */
    @Override
    public void onStart() {
        super.onStart();

        // Cambiar el título de la ActionBar cuando el fragmento está visible
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.detail_character);
        }
    }
}