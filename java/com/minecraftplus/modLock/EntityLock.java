package com.minecraftplus.modLock;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityLock extends EntitySideWithOwner
{
	public EntityLock(World par1World)
	{
		super(par1World);
		this.yOffset = 0.4F;
		this.setSize(1.0625F, 1.0625F);
	}

	public EntityLock(World par1World, int par2, int par3, int par4, int par5, String par6String)
	{
		super(par1World, par2, par3, par4, par5, par6String);
		this.yOffset = 0.4F;
		this.setSize(1.0625F, 1.0625F);
	}

	public void breakOpenLock(EntityPlayer par1EntityPlayer)
	{
		this.setDead();
		this.onBroken(par1EntityPlayer);

		if (par1EntityPlayer != null)
		{
			if (!this.worldObj.isRemote)
			{
				int i = 1 + this.worldObj.rand.nextInt(3) + this.worldObj.rand.nextInt(3);

				while (i > 0)
				{
					int j = EntityXPOrb.getXPSplit(i);
					i -= j;
					this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
				}
			}
		}
	}

	public void breakCloseLock(EntityPlayer par1EntityPlayer)
	{
		if (par1EntityPlayer != null)
		{
			par1EntityPlayer.inventory.consumeInventoryItem(MCP_Lock.lockpick);
			if (!par1EntityPlayer.inventory.hasItem(MCP_Lock.lockpick))
			{
				par1EntityPlayer.closeScreen();
			}
		}
	}

	@Override
	public boolean hitByEntity(Entity par1Entity)
	{
		if (par1Entity != null && par1Entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) par1Entity;
			if (player.getCommandSenderName().equals(this.getOwnerName()) || player.capabilities.isCreativeMode)
			{
				if (player.isSneaking())
				{
					this.setDirection(this.getInverseDirection());
					return true;
				}
			}
			else if (player.inventory.hasItem(MCP_Lock.lockpick))
			{
				player.openGui(MCP_Lock.INSTANCE, 0, this.worldObj, this.xPosition, this.getEntityId(), this.zPosition);
				return true;
			}
			else
			{
				return false;
			}
		}

		return false;
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public int getInverseDirection()
	{
		return ((this.getDirection() + 1) % 2) + ((this.getDirection() / 2) * 2);
	}

	public void onBroken(Entity par1Entity)
	{
		if (!this.worldObj.isRemote && par1Entity instanceof EntityPlayer && !((EntityPlayer) par1Entity).capabilities.isCreativeMode)
		{
			this.entityDropItem(new ItemStack(MCP_Lock.ironLock), 0F);
			this.setDead();
		}
	}

	@Override
	public ItemStack getPickedResult(MovingObjectPosition target)
	{
		return new ItemStack(MCP_Lock.ironLock);
	}

	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float par1)
	{
		return 15728640;
	}

	public boolean isPlayerBehindLock(EntityPlayer par1EntityPlayer, double par2)
	{
		if (par1EntityPlayer.worldObj.isRemote)
		{
			return false;
		}

		double d = par1EntityPlayer.posX - this.posX;
		double d1 = par1EntityPlayer.posY - this.posY;
		double d2 = par1EntityPlayer.posZ - this.posZ;

		double d3 = this.posX - par1EntityPlayer.posX;
		double d4 = this.posY - par1EntityPlayer.posY;
		double d5 = this.posZ - par1EntityPlayer.posZ;

		switch((int) this.getDirection())
		{
		case 1:
			//-Y - DOWN
			if ((d1 < par2) && (d < 0.5D && d > -0.5D) && (d2 < 1.5D && d2 > -1.5D))
			{
				return true;
			}
			break;
		case 0:
			//+Y - UP
			if ((d4 < par2) && (d < 0.5D && d > -0.5D) && (d2 < 1.5D && d2 > -1.5D))
			{
				return true;
			}
			break;
		case 3:
			//-Z - FRONT
			if ((d2 < par2) && (d3 < 1.5D && d3 > -1.5D) && (d4 < 1.5D && d4 > -0.25D))
			{
				return true;
			}
			break;
		case 2:
			//+Z - BACK
			if ((d5 < par2) && (d3 < 1.5D && d3 > -1.5D) && (d4 < 1.5D && d4 > -0.25D))
			{
				return true;
			}
			break;
		case 5:
			//-X - LEFT
			if ((d < par2) && (d2 < 1.5D && d2 > -1.5D) && (d4 < 1.5D && d4 > -0.25D))
			{
				return true;
			}
			break;
		case 4:
			//+X - RIGHT
			if ((d3 < par2) && (d2 < 1.5D && d2 > -1.5D) && (d4 < 1.5D && d4 > -0.25D))
			{
				return true;
			}
		}

		return false;
	}
}