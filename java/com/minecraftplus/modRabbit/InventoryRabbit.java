package com.minecraftplus.modRabbit;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.AnimalChest;

public class InventoryRabbit extends AnimalChest
{
	protected final EntityPlayer thePlayer;
	protected final Entity theAnimal;
	
	public InventoryRabbit(EntityPlayer par1EntityPlayer, Entity par2Entity)
	{
		super(par2Entity.getCommandSenderName(), true, 1);
		this.thePlayer = par1EntityPlayer;
		this.theAnimal = par2Entity;
	}
}
