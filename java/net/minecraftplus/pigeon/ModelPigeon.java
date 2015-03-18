package net.minecraftplus.pigeon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class ModelPigeon extends ModelBase
{
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightLeg;
	ModelRenderer leftLeg;
	ModelRenderer rightWing;
	ModelRenderer leftWing;
	ModelRenderer beak;

	public ModelPigeon()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;

		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-1F, -4.5F, -2F, 2, 6, 2);
		this.head.setRotationPoint(0F, 18F, -1.5F);
		this.head.setTextureSize(64, 32);
		this.head.mirror = true;
		this.setRotation(this.head, 0F, 0F, 0F);
		this.body = new ModelRenderer(this, 12, 0);
		this.body.addBox(-2F, -3F, -2F, 4, 6, 4);
		this.body.setRotationPoint(0F, 19F, 0F);
		this.body.setTextureSize(64, 32);
		this.body.mirror = true;
		this.setRotation(this.body, 1.570796F, 0F, 0F);
		this.rightLeg = new ModelRenderer(this, 8, 2);
		this.rightLeg.addBox(-0.5F, 0F, -0.5F, 1, 3, 1);
		this.rightLeg.setRotationPoint(-1F, 20F, 0.5F);
		this.rightLeg.setTextureSize(64, 32);
		this.rightLeg.mirror = true;
		this.setRotation(this.rightLeg, 0F, 0F, 0F);
		this.leftLeg = new ModelRenderer(this, 8, 2);
		this.leftLeg.addBox(-0.5F, 0F, -0.5F, 1, 3, 1);
		this.leftLeg.setRotationPoint(1F, 20F, 0.5F);
		this.leftLeg.setTextureSize(64, 32);
		this.leftLeg.mirror = true;
		this.setRotation(this.leftLeg, 0F, 0F, 0F);
		this.rightWing = new ModelRenderer(this, 0, 8);
		this.rightWing.addBox(0F, 0F, 0F, 1, 3, 5);
		this.rightWing.setRotationPoint(-3.2F, 17F, -2F);
		this.rightWing.setTextureSize(64, 32);
		this.rightWing.mirror = true;
		setRotation(this.rightWing, 0F, 0F, 0F);
		this.leftWing = new ModelRenderer(this, 0, 8);
		this.leftWing.addBox(0F, 0F, 0F, 1, 3, 5);
		this.leftWing.setRotationPoint(2.2F, 17F, -2F);
		this.leftWing.setTextureSize(64, 32);
		this.leftWing.mirror = true;
		setRotation(this.leftWing, 0F, 0F, 0F);
		this.beak = new ModelRenderer(this, 8, 0);
		this.beak.addBox(-0.5F, -3.5F, -2.5F, 1, 1, 1);
		this.beak.setRotationPoint(0F, 18F, -1.5F);
		this.beak.setTextureSize(64, 32);
		this.beak.mirror = true;
		this.setRotation(this.beak, 0F, 0F, 0F);
	}

	public void render(Entity par1Entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(par1Entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, par1Entity);
		
		if (((EntityPigeon)par1Entity).getIsFlying())
		{
			GL11.glScalef(1F, 1F, -1F);
		}

		if (this.isChild)
		{
			float f6 = 2.0F;
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, 0.24F + 0.11F, -0.1F);
			GL11.glTranslatef(0.0F, f5, 2*f5);
			GL11.glScalef(0.8F, 0.8F, 0.8F);
			head.render(f5);
			beak.render(f5);
			GL11.glScalef(1.25F, 1.25F, 1.25F);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
			GL11.glTranslatef(0.0F, 24.0F * f5 - 0.2F + 0.26F, 0.0F);
			body.render(f5);
			rightLeg.render(f5);
			leftLeg.render(f5);
			rightWing.render(f5);
			leftWing.render(f5);
			GL11.glPopMatrix();
		}
		else
		{
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, 0.06F, 0.0F);
			head.render(f5);
			body.render(f5);
			rightLeg.render(f5);
			leftLeg.render(f5);
			rightWing.render(f5);
			leftWing.render(f5);
			beak.render(f5);
			GL11.glPopMatrix();
		}
	}

	@Override
	public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
	{
		EntityPigeon entitypigeon = (EntityPigeon) par1EntityLivingBase;

		if (entitypigeon.isSitting())
		{
			head.rotationPointY = 20F;
			body.rotationPointY = 21F;
			rightWing.rotationPointY = 19F;
			leftWing.rotationPointY = 19F;
			beak.rotationPointY = 20F;
		}
		else
		{
			head.rotationPointY = 18F;
			body.rotationPointY = 19F;
			rightWing.rotationPointY = 17F;
			leftWing.rotationPointY = 17F;
			beak.rotationPointY = 18F;
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
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
		this.head.rotateAngleX = par5 / (180F / (float)Math.PI) + MathHelper.cos(par1) * 0.1F;
		this.head.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.beak.rotateAngleX = this.head.rotateAngleX;
		this.beak.rotateAngleY = this.head.rotateAngleY;
		this.beak.rotateAngleZ = this.head.rotateAngleZ;
		this.body.rotateAngleX = ((float)Math.PI / 2F);
		this.rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;

		if (!par7Entity.onGround || ((EntityPigeon) par7Entity).isInWater() || ((EntityPigeon) par7Entity).getIsFlying())
		{
			this.rightWing.rotateAngleZ = MathHelper.cos(par3 * 1.2F) * 1F + 1F;
			this.leftWing.rotateAngleZ = -MathHelper.cos(par3 * 1.2F) * 1F - 1F;
		}
		else
		{
			this.rightWing.rotateAngleZ = MathHelper.cos(par1) * 0.2F + 0.2F;
			this.leftWing.rotateAngleZ = -MathHelper.cos(par1) * 0.2F - 0.2F;
		}
	}
}