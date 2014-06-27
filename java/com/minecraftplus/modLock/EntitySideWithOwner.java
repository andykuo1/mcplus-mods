package com.minecraftplus.modLock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class EntitySideWithOwner extends EntitySide
{
	public EntitySideWithOwner(World par1World)
	{
		super(par1World);
	}

	public EntitySideWithOwner(World par1World, int par2, int par3, int par4, int par5, String par6String)
	{
		super(par1World, par2, par3, par4, par5);
		this.setOwnerName(par6String);
	}

	@Override
	public void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(5, "");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setString("Owner", this.getOwnerName());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		this.setOwnerName(par1NBTTagCompound.getString("Owner"));
	}

	public boolean hasOwner()
	{
		return this.getOwnerName().length() > 0;
	}

	public String getOwnerName()
	{
		return this.dataWatcher.getWatchableObjectString(5);
	}

	public void setOwnerName(String par1Str)
	{
		this.dataWatcher.updateObject(5, par1Str);
	}

	public EntityPlayer getOwner()
	{
		return this.worldObj.getPlayerEntityByName(this.getOwnerName());
	}
}
