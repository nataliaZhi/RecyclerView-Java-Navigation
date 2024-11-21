package dam.pmdm.tarea2nz.supermario;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import dam.pmdm.tarea2nz.databinding.ItemCharacterBinding;

/**
 * Adaptador para gestionar y mostrar una lista de personajes en un RecyclerView.
 * Utiliza ViewBinding para inflar los elementos y permite interactuar con ellos a través de clics.
 */
public class CharacterAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

    /** Lista de personajes a mostrar. */
    private final ArrayList<Character> characters;

    /** Contexto de la aplicación necesario para inflar vistas y manejar interacciones. */
    private final Context context;

    /**
     * Constructor de la clase.
     *
     * @param characters Lista de personajes que se mostrarán en el RecyclerView.
     * @param context Contexto de la aplicación o actividad actual.
     */
    public CharacterAdapter(ArrayList<Character> characters, Context context) {
        this.characters = characters;
        this.context = context;
    }

    /**
     * Infla el layout del elemento de la lista y crea un ViewHolder para gestionarlo.
     *
     * @param parent ViewGroup que contiene las vistas del RecyclerView.
     * @param viewType Tipo de vista (puede usarse si hay varios diseños diferentes).
     * @return Una instancia de CharacterViewHolder que contiene la vista inflada.
     */
    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout utilizando ViewBinding.
        ItemCharacterBinding binding = ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new CharacterViewHolder(binding);
    }

    /**
     * Enlaza los datos de un personaje específico con su correspondiente ViewHolder.
     *
     * @param holder Instancia de CharacterViewHolder que gestiona el elemento actual.
     * @param position Posición del elemento en la lista.
     */
    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        // Obtener el personaje actual.
        Character currentCharacter = this.characters.get(position);

        // Asociar los datos del personaje con las vistas y configurar el listener de clic.
        holder.bind(currentCharacter);
        holder.itemView.setOnClickListener(view -> itemClicked(currentCharacter, view));
    }

    /**
     * Devuelve el número total de elementos en la lista.
     *
     * @return Cantidad de personajes en la lista.
     */
    @Override
    public int getItemCount() {
        return characters.size();
    }

    /**
     * Método privado para manejar el evento de clic en un elemento de la lista.
     * Informa a la actividad principal que un personaje ha sido seleccionado.
     *
     * @param currentCharacter El personaje seleccionado.
     * @param view La vista asociada al elemento seleccionado.
     */
    private void itemClicked(Character currentCharacter, View view) {
        ((MainActivity) context).characterClicked(currentCharacter, view);
    }
}
