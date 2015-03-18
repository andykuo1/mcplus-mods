package net.minecraftplus.firepit;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFirePit extends ModelBase
{
	private ModelRenderer basePit;
	private ModelRenderer rockLong1;
	private ModelRenderer rockLong2;
	private ModelRenderer rockLong3;
	private ModelRenderer rockShort1;
	private ModelRenderer rockShort2;

	public ModelFirePit()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;

		this.basePit = new ModelRenderer(this, 0, 4);
		this.basePit.addBox(-6F, 0F, -6F, 12, 0, 12);
		this.basePit.setRotationPoint(0F, 23.8F, 0F);
		this.basePit.setTextureSize(64, 32);
		this.basePit.mirror = true;
		this.setRotation(basePit, 0F, 0F, 0F);
		this.rockLong1 = new ModelRenderer(this, 10, 0);
		this.rockLong1.addBox(-1F, 0F, -1F, 2, 1, 2);
		this.rockLong1.setRotationPoint(-3.5F, 23F, 3F);
		this.rockLong1.setTextureSize(64, 32);
		this.rockLong1.mirror = true;
		this.setRotation(rockLong1, 0F, 0.669215F, 0F);
		this.rockLong2 = new ModelRenderer(this, 0, 0);
		this.rockLong2.addBox(-1F, 0F, -1F, 2, 1, 3);
		this.rockLong2.setRotationPoint(-3F, 23F, -3F);
		this.rockLong2.setTextureSize(64, 32);
		this.rockLong2.mirror = true;
		this.setRotation(rockLong2, 0F, -0.9666439F, 0F);
		this.rockLong3 = new ModelRenderer(this, 0, 0);
		this.rockLong3.addBox(-1F, 0F, -1F, 2, 1, 3);
		this.rockLong3.setRotationPoint(3F, 23F, -3F);
		this.rockLong3.setTextureSize(64, 32);
		this.rockLong3.mirror = true;
		this.setRotation(rockLong3, 0F, 0.4089647F, 0F);
		this.rockShort1 = new ModelRenderer(this, 0, 0);
		this.rockShort1.addBox(-1F, 0F, -1F, 2, 1, 3);
		this.rockShort1.setRotationPoint(2F, 23.2F, 3F);
		this.rockShort1.setTextureSize(64, 32);
		this.rockShort1.mirror = true;
		this.setRotation(rockShort1, 0F, 1.858931F, 0F);
		this.rockShort2 = new ModelRenderer(this, 10, 0);
		this.rockShort2.addBox(-1F, 0F, -1F, 2, 1, 2);
		this.rockShort2.setRotationPoint(0F, 23.4F, -4F);
		this.rockShort2.setTextureSize(64, 32);
		this.rockShort2.mirror = true;
		this.setRotation(rockShort2, 0F, -0.2974289F, 0F);
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		basePit.render(par7);
		rockLong1.render(par7);
		rockLong2.render(par7);
		rockLong3.render(par7);
		rockShort1.render(par7);
		rockShort2.render(par7);
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
		basePit.render(0.0625F);
		rockLong1.render(0.0625F);
		rockLong2.render(0.0625F);
		rockLong3.render(0.0625F);
		rockShort1.render(0.0625F);
		rockShort2.render(0.0625F);
	}
}
