package net.minecraftplus.cart;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemCart extends Item
{
	public ItemCart()
	{
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabTransport);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par2World.isRemote)
		{
			return par1ItemStack;
		}
		else
		{
			MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);

			if (movingobjectposition == null)
			{
				return par1ItemStack;
			}
			else
			{
				if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
				{
					int i = movingobjectposition.blockX;
					int j = movingobjectposition.blockY + 1;
					int k = movingobjectposition.blockZ;

					Entity entity = spawnCreature(par2World, 0, (double)i + 0.5D, (double)j, (double)k + 0.5D);
				}

				if (!par3EntityPlayer.capabilities.isCreativeMode)
				{
					--par1ItemStack.stackSize;
				}

				return par1ItemStack;
			}
		}
	}

	public static Entity spawnCreature(World par0World, int par1, double par2, double par4, double par6)
	{
		Entity entity = null;

		for (int j = 0; j < 1; ++j)
		{
			entity = new EntityCart(par0World, par2, par4, par6);

			if (entity != null && entity instanceof EntityLivingBase)
			{
				EntityLivingBase entityliving = (EntityLivingBase)entity;
				entity.setLocationAndAngles(par2, par4, par6, MathHelper.wrapAngleTo180_float(par0World.rand.nextFloat() * 360.0F), 0.0F);
				entityliving.rotationYawHead = entityliving.rotationYaw;
				entityliving.renderYawOffset = entityliving.rotationYaw;
				par0World.spawnEntityInWorld(entity);
			}
		}

		return entity;
	}
}
