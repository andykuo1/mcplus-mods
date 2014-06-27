package com.minecraftplus.modTurtle;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import org.lwjgl.opengl.GL11;

public class ModelTurtleCollar extends ModelBase
{
	ModelRenderer head;
	ModelRenderer topShell;

	public ModelTurtleCollar()
	{
		textureWidth = 64;
		textureHeight = 32;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-1.5F, -1.5F, -4F, 3, 3, 4);
		head.setRotationPoint(0F, 21F, -4F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		topShell = new ModelRenderer(this, 40, 8);
		topShell.addBox(-3F, -1F, -3F, 6, 2, 6);
		topShell.setRotationPoint(0F, 18F, 0F);
		topShell.setTextureSize(64, 32);
		topShell.mirror = true;
		setRotation(topShell, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);

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
	public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
	{
		EntityTurtle entityturtle = (EntityTurtle) par1EntityLivingBase;

		if (entityturtle.isSitting())
		{
			head.rotationPointY = 21.5F;
			head.rotationPointZ = -1.5F;
			topShell.rotationPointY = 18.5F;
		}
		else
		{
			head.rotationPointY = 21F;
			head.rotationPointZ = -4F;
			topShell.rotationPointY = 18F;
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	{
		float f6 = (180F / (float)Math.PI);
		this.head.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.head.rotateAngleY = par4 / (180F / (float)Math.PI);
	}
}