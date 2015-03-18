package net.minecraftplus.sweetpotato;

import net.minecraft.item.ItemStack;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.MCP;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		MCP.SMELTS().add(ModSweetPotato.sweetPotato, new ItemStack(ModSweetPotato.bakedSweetPotato), 0.35F);
	}
}
