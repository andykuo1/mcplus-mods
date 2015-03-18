package net.minecraftplus.cart;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBlock extends ModelBase
{
	public ModelRenderer block;

	public ModelBlock()
	{
		this.block = (new ModelRenderer(this, -16, 0)).setTextureSize(64, 64);
		this.block.addBox(0.0F, 0.0F, 0.0F, 16, 16, 16, 0.0F);
		this.block.rotationPointX = 1.0F;
		this.block.rotationPointY = 6.0F;
		this.block.rotationPointZ = 1.0F;
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		this.block.render(0.0625F);
	}
}
