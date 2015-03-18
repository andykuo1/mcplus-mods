package net.minecraftplus.pigeon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

@Deprecated
public class EntityPigeonMail extends EntityPigeon
{
	protected Entity primeTarget;
	
	public EntityPigeonMail(World parWorld)
	{
		super(parWorld);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(22, new String(""));
	}

	public boolean isSending()
	{
		return this.getPigeonBoolean(8);
	}

	public void setSending(boolean parIsSending)
	{
		this.setPigeonBoolean(8, parIsSending);
	}
	
	public String getAddress()
	{
		return this.dataWatcher.getWatchableObjectString(22);
	}

	public void setAddress(String parName)
	{
		this.dataWatcher.updateObject(22, parName);
	}

	protected EntityPlayer findValidAddress()
	{
		ItemStack itemstack = this.pigeonChest.getStackInSlot(1);
		if (itemstack != null && itemstack.hasDisplayName())
		{
			String playername = itemstack.getDisplayName();
			EntityPlayer player = this.worldObj.getPlayerEntityByName(playername);
			return player;
		}

		return null;
	}
	
	@Override
	protected void updatePigeonBooleans()
	{
		super.updatePigeonBooleans();
		if (!this.worldObj.isRemote)
		{
			this.setSending(this.pigeonChest.getStackInSlot(1) != null);
		}
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.primeTarget != null)
		{
			if (this.primeTarget.isDead)
			{
				this.primeTarget = null;
				return;
			}
			else
			{
				double dist = this.getDistanceSqToEntity(this.primeTarget);

				if (dist < 5D)
				{
					this.primeTarget = null;
				}
				else
				{
					int i = MathHelper.floor_double(this.primeTarget.posX) - 2;
					int j = MathHelper.floor_double(this.primeTarget.posZ) - 2;
					int k = MathHelper.floor_double(this.primeTarget.boundingBox.minY);

					for (int l = 0; l <= 4; ++l)
					{
						for (int i1 = 0; i1 <= 4; ++i1)
						{
							if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && World.doesBlockHaveSolidTopSurface(this.worldObj, i + l, k - 1, j + i1) && !this.worldObj.getBlock(i + l, k, j + i1).isNormalCube() && !this.worldObj.getBlock(i + l, k + 1, j + i1).isNormalCube())
							{
								this.setLocationAndAngles((double)((float)(i + l) + 0.5F), (double)k, (double)((float)(j + i1) + 0.5F), this.rotationYaw, this.rotationPitch);
								this.primeTarget = null;
								return;
							}
						}
					}
				}
			}
		}
		else
		{
			super.onLivingUpdate();
		}
	}
	
	@Override
	public void onInventoryChanged(InventoryBasic parInventory)
	{
		super.onInventoryChanged(parInventory);
		ItemStack itemstack = parInventory.getStackInSlot(1);
		if (itemstack != null)
		{
			this.setAddress(itemstack.getDisplayName());
		}
	}

	public boolean sendTo(EntityPlayer parEntityPlayer)
	{
		if (parEntityPlayer != null)
		{
			this.primeTarget = this.target = parEntityPlayer;

			this.setSending(true);
			return true;
		}

		this.setSending(false);
		return false;
	}
}
