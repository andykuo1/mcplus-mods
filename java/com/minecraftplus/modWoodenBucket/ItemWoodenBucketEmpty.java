package com.minecraftplus.modWoodenBucket;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;

import org.lwjgl.opengl.GL11;

import com.minecraftplus._base.registry.IconRegistry;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWoodenBucketEmpty extends ItemArmor
{
	private static final ResourceLocation bucketBlurTexPath = new ResourceLocation("minecraftplus:textures/gui/bucketblur.png");

	public ItemWoodenBucketEmpty()
	{
		super(ArmorMaterial.IRON, 0, 0);
		this.setMaxStackSize(1);
		this.setMaxDamage(42);
		this.setCreativeTab(CreativeTabs.tabMisc);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IIconRegister)
	{
		this.itemIcon = IconRegistry.register(par1IIconRegister, this);
	}

	@Override
	public String getArmorTexture(ItemStack par1ItemStack, Entity par2Entity, int par3, String par4)
	{
		return "minecraftplus:textures/armors/" + this.getUnlocalizedName() + ".layer_1.png";
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void renderHelmetOverlay(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, ScaledResolution par3ScaledResolution, float par4, boolean par5, int par6, int par7)
	{
		this.renderBucketBlur(par3ScaledResolution.getScaledWidth(), par3ScaledResolution.getScaledHeight());
	}

	@SideOnly(Side.CLIENT)
	protected void renderBucketBlur(int par1, int par2)
	{
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glColor4f(0.0F, 0.0F, 0.0F, 1F);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		Minecraft.getMinecraft().getTextureManager().bindTexture(bucketBlurTexPath);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0.0D, (double)par2, -90.0D, 0.0D, 1.0D);
		tessellator.addVertexWithUV((double)par1, (double)par2, -90.0D, 1.0D, 1.0D);
		tessellator.addVertexWithUV((double)par1, 0.0D, -90.0D, 1.0D, 0.0D);
		tessellator.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
		tessellator.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, EntityLivingBase par3EntityLivingBase)
	{
		if (par2EntityPlayer.getCurrentEquippedItem().getItem() == MCP_WoodenBucket.woodenBucketEmpty && !par2EntityPlayer.capabilities.isCreativeMode && par3EntityLivingBase instanceof EntityCow)
		{
			if (!par2EntityPlayer.capabilities.isCreativeMode)
			{
				par1ItemStack.damageItem(1, par2EntityPlayer);
			}

			ItemStack itemstack = new ItemStack(MCP_WoodenBucket.woodenBucketMilk);
			itemstack.setItemDamage(par1ItemStack.getItemDamage());
			itemstack.setStackDisplayName(par1ItemStack.getDisplayName());
			par2EntityPlayer.destroyCurrentEquippedItem();
			par2EntityPlayer.inventory.mainInventory[par2EntityPlayer.inventory.currentItem] = itemstack;
			return true;
		}

		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);

		if (movingobjectposition == null)
		{
			return par1ItemStack;
		}
		else
		{
			FillBucketEvent event = new FillBucketEvent(par3EntityPlayer, par1ItemStack, par2World, movingobjectposition);
			if (MinecraftForge.EVENT_BUS.post(event))
			{
				return par1ItemStack;
			}

			if (event.getResult() == Event.Result.ALLOW)
			{
				if (par3EntityPlayer.capabilities.isCreativeMode)
				{
					return par1ItemStack;
				}

				if (--par1ItemStack.stackSize <= 0)
				{
					return event.result;
				}

				if (!par3EntityPlayer.inventory.addItemStackToInventory(event.result))
				{
					par3EntityPlayer.dropPlayerItemWithRandomChoice(event.result, false);
				}

				return par1ItemStack;
			}

			if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
			{
				int i = movingobjectposition.blockX;
				int j = movingobjectposition.blockY;
				int k = movingobjectposition.blockZ;

				if (!par2World.canMineBlock(par3EntityPlayer, i, j, k))
				{
					return par1ItemStack;
				}

				if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack))
				{
					return par1ItemStack;
				}

				Material material = par2World.getBlock(i, j, k).getMaterial();
				int l = par2World.getBlockMetadata(i, j, k);

				if (material == Material.water && l == 0)
				{
					par2World.setBlockToAir(i, j, k);
					return this.func_150910_a(par1ItemStack, par3EntityPlayer, MCP_WoodenBucket.woodenBucketWater);
				}
			}

			return par1ItemStack;
		}
	}

	private ItemStack func_150910_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, Item par3Item)
	{
		if (par2EntityPlayer.capabilities.isCreativeMode)
		{
			return par1ItemStack;
		}
		else if (--par1ItemStack.stackSize <= 0)
		{
			ItemStack itemstack = new ItemStack(par3Item);
			itemstack.setItemDamage(par1ItemStack.getItemDamage());
			return itemstack;
		}
		else
		{
			ItemStack itemstack = new ItemStack(par3Item, 1);
			itemstack.setItemDamage(par1ItemStack.getItemDamage());
			if (!par2EntityPlayer.inventory.addItemStackToInventory(itemstack))
			{
				par2EntityPlayer.dropPlayerItemWithRandomChoice(itemstack, false);
			}

			return par1ItemStack;
		}
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return par2ItemStack.getItem() == Items.stick || par2ItemStack.getItem() == Items.bowl || par2ItemStack.getItem() == MCP_WoodenBucket.woodenBucketEmpty ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@Override
	public boolean isDamageable()
	{
		return true;
	}
}
