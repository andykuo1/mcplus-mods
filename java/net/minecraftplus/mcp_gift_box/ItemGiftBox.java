package net.minecraftplus.mcp_gift_box;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftplus._api.dictionary.Sounds;
import net.minecraftplus._api.minecraft.base.ItemDyeable;

public class ItemGiftBox extends ItemDyeable
{
	public ItemGiftBox()
	{
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabMisc);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack parItemStack)
	{
		return EnumAction.EAT;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack parItemStack)
	{
		return 64;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack parItemStack, World parWorld, EntityPlayer parEntityPlayer)
	{
		--parItemStack.stackSize;
		parWorld.playSoundAtEntity(parEntityPlayer, Sounds.RANDOM_POP, 0.5F, parWorld.rand.nextFloat() * 0.1F + 0.9F);

		if (!parWorld.isRemote && parItemStack.hasTagCompound())
		{
			NBTTagCompound nbttagcompound = parItemStack.getTagCompound();
			NBTTagList nbttaglist = nbttagcompound.getCompoundTag("Inventory").getTagList("StackItems", 10);
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.getCompoundTagAt(0);
			ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbttagcompound1);

			parEntityPlayer.entityDropItem(itemstack, 1F);
		}

		return parItemStack;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack parItemStack, World parWorld, EntityPlayer parEntityPlayer)
	{
		parEntityPlayer.setItemInUse(parItemStack, this.getMaxItemUseDuration(parItemStack));
		return parItemStack;
	}

	@Override
	public int getDefaultColor()
	{
		return 0x444444;
	}
	
	public int getDefaultColorAlt()
	{
		return 0xFFFFFF;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getColorFromItemStack(ItemStack stack, int renderPass)
	{
		if (renderPass == 1)
		{
			int j = this.getColorAlt(stack);

			if (j < 0)
			{
				j = this.getDefaultColorAlt();
			}

			return j;
		}
		else
		{
			return super.getColorFromItemStack(stack, renderPass);
		}
	}

	@Override
	public boolean hasColor(ItemStack parItemStack)
	{
		//Compare to: @ItemArmor
		return !parItemStack.hasTagCompound() ? false : (!parItemStack.getTagCompound().hasKey("display", 10) ? false : parItemStack.getTagCompound().getCompoundTag("display").hasKey("color", 3) || parItemStack.getTagCompound().getCompoundTag("display").hasKey("coloralt", 3));
	}

	public int getColorAlt(ItemStack parItemStack)
	{
		NBTTagCompound nbttagcompound = parItemStack.getTagCompound();

		if (nbttagcompound != null)
		{
			NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

			if (nbttagcompound1 != null && nbttagcompound1.hasKey("coloralt", 3))
			{
				return nbttagcompound1.getInteger("coloralt");
			}
		}

		return this.getDefaultColorAlt();
	}

	@Override
	public void removeColor(ItemStack stack)
	{
		//Compare to: @ItemArmor
		NBTTagCompound nbttagcompound = stack.getTagCompound();

		if (nbttagcompound != null)
		{
			NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

			if (nbttagcompound1.hasKey("color"))
			{
				nbttagcompound1.removeTag("color");
			}
			else if (nbttagcompound1.hasKey("coloralt"))
			{
				nbttagcompound1.removeTag("coloralt");
			}
		}
	}

	public void setColor(ItemStack parItemStack, int parColor, int parColorAlt)
	{
		this.setColor(parItemStack, parColor);

		NBTTagCompound nbttagcompound = parItemStack.getTagCompound();

		if (nbttagcompound == null)
		{
			nbttagcompound = new NBTTagCompound();
			parItemStack.setTagCompound(nbttagcompound);
		}

		NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

		if (!nbttagcompound.hasKey("display", 10))
		{
			nbttagcompound.setTag("display", nbttagcompound1);
		}

		nbttagcompound1.setInteger("coloralt", parColorAlt);
	}
}
