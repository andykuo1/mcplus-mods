package net.minecraftplus.cart;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelCart extends ModelBase
{	
	private ModelRenderer[] cartSides = new ModelRenderer[5];
	private ModelRenderer[] armSticks = new ModelRenderer[4];

	public ModelCart()
	{
		this.cartSides[0] = new ModelRenderer(this, 0, 8);
		this.cartSides[1] = new ModelRenderer(this, 0, 0);
		this.cartSides[2] = new ModelRenderer(this, 0, 0);
		this.cartSides[3] = new ModelRenderer(this, 0, 0);
		this.cartSides[4] = new ModelRenderer(this, 0, 0);
		byte b0 = 24;
		byte b1 = 6;
		byte b2 = 20;
		byte b3 = 4;
		this.cartSides[0].addBox((float)(-b0 / 2), (float)(-b2 / 2 + 2), -3.0F, b0, b2 - 4, 4, 0.0F);
		this.cartSides[0].setRotationPoint(0.0F, (float)b3, 0.0F);
		this.cartSides[1].addBox((float)(-b0 / 2 + 2), (float)(-b1 - 1), -1.0F, b0 - 4, b1, 2, 0.0F);
		this.cartSides[1].setRotationPoint((float)(-b0 / 2 + 1), (float)b3, 0.0F);
		this.cartSides[2].addBox((float)(-b0 / 2 + 2), (float)(-b1 - 1), -1.0F, b0 - 4, b1, 2, 0.0F);
		this.cartSides[2].setRotationPoint((float)(b0 / 2 - 1), (float)b3, 0.0F);
		this.cartSides[3].addBox((float)(-b0 / 2 + 2), (float)(-b1 - 1), -1.0F, b0 - 4, b1, 2, 0.0F);
		this.cartSides[3].setRotationPoint(0.0F, (float)b3, (float)(-b2 / 2 + 1));
		this.cartSides[4].addBox((float)(-b0 / 2 + 2), (float)(-b1 - 1), -1.0F, b0 - 4, b1, 2, 0.0F);
		this.cartSides[4].setRotationPoint(0.0F, (float)b3, (float)(b2 / 2 - 1));
		this.cartSides[0].rotateAngleX = ((float)Math.PI / 2F);
		this.cartSides[1].rotateAngleY = ((float)Math.PI * 3F / 2F);
		this.cartSides[2].rotateAngleY = ((float)Math.PI / 2F);
		this.cartSides[3].rotateAngleY = (float)Math.PI;

		this.armSticks[0] = new ModelRenderer(this, 0, 0);
		this.armSticks[0].addBox(0F, -3F, -6F, 1, 3, 12);
		this.armSticks[0].setRotationPoint(-18F, 0F, 9F);
		this.armSticks[0].rotateAngleY = (float) (Math.PI/2);

		this.armSticks[1] = new ModelRenderer(this, 0, 0);
		this.armSticks[1].addBox(0F, -3F, -6F, 1, 3, 12);
		this.armSticks[1].setRotationPoint(-18F, 0F, -8F);
		this.armSticks[1].rotateAngleY = (float) (Math.PI/2);

		this.armSticks[2] = new ModelRenderer(this, 0, 0);
		this.armSticks[2].addBox(0F, -3F, -6F, 1, 4, 3);
		this.armSticks[2].setRotationPoint(-6F, 10F, 6F);
		this.armSticks[2].rotateAngleY = (float) (Math.PI/2);

		this.armSticks[3] = new ModelRenderer(this, 0, 0);
		this.armSticks[3].addBox(0F, -3F, -6F, 1, 4, 3);
		this.armSticks[3].setRotationPoint(-6F, 10F, -6F);
		this.armSticks[3].rotateAngleY = (float) (Math.PI/2);
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		for (int i = 0; i < 5; ++i)
		{
			this.cartSides[i].render(0.0625F);
		}

		for (int i = 0; i < 4; ++i)
		{
			this.armSticks[i].render(0.0625F);
		}
	}
}
