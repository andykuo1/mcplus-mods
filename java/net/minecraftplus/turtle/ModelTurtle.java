package net.minecraftplus.turtle;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class ModelTurtle extends ModelBase
{
	private ModelRenderer head;
	private ModelRenderer topShell;
	private ModelRenderer middleShell;
	private ModelRenderer bottomShell;
	private ModelRenderer lfrontLeg;
	private ModelRenderer rfrontLeg;
	private ModelRenderer lbackLeg;
	private ModelRenderer rbackLeg;
	private ModelRenderer tail;

	public ModelTurtle()
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
		this.middleShell = new ModelRenderer(this, 32, 20);
		this.middleShell.addBox(-4F, -2F, -4F, 8, 4, 8);
		this.middleShell.setRotationPoint(0F, 20F, 0F);
		this.middleShell.setTextureSize(64, 32);
		this.middleShell.mirror = true;
		this.setRotation(this.middleShell, 0F, 0F, 0F);
		this.bottomShell = new ModelRenderer(this, 0, 7);
		this.bottomShell.addBox(-5F, -4F, 0F, 10, 10, 1);
		this.bottomShell.setRotationPoint(0F, 22.03333F, -1F);
		this.bottomShell.setTextureSize(64, 32);
		this.bottomShell.mirror = true;
		this.setRotation(this.bottomShell, 1.570796F, 0F, 0F);
		this.lfrontLeg = new ModelRenderer(this, 14, 0);
		this.lfrontLeg.addBox(0F, 0F, -1F, 2, 2, 2);
		this.lfrontLeg.setRotationPoint(2F, 22F, -3F);
		this.lfrontLeg.setTextureSize(64, 32);
		this.lfrontLeg.mirror = true;
		this.setRotation(this.lfrontLeg, 0F, 0F, 0F);
		this.rfrontLeg = new ModelRenderer(this, 22, 0);
		this.rfrontLeg.addBox(-2F, 0F, -1F, 2, 2, 2);
		this.rfrontLeg.setRotationPoint(-2F, 22F, -3F);
		this.rfrontLeg.setTextureSize(64, 32);
		this.rfrontLeg.mirror = true;
		this.setRotation(this.rfrontLeg, 0F, 0F, 0F);
		this.lbackLeg = new ModelRenderer(this, 14, 0);
		this.lbackLeg.addBox(0F, 0F, -1F, 2, 2, 2);
		this.lbackLeg.setRotationPoint(2F, 22F, 3F);
		this.lbackLeg.setTextureSize(64, 32);
		this.lbackLeg.mirror = true;
		this.setRotation(this.lbackLeg, 0F, 0F, 0F);
		this.rbackLeg = new ModelRenderer(this, 22, 0);
		this.rbackLeg.addBox(-2F, 0F, -1F, 2, 2, 2);
		this.rbackLeg.setRotationPoint(-2F, 22F, 3F);
		this.rbackLeg.setTextureSize(64, 32);
		this.rbackLeg.mirror = true;
		this.setRotation(this.rbackLeg, 0F, 0F, 0F);
		this.tail = new ModelRenderer(this, 0, 18);
		this.tail.addBox(-0.4666667F, 0.2F, 0F, 1, 1, 3);
		this.tail.setRotationPoint(0F, 21.5F, 3.5F);
		this.tail.setTextureSize(64, 32);
		this.tail.mirror = true;
		this.setRotation(this.tail, 0F, 0F, 0F);
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
	public void setLivingAnimations(EntityLivingBase parEntity, float par2, float par3, float par4)
	{
		EntityTurtle entityturtle = (EntityTurtle)parEntity;

		if (entityturtle.isSitting())
		{
			this.head.rotationPointY = 21.5F;
			this.head.rotationPointZ = -1.5F;
			this.topShell.rotationPointY = 18.5F;
			this.middleShell.rotationPointY = 20.5F;
			this.bottomShell.rotationPointY = 22.53333F;
			this.tail.rotationPointZ = 2.5F;
		}
		else
		{
			this.head.rotationPointY = 21F;
			this.head.rotationPointZ = -4F;
			this.topShell.rotationPointY = 18F;
			this.middleShell.rotationPointY = 20F;
			this.bottomShell.rotationPointY = 22.03333F;
			this.tail.rotationPointZ = 3.5F;
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
		this.lfrontLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.rfrontLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.lbackLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.rbackLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.tail.rotateAngleY = MathHelper.cos(par1 * 0.6662F) * 0.8F * par2;
	}
}