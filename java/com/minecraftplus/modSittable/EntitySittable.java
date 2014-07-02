package com.minecraftplus.modSittable;

import net.minecraft.block.BlockStairs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntitySittable extends EntityHanging
{
	public EntitySittable(World par1World)
	{
		super(par1World);
	}

	public EntitySittable(World par1World, int par2, int par4, int par5)
	{
		super(par1World, par2, par4, par5, 0);
		this.field_146063_b = par2;
		this.field_146064_c = par4;
		this.field_146062_d = par5;
		this.prevPosX = this.posX = (float)this.field_146063_b + 0.5F;
		this.prevPosY = this.posY = (float)this.field_146064_c;
		this.prevPosZ = this.posZ = (float)this.field_146062_d + 0.5F;
		this.ySize = 0;
		this.setSize(0F, 0F);
	}

	@Override
	public boolean isInRangeToRenderDist(double par1)
	{
		return true;
	}

	@Override
	public AxisAlignedBB getBoundingBox()
	{
		return null;
	}

	@Override
	public boolean onValidSurface()
	{
		return !(this.ticksExisted > 1 && this.riddenByEntity == null) || this.worldObj.getBlock(this.field_146063_b, this.field_146064_c, this.field_146062_d) instanceof BlockStairs;
	}

	@Override
	public int getWidthPixels()
	{
		return 0;
	}

	@Override
	public int getHeightPixels()
	{
		return 0;
	}

	@Override
	public void onBroken(Entity par1Entity)
	{
		this.mountEntity(null);
	}
}
