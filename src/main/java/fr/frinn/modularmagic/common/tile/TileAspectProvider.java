package fr.frinn.modularmagic.common.tile;

import fr.frinn.modularmagic.common.tile.machinecomponent.MachineComponentAspectProvider;
import hellfirepvp.modularmachinery.common.data.Config;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.ColorableMachineTile;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.common.tiles.essentia.TileJarFillable;

import javax.annotation.Nullable;

public abstract class TileAspectProvider extends TileJarFillable implements MachineComponentTile, ColorableMachineTile {

    private int color = Config.machineColor;

    @Override
    public int getMachineColor() {
        return this.color;
    }

    @Override
    public void setMachineColor(int newColor) {
        this.color = newColor;
        IBlockState state = this.world.getBlockState(this.pos);
        this.world.notifyBlockUpdate(this.pos, state, state, 3);
        this.markDirty();
    }

    @Override
    public boolean canInputFrom(EnumFacing face) {
        return true;
    }

    @Override
    public boolean canOutputTo(EnumFacing face) {
        return true;
    }

    @Override
    public boolean isConnectable(EnumFacing face) {
        return true;
    }

    @Override
    public void update() {
        if (!this.world.isRemote && this.amount < TileJarFillable.CAPACITY) {
            for (EnumFacing face : EnumFacing.VALUES) {
                this.fillJar(face);
            }
        }
    }

    private void fillJar(EnumFacing face) {
        TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.world, this.pos, face);
        if (te != null) {
            IEssentiaTransport ic = (IEssentiaTransport) te;
            if (!ic.canOutputTo(face.getOpposite())) {
                return;
            }

            Aspect ta = null;
            if (this.aspect != null && this.amount > 0) {
                ta = this.aspect;
            } else if (ic.getEssentiaAmount(face.getOpposite()) > 0 && ic.getSuctionAmount(face.getOpposite()) < this.getSuctionAmount(face) && this.getSuctionAmount(face) >= ic.getMinimumSuction()) {
                ta = ic.getEssentiaType(face.getOpposite());
            }

            if (ta != null && ic.getSuctionAmount(face.getOpposite()) < this.getSuctionAmount(face)) {
                this.addToContainer(ta, ic.takeEssentia(ta, 1, face.getOpposite()));
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("casingColor", this.color);
        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.color = nbt.hasKey("casingColor") ? nbt.getInteger("casingColor") : Config.machineColor;
    }

    @Override
    public NBTTagCompound writeSyncNBT(NBTTagCompound nbt) {
        super.writeSyncNBT(nbt);
        nbt.setInteger("casingColor", this.color);
        return nbt;
    }

    @Override
    public void readSyncNBT(NBTTagCompound nbt) {
        super.readSyncNBT(nbt);
        this.color = nbt.hasKey("casingColor") ? nbt.getInteger("casingColor") : Config.machineColor;
    }

    public static class Input extends TileAspectProvider {

        @Nullable @Override
        public MachineComponent provideComponent() {
            return new MachineComponentAspectProvider(this, IOType.INPUT);
        }
    }

    public static class Output extends TileAspectProvider {

        @Nullable @Override
        public MachineComponent provideComponent() {
            return new MachineComponentAspectProvider(this, IOType.OUTPUT);
        }
    }
}
