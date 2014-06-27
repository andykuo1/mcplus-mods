package com.minecraftplus.modTurtle;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTurtle extends RenderLiving
{
	private static final ResourceLocation resTurtleWild = new ResourceLocation("minecraftplus:textures/entities/model.turtle.png");
	private static final ResourceLocation resTurtleTame = new ResourceLocation("minecraftplus:textures/entities/model.turtle.tamed.png");
	private static final ResourceLocation resTurtleCollar = new ResourceLocation("minecraftplus:textures/entities/model.turtle.collar.png");

	public RenderTurtle(ModelBase par1ModelBase, ModelBase par2ModelBase, float par2)
	{
		super(par1ModelBase, par2);
		this.setRenderPassModel(par2ModelBase);
	}

	protected int shouldRenderPass(EntityTurtle par1EntityTurtle, int par2, float par3)
	{
		if (par2 == 1 && par1EntityTurtle.isTamed())
		{
			this.bindTexture(resTurtleCollar);
			float f1 = 1.0F;
			int j = par1EntityTurtle.getCollarColor();
			GL11.glColor3f(f1 * EntitySheep.fleeceColorTable[j][0], f1 * EntitySheep.fleeceColorTable[j][1], f1 * EntitySheep.fleeceColorTable[j][2]);
			return 1;
		}
		else
		{
			return -1;
		}
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
	{
		return this.shouldRenderPass((EntityTurtle) par1EntityLivingBase, par2, par3);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return ((EntityTurtle) par1Entity).isTamed() ? resTurtleTame : resTurtleWild;
	}
}
