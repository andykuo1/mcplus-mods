package net.minecraftplus.clippers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.ResourceLocation;
import net.minecraftplus._api.tool.ResTool;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderChickenNaked extends RenderChicken
{
	private static final ResourceLocation resNakedChickenSkin = ResTool.getResource("model.naked_chicken", ResTool.ENTITIES, ModClippers.INSTANCE);

	public RenderChickenNaked(ModelBase par1ModelBase, float par2)
	{
		super(par1ModelBase, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityChicken par1EntityChicken)
	{
		return resNakedChickenSkin;
	}
}
