package net.minecraftplus.mcp_blowpipe;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSeeds extends RenderSnowball
{
	public RenderSeeds(Item parItem)
	{
		super(Minecraft.getMinecraft().getRenderManager(), parItem, Minecraft.getMinecraft().getRenderItem());
	}

	@Override
	public ItemStack func_177082_d(Entity parEntity)
	{
		return new ItemStack(((EntitySeeds) parEntity).getSeedItem(), 1, 0);
	}
}
