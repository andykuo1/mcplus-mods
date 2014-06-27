package com.minecraftplus.modTurtle;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.AnimalChest;

public class InventoryTurtle extends AnimalChest
{
	protected final EntityPlayer thePlayer;
	protected final Entity theAnimal;

	public InventoryTurtle(EntityPlayer par1EntityPlayer, Entity par2Entity)
	{
		super(par2Entity.getCommandSenderName(), true, 15);
		this.thePlayer = par1EntityPlayer;
		this.theAnimal = par2Entity;
	}

	public int getLengthInventory()
	{
		return 5;
	}

	public int getBaseHeight()
	{
		return -1;
	}

	public int getHeight()
	{
		return 18 * this.getRows();
	}

	public int getRows()
	{
		return (int) Math.ceil(this.getSizeInventory() / (double) this.getLengthInventory());
	}

	public int getWidth()
	{
		return 18 * this.getColumns();
	}

	public int getColumns()
	{
		return this.getSizeInventory() - (this.getRows() * this.getLengthInventory()) + this.getLengthInventory();
	}

	public int getCustomAlignment()
	{
		return 162 - getWidth();
	}
}
