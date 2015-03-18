package net.minecraftplus.firepit;

import net.minecraft.item.Item;
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

		MCP.RENDERS().add(TileEntityFirePit.class, new RenderFirePit());
		MCP.RENDERS().add(Item.getItemFromBlock(ModFirePit.firePit), new RenderFirePit());
	}
}
