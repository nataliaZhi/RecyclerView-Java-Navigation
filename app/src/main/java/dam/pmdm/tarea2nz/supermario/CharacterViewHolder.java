package dam.pmdm.tarea2nz.supermario;

import androidx.recyclerview.widget.RecyclerView;
import dam.pmdm.tarea2nz.databinding.ItemCharacterBinding;

/**
 * ViewHolder para gestionar y mostrar los datos de un personaje en un item de RecyclerView.
 * Utiliza ViewBinding para referenciar las vistas y enlazar los datos de un personaje.
 */
public class CharacterViewHolder extends RecyclerView.ViewHolder {

    /** Objeto de binding que referencia las vistas en el layout del item. */
    private final ItemCharacterBinding binding;

    /**
     * Constructor de la clase.
     * Inicializa el ViewHolder con el binding que contiene las vistas del item.
     *
     * @param binding Objeto de binding que gestiona las vistas del layout del item.
     */
    public CharacterViewHolder(ItemCharacterBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    /**
     * Enlaza los datos del personaje con las vistas correspondientes en el layout del item.
     * Este método se llama para actualizar la vista con la información del personaje.
     *
     * @param character El personaje cuyo nombre e imagen se deben mostrar en las vistas.
     */
    public void bind(Character character) {
        binding.name.setText(character.getName()); // Establecer el nombre del personaje.
        binding.image.setImageResource(character.getImage()); // Establecer la imagen del personaje.
        binding.executePendingBindings(); // Ejecuta cualquier binding pendiente.
    }
}



