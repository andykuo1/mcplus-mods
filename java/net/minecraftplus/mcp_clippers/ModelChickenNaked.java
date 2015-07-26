package net.minecraftplus.mcp_clippers;

import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModelChickenNaked extends ModelChicken
{
	private float headRotationAngleX;

	public ModelChickenNaked()
	{
		//Compare to: @ModelChicken
		byte b0 = 16;
		this.body = new ModelRenderer(this, 0, 9);
		this.body.addBox(-3.0F, (-4.0F - 0.1F), -3.0F, 6, 8, 6, (0.0F - 0.1F));
		this.body.setRotationPoint(0.0F, (float) (b0 - 0.1F), 0.0F);
		this.rightLeg = new ModelRenderer(this, 26, 0);
		this.rightLeg.addBox(-1.0F, (0.0F - 0.1F), -3.0F, 3, 5, 3, (0.0F  - 0.1F));
		this.rightLeg.setRotationPoint(-2.0F, (float) ((3 + b0) - 0.1F), 1.0F);
		this.leftLeg = new ModelRenderer(this, 26, 0);
		this.leftLeg.addBox(-1.0F, (0.0F - 0.1F), -3.0F, 3, 5, 3, (0.0F  - 0.1F));
		this.leftLeg.setRotationPoint(1.0F, (float) ((3 + b0) - 0.1F), 1.0F);
		this.rightWing = new ModelRenderer(this, 24, 13);
		this.rightWing.addBox(0.0F, (0.0F - 0.1F), -3.0F, 1, 4, 6, (0.0F  - 0.1F));
		this.rightWing.setRotationPoint(-4.0F, (float) ((-3 + b0) - 0.1F), 0.0F);
		this.leftWing = new ModelRenderer(this, 24, 13);
		this.leftWing.addBox(-1.0F, (0.0F - 0.1F), -3.0F, 1, 4, 6, (0.0F  - 0.1F));
		this.leftWing.setRotationPoint(4.0F, (float) ((-3 + b0) - 0.1F), 0.0F);
	}

	@Override
	public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_)
	{
		super.setLivingAnimations(p_78086_1_, p_78086_2_, p_78086_3_, p_78086_4_);

		//Compare to: @ModelSheep1
		//this.head.rotationPointY = 6.0F + ((EntityChickenNaked)p_78086_1_).getHeadRotationPointY(p_78086_4_) * 9.0F;
		this.headRotationAngleX = ((EntityChickenNaked)p_78086_1_).getHeadRotationAngleX(p_78086_4_);
	}

	@Override
	public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
	{
		super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);

		//Compare to: @ModelSheep1
		this.head.rotateAngleX = this.headRotationAngleX;
		
		//Move chicken's face with the head :P
		this.bill.rotateAngleX = this.headRotationAngleX;
		this.chin.rotateAngleX = this.headRotationAngleX;
	}
}
