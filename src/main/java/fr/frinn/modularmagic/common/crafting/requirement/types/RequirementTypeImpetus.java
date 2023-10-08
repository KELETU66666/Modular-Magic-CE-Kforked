package fr.frinn.modularmagic.common.crafting.requirement.types;

import com.google.gson.JsonObject;
import fr.frinn.modularmagic.common.crafting.requirement.RequirementImpetus;
import fr.frinn.modularmagic.common.integration.jei.ingredient.Impetus;
import fr.frinn.modularmagic.common.utils.RequirementUtils;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;

import javax.annotation.Nullable;

public class RequirementTypeImpetus extends RequirementType<Impetus, RequirementImpetus> {

    public static final RequirementTypeImpetus INSTANCE = new RequirementTypeImpetus();


    @Override
    public ComponentRequirement<Impetus, ? extends RequirementType<Impetus, RequirementImpetus>> createRequirement(IOType type, JsonObject json) {
        int amount = RequirementUtils.getRequiredInt(json, "amount", ModularMagicRequirements.KEY_REQUIREMENT_IMPETUS.toString());
        return new RequirementImpetus(type, amount);
    }

    @Nullable
    @Override
    public String requiresModid() {
        return "thaumicaugmentation";
    }
    }