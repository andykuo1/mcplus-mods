package net.minecraftplus.loom;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftplus._api.mod.MCP;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy
{
	@SideOnly(Side.CLIENT)
	@Override
	public void register()
	{
		super.register();

		MCP.RENDERS().add(TileEntityLoom.class, new RenderLoom());
		MCP.RENDERS().add(Item.getItemFromBlock(ModLoom.loom), new RenderItemLoom(new RenderLoom()));
	}
}
