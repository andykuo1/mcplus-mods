package net.minecraftplus.mcp_turtle;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTurtle extends RenderLiving
{
	private static final ResourceLocation turtleSkinTexture = new ResourceLocation(_Turtle.MODID + ":" + "textures/entity/model.turtle.png");
	private static final ResourceLocation turtleTamedTexture = new ResourceLocation(_Turtle.MODID + ":" + "textures/entity/model.turtle.tamed.png");

	public RenderTurtle(RenderManager renderManager, ModelBase modelBase, float size)
	{
		super(renderManager, modelBase, size);

		this.addLayer(new LayerTurtle(this));
	}

	protected ResourceLocation getEntityTexture(EntityTurtle entity)
	{
		return entity.isTamed() ? turtleTamedTexture : turtleSkinTexture;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityTurtle)entity);
	}
}