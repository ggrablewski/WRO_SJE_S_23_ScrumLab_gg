package pl.coderslab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Recipe {

    private int id;
    private String name;
    private String ingredients;
    private String description;
    private String created;
    private String updated;
    private int preparation_time;
    private String preparation;
    private int admin_id;

// konstruktor bez pól ustawianych automatycznie przez bazę
    public Recipe(String name, String ingredients, String description,
                  int preparation_time, String preparation, int admin_id) {
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        this.preparation_time = preparation_time;
        this.preparation = preparation;
        this.admin_id = admin_id;
    }

// to jest ładny toString
    @Override
    public String toString() {
        return "Przepis na " + name +
                "\n" + description +
                "\nSkładniki: " + ingredients +
                "\nPrzygotowanie: \n" + preparation +
                "\nCzas przygotowania: " + preparation_time +"\n";
    }

// a to jest toString tylko do testów, wyświetlający wszystkie pola
    public String toString(int n) {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", description='" + description + '\'' +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", preparation_time=" + preparation_time +
                ", preparation='" + preparation + '\'' +
                ", admin_id=" + admin_id +
                '}';
    }
}
