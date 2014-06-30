package com.minecraftplus.modSaw;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSaw extends ModelBase
{
	private ModelRenderer block;
	private ModelRenderer edge1;
	private ModelRenderer edge2;
	private ModelRenderer blade1;
	private ModelRenderer blade2;

	public ModelSaw()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;

		this.block = new ModelRenderer(this, 0, 17);
		this.block.addBox(-7F, -7F, -11F, 14, 14, 14);
		this.block.setRotationPoint(0F, 17F, 4F);
		this.block.setTextureSize(64, 64);
		this.block.mirror = true;
		this.setRotation(this.block, 0F, 0F, 0F);
		this.edge1 = new ModelRenderer(this, 0, 0);
		this.edge1.addBox(-8F, -1F, -8F, 16, 1, 16);
		this.edge1.setRotationPoint(0F, 14F, 0F);
		this.edge1.setTextureSize(64, 64);
		this.edge1.mirror = true;
		this.setRotation(this.edge1, 0F, 0F, 0F);
		this.edge2 = new ModelRenderer(this, 0, 0);
		this.edge2.addBox(-8F, -1F, -8F, 16, 1, 16);
		this.edge2.setRotationPoint(0F, 21F, 0F);
		this.edge2.setTextureSize(64, 64);
		this.edge2.mirror = true;
		this.setRotation(this.edge2, 0F, 0F, 0F);
		this.blade1 = new ModelRenderer(this, 0, 44);
		this.blade1.addBox(0F, -5F, -5F, 0, 10, 10);
		this.blade1.setRotationPoint(3F, 10F, 0F);
		this.blade1.setTextureSize(64, 64);
		this.blade1.mirror = true;
		this.setRotation(this.blade1, 0F, 0F, 0F);
		this.blade1 = new ModelRenderer(this, 0, 44);
		this.blade1.addBox(0F, -5F, -5F, 0, 10, 10);
		this.blade1.setRotationPoint(-3F, 10F, 0F);
		this.blade1.setTextureSize(64, 64);
		this.blade1.mirror = true;
		setRotation(this.blade1, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		block.render(par7);
		edge1.render(par7);
		edge2.render(par7);
		blade1.render(par7);
		blade2.render(par7);
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
	}

	public void doRender()
	{
		block.render(0.0625F);
		edge1.render(0.0625F);
		edge2.render(0.0625F);
		blade1.render(0.0625F);
		blade2.render(0.0625F);
	}
}
