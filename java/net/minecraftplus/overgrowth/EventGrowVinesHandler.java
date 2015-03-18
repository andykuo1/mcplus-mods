package net.minecraftplus.overgrowth;

import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.player.BonemealEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventGrowVinesHandler
{
	@SubscribeEvent
	public void onAttemptBonemeal(BonemealEvent par1Event)
	{
		if (!par1Event.world.isRemote && par1Event.block == Blocks.vine && par1Event.y > 0)
		{
			int i = par1Event.y;
			int j = par1Event.world.getBlockMetadata(par1Event.x, par1Event.y, par1Event.z);

			while(par1Event.world.getBlock(par1Event.x, --i, par1Event.z) == Blocks.vine) {}
			i++;

			while(par1Event.world.isAirBlock(par1Event.x, --i, par1Event.z) && par1Event.world.rand.nextInt(3) != 0)
			{
				par1Event.world.setBlock(par1Event.x, i, par1Event.z, Blocks.vine, j, 2);
			}
		}
	}
}
