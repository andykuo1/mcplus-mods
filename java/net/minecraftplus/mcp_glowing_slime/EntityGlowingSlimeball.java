package net.minecraftplus.mcp_glowing_slime;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityGlowingSlimeball extends EntityThrowable
{
	public EntityGlowingSlimeball(World worldIn)
	{
		super(worldIn);
	}

	public EntityGlowingSlimeball(World worldIn, EntityLivingBase p_i1774_2_)
	{
		super(worldIn, p_i1774_2_);
	}

	public EntityGlowingSlimeball(World worldIn, double x, double y, double z)
	{
		super(worldIn, x, y, z);
	}

	@Override
	protected void onImpact(MovingObjectPosition movingobjpos)
	{
		if (movingobjpos.entityHit != null)
		{
			movingobjpos.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)0);
		}
		
		if (this.worldObj.isRemote)
		{
			//@EntitySnowball
			for (int i = 0; i < 8; ++i)
			{
				this.worldObj.spawnParticle(EnumParticleTypes.SLIME, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
			}
			return;
		}

		if (movingobjpos.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
		{
			BlockPos hitPos = movingobjpos.getBlockPos().offset(movingobjpos.sideHit);
			IBlockState state = _Glowing_Slime.glowingSlime.getDefaultState().withProperty(BlockGlowingSlime.FACING, movingobjpos.sideHit);
			if (_Glowing_Slime.glowingSlime.canPlaceBlockOnSide(this.worldObj, hitPos, movingobjpos.sideHit))
			{
				this.worldObj.setBlockState(hitPos, state);
			}
			else
			{
				this.worldObj.spawnEntityInWorld(new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(_Glowing_Slime.glowingSlimeball)));
			}
		}

		this.setDead();
	}

}
