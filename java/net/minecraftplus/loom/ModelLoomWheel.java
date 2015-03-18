package net.minecraftplus.loom;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLoomWheel extends ModelBase
{
	private ModelRenderer wheel;

	public ModelLoomWheel()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;

		this.wheel = new ModelRenderer(this, 0, 40);
		this.wheel.addBox(-0.5F, -6F, -6F, 1, 12, 12);
		this.wheel.setRotationPoint(0F, 13F, 0F);
		this.wheel.setTextureSize(64, 64);
		this.wheel.mirror = true;
		setRotation(this.wheel, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		this.wheel.render(par7);
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	{
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
		this.wheel.rotateAngleX = par4;
	}

	private void setRotation(ModelRenderer parModel, float parX, float parY, float parZ)
	{
		parModel.rotateAngleX = parX;
		parModel.rotateAngleY = parY;
		parModel.rotateAngleZ = parZ;
	}
}
