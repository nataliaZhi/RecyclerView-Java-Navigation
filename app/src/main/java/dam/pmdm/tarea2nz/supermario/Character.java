package dam.pmdm.tarea2nz.supermario;

/**
 * Esta clase representa un personaje de Super Mario Bros.
 * Contiene información sobre el nombre, descripción, habilidades y la imagen del personaje.
 */
public class Character {

    // El nombre del personaje
    private final String name;

    // Una breve descripción del personaje.
    private final String description;

    // Las habilidades o características especiales del personaje.
    private final String abilities;

    // El ID del recurso de la imagen del personaje
    private final int image;

    /**
     * Construye un nuevo personaje con el nombre, descripción, habilidades e imagen especificados.
     *
     * @param name El nombre del personaje.
     * @param description Una breve descripción del personaje.
     * @param abilities Las habilidades o características especiales del personaje.
     * @param image El ID del recurso para la imagen del personaje.
     */
    public Character(String name, String description, String abilities, int image) {
        this.name = name;
        this.description = description;
        this.abilities = abilities;
        this.image = image;
    }

    /**
     * Devuelve las habilidades del personaje.
     *
     * @return Una cadena que representa las habilidades del personaje.
     */
    public String getAbilities() {
        return abilities;
    }

    /**
     * Devuelve el nombre del personaje.
     *
     * @return El nombre del personaje.
     */
    public String getName() {
        return name;
    }

    /**
     * Devuelve una breve descripción del personaje.
     *
     * @return Una cadena que representa la descripción del personaje.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Devuelve el ID del recurso de la imagen del personaje.
     *
     * @return Un entero que representa el ID del recurso de la imagen.
     */
    public int getImage() {
        return image;
    }
}