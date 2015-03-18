package net.minecraftplus.loom;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

public class RenderItemLoom implements IItemRenderer
{
	private TileEntity tileentity = new TileEntityLoom();
	private TileEntitySpecialRenderer render;

	public RenderItemLoom(TileEntitySpecialRenderer parRender)
	{
		this.render = parRender;
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		this.render.renderTileEntityAt(this.tileentity, 0D, 0D, 0D, 0F);
	}
}
