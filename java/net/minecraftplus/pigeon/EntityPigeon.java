package net.minecraftplus.pigeon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.AnimalChest;
import net.minecraft.inventory.IInvBasic;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityPigeon extends EntityPigeonFlyable implements IInvBasic
{
	protected AnimalChest pigeonChest;

	public EntityPigeon(World parWorld)
	{
		super(parWorld);
		this.setChested(false);
		this.chestInit();
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(21, new Byte((byte)0));
	}

	protected boolean getPigeonBoolean(int parIndex)
	{
		return (this.dataWatcher.getWatchableObjectByte(21) & parIndex) != 0;
	}

	protected void setPigeonBoolean(int parIndex, boolean parValue)
	{
		int j = this.dataWatcher.getWatchableObjectByte(21);

		if (parValue)
		{
			this.dataWatcher.updateObject(21, Byte.valueOf((byte)(j | parIndex)));
		}
		else
		{
			this.dataWatcher.updateObject(21, Byte.valueOf((byte)(j & ~parIndex)));
		}
	}

	public boolean isChested()
	{
		return this.getPigeonBoolean(2);
	}

	public void setChested(boolean parIsChested)
	{
		this.setPigeonBoolean(2, parIsChested);
	}

	public boolean hasHelmet()
	{
		return this.getPigeonBoolean(4);
	}

	public void setHasHelmet(boolean parHasHelmet)
	{
		this.setPigeonBoolean(4, parHasHelmet);
	}

	public int getHelmetColor()
	{
		ItemStack itemstack = this.pigeonChest.getStackInSlot(0);
		if (itemstack == null)
		{
			this.updateClientChest();
			itemstack = this.pigeonChest.getStackInSlot(0);
			if (itemstack == null) return 10511680;
		}

		if (Items.leather_helmet.hasColor(itemstack))
		{
			return Items.leather_helmet.getColor(itemstack);
		}
		else
		{
			return 10511680;
		}
	}

	private void chestInit()
	{
		AnimalChest animalchest = this.pigeonChest;
		this.pigeonChest = new AnimalChest("PigeonChest", this.getInventorySize());
		this.pigeonChest.func_110133_a(this.getCommandSenderName());

		if (animalchest != null)
		{
			animalchest.func_110132_b(this);
			int i = Math.min(animalchest.getSizeInventory(), this.pigeonChest.getSizeInventory());

			for (int j = 0; j < i; ++j)
			{
				ItemStack itemstack = animalchest.getStackInSlot(j);

				if (itemstack != null)
				{
					this.pigeonChest.setInventorySlotContents(j, itemstack.copy());
				}
			}

			animalchest = null;
		}

		this.pigeonChest.func_110134_a(this);
		this.updatePigeonBooleans();
	}

	protected void updatePigeonBooleans()
	{
		if (!this.worldObj.isRemote)
		{
			this.setHasHelmet(this.pigeonChest.getStackInSlot(0) != null);
		}
	}

	private void updateClientChest()
	{
		if (this.worldObj.isRemote && this.pigeonChest.getSizeInventory() != this.getInventorySize())
		{
			this.chestInit();
		}
	}

	@Override
	public void onInventoryChanged(InventoryBasic parInventory)
	{
		this.updatePigeonBooleans();
	}

	@Override
	public boolean interact(EntityPlayer parEntityPlayer)
	{
		ItemStack itemstack = parEntityPlayer.inventory.getCurrentItem();

		if (this.getOwner() != null && this.getOwner() != parEntityPlayer && (parEntityPlayer.getCurrentEquippedItem() == null || parEntityPlayer.getCurrentEquippedItem().getItem() != Items.melon_seeds || parEntityPlayer.getCurrentEquippedItem().getItem() != Items.melon))
		{
			this.setIsFlying(true);
			this.jump();
			return true;
		}
		else if (this.isTamed() && this.isChested())
		{
			this.updateClientChest();

			if (!this.hasHelmet() && itemstack != null && itemstack.getItem() == Items.leather_helmet)
			{
				this.pigeonChest.setInventorySlotContents(0, itemstack);
				if (!parEntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize == 0)
				{
					parEntityPlayer.inventory.setInventorySlotContents(parEntityPlayer.inventory.currentItem, (ItemStack)null);
				}
				this.setHasHelmet(true);
			}
			else if (!this.isChild() && parEntityPlayer.isSneaking())
			{
				this.openGUI(parEntityPlayer);
				return true;
			}
		}
		else if (itemstack != null && !this.isChild() && !this.isChested() && itemstack.getItem() == Item.getItemFromBlock(Blocks.chest))
		{
			this.setChested(true);
			this.playSound("mob.chicken.plop", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			this.chestInit();

			if (!parEntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize == 0)
			{
				parEntityPlayer.inventory.setInventorySlotContents(parEntityPlayer.inventory.currentItem, (ItemStack)null);
			}

			return true;
		}

		return super.interact(parEntityPlayer);
	}

	public void openGUI(EntityPlayer parEntityPlayer)
	{
		if (!this.worldObj.isRemote && this.isTamed())
		{
			this.pigeonChest.func_110133_a(this.getCommandSenderName());

			parEntityPlayer.openGui(ModPigeon.INSTANCE, 0, this.worldObj, (int)this.posX, this.getEntityId(), (int)this.posZ);
		}
	}

	protected AnimalChest getAnimalChest()
	{
		return this.pigeonChest;
	}

	private int getInventorySize()
	{
		return this.isChested() ? 7 : 0;
	}

	protected void dropChests()
	{
		if (!this.worldObj.isRemote && this.isChested())
		{
			this.dropItem(Item.getItemFromBlock(Blocks.chest), 1);
			this.setChested(false);
		}
	}

	protected void dropChestItems()
	{
		this.dropItemsInChest(this, this.pigeonChest);
		this.dropChests();
	}

	private void dropItemsInChest(Entity p_110240_1_, AnimalChest p_110240_2_)
	{
		if (p_110240_2_ != null && !this.worldObj.isRemote)
		{
			for (int i = 0; i < p_110240_2_.getSizeInventory(); ++i)
			{
				ItemStack itemstack = p_110240_2_.getStackInSlot(i);

				if (itemstack != null)
				{
					this.entityDropItem(itemstack, 0.0F);
				}
			}
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound parNBTTagCompound)
	{
		super.writeEntityToNBT(parNBTTagCompound);
		parNBTTagCompound.setBoolean("ChestedPigeon", this.isChested());

		if (this.isChested())
		{
			NBTTagList nbttaglist = new NBTTagList();

			for (int i = 0; i < this.pigeonChest.getSizeInventory(); ++i)
			{
				ItemStack itemstack = this.pigeonChest.getStackInSlot(i);

				if (itemstack != null)
				{
					NBTTagCompound nbttagcompound1 = new NBTTagCompound();
					nbttagcompound1.setByte("Slot", (byte)i);
					itemstack.writeToNBT(nbttagcompound1);
					nbttaglist.appendTag(nbttagcompound1);
				}
			}

			parNBTTagCompound.setTag("Items", nbttaglist);
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound parNBTTagCompound)
	{
		super.readEntityFromNBT(parNBTTagCompound);
		this.setChested(parNBTTagCompound.getBoolean("ChestedPigeon"));

		if (this.isChested())
		{
			NBTTagList nbttaglist = parNBTTagCompound.getTagList("Items", 10);
			this.chestInit();

			for (int i = 0; i < nbttaglist.tagCount(); i++)
			{
				NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
				int j = nbttagcompound1.getByte("Slot") & 255;

				if (j < this.pigeonChest.getSizeInventory())
				{
					this.pigeonChest.setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(nbttagcompound1));
				}
			}
		}
	}

	@Override
	public void onDeath(DamageSource p_70645_1_)
	{
		super.onDeath(p_70645_1_);

		if (!this.worldObj.isRemote)
		{
			this.dropChestItems();
		}
	}
}
