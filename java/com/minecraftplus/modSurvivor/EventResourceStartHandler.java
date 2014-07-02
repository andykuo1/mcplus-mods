package com.minecraftplus.modSurvivor;

import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventResourceStartHandler
{
	@SubscribeEvent
	public void onBlockBreak(BreakEvent parEvent)
	{
		if (!parEvent.world.isRemote && !parEvent.getPlayer().capabilities.isCreativeMode)
		{
			if (parEvent.block instanceof BlockLeaves && parEvent.world.rand.nextInt(4) == 0)
			{
				for(int i = parEvent.world.rand.nextInt(2); i >= 0; i--)
				{
					parEvent.world.spawnEntityInWorld(new EntityItem(parEvent.world, parEvent.x + 0.5F, parEvent.y + 0.5F, parEvent.z + 0.5F, new ItemStack(Items.stick, 1)));
				}
			}

			if (parEvent.block == Blocks.grass && parEvent.world.rand.nextInt(18) == 0)
			{
				parEvent.world.spawnEntityInWorld(new EntityItem(parEvent.world, parEvent.x + 0.5F, parEvent.y + 0.5F, parEvent.z + 0.5F, new ItemStack(Items.flint, 1)));
			}
		}
	}
}
