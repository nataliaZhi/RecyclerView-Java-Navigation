package dam.pmdm.tarea2nz.supermario;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import dam.pmdm.tarea2nz.R;
import dam.pmdm.tarea2nz.databinding.CharacterListFragmentBinding;


/**
 * Fragmento que muestra una lista de personajes en un RecyclerView.
 * Cada personaje tiene un nombre, una descripción, habilidades y una imagen asociada.
 */
public class CharacterListFragment extends Fragment {

    private CharacterListFragmentBinding binding;
    private ArrayList<Character> characters;

    /**
     * Método que infla el layout del fragmento utilizando ViewBinding.
     *
     * @param inflater El objeto LayoutInflater que se utiliza para inflar la vista.
     * @param container El contenedor al que se adjuntará la vista inflada.
     * @param savedInstanceState El estado guardado del fragmento, si existe.
     * @return La vista inflada que representa el fragmento.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflar el layout utilizando el binding
        binding = CharacterListFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Método que se ejecuta después de que la vista ha sido creada. En este método se
     * inicializa la lista de personajes, se muestra un mensaje de bienvenida y se configura el RecyclerView
     * con un adaptador y un layout manager.
     *
     * @param view La vista raíz que representa el fragmento.
     * @param savedInstanceState El estado guardado del fragmento.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializa la lista de personajes
        loadCharacters();

        // Muestra un Snackbar con un mensaje de bienvenida
        Snackbar.make(view, R.string.welcome_message, Snackbar.LENGTH_LONG).show();

        // Configura el RecyclerView con el adaptador y el LayoutManager
        CharacterAdapter adapter = new CharacterAdapter(characters, getActivity());
        binding.rvCharacter.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvCharacter.setAdapter(adapter);
    }

    /**
     * Método privado para cargar los personajes en la lista.
     * Cada personaje se crea con su nombre, descripción, habilidades e imagen.
     */
    private void loadCharacters() {
        // Inicializa la lista de personajes
        characters = new ArrayList<Character>();

        // Agrega los personajes a la lista
        characters.add(
                new Character(
                        getString(R.string.mario_name),
                        getString(R.string.mario_description),
                        getString(R.string.mario_abilities),
                        R.drawable.mario));
        characters.add(
                new Character(
                        getString(R.string.luigi_name),
                        getString(R.string.luigi_description),
                        getString(R.string.luigi_abilities),
                        R.drawable.luigi));
        characters.add(
                new Character(
                        getString(R.string.peach_name),
                        getString(R.string.peach_description),
                        getString(R.string.peach_abilities),
                        R.drawable.peach));
        characters.add(
                new Character(
                        getString(R.string.toad_name),
                        getString(R.string.toad_description),
                        getString(R.string.toad_abilities),
                        R.drawable.toad));
        characters.add(
                new Character(
                        getString(R.string.yoshi_name),
                        getString(R.string.yoshi_description),
                        getString(R.string.yoshi_abilities),
                        R.drawable.yoshi));
        characters.add(
                new Character(
                        getString(R.string.bowser_name),
                        getString(R.string.bowser_description),
                        getString(R.string.bowser_abilities),
                        R.drawable.bowser));
        characters.add(
                new Character(
                        getString(R.string.wario_name),
                        getString(R.string.wario_description),
                        getString(R.string.wario_abilities),
                        R.drawable.wario));
        characters.add(
                new Character(
                        getString(R.string.koopa_name),
                        getString(R.string.koopa_description),
                        getString(R.string.koopa_abilities),
                        R.drawable.koopa));
    }

    /**
     * Método que se ejecuta cuando el fragmento está en estado activo. Este método
     * cambia el título de la ActionBar para que refleje el título de la lista de personajes.
     */
    @Override
    public void onStart() {
        super.onStart();

        // Cambia el título de la ActionBar cuando el fragmento está visible
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.characters_list);
        }
    }
}
