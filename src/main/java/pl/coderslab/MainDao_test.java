package pl.coderslab;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import java.util.List;
import java.util.ListIterator;

public class MainDao_test {

    public static void main(String[] args) {
        Recipe testRecipe = new Recipe("sernik",
                "ser 1kg, mąka 400g, jajka 4, proszek do pieczenia 1, cukier 400g, aromat 1",
                "pyszny sernik wg przepisu babuni", 90,
                "żółtka jajek utrzeć ze 100g cukru, dodać 300g mąki, wymieszać, wyłożyć na blachę" +
                        "osobno zmielić, dodać resztę cukru, mąki, aromat, wymieszać," +
                        "białka jajek ubić na sztywną pianę, delikatnie wymieszać z serem," +
                        "całość wyłożyć na blachę, piec 60min w temperaturze 180C", 1);

        Recipe createdRecipe = RecipeDao.create(testRecipe);

        System.out.println(testRecipe);
        System.out.println(createdRecipe.toString(1));

        Recipe readRecipe = RecipeDao.read(4);
        readRecipe.setName("humus");
        readRecipe.setIngredients("ciecierzyca, olej");
        readRecipe.setDescription("pyszna pasta do kanapek");
        readRecipe.setPreparation("ciecierzycę zmiksować, dodając olej, przyprawić wg uznania");
        RecipeDao.update(readRecipe);

        RecipeDao.delete(5);

        List<Recipe> recipeList = RecipeDao.findAll();
        ListIterator<Recipe> listIterator = recipeList.listIterator();
        while (listIterator.hasNext())
            System.out.println(listIterator.next());
    }
}
