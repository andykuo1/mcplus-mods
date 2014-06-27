package com.minecraftplus.modRabbit;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class ModelRabbit extends ModelBase
{
	ModelRenderer a;
	ModelRenderer body;
	ModelRenderer tail;
	ModelRenderer e1;
	ModelRenderer e2;
	ModelRenderer ff1;
	ModelRenderer ff2;
	ModelRenderer g;
	ModelRenderer g2;
	ModelRenderer h;
	ModelRenderer h2;

	public ModelRabbit()
	{
		byte byte0 = 16;
		this.a = new ModelRenderer(this, 0, 0);
		this.a.addBox(-2.0F, -1.0F, -4.0F, 4, 4, 6, 0.0F);
		this.a.setRotationPoint(0.0F, -1 + byte0, -4.0F);

		this.g = new ModelRenderer(this, 14, 0);
		this.g.addBox(-2.0F, -5.0F, -3.0F, 1, 4, 2, 0.0F);
		this.g.setRotationPoint(0.0F, -1 + byte0, -4.0F);
		this.g2 = new ModelRenderer(this, 14, 0);
		this.g2.addBox(1.0F, -5.0F, -3.0F, 1, 4, 2, 0.0F);
		this.g2.setRotationPoint(0.0F, -1 + byte0, -4.0F);

		this.h = new ModelRenderer(this, 20, 0);
		this.h.addBox(-4.0F, 0.0F, -3.0F, 2, 3, 2, 0.0F);
		this.h.setRotationPoint(0.0F, -1 + byte0, -4.0F);
		this.h2 = new ModelRenderer(this, 20, 0);
		this.h2.addBox(2.0F, 0.0F, -3.0F, 2, 3, 2, 0.0F);
		this.h2.setRotationPoint(0.0F, -1 + byte0, -4.0F);

		this.body = new ModelRenderer(this, 0, 10);
		this.body.addBox(-3.0F, -4.0F, -3.0F, 6, 8, 6, 0.0F);
		this.body.setRotationPoint(0.0F, 0 + byte0, 0.0F);

		this.tail = new ModelRenderer(this, 0, 24);
		this.tail.addBox(-2.0F, 4.0F, -2.0F, 4, 3, 4, 0.0F);
		this.tail.setRotationPoint(0.0F, 0 + byte0, 0.0F);

		this.e1 = new ModelRenderer(this, 24, 16);
		this.e1.addBox(-2.0F, 0.0F, -1.0F, 2, 2, 2);
		this.e1.setRotationPoint(3.0F, 3 + byte0, -3.0F);
		this.e2 = new ModelRenderer(this, 24, 16);
		this.e2.addBox(0.0F, 0.0F, -1.0F, 2, 2, 2);
		this.e2.setRotationPoint(-3.0F, 3 + byte0, -3.0F);

		this.ff1 = new ModelRenderer(this, 16, 24);
		this.ff1.addBox(-2.0F, 0.0F, -4.0F, 2, 2, 4);
		this.ff1.setRotationPoint(3.0F, 3 + byte0, 4.0F);
		this.ff2 = new ModelRenderer(this, 16, 24);
		this.ff2.addBox(0.0F, 0.0F, -4.0F, 2, 2, 4);
		this.ff2.setRotationPoint(-3.0F, 3 + byte0, 4.0F);
	}

	public void render(Entity par1Entity, float f1, float f2, float f3, float f4, float f5, float f6)
	{
		super.render(par1Entity, f1, f2, f3, f4, f5, f6);
		this.setRotationAngles(f1, f2, f3, f4, f5, f6, par1Entity);

		if (this.isChild)
		{
			float f7 = 2.0F;
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, 0.2F, 0.0F);
			GL11.glTranslatef(0.0F, f6, 2*f6);
			this.a.render(f6);
			this.g.render(f6);
			this.g2.render(f6);
			this.h.render(f6);
			this.h2.render(f6);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glScalef(1.0F / f7, 1.0F / f7, 1.0F / f7);
			GL11.glTranslatef(0.0F, 24.0F * f6 + 0.1F, 0.0F);
			this.body.render(f6);
			this.tail.render(f6);
			this.e1.render(f6);
			this.e2.render(f6);
			this.ff1.render(f6);
			this.ff2.render(f6);
			GL11.glPopMatrix();
		}
		else
		{
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, 0.2F, 0.0F);
			this.a.render(f6);
			this.g.render(f6);
			this.g2.render(f6);
			this.h.render(f6);
			this.h2.render(f6);
			this.body.render(f6);
			this.tail.render(f6);
			this.e1.render(f6);
			this.e2.render(f6);
			this.ff1.render(f6);
			this.ff2.render(f6);
			GL11.glPopMatrix();
		}
	}

	@Override
	public void setRotationAngles(float f1, float f2, float f3, float f4, float f5, float f6, Entity par7Entity)
	{
		this.a.rotateAngleX = f5 / 57.29578F;
		this.a.rotateAngleY = f4 / 57.29578F;
		this.g.rotateAngleX = this.a.rotateAngleX;
		this.g.rotateAngleY = this.a.rotateAngleY;
		this.g2.rotateAngleX = this.a.rotateAngleX;
		this.g2.rotateAngleY = this.a.rotateAngleY;
		this.h.rotateAngleX = this.a.rotateAngleX;
		this.h.rotateAngleY = this.a.rotateAngleY;
		this.h2.rotateAngleX = this.a.rotateAngleX;
		this.h2.rotateAngleY = this.a.rotateAngleY;
		this.body.rotateAngleX = 1.570796F;
		this.tail.rotateAngleX = 1.570796F;
		this.e1.rotateAngleX = (MathHelper.sin(f1 * 0.6662F) * 1.0F * f2);
		this.ff1.rotateAngleX = (MathHelper.cos((float) (f1 * 0.6662F + Math.PI)) * 0.4F * f2);
		this.e2.rotateAngleX = (MathHelper.cos(f1 * 0.6662F) * 1.0F * f2);
		this.ff2.rotateAngleX = (MathHelper.sin((float) (f1 * 0.6662F + Math.PI)) * 0.4F * f2);
	}
}