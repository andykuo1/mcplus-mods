package net.minecraftplus.soulextractor;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntitySoulExtractor extends TileEntity implements ISidedInventory
{
	private ItemStack[] soulExtractorItemStacks = new ItemStack[4];

	@SideOnly(Side.CLIENT)
	private float orbYaw = 0F;
	@SideOnly(Side.CLIENT)
	private float orbPitch = 0F;

	private final int soulExtractTimeMax = 250;
	public int soulExtractTime = this.soulExtractTimeMax;

	public static Random rand = new Random();
	private String field_94136_s;

	@Override
	public int getSizeInventory()
	{
		return this.soulExtractorItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int par1)
	{
		return this.soulExtractorItemStacks[par1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		if (this.soulExtractorItemStacks[par1] != null)
		{
			ItemStack itemstack;

			if (this.soulExtractorItemStacks[par1].stackSize <= par2)
			{
				itemstack = this.soulExtractorItemStacks[par1];
				this.soulExtractorItemStacks[par1] = null;
				return itemstack;
			}
			else
			{
				itemstack = this.soulExtractorItemStacks[par1].splitStack(par2);

				if (this.soulExtractorItemStacks[par1].stackSize == 0)
				{
					this.soulExtractorItemStacks[par1] = null;
				}

				return itemstack;
			}
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1)
	{
		if (this.soulExtractorItemStacks[par1] != null)
		{
			ItemStack itemstack = this.soulExtractorItemStacks[par1];
			this.soulExtractorItemStacks[par1] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		this.soulExtractorItemStacks[par1] = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
		{
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setShort("ExtractTime", (short)this.soulExtractTime);

		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.soulExtractorItemStacks.length; ++i)
		{
			if (this.soulExtractorItemStacks[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				this.soulExtractorItemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		par1NBTTagCompound.setTag("Items", nbttaglist);

		if (this.hasCustomInventoryName())
		{
			par1NBTTagCompound.setString("CustomName", this.field_94136_s);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);

		NBTTagList nbttaglist = (NBTTagList) par1NBTTagCompound.getTag("Items");
		this.soulExtractorItemStacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.soulExtractorItemStacks.length)
			{
				this.soulExtractorItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.soulExtractTime = par1NBTTagCompound.getShort("ExtractTime");

		if (par1NBTTagCompound.hasKey("CustomName"))
		{
			this.field_94136_s = par1NBTTagCompound.getString("CustomName");
		}
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Returns an integer between 0 and the passed value representing how close the current item is to being completely
	 * cooked
	 */
	public int getExtractProgressScaled(int par1)
	{
		return (this.soulExtractTimeMax - this.soulExtractTime) * par1 / this.soulExtractTimeMax;
	}

	/**
	 * Returns true if the soul extractor is currently burning
	 */
	public boolean isExtracting()
	{
		return this.soulExtractTime > 0;
	}

	public boolean shouldExtract()
	{
		return this.soulExtractTime <= 0 && this.canSmelt();
	}

	@Override
	public void updateEntity()
	{
		boolean flag = this.isExtracting();
		boolean flag1 = false;

		if (this.soulExtractTime > 0)
		{
			if (this.canSmelt())
			{
				--this.soulExtractTime;
				EntityPlayer player = this.worldObj.getClosestPlayer(this.xCoord + 0.5D, this.yCoord, this.zCoord + 0.5D, 3.2D);
				if (player != null)
				{
					player.motionX *= 0.2;
					player.motionZ *= 0.2;
				}
			}
			else if (this.soulExtractTime < this.soulExtractTimeMax)
			{
				++this.soulExtractTime;
			}
		}

		if (!this.worldObj.isRemote)
		{
			if (!this.isExtracting())
			{
				if (this.shouldExtract())
				{
					flag1 = true;
					this.smeltItem();
				}
				else if (this.canSmelt())
				{
					flag1 = true;
					this.soulExtractTime = this.soulExtractTimeMax;
					if (this.soulExtractorItemStacks[0] != null && this.soulExtractorItemStacks[1] != null && this.soulExtractorItemStacks[2] != null)
					{
						for(int i = 0; i < this.soulExtractorItemStacks.length; i++)
						{
							--this.soulExtractorItemStacks[i].stackSize;
							if (this.soulExtractorItemStacks[i].stackSize == 0)
							{
								this.soulExtractorItemStacks[i] = this.soulExtractorItemStacks[i].getItem().getContainerItem(this.soulExtractorItemStacks[i]);
							}
						}
					}
				}
				else
				{
					this.soulExtractTime = this.soulExtractTimeMax;
				}
			}

			if (flag != this.isExtracting())
			{
				flag1 = true;
				BlockSoulExtractor.updateSoulExtractorBlockState(this.isExtracting(), this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}
		else
		{
			this.updateOrbAngle();
		}

		if (flag1)
		{
			this.markDirty();
		}
	}

	/**
	 * Returns true if the soul extractor can extract soul, i.e. has a source item, destination stack isn't full, etc.
	 */
	private boolean canSmelt()
	{
		ItemStack stack;

		stack = this.soulExtractorItemStacks[0];
		if (stack == null || stack.getItem() != Items.glass_bottle)
		{
			return false;
		}

		stack = this.soulExtractorItemStacks[1];
		if (stack == null || stack.getItem() != Item.getItemFromBlock(Blocks.soul_sand))
		{
			return false;
		}

		stack = this.soulExtractorItemStacks[2];
		if (stack == null || stack.getItem() != Items.ender_pearl)
		{
			return false;
		}

		if (this.soulExtractorItemStacks[3] != null)
		{
			return false;
		}

		EntityPlayer player = this.worldObj.getClosestPlayer(this.xCoord + 0.5D, this.yCoord, this.zCoord + 0.5D, 3D);
		if (player != null && player.experienceLevel >= 5)
		{
			return true;
		}

		return false;
	}

	/**
	 * Turn one item from the soul extractor source stack into the appropriate item in the soul extractor result stack
	 */
	public void smeltItem()
	{
		if (this.canSmelt())
		{
			ItemStack itemstack = new ItemStack(Items.experience_bottle);

			EntityPlayer player = this.worldObj.getClosestPlayer(this.xCoord + 0.5D, this.yCoord, this.zCoord + 0.5D, 3D);
			if (player != null)
			{
				NBTTagCompound nbttagcompound = new NBTTagCompound();

				int lvlMax = player.experienceLevel > 10 ? 10 : player.experienceLevel;
				int lvl = 5 + ((int) Math.random() * lvlMax);
				int exp = this.getPlayerExperience(player, lvl);

				nbttagcompound.setInteger("ExpValue", exp);
				itemstack.setTagCompound(nbttagcompound);
				itemstack.setStackDisplayName(itemstack.getDisplayName() + " (+" + exp + ")");
				player.addExperienceLevel(-5);
			}

			if (this.soulExtractorItemStacks[3] == null)
			{
				this.soulExtractorItemStacks[3] = itemstack.copy();
			}
			else if (this.soulExtractorItemStacks[3].isItemEqual(itemstack))
			{
				soulExtractorItemStacks[3].stackSize += itemstack.stackSize;
			}

			for(int i = 0; i < this.soulExtractorItemStacks.length - 1; i++)
			{
				--this.soulExtractorItemStacks[i].stackSize;

				if (this.soulExtractorItemStacks[i].stackSize <= 0)
				{
					this.soulExtractorItemStacks[i] = null;
				}
			}
		}
	}

	public boolean canAbsorb(EntityPlayer par1EntityPlayer)
	{
		return par1EntityPlayer != null && this.isUseableByPlayer(par1EntityPlayer) && this.soulExtractorItemStacks[3] != null && this.soulExtractorItemStacks[3].getItem() == Items.experience_bottle;
	}

	public void absorbSoul(EntityPlayer par1EntityPlayer)
	{
		if (this.soulExtractorItemStacks[3] != null)
		{
			int i = 10;
			NBTTagCompound nbttagcompound = this.soulExtractorItemStacks[3].getTagCompound();
			if (nbttagcompound != null && nbttagcompound.hasKey("ExpValue"))
			{
				i = nbttagcompound.getInteger("ExpValue");
			}

			this.soulExtractorItemStacks[3] = null;
			if (!par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle)))
			{
				par1EntityPlayer.dropItem(Items.glass_bottle, 1);
			}

			par1EntityPlayer.playSound("random.orb", 0.6F, (this.rand.nextFloat() * 0.6F) + 0.4F);
			par1EntityPlayer.addExperience(i);
		}
	}

	public int getPlayerExperience(EntityPlayer par1EntityPlayer, int par2)
	{
		int i = par1EntityPlayer.experienceLevel;
		int j = i - par2;
		int k = 0;

		if (j <= 0)
		{
			k = (int) (par1EntityPlayer.experience * this.xpBarCap(i));
		}

		i -= 1;

		while(i >= j)
		{
			k += this.xpBarCap(i);
			i--;
		}

		return k;
	}

	/**
	 * This method returns the cap amount of experience that the experience bar can hold. With each level, the
	 * experience cap on the player's experience bar is raised by 10.
	 */
	public int xpBarCap(int par1)
	{
		return par1 >= 30 ? 62 + (par1 - 30) * 7 : (par1 >= 15 ? 17 + (par1 - 15) * 3 : 17);
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 6.0D;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
	{
		return par1 == 0 ? par2ItemStack.getItem() == Items.glass_bottle : par1 == 1 ? par2ItemStack.getItem() == Item.getItemFromBlock(Blocks.soul_sand) : par1 == 2 ? par2ItemStack.getItem() == Items.ender_pearl : par2ItemStack.getItem() == Items.experience_bottle;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int par1)
	{
		return par1 == 0 ? new int[] {3} : new int[] {0, 1, 2};
	}

	@Override
	public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
	{
		return this.isItemValidForSlot(par1, par2ItemStack);
	}

	@Override
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
	{
		return par3 == 0 && par1 == 3;
	}

	@Override
	public String getInventoryName()
	{
		return this.hasCustomInventoryName() ? this.field_94136_s : "container.soul_extractor";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return this.field_94136_s != null && this.field_94136_s.length() > 0;
	}

	/**
	 * Sets the custom display name to use when opening a GUI linked to this tile entity.
	 */
	public void setGuiDisplayName(String par1Str)
	{
		this.field_94136_s = par1Str;
	}

	@SideOnly(Side.CLIENT)
	public float getOrbYaw()
	{
		return (float) -Math.toDegrees(this.orbYaw) + 90F;
	}

	@SideOnly(Side.CLIENT)
	public float getOrbPitch()
	{
		return this.orbPitch;
	}

	@SideOnly(Side.CLIENT)
	public void updateOrbAngle()
	{
		EntityPlayer player = this.worldObj.getClosestPlayer(this.xCoord + 0.5D, this.yCoord, this.zCoord + 0.5D, 3D);
		if (player == null)
		{
			this.orbYaw += 0.06F;
			this.orbPitch += 2.5F;
			if (this.orbYaw >= 360)
			{
				this.orbYaw = 0F;
			}
			if (this.orbPitch >= 360)
			{
				this.orbPitch = 0F;
			}
		}
		else
		{
			if (this.orbPitch < 175)
			{
				this.orbPitch += 10F;
			}
			else if (this.orbPitch > 185)
			{
				this.orbPitch -= 10F;
			}
			else
			{
				this.orbPitch = 180F;
			}

			double d0 = player.posX - (double)((float)this.xCoord + 0.5F);
			double d1 = player.posZ - (double)((float)this.zCoord + 0.5F);
			float f = (float)Math.atan2(d1, d0);

			while (this.orbYaw >= (float)Math.PI)
			{
				this.orbYaw -= ((float)Math.PI * 2F);
			}

			while (this.orbYaw < -(float)Math.PI)
			{
				this.orbYaw += ((float)Math.PI * 2F);
			}

			while (f >= (float)Math.PI)
			{
				f -= ((float)Math.PI * 2F);
			}

			while (f < -(float)Math.PI)
			{
				f += ((float)Math.PI * 2F);
			}

			float f1;

			for (f1 = f - this.orbYaw; f1 >= (float)Math.PI; f1 -= ((float)Math.PI * 2F)) {}

			while (f1 < -(float)Math.PI)
			{
				f1 += ((float)Math.PI * 2F);
			}

			this.orbYaw += f1 * 0.4F;
		}
	}
}
