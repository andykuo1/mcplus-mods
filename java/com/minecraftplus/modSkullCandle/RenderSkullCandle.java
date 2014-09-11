package com.minecraftplus.modSkullCandle;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSkullCandle extends TileEntitySpecialRenderer
{
	private final TileEntitySpecialRenderer renderSkullTorch = new RenderTorchSkull();
	private final TileEntitySpecialRenderer renderSkullGlowing = new RenderGlowingSkull();

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
		if (MCP_SkullCandle.isTorchVariant)
		{
			this.renderSkullTorch.renderTileEntityAt(par1TileEntity, par2, par3, par4, par5);
		}
		else
		{
			this.renderSkullGlowing.renderTileEntityAt(par1TileEntity, par2, par3, par4, par5);
		}
	}
}