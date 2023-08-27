package fr.frinn.modularmagic.common.integration.jei.helper;

import WayofTime.bloodmagic.soul.EnumDemonWillType;
import com.google.common.collect.Iterables;
import fr.frinn.modularmagic.ModularMagic;
import fr.frinn.modularmagic.common.integration.jei.ingredient.DemonWill;
import javax.annotation.Nullable;
import mezz.jei.api.ingredients.IIngredientHelper;

public class DemonWillHelper<T extends DemonWill> implements IIngredientHelper<T> {

    @Nullable @Override
    public T getMatch(Iterable<T> ingredients, T ingredientToMatch) {
        if(Iterables.isEmpty(ingredients))
            return null;

        EnumDemonWillType willTypeToMatch = ingredientToMatch.getWillType();
        for(T demonWill : ingredients) {
            if(demonWill.getWillType() == willTypeToMatch)
                return demonWill;
        }
        return null;
    }

    @Override
    public String getDisplayName(T ingredient) {
        return ingredient.getWillType().name;
    }

    @Override
    public String getUniqueId(T ingredient) {
        return ingredient.getWillType().name;
    }

    @Override
    public String getWildcardId(T ingredient) {
        return ingredient.getWillType().name;
    }

    @Override
    public String getModId(T ingredient) {
        return ModularMagic.MODID;
    }

    @Override
    public String getResourceId(T ingredient) {
        return null;
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
