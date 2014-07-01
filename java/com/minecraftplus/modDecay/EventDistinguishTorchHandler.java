package com.minecraftplus.modDecay;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventDistinguishTorchHandler
{
	@SubscribeEvent
	public void onPlayerInteractWithBlock(PlayerInteractEvent par1Event)
	{
		if (par1Event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
		{
			if (par1Event.entityPlayer.getCurrentEquippedItem() != null && par1Event.entityPlayer.getCurrentEquippedItem().getItem() == Item.getItemFromBlock(Blocks.torch))
			{
				if (!par1Event.entity.worldObj.isRemote && par1Event.entity.worldObj.rand.nextInt(8) == 0)
				{
					Block block = MCP_Decay.torchLit;
					Item.getItemFromBlock(block).onItemUse(new ItemStack(block), par1Event.entityPlayer, par1Event.entity.worldObj, par1Event.x, par1Event.y, par1Event.z, par1Event.face, 0F, 0F, 0F);
					if (!par1Event.entityPlayer.capabilities.isCreativeMode)
					{
						par1Event.entityPlayer.getCurrentEquippedItem().stackSize--;
					}
				}
			}
		}
	}
}
