package net.minecraftplus.skullcandle;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftplus._api.tool.ResTool;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSkullCandle extends TileEntitySpecialRenderer
{
	private static final ResourceLocation skeletonTorch = ResTool.getResource("skeleton", ResTool.ENTITIES + "/skeleton", true);
	private static final ResourceLocation witherSkeletonTorch = ResTool.getResource("wither_skeleton", ResTool.ENTITIES + "/skeleton", true);
	private static final ResourceLocation zombieTorch = ResTool.getResource("zombie", ResTool.ENTITIES + "/zombie", true);
	private static final ResourceLocation creeperTorch = ResTool.getResource("creeper", ResTool.ENTITIES + "/creeper", true);

	private static final ResourceLocation skeleton = ResTool.getResource("tile.glowing_skull.skeleton", ResTool.BLOCKS, ModSkullCandle.INSTANCE);
	private static final ResourceLocation witherSkeleton = ResTool.getResource("tile.glowing_skull.wither_skeleton", ResTool.BLOCKS, ModSkullCandle.INSTANCE);
	private static final ResourceLocation zombie = ResTool.getResource("tile.glowing_skull.zombie", ResTool.BLOCKS, ModSkullCandle.INSTANCE);
	private static final ResourceLocation creeper = ResTool.getResource("tile.glowing_skull.creeper", ResTool.BLOCKS, ModSkullCandle.INSTANCE);
	private static final ResourceLocation steve = ResTool.getResource("tile.glowing_skull.steve", ResTool.BLOCKS, ModSkullCandle.INSTANCE);

	private static final ResourceLocation skeletonRedstone = ResTool.getResource("tile.glowing_redstone_skull.skeleton", ResTool.BLOCKS, ModSkullCandle.INSTANCE);
	private static final ResourceLocation witherSkeletonRedstone = ResTool.getResource("tile.glowing_redstone_skull.wither_skeleton", ResTool.BLOCKS, ModSkullCandle.INSTANCE);
	private static final ResourceLocation zombieRedstone = ResTool.getResource("tile.glowing_redstone_skull.zombie", ResTool.BLOCKS, ModSkullCandle.INSTANCE);
	private static final ResourceLocation creeperRedstone = ResTool.getResource("tile.glowing_redstone_skull.creeper", ResTool.BLOCKS, ModSkullCandle.INSTANCE);
	private static final ResourceLocation steveRedstone = ResTool.getResource("tile.glowing_redstone_skull.steve", ResTool.BLOCKS, ModSkullCandle.INSTANCE);

	private final TileEntitySpecialRenderer renderSkullTorch;
	private final TileEntitySpecialRenderer renderSkullGlowing;

	public RenderSkullCandle(boolean parRedstone)
	{
		if (parRedstone)
		{
			this.renderSkullTorch = new SkullTorchRenderer(Blocks.redstone_torch, skeletonTorch, witherSkeletonTorch, zombieTorch, creeperTorch);
			this.renderSkullGlowing = new SkullGlowingRenderer(skeletonRedstone, witherSkeletonRedstone, zombieRedstone, creeperRedstone, steveRedstone);
		}
		else
		{
			this.renderSkullTorch = new SkullTorchRenderer(Blocks.torch, skeletonTorch, witherSkeletonTorch, zombieTorch, creeperTorch);
			this.renderSkullGlowing = new SkullGlowingRenderer(skeleton, witherSkeleton, zombie, creeper, steve);
		}
	}

	@Override
	public void func_147497_a(TileEntityRendererDispatcher p_147497_1_)
	{
		super.func_147497_a(p_147497_1_);
		this.renderSkullTorch.func_147497_a(p_147497_1_);
		this.renderSkullGlowing.func_147497_a(p_147497_1_);
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par3, double par4, float par5)
	{
		if (ModSkullCandle.isTorchVariant)
		{
			this.renderSkullTorch.renderTileEntityAt(par1TileEntity, par2, par3, par4, par5);
		}
		else
		{
			this.renderSkullGlowing.renderTileEntityAt(par1TileEntity, par2, par3, par4, par5);
		}
	}
}