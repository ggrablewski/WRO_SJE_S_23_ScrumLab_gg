package pl.coderslab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
public class LastPlanDetails {
    private String dayName;
    private String mealName;
    private String recipeName;
    private String recipeDesc;
}
