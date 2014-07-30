package com.minecraftplus.modSurvivor;

import net.minecraft.block.BlockLeaves;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventResourceStartHandler
{
	@SubscribeEvent
	public void onBlockBreak(BreakEvent par1Event)
	{
		if (!par1Event.world.isRemote && par1Event.getPlayer() != null && !par1Event.getPlayer().capabilities.isCreativeMode)
		{
			if (par1Event.block instanceof BlockLeaves && par1Event.world.rand.nextInt(4) == 0)
			{
				for(int i = par1Event.world.rand.nextInt(2); i >= 0; i--)
				{
					par1Event.world.spawnEntityInWorld(new EntityItem(par1Event.world, par1Event.x + 0.5F, par1Event.y + 0.5F, par1Event.z + 0.5F, new ItemStack(Items.stick, 1)));
				}
			}

			if (par1Event.block == Blocks.grass && par1Event.world.rand.nextInt(18) == 0)
			{
				par1Event.world.spawnEntityInWorld(new EntityItem(par1Event.world, par1Event.x + 0.5F, par1Event.y + 0.5F, par1Event.z + 0.5F, new ItemStack(Items.flint, 1)));
			}
		}
	}

	@SubscribeEvent
	public void onHarvestDrop(HarvestDropsEvent par1Event)
	{
		if (!par1Event.world.isRemote && par1Event.harvester != null && !par1Event.harvester.capabilities.isCreativeMode)
		{
			if (par1Event.block == Blocks.gravel && par1Event.harvester.getCurrentEquippedItem() == null)
			{
				if (par1Event.world.rand.nextInt(6) == 0)
				{
					par1Event.dropChance = 1F;
					par1Event.drops.clear();
					par1Event.drops.add(new ItemStack(Items.flint, 1));
				}
			}
		}
	}
}
