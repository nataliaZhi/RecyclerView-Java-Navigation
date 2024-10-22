package dam.pmdm.tarea2nz.supermario;


import androidx.recyclerview.widget.RecyclerView;


import dam.pmdm.tarea2nz.databinding.ItemCharacterBinding;


public  class CharacterViewHolder extends RecyclerView.ViewHolder{
    // Objeto de binding que referencia las vistas en el layout del item.
    private final ItemCharacterBinding binding;



    public CharacterViewHolder( ItemCharacterBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

    }


    // Método para enlazar los datos del personaje
    public void bind(Character character){
        binding.name.setText(character.getName());
        binding.image.setImageResource(character.getImage());
        binding.executePendingBindings();

    }
}




