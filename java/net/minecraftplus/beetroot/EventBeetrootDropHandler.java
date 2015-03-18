package net.minecraftplus.beetroot;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventBeetrootDropHandler
{
	@SubscribeEvent
	public void onHoeEvent(UseHoeEvent parEvent)
	{
		if (parEvent.entityPlayer.capabilities.isCreativeMode) return;

		Block block = parEvent.world.getBlock(parEvent.x, parEvent.y, parEvent.z);
		if (block.equals(Blocks.grass) || block.equals(Blocks.dirt))
		{
			if (!parEvent.world.isRemote && parEvent.world.rand.nextInt(18) == 0)
			{
				parEvent.world.spawnEntityInWorld(new EntityItem(parEvent.world, parEvent.x, parEvent.y, parEvent.z, new ItemStack(ModBeetroot.beetrootSeeds)));
			}
		}
	}
}
