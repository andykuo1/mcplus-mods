package net.minecraftplus.mcp_pigeon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.util.MathHelper;

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
		this.head.setRotationPoint(0F, 20F, -1.5F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);

		if (((EntityTameable)entity).isSitting())
		{
			this.head.rotationPointY = 20F;
		}
		else
		{
			this.head.rotationPointY = 18F;
		}

		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		if (this.isChild)
		{
			GlStateManager.pushMatrix();
			GlStateManager.translate(0.0F, -f5 * this.head.rotationPointY, -f5 * -this.head.rotationPointZ);
			GlStateManager.scale(0.6F, 0.6F, 0.6F);
			GlStateManager.translate(0.0F, f5 * this.head.rotationPointY, f5 * -this.head.rotationPointZ);
			GlStateManager.translate(0.0F, this.head.rotationPointY / 11F, 0.0F);

			GlStateManager.translate(0.0F, 0.24F + 0.11F, -0.1F);
			GlStateManager.translate(0.0F, f5, 2*f5);
			this.head.render(f5);
			GlStateManager.popMatrix();
		}
		else
		{
			GlStateManager.pushMatrix();

			GlStateManager.translate(0.0F, -f5 * this.head.rotationPointY, -f5 * -this.head.rotationPointZ);
			GlStateManager.scale(0.6F, 0.6F, 0.6F);
			GlStateManager.translate(0.0F, f5 * this.head.rotationPointY, f5 * -this.head.rotationPointZ);

			GlStateManager.translate(0.0F, this.head.rotationPointY / 11F, 0.0F);
			this.head.render(f5);
			GlStateManager.popMatrix();
		}
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	{
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
		this.head.rotateAngleX = par5 / (180F / (float)Math.PI) + MathHelper.cos(par1) * 0.1F;
		this.head.rotateAngleY = par4 / (180F / (float)Math.PI);
	}
}