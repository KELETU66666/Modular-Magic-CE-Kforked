package fr.frinn.modularmagic.common.tile;

import com.google.common.collect.Lists;
import fr.frinn.modularmagic.ModularMagic;
import fr.frinn.modularmagic.common.tile.machinecomponent.MachineComponentStarlightProviderOutput;
import hellfirepvp.astralsorcery.common.auxiliary.link.ILinkableTile;
import hellfirepvp.astralsorcery.common.constellation.ConstellationRegistry;
import hellfirepvp.astralsorcery.common.constellation.IWeakConstellation;
import hellfirepvp.astralsorcery.common.starlight.IIndependentStarlightSource;
import hellfirepvp.astralsorcery.common.starlight.IStarlightSource;
import hellfirepvp.astralsorcery.common.starlight.transmission.ITransmissionSource;
import hellfirepvp.astralsorcery.common.starlight.transmission.base.SimpleIndependentSource;
import hellfirepvp.astralsorcery.common.starlight.transmission.base.SimpleTransmissionSourceNode;
import hellfirepvp.astralsorcery.common.starlight.transmission.registry.SourceClassRegistry;
import hellfirepvp.astralsorcery.common.tile.base.TileSourceBase;
import hellfirepvp.modularmachinery.common.data.Config;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.ColorableMachineTile;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public class TileStarlightOutput extends TileSourceBase implements MachineComponentTile, IStarlightSource, ILinkableTile, ColorableMachineTile {

    private float starlightProduced = 0.0F;
    public int tick;
    private int color = Config.machineColor;

    public IWeakConstellation constellation = ConstellationRegistry.getWeakConstellations().get(0);

    @Override
    public int getMachineColor() {
        return this.color;
    }

    @Override
    public void setMachineColor(int newColor) {
        this.color = newColor;
        this.markForUpdate();
    }

    public float getStarlightProduced() {
        return starlightProduced;
    }

    public void setStarlightProduced(float starlightProduced) {
        this.tick = 2;
        this.starlightProduced = starlightProduced;
    }

    @Nullable @Override
    public MachineComponent provideComponent() {
        return new MachineComponentStarlightProviderOutput(this, IOType.OUTPUT);
    }

    @Override
    public void update() {
        if(tick > 0)
            tick--;
        else if(starlightProduced > 0)
            starlightProduced = 0;

        super.update();
    }

    @Nullable @Override
    public String getUnLocalizedDisplayName() {
        return "tile.blockstarlightprovideroutput.name";
    }

    @Nonnull
    @Override
    public IIndependentStarlightSource provideNewSourceNode() {
        return new IndependantStarlightProviderSource(constellation, this);
    }

    @Nonnull
    @Override
    public ITransmissionSource provideSourceNode(BlockPos at) {
        return new SimpleTransmissionSourceNode(at);
    }

    @Override
    public boolean onSelect(EntityPlayer player) {
        if(player.isSneaking()) {
            for (BlockPos linkTo : Lists.newArrayList(getLinkedPositions())) {
                tryUnlink(player, linkTo);
            }
            player.sendMessage(new TextComponentTranslation("misc.link.unlink.all").setStyle(new Style().setColor(TextFormatting.GREEN)));
            return false;
        }
        return true;
    }

    @Override
    public void writeCustomNBT(NBTTagCompound compound) {
        super.writeCustomNBT(compound);

        compound.setInteger("casingColor", color);
    }

    @Override
    public void readCustomNBT(NBTTagCompound compound) {
        super.readCustomNBT(compound);

        color = compound.getInteger("casingColor");
    }

    public static class IndependantStarlightProviderSource extends SimpleIndependentSource {

        private TileStarlightOutput provider;

        public IndependantStarlightProviderSource(IWeakConstellation constellation, TileStarlightOutput provider) {
            super(constellation);
            this.provider = provider;
        }

        @Override
        public float produceStarlightTick(World world, BlockPos pos) {
            if(provider != null) {
                return provider.getStarlightProduced();
            }
            else if(world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof TileStarlightOutput) {
                provider = (TileStarlightOutput) world.getTileEntity(pos);
                return provider.getStarlightProduced();
            }
            else
                return 0.0F;
        }

        @Override
        public void informTileStateChange(IStarlightSource sourceTile) {

        }

        @Override
        public void threadedUpdateProximity(BlockPos thisPos, Map<BlockPos, IIndependentStarlightSource> otherSources) {

        }

        @Override
        public SourceClassRegistry.SourceProvider getProvider() {
            return new Provider();
        }
    }

    public static class Provider implements SourceClassRegistry.SourceProvider {

        @Override
        public IIndependentStarlightSource provideEmptySource() {
            return new IndependantStarlightProviderSource(null, null);
        }

        @Override
        public String getIdentifier() {
            return ModularMagic.MODID + ":IndependantStarlightProviderSource";
        }
    }
}
