package com.minecraftplus.modLoom;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLoomWheel extends ModelBase
{
	ModelRenderer wheel;

	public ModelLoomWheel()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;

		wheel = new ModelRenderer(this, 0, 40);
		wheel.addBox(-0.5F, -6F, -6F, 1, 12, 12);
		wheel.setRotationPoint(0F, 13F, 0F);
		wheel.setTextureSize(64, 64);
		wheel.mirror = true;
		setRotation(wheel, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		wheel.render(par7);
	}

	private void setRotation(ModelRenderer par1Model, float x, float y, float z)
	{
		par1Model.rotateAngleX = x;
		par1Model.rotateAngleY = y;
		par1Model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	{
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
		wheel.rotateAngleX = par4;
	}
}
