package com.minecraftplus.modDecay;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
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
				Block block = MCP_Decay.torchLit;
				Item.getItemFromBlock(block).onItemUse(new ItemStack(block), par1Event.entityPlayer, par1Event.entity.worldObj, par1Event.x, par1Event.y, par1Event.z, par1Event.face, 0F, 0F, 0F);
				par1Event.entity.worldObj.playSound((double)((float)par1Event.x + 0.5F), (double)((float)par1Event.y + 0.5F), (double)((float)par1Event.z + 0.5F), MCP_Decay.torchLit.stepSound.func_150496_b(), (MCP_Decay.torchLit.stepSound.getVolume() + 1.0F) / 2.0F, MCP_Decay.torchLit.stepSound.getPitch() * 0.8F, false);

			}
		}
	}
}
