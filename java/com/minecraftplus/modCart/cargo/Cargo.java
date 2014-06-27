package com.minecraftplus.modCart.cargo;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import com.minecraftplus.modCart.EntityCart;
import com.minecraftplus.modCart.MCP_Cart;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class Cargo
{
	protected Random rand = new Random();

	protected EntityCart theCart;

	protected ModelBase model;
	protected ResourceLocation modelTexture;

	public Cargo(EntityCart par1EntityCart)
	{
		this.theCart = par1EntityCart;
	}

	public abstract int getCargoID();

	@SideOnly(Side.CLIENT)
	public ModelBase getRenderModel()
	{
		return this.model;
	}

	@SideOnly(Side.CLIENT)
	public ResourceLocation getRenderTexture()
	{
		return this.modelTexture;
	}

	@SideOnly(Side.CLIENT)
	public void renderModel()
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(this.getRenderTexture());
		this.getRenderModel().render(this.theCart, 0, 0, 0, 0, 0, 0);
	}

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
		par1EntityPlayer.openGui(MCP_Cart.INSTANCE, this.getCargoID() - 1, par1EntityPlayer.worldObj, this.getTileX(), this.theCart.getEntityId(), this.getTileZ());
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
