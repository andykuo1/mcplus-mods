package net.minecraftplus.mcp_clippers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderChickenNaked extends RenderChicken
{
	//Compare to: @RenderChicken
	private static final ResourceLocation chickenTextures = new ResourceLocation(_Clippers.MODID + ":" + "textures/entity/naked_chicken.png");

	public RenderChickenNaked(ModelBase parModel, float parSize)
	{
		super(Minecraft.getMinecraft().getRenderManager(), parModel, parSize);
	}

	@Override
	protected ResourceLocation func_180568_a(EntityChicken p_180568_1_)
	{
		//Compare to: @RenderChicken
		return chickenTextures;
	}
}
