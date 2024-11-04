package dam.pmdm.tarea2nz.supermario;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import dam.pmdm.tarea2nz.databinding.ItemCharacterBinding;


public class CharacterAdapter extends RecyclerView.Adapter<CharacterViewHolder> {
    // Lista de personajes a mostrar.
    private final ArrayList<Character> characters;
    private final Context context;


    public CharacterAdapter(ArrayList<Character> characters, Context context) {
        this.characters = characters;
        this.context = context;

    }



    @NonNull
    @Override

    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout del item utilizando ViewBinding.
        ItemCharacterBinding binding = ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new CharacterViewHolder(binding);


    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        // Obtener el personaje actual de la lista basado en la posición.
        Character currentCharacter = this.characters.get(position);

// Enlazar los datos y asignar el listener de clic.
        holder.bind(currentCharacter);
        holder.itemView.setOnClickListener(view -> itemClicked(currentCharacter, view));


    }



    @Override
    public int getItemCount() {
        return characters.size();
    }

    private void itemClicked(Character currentCharacter, View view) {

        ((MainActivity) context).characterClicked(currentCharacter, view);
    }


}
