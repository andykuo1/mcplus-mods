package net.minecraftplus.mcp_shatter;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityArrowShatter extends EntityArrow
{
	private boolean inGround = false;

	public EntityArrowShatter(World worldIn)
	{
		//Compare To: @EntityArrow
		super(worldIn);
	}

	public EntityArrowShatter(World worldIn, double x, double y, double z)
	{
		//Compare To: @EntityArrow
		super(worldIn, x, y, z);
	}

	public EntityArrowShatter(World worldIn, EntityLivingBase shooter, EntityLivingBase p_i1755_3_, float p_i1755_4_, float p_i1755_5_)
	{
		//Compare To: @EntityArrow
		super(worldIn, shooter, p_i1755_3_, p_i1755_4_, p_i1755_5_);
	}

	public EntityArrowShatter(World worldIn, EntityLivingBase shooter, float p_i1756_3_)
	{
		//Compare To: @EntityArrow
		super(worldIn, shooter, p_i1756_3_);
	}

	@Override
	public void onUpdate()
	{
		if (this.inGround)
		{
			super.onUpdate();
			return;
		}

		float scale = 20F;
		Vec3 vec31 = new Vec3(this.posX, this.posY, this.posZ);
		Vec3 vec3 = new Vec3(this.posX + this.motionX * scale, this.posY + this.motionY * scale, this.posZ + this.motionZ * scale);
		MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec31, vec3, false, true, false);

		if (movingobjectposition == null)
		{
			super.onUpdate();
			return;
		}

		BlockPos pos = movingobjectposition.getBlockPos();
		IBlockState block = this.worldObj.getBlockState(pos);

		if (this.isBreakable(block))
		{
			this.breakGlass(this.worldObj, block, pos);

			IBlockState block1;
			if (this.isBreakable(block1 = this.worldObj.getBlockState(pos.up())))
			{
				this.breakGlass(this.worldObj, block1, pos.up());
			}
			if (this.isBreakable(block1 = this.worldObj.getBlockState(pos.down())))
			{
				this.breakGlass(this.worldObj, block, pos.down());
			}
			if (this.isBreakable(block1 = this.worldObj.getBlockState(pos.west())))
			{
				this.breakGlass(this.worldObj, block, pos.west());
			}
			if (this.isBreakable(block1 = this.worldObj.getBlockState(pos.east())))
			{
				this.breakGlass(this.worldObj, block, pos.east());
			}
			if (this.isBreakable(block1 = this.worldObj.getBlockState(pos.north())))
			{
				this.breakGlass(this.worldObj, block, pos.north());
			}
			if (this.isBreakable(block1 = this.worldObj.getBlockState(pos.south())))
			{
				this.breakGlass(this.worldObj, block, pos.south());
			}
			return;
		}

		super.onUpdate();

		if (this.arrowShake <= 0)
		{
			this.inGround = true;
		}
	}

	private boolean isBreakable(IBlockState parBlock)
	{
		return parBlock.getBlock() == Blocks.glass_pane || parBlock.getBlock() == Blocks.stained_glass_pane;
	}

	private void breakGlass(World parWorld, IBlockState parBlock, BlockPos parBlockPos)
	{
		this.worldObj.setBlockToAir(parBlockPos);
		Block block = parBlock.getBlock();
		parWorld.playAuxSFX(2001, parBlockPos, Block.getIdFromBlock(block) + (block.getMetaFromState(parBlock) << 12));
	}
}
