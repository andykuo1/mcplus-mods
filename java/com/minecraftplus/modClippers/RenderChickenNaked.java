package com.minecraftplus.modClippers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderChickenNaked extends RenderChicken
{
	private static final ResourceLocation resNakedChicken = new ResourceLocation("minecraftplus:textures/entities/model.naked_chicken.png");

	public RenderChickenNaked(ModelBase par1ModelBase, float par2)
	{
		super(par1ModelBase, par2);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityChicken par1EntityChicken)
	{
		return resNakedChicken;
	}
}
