package fr.frinn.modularmagic.common.integration.jei.helper;

import fr.frinn.modularmagic.ModularMagic;
import fr.frinn.modularmagic.common.integration.jei.ingredient.Grid;
import javax.annotation.Nullable;
import mezz.jei.api.ingredients.IIngredientHelper;

public class GridHelper<T extends Grid> implements IIngredientHelper<T> {

    @Nullable @Override
    public T getMatch(Iterable<T> ingredients, T ingredientToMatch) {
        for (T grid : ingredients) {
            if (grid.getPower() == ingredientToMatch.getPower())
                return grid;
        }
        return null;
    }

    @Override
    public String getDisplayName(T ingredient) {
        return "Grid Power";
    }

    @Override
    public String getUniqueId(T ingredient) {
        return "grid";
    }

    @Override
    public String getWildcardId(T ingredient) {
        return "grid";
    }

    @Override
    public String getModId(T ingredient) {
        return ModularMagic.MODID;
    }

    @Override
    public String getResourceId(T ingredient) {
        return "grid";
    }

    @Override
    public T copyIngredient(T ingredient) {
        return ingredient;
    }

    @Override
    public String getErrorInfo(@Nullable T ingredient) {
        return null;
    }
}
