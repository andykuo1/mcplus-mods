package net.minecraftplus.clippers;

import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelChickenNaked extends ModelChicken
{
	public ModelChickenNaked()
	{
		byte b0 = 16;
		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-2.0F, -6.0F, -2.0F, 4, 6, 3, 0.0F);
		this.head.setRotationPoint(0.0F, (float)(-1 + b0), -4.0F);
		this.bill = new ModelRenderer(this, 14, 0);
		this.bill.addBox(-2.0F, -4.0F, -4.0F, 4, 2, 2, 0.0F);
		this.bill.setRotationPoint(0.0F, (float)(-1 + b0), -4.0F);
		this.chin = new ModelRenderer(this, 14, 4);
		this.chin.addBox(-1.0F, -2.0F, -3.0F, 2, 2, 2, 0.0F);
		this.chin.setRotationPoint(0.0F, (float)(-1 + b0), -4.0F);

		this.body = new ModelRenderer(this, 0, 9);
		this.body.addBox(-3.0F, -4.0F - 0.1F, -3.0F, 6, 8, 6, 0.0F - 0.1F);
		this.body.setRotationPoint(0.0F, (float)b0 - 0.1F, 0.0F);
		this.rightLeg = new ModelRenderer(this, 26, 0);
		this.rightLeg.addBox(-1.0F, 0.0F - 0.1F, -3.0F, 3, 5, 3, 0.0F - 0.1F);
		this.rightLeg.setRotationPoint(-2.0F, (float)(3 + b0) - 0.1F, 1.0F);
		this.leftLeg = new ModelRenderer(this, 26, 0);
		this.leftLeg.addBox(-1.0F, 0.0F - 0.1F, -3.0F, 3, 5, 3, 0.0F - 0.1F);
		this.leftLeg.setRotationPoint(1.0F, (float)(3 + b0) - 0.1F, 1.0F);
		this.rightWing = new ModelRenderer(this, 24, 13);
		this.rightWing.addBox(0.0F, 0.0F - 0.1F, -3.0F, 1, 4, 6, 0.0F - 0.1F);
		this.rightWing.setRotationPoint(-4.0F, (float)(-3 + b0) - 0.1F, 0.0F);
		this.leftWing = new ModelRenderer(this, 24, 13);
		this.leftWing.addBox(-1.0F, 0.0F - 0.1F, -3.0F, 1, 4, 6, 0.0F - 0.1F);
		this.leftWing.setRotationPoint(4.0F, (float)(-3 + b0) - 0.1F, 0.0F);
	}
}
