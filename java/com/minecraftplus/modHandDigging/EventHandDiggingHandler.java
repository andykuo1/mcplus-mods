package com.minecraftplus.modHandDigging;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventHandDiggingHandler
{
	@SubscribeEvent
	public void onBlockBreak(BreakEvent par1Event)
	{
		if (par1Event.getPlayer() != null && !par1Event.getPlayer().capabilities.isCreativeMode)
		{
			if (par1Event.block instanceof BlockLeaves && par1Event.world.rand.nextInt(4) == 0)
			{
				for(int i = par1Event.world.rand.nextInt(3); i >= 0; i--)
				{
					par1Event.world.spawnEntityInWorld(new EntityItem(par1Event.world, par1Event.x + 0.5F, par1Event.y + 0.5F, par1Event.z + 0.5F, new ItemStack(Items.stick, 1)));
				}
			}
		}
	}

	@SubscribeEvent
	public void onHarvestDrop(HarvestDropsEvent par1Event)
	{
		if (!par1Event.world.isRemote && par1Event.harvester != null && !par1Event.harvester.capabilities.isCreativeMode)
		{
			if (par1Event.block == Blocks.stone && par1Event.harvester.getCurrentEquippedItem() == null)
			{
				par1Event.dropChance = 1F;
				for(int i = par1Event.world.rand.nextInt(6); i >= 0; i--)
				{
					par1Event.drops.add(new ItemStack(MCP_HandDigging.rock, 1));
				}
			}

			if ((par1Event.block == Blocks.dirt || par1Event.block == Blocks.grass || par1Event.block == Blocks.mycelium) && isHandDigging(par1Event.harvester, par1Event.block, par1Event.blockMetadata))
			{
				par1Event.dropChance = 1F;
				for(int i = par1Event.drops.size() - 1; i >= 0; i--)
				{
					if (par1Event.drops.get(i).getItem() == Item.getItemFromBlock(Blocks.dirt))
					{
						par1Event.drops.remove(i);
					}
				}
				
				for(int i = par1Event.world.rand.nextInt(3) + 1; i >= 0; i--)
				{
					par1Event.drops.add(new ItemStack(MCP_HandDigging.dirtBall, 1));
				}

				if (par1Event.world.rand.nextInt(16) == 0)
				{
					par1Event.drops.add(new ItemStack(Items.flint, 1));
				}

				if (par1Event.world.rand.nextInt(10) == 0)
				{
					for(int i = par1Event.world.rand.nextInt(2); i >= 0; i--)
					{
						par1Event.drops.add(new ItemStack(Items.clay_ball, 1));
					}
				}
			}
		}
	}

	public static boolean isHandDigging(EntityPlayer par1EntityPlayer, Block par2Block, int par3)
	{
		if (par1EntityPlayer != null && !par1EntityPlayer.capabilities.isCreativeMode)
		{
			if (par1EntityPlayer.getCurrentEquippedItem() != null)
			{
				if (!ForgeHooks.isToolEffective(par1EntityPlayer.getCurrentEquippedItem(), par2Block, par3) || par2Block.getMaterial() == Material.gourd || par2Block.getMaterial() == Material.circuits || par2Block.getMaterial() == Material.redstoneLight)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return true;
			}
		}

		return false;
	}
}
