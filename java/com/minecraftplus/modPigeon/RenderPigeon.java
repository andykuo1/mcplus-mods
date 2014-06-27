package com.minecraftplus.modPigeon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPigeon extends RenderLiving
{
	private static final ResourceLocation res_Pigeon = new ResourceLocation("minecraftplus:textures/entities/model.pigeon.png");

	public RenderPigeon(ModelBase par1ModelBase, float par2)
	{
		super(par1ModelBase, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return res_Pigeon;
	}
}