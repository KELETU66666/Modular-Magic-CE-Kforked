package fr.frinn.modularmagic.common.integration.jei.component;

import fr.frinn.modularmagic.common.crafting.requirement.RequirementImpetus;
import fr.frinn.modularmagic.common.integration.jei.ingredient.Impetus;
import fr.frinn.modularmagic.common.integration.jei.recipelayoutpart.LayoutImpetus;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;

import java.awt.*;
import java.util.Collections;
import java.util.List;

/**
 * @author youyihj
 */
public class JEIComponentImpetus extends ComponentRequirement.JEIComponent<Impetus> {
    private final RequirementImpetus requirementImpetus;

    public JEIComponentImpetus(RequirementImpetus requirementImpetus) {
        this.requirementImpetus = requirementImpetus;
    }

    @Override
    public Class<Impetus> getJEIRequirementClass() {
        return Impetus.class;
    }

    @Override
    public List<Impetus> getJEIIORequirements() {
        return Collections.singletonList(requirementImpetus.getImpetus());
    }

    @Override
    public RecipeLayoutPart<Impetus> getLayoutPart(Point offset) {
        return new LayoutImpetus(offset);
    }

    @Override
    public void onJEIHoverTooltip(int slotIndex, boolean input, Impetus ingredient, List<String> tooltip) {

    }
}
