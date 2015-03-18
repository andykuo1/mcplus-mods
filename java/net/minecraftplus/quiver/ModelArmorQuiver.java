package net.minecraftplus.quiver;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelArmorQuiver extends ModelBiped
{
	public ModelRenderer bipedArrow;

	public EntityPlayer player;
	public int bipedColor = -1;

	public ModelArmorQuiver()
	{
		this(0.0F);
	}

	public ModelArmorQuiver(float par1)
	{
		this(0.5F, 0.0F, 64, 32);
	}

	public ModelArmorQuiver(float par1, float par2, int par3, int par4)
	{
		super(par1, par2, par3, par4);
		this.bipedArrow = new ModelRenderer(this, 0, 0);
		this.bipedArrow.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, par1);
		this.bipedArrow.setRotationPoint(-1.9F, 12.0F + par2, 0.0F);
	}

	public EntityPlayer getPlayer(Entity par1Entity)
	{
		return (EntityPlayer) (par1Entity instanceof EntityPlayer ? par1Entity : null);
	}

	public int getColor(EntityPlayer par1EntityPlayer)
	{
		if (player != null)
		{
			ItemStack itemstack = player.getCurrentArmor(1);
			if (itemstack != null && itemstack.getItem() == ModQuiver.quiver)
			{
				ItemQuiver item = (ItemQuiver) itemstack.getItem();
				return item.getColor(itemstack);
			}
		}

		return 12999733;
	}


	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);

		if (this.isChild)
		{
			float f6 = 2.0F;
			GL11.glPushMatrix();
			GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
			GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
			this.bipedBody.render(par7);
			this.bipedRightLeg.render(par7);
			this.bipedArrow.render(par7);
			GL11.glPopMatrix();
		}
		else
		{
			if (this.player == null)
			{
				this.player = this.getPlayer(par1Entity);
				this.bipedColor = this.getColor(this.player);
			}

			if (this.bipedColor != -1)
			{
				float f2 = (float)(this.bipedColor >> 16 & 255) / 255.0F;
				float f3 = (float)(this.bipedColor >> 8 & 255) / 255.0F;
				float f4 = (float)(this.bipedColor & 255) / 255.0F;
				GL11.glColor3f(f2, f3, f4);
			}

			this.bipedBody.render(par7);
			this.bipedRightLeg.render(par7);

			GL11.glColor3f(1F, 1F, 1F);
			this.bipedArrow.render(par7);
		}
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	{
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);

		this.bipedArrow.rotateAngleX = this.bipedRightLeg.rotateAngleX;
		this.bipedArrow.rotateAngleY = this.bipedRightLeg.rotateAngleY;
		this.bipedArrow.rotationPointY = this.bipedRightLeg.rotationPointY;
		this.bipedArrow.rotationPointZ = this.bipedRightLeg.rotationPointZ;
	}
}
