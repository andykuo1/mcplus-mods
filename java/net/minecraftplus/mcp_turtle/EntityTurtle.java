package net.minecraftplus.mcp_turtle;

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
import net.minecraftplus._api.dictionary.Sounds;

public class EntityTurtle extends EntityTurtleBase implements IInvBasic
{
	//Compare To: @EntityHorse
	private AnimalChest turtleChest;

	public EntityTurtle(World worldIn)
	{
		//Compare To: @EntityHorse
		super(worldIn);
		this.setChested(false);
		this.chestInit();
	}

	@Override
	protected void entityInit()
	{
		//Compare To: @EntityHorse
		super.entityInit();
		this.dataWatcher.addObject(21, Integer.valueOf(0));
	}

	private boolean getTurtleWatchableBoolean(int p_110233_1_)
	{
		//Compare To: @EntityHorse
		return (this.dataWatcher.getWatchableObjectInt(21) & p_110233_1_) != 0;
	}

	private void setTurtleWatchableBoolean(int p_110208_1_, boolean p_110208_2_)
	{
		//Compare To: @EntityHorse
		int j = this.dataWatcher.getWatchableObjectInt(21);

		if (p_110208_2_)
		{
			this.dataWatcher.updateObject(21, Integer.valueOf(j | p_110208_1_));
		}
		else
		{
			this.dataWatcher.updateObject(21, Integer.valueOf(j & ~p_110208_1_));
		}
	}

	public boolean isChested()
	{
		//Compare To: @EntityHorse
		return this.getTurtleWatchableBoolean(2);
	}

	public void setChested(boolean p_110207_1_)
	{
		//Compare To: @EntityHorse
		this.setTurtleWatchableBoolean(2, p_110207_1_);
	}

	private int getChestSize()
	{
		//Compare To: @EntityHorse
		return this.isChested() ? 15 : 0;
	}

	public AnimalChest getChest()
	{
		return this.turtleChest;
	}

	private void chestInit()
	{
		//Compare To: @EntityHorse
		AnimalChest animalchest = this.turtleChest;
		this.turtleChest = new AnimalChest("TurtleChest", this.getChestSize());
		this.turtleChest.setCustomName(this.getName());

		if (animalchest != null)
		{
			animalchest.func_110132_b(this);
			int i = Math.min(animalchest.getSizeInventory(), this.turtleChest.getSizeInventory());

			for (int j = 0; j < i; ++j)
			{
				ItemStack itemstack = animalchest.getStackInSlot(j);

				if (itemstack != null)
				{
					this.turtleChest.setInventorySlotContents(j, itemstack.copy());
				}
			}
		}

		this.turtleChest.func_110134_a(this);
		this.updateWatcheables();
	}

	private void updateWatcheables()
	{
		if (this.worldObj.isRemote && this.turtleChest.getSizeInventory() != this.getChestSize())
		{
			this.chestInit();
		}
	}

	@Override
	public void onInventoryChanged(InventoryBasic p_76316_1_)
	{
		//Compare To: @EntityHorse
		this.updateWatcheables();
	}

	public void openGUI(EntityPlayer playerEntity)
	{
		//Compare To: @EntityHorse
		if (!this.worldObj.isRemote && this.isTamed())
		{
			this.turtleChest.setCustomName(this.getName());
			playerEntity.openGui(_Turtle.INSTANCE, 0, this.worldObj, (int)this.posX, this.getEntityId(), (int)this.posZ);
		}
	}

