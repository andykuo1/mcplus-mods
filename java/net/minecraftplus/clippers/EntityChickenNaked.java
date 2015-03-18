package net.minecraftplus.clippers;

import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Items;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityChickenNaked extends EntityChicken
{
	/**
	 * Used to control movement as well as feather regrowth. Set to 40 on handleHealthUpdate and counts down with each
	 * tick.
	 */
	private int chickenTimer;

	/** The eat grass AI task for this mob. */
	private EntityAIEatGrass aiEatGrass = new EntityAIEatGrass(this);

	public EntityChickenNaked(World par1World)
	{
		super(par1World);
		this.tasks.addTask(4, this.aiEatGrass);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.worldObj.playSoundAtEntity(this, "mob.sheep.shear", 1.0F, 1.0F);
	}

	@Override
	protected void updateAITasks()
	{
		this.chickenTimer = this.aiEatGrass.func_151499_f();
		super.updateAITasks();
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.worldObj.isRemote)
		{
			this.chickenTimer = Math.max(0, this.chickenTimer - 1);
		}

		super.onLivingUpdate();
	}

	@SideOnly(Side.CLIENT)
	public float func_70894_j(float par1)
	{
		return this.chickenTimer <= 0 ? 0.0F : (this.chickenTimer >= 4 && this.chickenTimer <= 36 ? 1.0F : (this.chickenTimer < 4 ? ((float)this.chickenTimer - par1) / 4.0F : -((float)(this.chickenTimer - 40) - par1) / 4.0F));
	}

	@SideOnly(Side.CLIENT)
	public float func_70890_k(float par1)
	{
		if (this.chickenTimer > 4 && this.chickenTimer <= 36)
		{
			float f1 = ((float)(this.chickenTimer - 4) - par1) / 32.0F;
			return ((float)Math.PI / 5F) + ((float)Math.PI * 7F / 100F) * MathHelper.sin(f1 * 28.7F);
		}
		else
		{
			return this.chickenTimer > 0 ? ((float)Math.PI / 5F) : this.rotationPitch / (180F / (float)Math.PI);
		}
	}

	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		int j = this.rand.nextInt(3) + this.rand.nextInt(1 + par2);

		if (this.isBurning())
		{
			this.dropItem(Items.cooked_chicken, 1);
		}
		else
		{
			this.dropItem(Items.chicken, 1);
		}
	}

	@Override
	public void eatGrassBonus()
	{
		ItemClippers.replaceEntity(this, new EntityChicken(this.worldObj));
	}
}
