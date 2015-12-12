package net.minecraftplus._api;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftplus._api.dictionary.Resources;

/**Minecraft Client*/
@SideOnly(Side.CLIENT)
public final class MCC
{
	public static final String INVENTORY = "inventory";
	public static final String WORLD = "world";

	private MCC() {}

	/**Register the item models*/
	public static final Item item(Item parItem, String... parAttributes)
	{
		assert(MCP.isClientSide());

		ModContainer mod = MCP.mod();
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(parItem, 0, new ModelResourceLocation(Resources.ofModel(parItem, parAttributes), INVENTORY));

		return parItem;
	}

	/**Register the item models with metadata*/
	public static final Item item(Item parItem, int parMetadata, String... parAttributes)
	{
		assert(MCP.isClientSide());

		ModContainer mod = MCP.mod();
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(parItem, parMetadata, new ModelResourceLocation(Resources.ofModel(parItem, parAttributes), INVENTORY));

		return parItem;
	}

	/**Register the block item models*/
	public static final Block block(Block parBlock, String... parAttributes)
	{
		assert(MCP.isClientSide());

		item(Item.getItemFromBlock(parBlock), parAttributes);
		return parBlock;
	}

	/**Register the entity renders*/
	public static final void entity(Class<? extends Entity> parEntity, Render parRender)
	{
		RenderingRegistry.registerEntityRenderingHandler(parEntity, parRender);
	}

	/**Register the tile entity special renders*/
	public static final void tileEntity(Class<? extends TileEntity> parTileEntity, TileEntitySpecialRenderer parRender)
	{
		ClientRegistry.bindTileEntitySpecialRenderer(parTileEntity, parRender);
	}
}
