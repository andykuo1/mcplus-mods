package net.minecraftplus.loom;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLoom extends ModelBase
{
	private ModelRenderer base;
	private ModelRenderer backPanel;
	private ModelRenderer leftArmPanel;
	private ModelRenderer rightArmPanel;

	public ModelLoom()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;

		this.base = new ModelRenderer(this, 0, 0);
		this.base.addBox(-7F, 0F, -8F, 14, 6, 16);
		this.base.setRotationPoint(0F, 18F, 0F);
		this.base.setTextureSize(64, 64);
		this.base.mirror = true;
		this.setRotation(this.base, 0F, 0F, 0F);
		this.backPanel = new ModelRenderer(this, 0, 22);
		this.backPanel.addBox(-8F, -7F, 0F, 16, 14, 2);
		this.backPanel.setRotationPoint(0F, 17F, 6F);
		this.backPanel.setTextureSize(64, 64);
		this.backPanel.mirror = true;
		this.setRotation(this.backPanel, 0F, 0F, 0F);
		this.leftArmPanel = new ModelRenderer(this, 34, 48);
		this.leftArmPanel.addBox(0F, -2F, 0F, 3, 4, 12);
		this.leftArmPanel.setRotationPoint(2F, 16F, -5F);
		this.leftArmPanel.setTextureSize(64, 64);
		this.leftArmPanel.mirror = true;
		this.setRotation(this.leftArmPanel, 0F, 0F, 0F);
		this.rightArmPanel = new ModelRenderer(this, 34, 48);
		this.rightArmPanel.addBox(-3F, -2F, 0F, 3, 4, 12);
		this.rightArmPanel.setRotationPoint(-2F, 16F, -5F);
		this.rightArmPanel.setTextureSize(64, 64);
		this.rightArmPanel.mirror = true;
		this.setRotation(this.rightArmPanel, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		this.base.render(par7);
		this.backPanel.render(par7);
		this.leftArmPanel.render(par7);
		this.rightArmPanel.render(par7);
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	{
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
	}

	public void doRender()
	{
		this.base.render(0.0625F);
		this.backPanel.render(0.0625F);
		this.leftArmPanel.render(0.0625F);
		this.rightArmPanel.render(0.0625F);
	}
	
	private void setRotation(ModelRenderer parModel, float parX, float parY, float parZ)
	{
		parModel.rotateAngleX = parX;
		parModel.rotateAngleY = parY;
		parModel.rotateAngleZ = parZ;
	}
}
