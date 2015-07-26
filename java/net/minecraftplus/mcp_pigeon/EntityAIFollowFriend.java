package net.minecraftplus.mcp_pigeon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAIFollowFriend extends EntityAIBase
{
	private EntityPigeon thePet;
	private EntityLivingBase theFriend;
	private World theWorld;
	private double field_75336_f;
	private PathNavigate petPathfinder;
	private int field_75343_h;
	private float maxDist;
	private float minDist;
	private boolean field_75344_i;

	public EntityAIFollowFriend(EntityPigeon p_i1625_1_, double p_i1625_2_, float p_i1625_4_, float p_i1625_5_)
	{
		this.thePet = p_i1625_1_;
		this.theWorld = p_i1625_1_.worldObj;
		this.field_75336_f = p_i1625_2_;
		this.petPathfinder = p_i1625_1_.getNavigator();
		this.minDist = p_i1625_4_;
		this.maxDist = p_i1625_5_;
		this.setMutexBits(3);

		if (!(p_i1625_1_.getNavigator() instanceof PathNavigateGround))
		{
			throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
		}
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute()
	{
		EntityLivingBase entitylivingbase = this.thePet.getFriendEntity();

		if (entitylivingbase == null)
		{
			return false;
		}
		else if (this.thePet.isSitting())
		{
			return false;
		}
		else if (this.thePet.getDistanceSqToEntity(entitylivingbase) < (double)(this.minDist * this.minDist))
		{
			return false;
		}
		else
		{
			this.theFriend = entitylivingbase;
			return true;
		}
	}

	@Override
	public boolean continueExecuting()
	{
		return !this.petPathfinder.noPath() && this.thePet.getDistanceSqToEntity(this.theFriend) > (double)(this.maxDist * this.maxDist) && !this.thePet.isSitting();
	}

	@Override
	public void startExecuting()
	{
		this.field_75343_h = 0;
		this.field_75344_i = ((PathNavigateGround)this.thePet.getNavigator()).func_179689_e();
		((PathNavigateGround)this.thePet.getNavigator()).func_179690_a(false);
	}

	@Override
	public void resetTask()
	{
		this.theFriend = null;
		this.petPathfinder.clearPathEntity();
		((PathNavigateGround)this.thePet.getNavigator()).func_179690_a(true);
	}

	@Override
	public void updateTask()
	{
		this.thePet.getLookHelper().setLookPositionWithEntity(this.theFriend, 10.0F, (float)this.thePet.getVerticalFaceSpeed());

		if (!this.thePet.isSitting())
		{
			if (--this.field_75343_h <= 0)
			{
				this.field_75343_h = 10;

				if (!this.petPathfinder.tryMoveToEntityLiving(this.theFriend, this.field_75336_f))
				{
					if (!this.thePet.getLeashed())
					{
						if (this.thePet.getDistanceSqToEntity(this.theFriend) >= 144.0D && this.thePet.getDistanceSqToEntity(this.thePet.getOwner()) >= 144.0D)
						{
							int i = MathHelper.floor_double(this.theFriend.posX) - 2;
							int j = MathHelper.floor_double(this.theFriend.posZ) - 2;
							int k = MathHelper.floor_double(this.theFriend.getEntityBoundingBox().minY);

							for (int l = 0; l <= 4; ++l)
							{
								for (int i1 = 0; i1 <= 4; ++i1)
								{
									if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && World.doesBlockHaveSolidTopSurface(this.theWorld, new BlockPos(i + l, k - 1, j + i1)) && !this.theWorld.getBlockState(new BlockPos(i + l, k, j + i1)).getBlock().isFullCube() && !this.theWorld.getBlockState(new BlockPos(i + l, k + 1, j + i1)).getBlock().isFullCube())
									{
										this.thePet.setLocationAndAngles((double)((float)(i + l) + 0.5F), (double)k, (double)((float)(j + i1) + 0.5F), this.thePet.rotationYaw, this.thePet.rotationPitch);
										this.petPathfinder.clearPathEntity();
										return;
									}
								}
							}
						}
					}
				}
			}
		}
	}
}