package net.minecraftplus.sweetpotato;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventSweetPotatoDropHandler
{
	@SubscribeEvent
	public void onBlockBreak(BreakEvent par1Event)
	{
		if (!par1Event.world.isRemote && par1Event.block == Blocks.grass && (par1Event.getPlayer() != null ? !par1Event.getPlayer().capabilities.isCreativeMode : true))
		{
			if (par1Event.world.rand.nextInt(64) == 0)
			{
				par1Event.world.spawnEntityInWorld(new EntityItem(par1Event.world, par1Event.x + 0.5F, par1Event.y + 0.5F, par1Event.z + 0.5F, new ItemStack(ModSweetPotato.sweetPotato)));
			}
		}
	}
}
