package net.minecraftplus.mcp_sweet_potato;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandlerSweetPotato
{
	@SubscribeEvent
	public void onBlockBreak(BreakEvent parEvent)
	{
		if (!parEvent.world.isRemote && parEvent.state.getBlock() == Blocks.grass && (parEvent.getPlayer() != null ? !parEvent.getPlayer().capabilities.isCreativeMode : true))
		{
			if (parEvent.world.rand.nextInt(64) == 0)
			{
				parEvent.world.spawnEntityInWorld(new EntityItem(parEvent.world, parEvent.pos.getX() + 0.5F, parEvent.pos.getY() + 0.5F, parEvent.pos.getZ() + 0.5F, new ItemStack(_Sweet_Potato.sweetPotato)));
			}
		}
	}
}
