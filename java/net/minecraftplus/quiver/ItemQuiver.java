package net.minecraftplus.quiver;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftplus._api.registry.IconRegistry;
import net.minecraftplus._api.tool.ResTool;
import net.minecraftplus._common.IItemDyeable;
import net.minecraftplus._common.InventoryItem;
import net.minecraftplus._common.util.InventoryUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemQuiver extends ItemArmor implements IItemDyeable
{
	private int autoCoolDown = 0;
	private final int autoCoolDownMax = 36;

	@SideOnly(Side.CLIENT)
	private IIcon itemIcon1;

	public ItemQuiver()
	{
		super(ArmorMaterial.CLOTH, 0, 2);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IIconRegister)
	{
		super.registerIcons(par1IIconRegister);
		this.itemIcon1 = IconRegistry.add(par1IIconRegister, this, "", "overlay");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamageForRenderPass(int par1, int par2)
	{
		return par2 == 1 ? this.itemIcon1 : super.getIconFromDamageForRenderPass(par1, par2);
	}

	@Override
	public String getArmorTexture(ItemStack parItemStack, Entity parEntity, int parLayer, String par4)
	{
		return ResTool.getResource(this.getUnlocalizedName() + ".layer_" + (parLayer == 2 ? 2 : 1), ResTool.ARMORS, ModQuiver.INSTANCE).toString();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ModelBiped getArmorModel(EntityLivingBase par1EntityLivingBase, ItemStack par2ItemStak, int par3Slot)
	{
		return new ModelArmorQuiver();
	}

	@Override
	public void onArmorTick(World parWorld, EntityPlayer parEntityPlayer, ItemStack parItemStack)
	{
		if (!parWorld.isRemote && !parEntityPlayer.capabilities.isCreativeMode)
		{
			int i = this.autoCoolDown-- > 0 ? 0 : 1;
			if (i > 0)
			{
				IInventory itemchest = ItemQuiver.getItemChest(parItemStack);
				if (itemchest != null)
				{
					if (InventoryUtil.getTotalAmount(parEntityPlayer.inventory, Items.arrow) + i <= 64)
					{
						parEntityPlayer.worldObj.playSoundAtEntity(parEntityPlayer, "mob.chicken.step", 0.6F, 0.6F);
						itemchest.openInventory();
						this.givePlayerArrows(parEntityPlayer, itemchest, i);
						itemchest.closeInventory();
					}
				}

				this.autoCoolDown = this.autoCoolDownMax;
			}
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack parItemStack)
	{
		return EnumAction.block;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack parItemStack)
	{
		return 2;
	}

	protected int getWhiteColor()
	{
		return 16777215;
	}

	protected int getDefaultColor()
	{
		return 12999733;
	}

	@Override
	public int getColorFromItemStack(ItemStack parItemStack, int parColor)
	{
		if (parColor > 0)
		{
			return this.getWhiteColor();
		}
		else
		{
			int j = this.getColor(parItemStack);

			if (j < 0)
			{
				j = this.getWhiteColor();
			}

			return j;
		}
	}

	@Override
	public boolean hasColor(ItemStack parItemStack)
	{
		return !parItemStack.hasTagCompound() ? false : (!parItemStack.getTagCompound().hasKey("display", 10) ? false : parItemStack.getTagCompound().getCompoundTag("display").hasKey("color", 3));
	}

	@Override
	public int getColor(ItemStack parItemStack)
	{
		NBTTagCompound nbttagcompound = parItemStack.getTagCompound();

		if (nbttagcompound == null)
		{
			return this.getDefaultColor();
		}
		else
		{
			NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");
			return nbttagcompound1 == null ? this.getDefaultColor() : (nbttagcompound1.hasKey("color", 3) ? nbttagcompound1.getInteger("color") : this.getDefaultColor());
		}
	}

	@Override
	public void removeColor(ItemStack parItemStack)
	{
		NBTTagCompound nbttagcompound = parItemStack.getTagCompound();

		if (nbttagcompound != null)
		{
			NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

			if (nbttagcompound1.hasKey("color"))
			{
				nbttagcompound1.removeTag("color");
			}
		}
	}

	@Override
	public void setColor(ItemStack parItemStack, int parColor)
	{
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

		nbttagcompound1.setInteger("color", parColor);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack parItemStack, World parWorld, EntityPlayer parEntityPlayer)
	{
		if (parEntityPlayer.isSneaking())
		{
			parEntityPlayer.openGui(ModQuiver.INSTANCE, 0, parWorld, 0, 0, 0);
		}
		else
		{
			IInventory itemchest = ItemQuiver.getItemChest(parItemStack);
			itemchest.openInventory();
			this.givePlayerArrows(parEntityPlayer, itemchest, 1);
			itemchest.closeInventory();
			parEntityPlayer.setItemInUse(parItemStack, this.getMaxItemUseDuration(parItemStack));
		}
		return parItemStack;
	}

	private void givePlayerArrows(EntityPlayer parEntityPlayer, IInventory parInventory, int parAmount)
	{
		if (parInventory != null)
		{
			ItemStack itemstack = InventoryUtil.takeSome(parInventory, Items.arrow, parAmount);
			if (itemstack != null && itemstack.stackSize > 0)
			{
				parEntityPlayer.playSound("mob.chicken.step", 0.6F, 0.6F);
				parEntityPlayer.inventory.addItemStackToInventory(itemstack);
			}
		}
	}

	private static int getSlotWithItem(IInventory parInventory, Item parItem)
	{
		for(int i = 0; i < parInventory.getSizeInventory(); i++)
		{
			if (parInventory.getStackInSlot(i) != null && parInventory.getStackInSlot(i).getItem() == parItem)
			{
				return i;
			}
		}

		return -1;
	}

	public static IInventory getItemChest(ItemStack parItemStack)
	{
		return new InventoryItem(parItemStack, 18);
	}
}
