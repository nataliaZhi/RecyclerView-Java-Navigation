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


public class CharacterDetailFragment extends Fragment {
    private CharacterDetailFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Inflar layout para este fragmento
        binding = CharacterDetailFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Obtener datos del argumento que inicia este fragmento
        if (getArguments() != null) {
            String name = getArguments().getString("name");
            int image = getArguments().getInt("image");
            String description = getArguments().getString("description");
            String abilities = getArguments().getString("abilities");
//Asignar los datos a los componentes
            binding.name.setText(name);
            binding.image.setImageResource(image);
            binding.description.setText(description);
            binding.abilities.setText(abilities);

        }


    }


    @Override
    public void onStart() {
        super.onStart();
        // Cambia el título del ActionBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.detail_character);
        }
    }
}