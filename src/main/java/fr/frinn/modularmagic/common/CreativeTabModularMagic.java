package fr.frinn.modularmagic.common;

import hellfirepvp.modularmachinery.common.lib.BlocksMM;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class CreativeTabModularMagic extends CreativeTabs {

    public CreativeTabModularMagic() {
        super("ModularMagic");
    }

    @Nonnull
    @Override
    public ItemStack createIcon() {
        return new ItemStack(BlocksMM.blockController);
    }
}
