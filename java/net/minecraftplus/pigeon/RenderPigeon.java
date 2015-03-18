package net.minecraftplus.pigeon;

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
public class RenderPigeon extends RenderLiving
{
	private static final ResourceLocation resPigeonSkin = ResTool.getResource("model.pigeon", ResTool.ENTITIES, ModPigeon.INSTANCE);
	private static final ResourceLocation resPigeonCollar = ResTool.getResource("model.pigeon.collar", ResTool.ENTITIES, ModPigeon.INSTANCE);
	private static final ResourceLocation resPigeonHelmet = ResTool.getResource("model.pigeon.helmet", ResTool.ENTITIES, ModPigeon.INSTANCE);
	private static final ResourceLocation resPigeonHelmetOverlay = ResTool.getResource("model.pigeon.helmet_overlay", ResTool.ENTITIES, ModPigeon.INSTANCE);

	public RenderPigeon(ModelBase parModelBase, ModelBase parModelHead, float parScale)
	{
		super(parModelBase, parScale);
		this.setRenderPassModel(parModelHead);
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase parEntityLivingBase, int parRenderPass, float par3)
	{
		EntityPigeon entitypigeon = (EntityPigeon) parEntityLivingBase;
		if (!entitypigeon.isTamed()) return -1;

		if (parRenderPass == 1)
		{
			this.bindTexture(resPigeonCollar);
			float f1 = 1.0F;
			int j = entitypigeon.getCollarColor();
			GL11.glColor3f(f1 * EntitySheep.fleeceColorTable[j][0], f1 * EntitySheep.fleeceColorTable[j][1], f1 * EntitySheep.fleeceColorTable[j][2]);
			return 1;
		}
		else if (parRenderPass == 2 && entitypigeon.hasHelmet())
		{
			float f1 = 1.0F;
			GL11.glColor3f(f1, f1, f1);
			this.bindTexture(resPigeonHelmet);
			return 1;
		}
		else if (parRenderPass == 3 && entitypigeon.hasHelmet())
		{
			this.bindTexture(resPigeonHelmetOverlay);
			int j = entitypigeon.getHelmetColor();
			float f1 = (float)(j >> 16 & 255) / 255.0F;
			float f2 = (float)(j >> 8 & 255) / 255.0F;
			float f3 = (float)(j & 255) / 255.0F;
			GL11.glColor3f(f1, f2, f3);
			return 1;
		}
		else
		{
			return -1;
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return resPigeonSkin;
	}
}