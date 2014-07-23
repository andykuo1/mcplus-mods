package com.minecraftplus.modSurvivor;

import net.minecraft.block.BlockLeaves;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventResourceStartHandler
{
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent par1Event)
	{
		if (par1Event.entity instanceof EntityPlayer && par1Event.entity.ticksExisted % 80 == 0)
		{
			((EntityPlayer) par1Event.entity).getFoodStats().addExhaustion(1F);
		}
	}

	@SubscribeEvent
	public void onBlockBreak(BreakEvent par1Event)
	{
		if (!par1Event.world.isRemote && (par1Event.getPlayer() != null ? !par1Event.getPlayer().capabilities.isCreativeMode : true))
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

			if (par1Event.block == Blocks.stone && (par1Event.getPlayer().getCurrentEquippedItem() == null || !par1Event.getPlayer().getCurrentEquippedItem().getItem().canHarvestBlock(Blocks.stone, par1Event.getPlayer().getCurrentEquippedItem())))
			{
				for(int i = par1Event.world.rand.nextInt(4) + 1; i > 0; i--)
				{
					par1Event.world.spawnEntityInWorld(new EntityItem(par1Event.world, par1Event.x + 0.5F, par1Event.y + 0.5F, par1Event.z + 0.5F, new ItemStack(MCP_Survivor.stones, 1)));
				}

				if (par1Event.world.rand.nextBoolean())
				{
					par1Event.world.spawnEntityInWorld(new EntityItem(par1Event.world, par1Event.x + 0.5F, par1Event.y + 0.5F, par1Event.z + 0.5F, new ItemStack(Items.flint, 1)));
				}
			}
		}
	}
}
