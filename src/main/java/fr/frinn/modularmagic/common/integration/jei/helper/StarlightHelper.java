package fr.frinn.modularmagic.common.integration.jei.helper;

import fr.frinn.modularmagic.ModularMagic;
import fr.frinn.modularmagic.common.integration.jei.ingredient.Starlight;
import javax.annotation.Nullable;
import mezz.jei.api.ingredients.IIngredientHelper;

public class StarlightHelper<T extends Starlight> implements IIngredientHelper<Starlight> {

    @Nullable @Override
    public Starlight getMatch(Iterable<Starlight> ingredients, Starlight ingredientToMatch) {
        return ingredients.iterator().next();
    }

    @Override
    public String getDisplayName(Starlight ingredient) {
        return "Starlight";
    }

    @Override
    public String getUniqueId(Starlight ingredient) {
        return "starlight";
    }

    @Override
    public String getWildcardId(Starlight ingredient) {
        return "starlight";
    }

    @Override
    public String getModId(Starlight ingredient) {
        return ModularMagic.MODID;
    }

    @Override
    public String getResourceId(Starlight ingredient) {
        return "starlight";
    }

    @Override
    public Starlight copyIngredient(Starlight ingredient) {
        return ingredient;
    }

    @Override
    public String getErrorInfo(@Nullable Starlight ingredient) {
        return null;
    }
}
