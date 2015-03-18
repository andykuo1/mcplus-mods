package net.minecraftplus.pigeon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class ModelPigeonHead extends ModelBase
{
	private ModelRenderer head;

	public ModelPigeonHead()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;

		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-2F, -9F, -4F, 4, 12, 4);
		this.head.setTextureSize(64, 32);
		this.head.setRotationPoint(0F, 18F, -1.5F);
		this.head.mirror = true;
		this.setRotation(this.head, 0F, 0F, 0F);
	}

	public void render(Entity par1Entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(par1Entity, f, f1, f2, f3, f4, f5);
		
		this.setRotationAngles(f, f1, f2, f3, f4, f5, par1Entity);

		if (this.isChild)
		{
			GL11.glPushMatrix();
			
			GL11.glTranslatef(0.0F, -f5 * this.head.rotationPointY, -f5 * -this.head.rotationPointZ);
			GL11.glScalef(0.6F, 0.6F, 0.6F);
			GL11.glTranslatef(0.0F, f5 * this.head.rotationPointY, f5 * -this.head.rotationPointZ);
			GL11.glTranslatef(0.0F, this.head.rotationPointY / 11F, 0.0F);
			
			GL11.glTranslatef(0.0F, 0.24F + 0.11F, -0.1F);
			GL11.glTranslatef(0.0F, f5, 2*f5);
			this.head.render(f5);
			GL11.glPopMatrix();
		}
		else
		{
			GL11.glPushMatrix();
			
			GL11.glTranslatef(0.0F, -f5 * this.head.rotationPointY, -f5 * -this.head.rotationPointZ);
			GL11.glScalef(0.6F, 0.6F, 0.6F);
			GL11.glTranslatef(0.0F, f5 * this.head.rotationPointY, f5 * -this.head.rotationPointZ);

			GL11.glTranslatef(0.0F, this.head.rotationPointY / 11F, 0.0F);
			this.head.render(f5);
			GL11.glPopMatrix();
		}
	}

	@Override
	public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
	{
		EntityPigeon entitypigeon = (EntityPigeon) par1EntityLivingBase;

		if (entitypigeon.isSitting())
		{
			this.head.rotationPointY = 20F;
		}
		else
		{
			this.head.rotationPointY = 18F;
		}
	}

	private void setRotation(ModelRenderer parModel, float parX, float parY, float parZ)
	{
		parModel.rotateAngleX = parX;
		parModel.rotateAngleY = parY;
		parModel.rotateAngleZ = parZ;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	{
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
		this.head.rotateAngleX = par5 / (180F / (float)Math.PI) + MathHelper.cos(par1) * 0.1F;
		this.head.rotateAngleY = par4 / (180F / (float)Math.PI);
	}
}