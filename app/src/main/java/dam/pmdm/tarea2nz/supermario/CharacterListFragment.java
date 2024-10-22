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


public class CharacterListFragment extends Fragment {

    private CharacterListFragmentBinding binding;
    private ArrayList<Character> characters;
    private CharacterAdapter adapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflar el layout utilizando el binding
        binding=CharacterListFragmentBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //Inicializa la lista
        loadCharacters();

        // Mostrar Snackbar al cargar la lista
        Snackbar.make(view, "Bienvenidos al mundo de Mario", Snackbar.LENGTH_LONG).show();

        //Configurar el RecyclerView

        adapter= new CharacterAdapter(characters, getActivity());
        binding.rvCharacter.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvCharacter.setAdapter(adapter);



    }

    private void loadCharacters() {

        // Inicializar la lista de personajes.
        characters= new ArrayList<Character>();
//llenar lista con datos
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
                        R.drawable.luigi)
                );
        characters.add(
                new Character(
                        getString(R.string.peach_name),
                        getString(R.string.peach_description),
                        getString(R.string.peach_abilities),
                        R.drawable.peach)
                );
        characters.add(
                new Character(
                        getString(R.string.toad_name),
                        getString(R.string.toad_description),
                        getString(R.string.toad_abilities),
                        R.drawable.toad)
                );
        characters.add(

                new Character(
                        getString(R.string.yoshi_name),
                        getString(R.string.yoshi_description),
                        getString(R.string.yoshi_abilities),
                        R.drawable.yoshi)
                );
        characters.add(
                new Character(
                        getString(R.string.bowser_name),
                        getString(R.string.bowser_description),
                        getString(R.string.bowser_abilities),
                        R.drawable.bowser)
                );
        characters.add(
                new Character(
                        getString(R.string.wario_name),
                        getString(R.string.wario_description),
                        getString(R.string.wario_abilities),
                        R.drawable.wario)
                );
        characters.add(
                new Character(
                        getString(R.string.koopa_name),
                        getString(R.string.koopa_description),
                        getString(R.string.koopa_abilities),
                        R.drawable.koopa)

        );

    }

    @Override
    public void onStart() {
        super.onStart();
        // Cambia el título del ActionBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.characters_list);
        }
    }
}