package net.minecraftplus.cart.cargo;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftplus.cart.EntityCart;
import net.minecraftplus.cart.ModCart;

public abstract class Cargo
{
	protected Random rand = new Random();

	protected EntityCart theCart;

	public Cargo(EntityCart par1EntityCart)
	{
		this.theCart = par1EntityCart;
	}

	public abstract int getCargoID();

	public void initCargo() {}

	public void onUpdate() {}

	public abstract void interact(EntityPlayer par1EntityPlayer);

	public IInventory getInventory()
	{
		return null;
	}

	public void readCargoFromNBT(NBTTagCompound par1NBTTagCompound) {}

	public void writeCargoToNBT(NBTTagCompound par1NBTTagCompound) {}

	public void dropDeadItems() {}

	public EntityCart getCart()
	{
		return this.theCart;
	}

	public void openGui(EntityPlayer par1EntityPlayer)
	{
		par1EntityPlayer.openGui(ModCart.INSTANCE, this.getCargoID() - 1, par1EntityPlayer.worldObj, this.getTileX(), this.theCart.getEntityId(), this.getTileZ());
	}

	public int getTileX()
	{
		return (int) this.theCart.posX;
	}

	public int getTileY()
	{
		return (int) this.theCart.posY;
	}

	public int getTileZ()
	{
		return (int) this.theCart.posZ;
	}

	public World getWorldObj()
	{
		return this.theCart.worldObj;
	}

	public boolean isSteerableInUse()
	{
		return true;
	}
}
