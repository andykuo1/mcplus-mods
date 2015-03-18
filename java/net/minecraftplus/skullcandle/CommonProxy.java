package net.minecraftplus.skullcandle;

import net.minecraft.init.Blocks;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.MCP;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		MCP.TILES().add(TileEntitySkullCandle.class, "skull_candles");
		MCP.TILES().add(TileEntityRedstoneSkullCandle.class, "redstone_skull_candles");

		this.addRecipe(new RecipesSkullCandle(Blocks.torch, ModSkullCandle.skullCandle));
		this.addRecipe(new RecipesSkullCandle(Blocks.redstone_torch, ModSkullCandle.redstoneSkullCandle));
	}
}
