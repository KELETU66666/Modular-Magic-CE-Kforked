package fr.frinn.modularmagic.common.block;

import fr.frinn.modularmagic.ModularMagic;
import fr.frinn.modularmagic.common.tile.TileConstellationProvider;
import hellfirepvp.modularmachinery.common.block.BlockMachineComponent;
import javax.annotation.Nullable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;

public class BlockConstellationProvider extends BlockMachineComponent {

    public BlockConstellationProvider() {
        super(Material.IRON);
        setHardness(2F);
        setResistance(10F);
        setSoundType(SoundType.METAL);
        setHarvestLevel("pickaxe", 1);
        setCreativeTab(ModularMagic.creativeTabModularMagic);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileConstellationProvider();
    }

    @Nullable @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }
}
