package pl.coderslab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class RecipePlan {
    private int id;
    private int recipeId;
    private String mealName;
    private int displayOrder;
    private int dayNameId;
    private int planId;

    public RecipePlan(int recipeId, String mealName, int displayOrder, int dayNameId, int planId) {
        this.recipeId = recipeId;
        this.mealName = mealName;
        this.displayOrder = displayOrder;
        this.dayNameId = dayNameId;
        this.planId = planId;
    }
}
