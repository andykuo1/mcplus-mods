package net.minecraftplus.mcp_clippers;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Items;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftplus._api.dictionary.Sounds;
import net.minecraftplus._api.minecraft.util.EntityLivingUtil;

public class EntityChickenNaked extends EntityChicken
{
	//Compare to: @EntitySheep
	/**Used to control movement as well as feather regrowth. Set to 40 on handleHealthUpdate and counts down with each tick.*/
	private int chickenTimer;
	private EntityAIEatGrass entityAIEatGrass = new EntityAIEatGrass(this);

	public EntityChickenNaked(World worldIn)
	{
		super(worldIn);
		this.tasks.addTask(4, this.entityAIEatGrass);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.worldObj.playSoundAtEntity(this, Sounds.MOB_SHEEP_SHEAR, 1.0F, 1.0F);
	}

	@Override
	protected void updateAITasks()
	{
		//Compare to: @EntitySheep
		this.chickenTimer = this.entityAIEatGrass.getEatingGrassTimer();
		super.updateAITasks();
	}

	@Override
	public void onLivingUpdate()
	{
		//Compare To: @EntitySheep
		if (this.worldObj.isRemote)
		{
			this.chickenTimer = Math.max(0, this.chickenTimer - 1);
		}

		super.onLivingUpdate();
	}

	@Override
	protected void dropFewItems(boolean parIsKilled, int parFortune)
	{
		//Compare to: @EntityChicken
		int j = this.rand.nextInt(3) + this.rand.nextInt(1 + parFortune);

		if (this.isBurning())
		{
			this.dropItem(Items.cooked_chicken, 1);
		}
		else
		{
			this.dropItem(Items.chicken, 1);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void handleHealthUpdate(byte p_70103_1_)
	{
		//Compare to: @EntitySheep
		if (p_70103_1_ == 10)
		{
			this.chickenTimer = 40;
		}
		else
		{
			super.handleHealthUpdate(p_70103_1_);
		}
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationPointY(float p_70894_1_)
	{
		//Compare to: @EntitySheep
		return this.chickenTimer <= 0 ? 0.0F : (this.chickenTimer >= 4 && this.chickenTimer <= 36 ? 1.0F : (this.chickenTimer < 4 ? ((float)this.chickenTimer - p_70894_1_) / 4.0F : -((float)(this.chickenTimer - 40) - p_70894_1_) / 4.0F));
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationAngleX(float p_70890_1_)
	{
		//Compare to: @EntitySheep
		if (this.chickenTimer > 4 && this.chickenTimer <= 36)
		{
			float f1 = ((float)(this.chickenTimer - 4) - p_70890_1_) / 32.0F;
			return ((float)Math.PI / 5F) + ((float)Math.PI * 7F / 100F) * MathHelper.sin(f1 * 28.7F);
		}
		else
		{
			return this.chickenTimer > 0 ? ((float)Math.PI / 5F) : this.rotationPitch / (180F / (float)Math.PI);
		}
	}

	/**
	 * This function applies the benefits of growing back wool and faster growing up to the acting entity. (This
	 * function is used in the AIEatGrass)
	 */
	public void eatGrassBonus()
	{
		//Compare to: @EntitySheep
		EntityLivingUtil.replace(this, new EntityChicken(this.worldObj));
	}
}
