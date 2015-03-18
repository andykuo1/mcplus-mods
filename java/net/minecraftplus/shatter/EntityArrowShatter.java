package net.minecraftplus.shatter;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityArrowShatter extends EntityArrow
{
	public EntityArrowShatter(World par1World)
	{
		super(par1World);
	}

	public EntityArrowShatter(World par1World, double par2, double par4, double par6)
	{
		super(par1World, par2, par4, par6);
	}

	public EntityArrowShatter(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase, float par4, float par5)
	{
		super(par1World, par2EntityLivingBase, par3EntityLivingBase, par4, par5);
	}

	public EntityArrowShatter(World par1World, EntityLivingBase par2EntityLivingBase, float par3)
	{
		super(par1World, par2EntityLivingBase, par3);
	}

	@Override
	public void onUpdate()
	{
		float scale = 20F;
		Vec3 vec31 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
		Vec3 vec3 = Vec3.createVectorHelper(this.posX + this.motionX * scale, this.posY + this.motionY * scale, this.posZ + this.motionZ * scale);
		MovingObjectPosition movingobjectposition = this.worldObj.func_147447_a(vec31, vec3, false, true, false);
		vec31 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
		vec3 = Vec3.createVectorHelper(this.posX + this.motionX * scale, this.posY + this.motionY * scale, this.posZ + this.motionZ * scale);

		if (movingobjectposition != null)
		{
			vec3 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
		}
		else
		{
			super.onUpdate();
			return;
		}

		int x = movingobjectposition.blockX;
		int y = movingobjectposition.blockY;
		int z = movingobjectposition.blockZ;
		Block block = this.worldObj.getBlock(x, y, z);

		if (this.getIsCritical() && this.isBreakable(block))
		{
			this.breakGlass(this.worldObj, block, x, y, z);

			Block block1;
			if (this.isBreakable(block1 = this.worldObj.getBlock(x, y + 1, z))) {this.breakGlass(this.worldObj, block1, x, y + 1, z);}
			if (this.isBreakable(block1 = this.worldObj.getBlock(x, y - 1, z))) {this.breakGlass(this.worldObj, block, x, y - 1, z);}
			if (this.isBreakable(block1 = this.worldObj.getBlock(x + 1, y, z))) {this.breakGlass(this.worldObj, block, x + 1, y, z);}
			if (this.isBreakable(block1 = this.worldObj.getBlock(x - 1, y, z))) {this.breakGlass(this.worldObj, block, x - 1, y, z);}
			if (this.isBreakable(block1 = this.worldObj.getBlock(x, y, z + 1))) {this.breakGlass(this.worldObj, block, x, y, z + 1);}
			if (this.isBreakable(block1 = this.worldObj.getBlock(x, y, z - 1))) {this.breakGlass(this.worldObj, block, x, y, z - 1);}
			return;
		}

		super.onUpdate();
	}

	private boolean isBreakable(Block parBlock)
	{
		return parBlock == Blocks.glass_pane || parBlock == Blocks.stained_glass_pane;
	}

	private void breakGlass(World par1World, Block parBlock, int par2, int par3, int par4)
	{
		this.worldObj.setBlockToAir(par2, par3, par4);
		par1World.playAuxSFX(2001, par2, par3, par4, Block.getIdFromBlock(parBlock) + (par1World.getBlockMetadata(par2, par3, par4) << 12));
	}
}
