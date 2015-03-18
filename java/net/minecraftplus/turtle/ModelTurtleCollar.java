package net.minecraftplus.turtle;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import org.lwjgl.opengl.GL11;

public class ModelTurtleCollar extends ModelBase
{
	private ModelRenderer head;
	private ModelRenderer topShell;

	public ModelTurtleCollar()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;

		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-1.5F, -1.5F, -4F, 3, 3, 4);
		this.head.setRotationPoint(0F, 21F, -4F);
		this.head.setTextureSize(64, 32);
		this.head.mirror = true;
		this.setRotation(this.head, 0F, 0F, 0F);
		this.topShell = new ModelRenderer(this, 40, 8);
		this.topShell.addBox(-3F, -1F, -3F, 6, 2, 6);
		this.topShell.setRotationPoint(0F, 18F, 0F);
		this.topShell.setTextureSize(64, 32);
		this.topShell.mirror = true;
		this.setRotation(this.topShell, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity parEntity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		this.setRotationAngles(par2, par3, par4, par5, par6, par7, parEntity);

		if (this.isChild)
		{
			float f6 = 2.0F;
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, par7, 2*par7);
			this.head.render(par7);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
			GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
			this.topShell.render(par7);
			GL11.glPopMatrix();
		}
		else
		{
			this.head.render(par7);
			this.topShell.render(par7);
		}
	}

	@Override
	public void setLivingAnimations(EntityLivingBase parEntity, float par2, float par3, float par4)
	{
		EntityTurtle entityturtle = (EntityTurtle)parEntity;

		if (entityturtle.isSitting())
		{
			this.head.rotationPointY = 21.5F;
			this.head.rotationPointZ = -1.5F;
			this.topShell.rotationPointY = 18.5F;
		}
		else
		{
			this.head.rotationPointY = 21F;
			this.head.rotationPointZ = -4F;
			this.topShell.rotationPointY = 18F;
		}
	}

	private void setRotation(ModelRenderer parModel, float parX, float parY, float parZ)
	{
		parModel.rotateAngleX = parX;
		parModel.rotateAngleY = parY;
		parModel.rotateAngleZ = parZ;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity parEntity)
	{
		float f6 = (180F / (float)Math.PI);
		this.head.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.head.rotateAngleY = par4 / (180F / (float)Math.PI);
	}
}