package fr.frinn.modularmagic.common;

import hellfirepvp.modularmachinery.common.lib.BlocksMM;
import javax.annotation.Nonnull;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

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
