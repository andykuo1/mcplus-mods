package net.minecraftplus.mcp_pigeon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftplus._api.util.ColorUtil;

@SideOnly(Side.CLIENT)
public class LayerPigeon implements LayerRenderer
{
	//Compare To: @EntityWolf
	private static final ResourceLocation field_177147_a = new ResourceLocation(_Pigeon.MODID + ":" + "textures/entity/model.pigeon.collar.png");
	private static final ResourceLocation pigeonHelmet = new ResourceLocation(_Pigeon.MODID + ":" + "textures/entity/model.pigeon.helmet.png");
	private static final ResourceLocation pigeonHelmetOverlay = new ResourceLocation(_Pigeon.MODID + ":" + "textures/entity/model.pigeon.helmet_overlay.png");
	private final RenderPigeon field_177146_b;
	private final ModelBase model = new ModelPigeonHead();

	public LayerPigeon(RenderPigeon p_i46104_1_)
	{
		//Compare To: @EntityWolf
		this.field_177146_b = p_i46104_1_;
	}

	public void func_177145_a(EntityPigeon p_177145_1_, float p_177145_2_, float p_177145_3_, float p_177145_4_, float p_177145_5_, float p_177145_6_, float p_177145_7_, float p_177145_8_)
	{
		//Compare To: @EntityWolf
		if (p_177145_1_.isTamed() && !p_177145_1_.isInvisible())
		{
			this.field_177146_b.bindTexture(field_177147_a);
			EnumDyeColor enumdyecolor = EnumDyeColor.byMetadata(p_177145_1_.getCollarColor().getMetadata());
			float[] afloat = EntitySheep.func_175513_a(enumdyecolor);
			GlStateManager.color(afloat[0], afloat[1], afloat[2]);
			this.model.setModelAttributes(this.field_177146_b.getMainModel());
			this.model.render(p_177145_1_, p_177145_2_, p_177145_3_, p_177145_5_, p_177145_6_, p_177145_7_, p_177145_8_);
		}

		if (p_177145_1_.isHelmetted())
		{
			this.field_177146_b.bindTexture(pigeonHelmet);
			GlStateManager.color(1F, 1F, 1F);
			this.model.setModelAttributes(this.field_177146_b.getMainModel());
			this.model.render(p_177145_1_, p_177145_2_, p_177145_3_, p_177145_5_, p_177145_6_, p_177145_7_, p_177145_8_);

			this.field_177146_b.bindTexture(pigeonHelmetOverlay);
			int color = p_177145_1_.getHelmetColor();
			GlStateManager.color(ColorUtil.hexToFloatR(color), ColorUtil.hexToFloatG(color), ColorUtil.hexToFloatB(color));
			this.model.setModelAttributes(this.field_177146_b.getMainModel());
			this.model.render(p_177145_1_, p_177145_2_, p_177145_3_, p_177145_5_, p_177145_6_, p_177145_7_, p_177145_8_);
		}
	}

	public boolean shouldCombineTextures()
	{
		//Compare To: @EntityWolf
		return true;
	}

	public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_)
	{
		//Compare To: @EntityWolf
		this.func_177145_a((EntityPigeon)p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
	}
}