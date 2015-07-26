package net.minecraftplus.mcp_pigeon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPigeon extends RenderLiving
{
	private static final ResourceLocation pigeonSkinTexture = new ResourceLocation(_Pigeon.MODID + ":" + "textures/entity/model.pigeon.png");

	public RenderPigeon(RenderManager renderManager, ModelBase modelBase, float size)
	{
		super(renderManager, modelBase, size);

		this.addLayer(new LayerPigeon(this));
	}

	protected ResourceLocation getEntityTexture(EntityPigeon entity)
	{
		return pigeonSkinTexture;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityPigeon)entity);
	}
}