	@Override
	public boolean interact(EntityPlayer player)
	{
		//Compare To: @EntityHorse
		ItemStack itemstack = player.inventory.getCurrentItem();

		if (this.worldObj.isRemote && this.isChested() && this.turtleChest.getSizeInventory() != this.getChestSize())
		{
			this.chestInit();
		}

		if (itemstack != null && (itemstack.getItem() == Items.spawn_egg || itemstack.getItem() == Items.fish))
		{
			return super.interact(player);
		}
		else if (this.isTamed() && !this.isChild() && player.isSneaking())
		{
			this.openGUI(player);
			return true;
		}
		else
		{
			if (itemstack != null)
			{
				boolean flag = false;

				if (!flag && this.isTamed() && !this.isChested() && itemstack.getItem() == Item.getItemFromBlock(Blocks.chest))
				{
					this.setChested(true);
					this.playSound(Sounds.MOB_CHICKEN_PLOP, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
					flag = true;
					this.chestInit();
				}

				if (flag)
				{
					if (!player.capabilities.isCreativeMode && --itemstack.stackSize == 0)
					{
						player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
					}

					return true;
				}
			}
		}

		return super.interact(player);
	}

	@Override
	public void onDeath(DamageSource cause)
	{
		//Compare To: @EntityHorse
		super.onDeath(cause);

		if (!this.worldObj.isRemote)
		{
			this.dropChestItems();
		}
	}

	public void dropChestItems()
	{
		//Compare To: @EntityHorse
		this.dropItemsInChest(this, this.turtleChest);
		this.dropChests();
	}

	public void dropChests()
	{
		//Compare To: @EntityHorse
		if (!this.worldObj.isRemote && this.isChested())
		{
			this.dropItem(Item.getItemFromBlock(Blocks.chest), 1);
			this.setChested(false);
		}
	}

	private void dropItemsInChest(Entity p_110240_1_, AnimalChest p_110240_2_)
	{
		//Compare To: @EntityHorse
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
	public void writeEntityToNBT(NBTTagCompound tagCompound)
	{
		//Compare To: @EntityHorse
		super.writeEntityToNBT(tagCompound);
		tagCompound.setBoolean("ChestedTurtle", this.isChested());

		if (this.isChested())
		{
			NBTTagList nbttaglist = new NBTTagList();

			for (int i = 0; i < this.turtleChest.getSizeInventory(); ++i)
			{
				ItemStack itemstack = this.turtleChest.getStackInSlot(i);

				if (itemstack != null)
				{
					NBTTagCompound nbttagcompound1 = new NBTTagCompound();
					nbttagcompound1.setByte("Slot", (byte)i);
					itemstack.writeToNBT(nbttagcompound1);
					nbttaglist.appendTag(nbttagcompound1);
				}
			}

			tagCompound.setTag("Items", nbttaglist);
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompound)
	{
		//Compare To: @EntityHorse
		super.readEntityFromNBT(tagCompound);
		this.setChested(tagCompound.getBoolean("ChestedTurtle"));

		if (this.isChested())
		{
			NBTTagList nbttaglist = tagCompound.getTagList("Items", 10);
			this.chestInit();

			for (int i = 0; i < nbttaglist.tagCount(); ++i)
			{
				NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
				int j = nbttagcompound1.getByte("Slot") & 255;

				if (j < this.turtleChest.getSizeInventory())
				{
					this.turtleChest.setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(nbttagcompound1));
				}
			}
		}

		this.updateWatcheables();
	}

	@Override
	public boolean isOnLadder()
	{
		//Compare To: @EntityHorse
		return false;
	}

	@Override
	public float getEyeHeight()
	{
		//Compare To: @EntityHorse
		return this.height;
	}

	@Override
	public boolean replaceItemInInventory(int p_174820_1_, ItemStack p_174820_2_)
	{
		//Compare To: @EntityHorse
		if (p_174820_1_ == 499 && this.isTamed())
		{
			if (p_174820_2_ == null && this.isChested())
			{
				this.setChested(false);
				this.chestInit();
				return true;
			}

			if (p_174820_2_ != null && p_174820_2_.getItem() == Item.getItemFromBlock(Blocks.chest) && !this.isChested())
			{
				this.setChested(true);
				this.chestInit();
				return true;
			}
		}

		int k = p_174820_1_ - 500 + 1;

		if (k >= 0 && k < this.turtleChest.getSizeInventory())
		{
			this.turtleChest.setInventorySlotContents(k, p_174820_2_);
			return true;
		}
		else
		{
			return false;
		}
	}
}