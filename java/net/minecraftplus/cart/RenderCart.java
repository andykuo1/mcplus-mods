package net.minecraftplus.cart;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftplus._api.tool.ResTool;
import net.minecraftplus.wheel.ModWheel;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCart extends RenderItemStatic
{
	private RenderCargo renderCargo;
	private ModelCart modelCart;
	private static final ResourceLocation resCartSkin = ResTool.getResource("model.wooden_cart", ResTool.ENTITIES, ModCart.INSTANCE);

	public RenderCart()
	{
		super(ModWheel.wheel, 1, 16);
		this.renderCargo = new RenderCargo();
		this.modelCart = new ModelCart();
	}

	@Override
	public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1)
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(resCartSkin);

		float yOffset = 0.2F;

		GL11.glPushMatrix();
		GL11.glTranslatef((float)d0, (float)d1 + 0.5F + yOffset, (float)d2);
		GL11.glScalef(1F, -1F, -1F);
		GL11.glRotatef(entity.rotationYaw, 0.0F, 1.0F, 0.0F);
		this.modelCart.render(null, 0, 0, 0, 0, 0, 0);

		EntityCart cart = (EntityCart) entity;

		if (cart.hasCargo())
		{
			GL11.glRotatef(-90, 0F, 1F, 0F);
			GL11.glTranslatef(-0.5625F, -1.2F, -0.5625F);
			GL11.glScalef(1F, 1F, 1F);
			this.renderCargo.render(cart.getCargo());
		}

		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		this.doRenderItem(cart, (float) d0, (float) d1 + 0.15F + yOffset, (float) d2, 1F);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		this.doRenderItem(cart, (float) d0, (float) d1 + 0.15F + yOffset, (float) d2, -1F);
		GL11.glPopMatrix();
	}

	protected void doRenderItem(EntityCart par1EntityCart, float par2, float par4, float par6, float par7)
	{
		GL11.glTranslatef(par2, par4, par6);
		GL11.glRotatef(-par1EntityCart.rotationYaw, 0F, 1F, 0F);

		GL11.glTranslatef(0.2F, 0F, 0.6F * par7);

		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		Tessellator tessellator = Tessellator.instance;

		GL11.glRotatef(par1EntityCart.getWheelAngle(), 0F, 0F, 1F);
		this.drawItem(tessellator, 1.0F);

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return resCartSkin;
	}
}
