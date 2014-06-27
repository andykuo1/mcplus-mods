package com.minecraftplus.modTurtle;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class ModelTurtle extends ModelBase
{
	ModelRenderer head;
	ModelRenderer topShell;
	ModelRenderer middleShell;
	ModelRenderer bottomShell;
	ModelRenderer lfrontLeg;
	ModelRenderer rfrontLeg;
	ModelRenderer lbackLeg;
	ModelRenderer rbackLeg;
	ModelRenderer tail;

	public ModelTurtle()
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
		middleShell = new ModelRenderer(this, 32, 20);
		middleShell.addBox(-4F, -2F, -4F, 8, 4, 8);
		middleShell.setRotationPoint(0F, 20F, 0F);
		middleShell.setTextureSize(64, 32);
		middleShell.mirror = true;
		setRotation(middleShell, 0F, 0F, 0F);
		bottomShell = new ModelRenderer(this, 0, 7);
		bottomShell.addBox(-5F, -4F, 0F, 10, 10, 1);
		bottomShell.setRotationPoint(0F, 22.03333F, -1F);
		bottomShell.setTextureSize(64, 32);
		bottomShell.mirror = true;
		setRotation(bottomShell, 1.570796F, 0F, 0F);
		lfrontLeg = new ModelRenderer(this, 14, 0);
		lfrontLeg.addBox(0F, 0F, -1F, 2, 2, 2);
		lfrontLeg.setRotationPoint(2F, 22F, -3F);
		lfrontLeg.setTextureSize(64, 32);
		lfrontLeg.mirror = true;
		setRotation(lfrontLeg, 0F, 0F, 0F);
		rfrontLeg = new ModelRenderer(this, 22, 0);
		rfrontLeg.addBox(-2F, 0F, -1F, 2, 2, 2);
		rfrontLeg.setRotationPoint(-2F, 22F, -3F);
		rfrontLeg.setTextureSize(64, 32);
		rfrontLeg.mirror = true;
		setRotation(rfrontLeg, 0F, 0F, 0F);
		lbackLeg = new ModelRenderer(this, 14, 0);
		lbackLeg.addBox(0F, 0F, -1F, 2, 2, 2);
		lbackLeg.setRotationPoint(2F, 22F, 3F);
		lbackLeg.setTextureSize(64, 32);
		lbackLeg.mirror = true;
		setRotation(lbackLeg, 0F, 0F, 0F);
		rbackLeg = new ModelRenderer(this, 22, 0);
		rbackLeg.addBox(-2F, 0F, -1F, 2, 2, 2);
		rbackLeg.setRotationPoint(-2F, 22F, 3F);
		rbackLeg.setTextureSize(64, 32);
		rbackLeg.mirror = true;
		setRotation(rbackLeg, 0F, 0F, 0F);
		tail = new ModelRenderer(this, 0, 18);
		tail.addBox(-0.4666667F, 0.2F, 0F, 1, 1, 3);
		tail.setRotationPoint(0F, 21.5F, 3.5F);
		tail.setTextureSize(64, 32);
		tail.mirror = true;
		setRotation(tail, 0F, 0F, 0F);
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
			this.middleShell.render(par7);
			this.bottomShell.render(par7);
			this.lfrontLeg.render(par7);
			this.rfrontLeg.render(par7);
			this.lbackLeg.render(par7);
			this.rbackLeg.render(par7);
			this.tail.render(par7);
			GL11.glPopMatrix();
		}
		else
		{
			this.head.render(par7);
			this.topShell.render(par7);
			this.middleShell.render(par7);
			this.bottomShell.render(par7);
			this.lfrontLeg.render(par7);
			this.rfrontLeg.render(par7);
			this.lbackLeg.render(par7);
			this.rbackLeg.render(par7);
			this.tail.render(par7);
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
			middleShell.rotationPointY = 20.5F;
			bottomShell.rotationPointY = 22.53333F;
			tail.rotationPointZ = 2.5F;
		}
		else
		{
			head.rotationPointY = 21F;
			head.rotationPointZ = -4F;
			topShell.rotationPointY = 18F;
			middleShell.rotationPointY = 20F;
			bottomShell.rotationPointY = 22.03333F;
			tail.rotationPointZ = 3.5F;
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
		this.lfrontLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.rfrontLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.lbackLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.rbackLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.tail.rotateAngleY = MathHelper.cos(par1 * 0.6662F) * 0.8F * par2;
	}
}