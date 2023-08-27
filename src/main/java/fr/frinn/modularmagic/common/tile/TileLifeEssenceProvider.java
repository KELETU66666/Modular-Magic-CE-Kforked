package fr.frinn.modularmagic.common.tile;

import WayofTime.bloodmagic.core.data.Binding;
import WayofTime.bloodmagic.core.data.SoulNetwork;
import WayofTime.bloodmagic.item.ItemBindableBase;
import WayofTime.bloodmagic.orb.BloodOrb;
import WayofTime.bloodmagic.orb.IBloodOrb;
import WayofTime.bloodmagic.util.helper.NetworkHelper;
import fr.frinn.modularmagic.common.tile.machinecomponent.MachineComponentLifeEssenceProvider;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileInventory;
import hellfirepvp.modularmachinery.common.util.IOInventory;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.item.ItemStack;

public class TileLifeEssenceProvider extends TileInventory implements MachineComponentTile {

    public  TileLifeEssenceProvider() {
        super(42);
    }

    @Override
    public void doRestrictedTick() {
    }

    public SoulNetwork getSoulNetwork() {
        ItemStack stack = getInventory().getStackInSlot(0);
        if(stack != null && stack.getItem() instanceof ItemBindableBase) {
            ItemBindableBase itemBloodOrb = (ItemBindableBase) stack.getItem();
            Binding binding = itemBloodOrb.getBinding(stack);
            if(binding != null) {
                SoulNetwork network = NetworkHelper.getSoulNetwork(binding);
                if(network != null)
                    return network;
            }
        }
        return null;
    }

    public int getOrbCapacity() {
        ItemStack stack = getInventory().getStackInSlot(0);
        if(stack != null && stack.getItem() instanceof IBloodOrb) {
            BloodOrb orb = ((IBloodOrb) stack.getItem()).getOrb(stack);
            return orb.getCapacity();
        }
        return Integer.MIN_VALUE;
    }

    @Override
    public IOInventory buildInventory(TileInventory tile, int size) {
        return new IOInventory(tile, new int[1], new int[1]) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if(stack.getItem() instanceof IBloodOrb)
                    return true;
                else
                    return false;
            }
        };
    }

    @Nullable @Override
    public MachineComponent provideComponent() {
        return null;
    }

    public static class Input extends TileLifeEssenceProvider {

        @Nullable @Override
        public MachineComponent provideComponent() {
            return new MachineComponentLifeEssenceProvider(this, IOType.INPUT);
        }
    }

    public static class Output extends TileLifeEssenceProvider {

        @Nullable @Override
        public MachineComponent provideComponent() {
            return new MachineComponentLifeEssenceProvider(this, IOType.OUTPUT);
        }
    }
}
