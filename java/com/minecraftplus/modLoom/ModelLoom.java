package com.minecraftplus.modLoom;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLoom extends ModelBase
{
	ModelRenderer base;
	ModelRenderer backPanel;
	ModelRenderer leftArmPanel;
	ModelRenderer rightArmPanel;

	public ModelLoom()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;

		base = new ModelRenderer(this, 0, 0);
		base.addBox(-7F, 0F, -8F, 14, 6, 16);
		base.setRotationPoint(0F, 18F, 0F);
		base.setTextureSize(64, 64);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		backPanel = new ModelRenderer(this, 0, 22);
		backPanel.addBox(-8F, -7F, 0F, 16, 14, 2);
		backPanel.setRotationPoint(0F, 17F, 6F);
		backPanel.setTextureSize(64, 64);
		backPanel.mirror = true;
		setRotation(backPanel, 0F, 0F, 0F);
		leftArmPanel = new ModelRenderer(this, 34, 48);
		leftArmPanel.addBox(0F, -2F, 0F, 3, 4, 12);
		leftArmPanel.setRotationPoint(2F, 16F, -5F);
		leftArmPanel.setTextureSize(64, 64);
		leftArmPanel.mirror = true;
		setRotation(leftArmPanel, 0F, 0F, 0F);
		rightArmPanel = new ModelRenderer(this, 34, 48);
		rightArmPanel.addBox(-3F, -2F, 0F, 3, 4, 12);
		rightArmPanel.setRotationPoint(-2F, 16F, -5F);
		rightArmPanel.setTextureSize(64, 64);
		rightArmPanel.mirror = true;
		setRotation(rightArmPanel, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		base.render(par7);
		backPanel.render(par7);
		leftArmPanel.render(par7);
		rightArmPanel.render(par7);
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
		base.render(0.0625F);
		backPanel.render(0.0625F);
		leftArmPanel.render(0.0625F);
		rightArmPanel.render(0.0625F);
	}
}
