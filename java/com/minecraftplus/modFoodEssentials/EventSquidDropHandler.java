package com.minecraftplus.modFoodEssentials;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;


public class EventSquidDropHandler {
	
	@SubscribeEvent
	public void onLivingDrop(LivingDropsEvent par1Event)
	{
	   if (par1Event.entityLiving instanceof EntitySquid)
	    {
		   par1Event.drops.add(new EntityItem(par1Event.entity.worldObj, 
				   par1Event.entity.posZ, par1Event.entity.posX, par1Event.entity.posY, 
				   new ItemStack(MCP_FoodEssentials.rawSquid, 8)));	   
		   
	    }
	}

}
