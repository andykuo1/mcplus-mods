package com.minecraftplus.modQuiver;

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
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.minecraftplus._base.registry.IconRegistry;
import com.minecraftplus._common.dye.Dyeable;
import com.minecraftplus._common.dye.IDyeable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemQuiver extends ItemArmor implements IDyeable.Item
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
	public IIcon getIconFromDamageForRenderPass(int par1, int par2)
	{
		return par2 == 1 ? this.itemIcon1 : super.getIconFromDamageForRenderPass(par1, par2);
	}

	@Override
	public int getColor(ItemStack par1ItemStack)
	{
		return Dyeable.Item.getColor(par1ItemStack, 12999733);
	}

	@Override
	public void setColor(ItemStack par1ItemStack, int par2)
	{
		super.func_82813_b(par1ItemStack, par2);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IIconRegister)
	{
		this.itemIcon = IconRegistry.register(par1IIconRegister, this);
		this.itemIcon1 = IconRegistry.register(par1IIconRegister, this, ".overlay");
	}

	@Override
	public String getArmorTexture(ItemStack par1ItemStack, Entity par2Entity, int par3, String par4)
	{
		int layer = par3 == 2 ? 2 : 1;
		return "minecraftplus:textures/armors/" + this.getUnlocalizedName() + ".layer_" + par3 + ".png";
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ModelBiped getArmorModel(EntityLivingBase par1EntityLivingBase, ItemStack par2ItemStak, int par3Slot)
	{
		return new ModelArmorQuiver();
	}

	@Override
	public void onArmorTick(World par1World, EntityPlayer par2EntityPlayer, ItemStack par3ItemStack)
	{
		if (!par1World.isRemote && !par2EntityPlayer.capabilities.isCreativeMode)
		{
			int i = this.autoCoolDown-- > 0 ? 0 : 1;
			if (i > 0)
			{
				IInventory inventoryquiver = getQuiverArmorInv(par2EntityPlayer);
				if (inventoryquiver != null)
				{
					if (this.getItemAmount(par2EntityPlayer.inventory, Items.arrow) + i <= 64)
					{
						par2EntityPlayer.worldObj.playSoundAtEntity(par2EntityPlayer, "mob.chicken.step", 0.6F, 0.6F);
						this.givePlayerArrows((InventoryQuiver) inventoryquiver, par2EntityPlayer, i);
					}
				}

				this.autoCoolDown = this.autoCoolDownMax;
			}
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.block;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 2;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par3EntityPlayer.isSneaking())
		{
			par3EntityPlayer.openGui(MCP_Quiver.INSTANCE, 0, par2World, 0, 0, 0);
		}
		else
		{
			InventoryQuiver inventoryquiver = (InventoryQuiver) new ContainerQuiver(par3EntityPlayer, getQuiverInv(par3EntityPlayer), par1ItemStack, par3EntityPlayer.inventory.currentItem).getChestInventory();
			this.givePlayerArrows(inventoryquiver, par3EntityPlayer, 1);
			par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		}
		return par1ItemStack;
	}

	private boolean givePlayerArrows(InventoryQuiver par1InventoryQuiver, EntityPlayer par2EntityPlayer, int par3Iterations)
	{
		if (par1InventoryQuiver != null)
		{
			for (int i = 0; i < par3Iterations; i++)
			{
				int slot = ItemQuiver.getSlotWithItem(par1InventoryQuiver, Items.arrow);
				if (slot != -1)
				{
					//Has arrows in quiver
					par2EntityPlayer.playSound("mob.chicken.step", 0.6F, 0.6F);
					//Give player an arrow
					par1InventoryQuiver.decrStackSize(slot, 1);
					par2EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.arrow, 1));
				}
				else
				{
					//Out of arrows
					return false;
				}
			}

			return true;
		}

		return false;
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

	public static InventoryQuiver getQuiverInv(EntityPlayer player, boolean par2)
	{
		return par2 ? getQuiverInv(player) : getQuiverArmorInv(player);
	}

	public static InventoryQuiver getQuiverArmorInv(EntityPlayer player)
	{
		InventoryQuiver inventoryquiver = null;

		if (player.getCurrentArmor(1) != null && player.getCurrentArmor(1).getItem() == MCP_Quiver.quiver)
		{
			ItemStack itemstack = player.getCurrentArmor(1);
			int i = 1;
			if (itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("Size"))
			{
				i = itemstack.getTagCompound().getInteger("Size");
			}

			inventoryquiver = new InventoryQuiver(player, itemstack, i, true);
		}

		return inventoryquiver;
	}

	public static InventoryQuiver getQuiverInv(EntityPlayer par1EntityPlayer)
	{
		InventoryQuiver inventoryquiver = null;

		if ((par1EntityPlayer.getCurrentEquippedItem() != null) && (par1EntityPlayer.getCurrentEquippedItem().getItem() instanceof ItemQuiver))
		{
			ItemStack itemstack = par1EntityPlayer.getCurrentEquippedItem();
			int i = 1;
			if (itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("Size"))
			{
				i = itemstack.getTagCompound().getInteger("Size");
			}

			inventoryquiver = new InventoryQuiver(par1EntityPlayer, itemstack, i, false);
		}

		return inventoryquiver;
	}

	private static int getItemAmount(IInventory par1IInventory, Item par2Item)
	{
		int i = 0;
		for(int j = 0; j < par1IInventory.getSizeInventory(); j++)
		{
			ItemStack itemstack = par1IInventory.getStackInSlot(j);
			if (itemstack != null && itemstack.getItem() == par2Item)
			{
				i += itemstack.stackSize;
			}
		}
		return i;
	}
}