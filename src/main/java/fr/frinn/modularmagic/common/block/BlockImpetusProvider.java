package fr.frinn.modularmagic.common.block;

import fr.frinn.modularmagic.ModularMagic;
import fr.frinn.modularmagic.common.tile.TileImpetusComponent;
import hellfirepvp.modularmachinery.common.CommonProxy;
import hellfirepvp.modularmachinery.common.block.BlockMachineComponent;
import hellfirepvp.modularmachinery.common.item.ItemBlockMachineComponent;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * @author youyihj
 */
public abstract class BlockImpetusProvider extends BlockMachineComponent {
    private BlockImpetusProvider(String name) {
        super(Material.IRON);
        this.setHardness(3F);
        this.setResistance(50F);
        this.setHarvestLevel("pickaxe", 1);
        this.setCreativeTab(ModularMagic.creativeTabModularMagic);
        this.setTranslationKey(name);
    }

    @Nullable
    @Override
    public abstract TileEntity createTileEntity(World world, IBlockState state);

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        return 15;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }

    @Override
    public int getColorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
        switch (tintIndex) {
            case 0:
                return super.getColorMultiplier(state, worldIn, pos, tintIndex);
            case 1:
                return getIOPresentationColor();
            default:
                return -1;
        }
    }

    protected abstract int getIOPresentationColor();

    public static class Input extends BlockImpetusProvider {

        private Input() {
            super("blockimpetusproviderinput");
        }

        public static final Input INSTANCE = new Input();
        public static final ItemBlock ITEM_BLOCK = new ItemBlockMachineComponent(INSTANCE);

        @Nullable
        @Override
        public TileEntity createTileEntity(World world, IBlockState state) {
            return new TileImpetusComponent.Input();
        }

        @Override
        protected int getIOPresentationColor() {
            return 0x085ca2;
        }
    }

    public static class Output extends BlockImpetusProvider {

        private Output() {
            super("blockimpetusprovideroutput");
        }

        public static final Output INSTANCE = new Output();
        public static final ItemBlock ITEM_BLOCK = new ItemBlockMachineComponent(INSTANCE);

        @Nullable
        @Override
        public TileEntity createTileEntity(World world, IBlockState state) {
            return new TileImpetusComponent.Output();
        }

        @Override
        protected int getIOPresentationColor() {
            return 0xa14e08;
        }
    }
}
