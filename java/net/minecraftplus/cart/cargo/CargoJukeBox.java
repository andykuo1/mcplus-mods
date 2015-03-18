package net.minecraftplus.cart.cargo;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftplus.cart.EntityCart;

public class CargoJukeBox extends Cargo
{
	private ItemStack record;

	public CargoJukeBox(EntityCart par1EntityCart)
	{
		super(par1EntityCart);
	}

	public int getCargoID()
	{
		return 3;
	}

	public void interact(EntityPlayer par1EntityPlayer)
	{
		if (this.getRecord() != null)
		{
			this.ejectRecord(par1EntityPlayer.worldObj, this.getTileX(), this.getTileY(), this.getTileZ());
		}
		else
		{
			if (par1EntityPlayer.getCurrentEquippedItem() != null && par1EntityPlayer.getCurrentEquippedItem().getItem() instanceof ItemRecord)
			{
				this.insertRecord(par1EntityPlayer.getCurrentEquippedItem());

				if (--par1EntityPlayer.getCurrentEquippedItem().stackSize <= 0)
				{
					par1EntityPlayer.destroyCurrentEquippedItem();
				}

				par1EntityPlayer.worldObj.playAuxSFX(1005, this.getTileX(), this.getTileY(), this.getTileZ(), Item.getIdFromItem(this.record.getItem()));
				//par1EntityPlayer.worldObj.playRecord("records." + ((ItemRecord)this.record.getItem()).recordName, this.getTileX(), this.getTileY(), this.getTileZ());
			}
		}
	}

	public void readCargoFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		if (par1NBTTagCompound.hasKey("RecordItem", 10))
		{
			this.setRecord(ItemStack.loadItemStackFromNBT(par1NBTTagCompound.getCompoundTag("RecordItem")));
		}
		else if (par1NBTTagCompound.getInteger("Record") > 0)
		{
			this.setRecord(new ItemStack(Item.getItemById(par1NBTTagCompound.getInteger("Record")), 1, 0));
		}
	}

	public void writeCargoToNBT(NBTTagCompound par1NBTTagCompound)
	{
		if (this.getRecord() != null)
		{
			par1NBTTagCompound.setTag("RecordItem", this.getRecord().writeToNBT(new NBTTagCompound()));
			par1NBTTagCompound.setInteger("Record", Item.getIdFromItem(this.getRecord().getItem()));
		}
	}

	public void dropDeadItems()
	{
		this.ejectRecord(this.getWorldObj(), this.getTileX(), this.getTileY(), this.getTileZ());
		if (!this.getWorldObj().isRemote)
		{
			this.theCart.entityDropItem(new ItemStack(Blocks.jukebox), 0.0F);
		}
	}

	/**
	 * Insert the specified music disc in the jukebox at the given coordinates
	 */
	public void insertRecord(ItemStack par1ItemStack)
	{
		this.setRecord(par1ItemStack.copy());
	}

	/**
	 * Ejects the current record inside of the jukebox.
	 */
	public void ejectRecord(World par1World, int par2, int par3, int par4)
	{
		ItemStack itemstack = this.getRecord();

		if (itemstack != null)
		{
			par1World.playAuxSFX(1005, par2, par3, par4, 0);
			par1World.playRecord((String)null, par2, par3, par4);

			this.setRecord((ItemStack)null);
			if (!par1World.isRemote)
			{
				this.theCart.entityDropItem(itemstack, 0F);
			}
		}
	}

	public ItemStack getRecord()
	{
		return this.record;
	}

	public void setRecord(ItemStack par1ItemStack)
	{
		this.record = par1ItemStack;
	}

	public boolean isSteerableInUse()
	{
		return this.record == null;
	}
}
