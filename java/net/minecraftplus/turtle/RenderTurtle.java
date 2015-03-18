package net.minecraftplus.turtle;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;
import net.minecraftplus._api.tool.ResTool;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTurtle extends RenderLiving
{
	private static final ResourceLocation resTurtleSkin = ResTool.getResource("model.turtle", ResTool.ENTITIES, ModTurtle.INSTANCE);
	private static final ResourceLocation resTurtleSkinTamed = ResTool.getResource("model.turtle.tamed", ResTool.ENTITIES, ModTurtle.INSTANCE);
	private static final ResourceLocation resTurtleCollar = ResTool.getResource("model.turtle.collar", ResTool.ENTITIES, ModTurtle.INSTANCE);

	public RenderTurtle(ModelBase parModelBase, ModelBase parModelCollar, float par2)
	{
		super(parModelBase, par2);
		this.setRenderPassModel(parModelCollar);
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase parEntityLivingBase, int parRenderPass, float par3)
	{
		EntityTurtle turtle = (EntityTurtle) parEntityLivingBase;
		if (parRenderPass == 1 && turtle.isTamed())
		{
			this.bindTexture(resTurtleCollar);
			float f1 = 1.0F;
			int j = turtle.getCollarColor();
			GL11.glColor3f(f1 * EntitySheep.fleeceColorTable[j][0], f1 * EntitySheep.fleeceColorTable[j][1], f1 * EntitySheep.fleeceColorTable[j][2]);
			return 1;
		}
		else
		{
			return -1;
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity parEntity)
	{
		return ((EntityTurtle) parEntity).isTamed() ? resTurtleSkinTamed : resTurtleSkin;
	}
}
