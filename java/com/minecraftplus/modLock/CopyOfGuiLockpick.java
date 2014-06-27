package com.minecraftplus.modLock;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class CopyOfGuiLockpick extends Gui
{
	protected final Minecraft mc = Minecraft.getMinecraft();
	protected final Random rand = new Random();
	protected GuiContainer theScreen;
	protected EntityPlayer thePlayer;

	private ResourceLocation res_Lockpick = new ResourceLocation("minecraftplus:textures/gui/gui_lockpick.png");

	private boolean isVisible = true;

	/**Checked if to render new lockpick animation*/
	private boolean isNewLockpick = true;
	private boolean isOpenLockpick = false;
	private int tickCounter = 0;

	/**Returns true if currently picking lock*/
	private boolean isLockpicking = false;
	private float sweetSpot;
	private final float maxLockpickingRange;

	/**Lockpick rotation in lock*/
	private float pickRotation;
	private final float pickSpeed = 0.2F;

	/**Lockpick rotation while lockpicking*/
	private float pickingRotation;
	private final float pickingSpeed = 0.1F;
	private int pickingHealth;

	/**Possible breaking range when lockpicking*/
	private final float pickingBreakRange =  (float) (2 * Math.PI / 3);

	private int posX;
	private int posY;

	public CopyOfGuiLockpick(GuiContainer par1GuiContainer, EntityPlayer par2EntityPlayer)
	{
		this.theScreen = par1GuiContainer;
		this.thePlayer = par2EntityPlayer;

		this.maxLockpickingRange = (float) ((Math.PI * 2) - this.pickingBreakRange);
	}

	public void onUpdate(int par1, int par2)
	{
		if (this.isVisible)
		{
			if (!this.isLockpicking)
			{
				this.updateLockpickRotation(par1, par2);
			}
		}
	}

	public void openLockpick()
	{
		this.isLockpicking = false;
		this.isOpenLockpick = true;
	}

	public void breakLockpick()
	{
		this.isVisible = false;
		this.isNewLockpick = true;
		this.isOpenLockpick = false;
		this.tickCounter = 100;

		this.pickingHealth = this.rand.nextInt(40) + 10;

		ContainerLock.sendLockOpenPacket(this.thePlayer, this.theScreen.inventorySlots.windowId, 2);
	}

	public void updateLockpickRotation(int par1, int par2)
	{
		int i0 = par1 - this.posX;
		int i1 = par2 - this.posY;
		float f = (float) Math.atan2(i1, i0);

		while (this.pickRotation >= (float)Math.PI)
		{
			this.pickRotation -= ((float)Math.PI * 2F);
		}

		while (this.pickRotation < -(float)Math.PI)
		{
			this.pickRotation += ((float)Math.PI * 2F);
		}

		while (f >= (float)Math.PI)
		{
			f -= ((float)Math.PI * 2F);
		}

		while (f < -(float)Math.PI)
		{
			f += ((float)Math.PI * 2F);
		}

		if (f < 0F) f = 0F;
		if (f > this.maxLockpickingRange) f = this.maxLockpickingRange;

		float f1;
		for (f1 = f - this.pickRotation; f1 >= (float)Math.PI; f1 -= ((float)Math.PI * 2F)) {}

		while (f1 < -(float)Math.PI)
		{
			f1 += ((float)Math.PI * 2F);
		}

		this.pickRotation += f1 * this.pickSpeed;
	}

	public void doRender()
	{
		if (this.isNewLockpick)
		{
			if (this.tickCounter-- <= 0)
			{
				this.isNewLockpick = false;
				this.isVisible = true;
			}
		}

		if (this.isVisible)
		{
			GL11.glPushMatrix();
			this.mc.renderEngine.bindTexture(res_Lockpick);

			GL11.glTranslatef(0, 0, 0);
			GL11.glRotatef(this.getRotation(), 0, 0, 1);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			drawTexturedModalRect(0, 0, 0, 0, 9, 93);
			GL11.glPopMatrix();
		}
	}

	public void resetLockpicking()
	{
		this.isLockpicking = false;
		if (this.pickingRotation > 0F)
		{
			this.pickingRotation -= this.pickingSpeed;
		}
		else
		{
			this.pickingRotation = 0F;
		}
	}

	public void handleLockpicking()
	{
		if (!this.isOpenLockpick && this.isVisible)
		{
			this.isLockpicking = true;
			float f = (float) rotToRot(this.pickRotation, this.sweetSpot);
			this.pickingRotation += this.pickingSpeed;
			float f1 = this.pickingBreakRange - f;

			if (this.pickingRotation > this.pickingBreakRange - Math.toRadians(5D))
			{
				this.openLockpick();
			}
			else if (this.pickingRotation > f1)
			{
				this.pickingRotation -= 0.04F * this.rand.nextFloat() + 0.02F;
				this.pickingHealth --;
				if (this.pickingHealth < 0)
				{
					this.breakLockpick();
					this.resetLockpicking();
				}
			}
		}
	}

	public void setPosition(int par1, int par2)
	{
		this.posX = par1;
		this.posY = par2;
	}

	public float getRotation()
	{
		return (float) Math.toDegrees(this.pickRotation + this.pickingRotation);
	}

	public float getPickingRotation()
	{
		return (float) Math.toDegrees(this.pickingRotation);
	}

	public boolean isOpen()
	{
		return this.isOpenLockpick;
	}

	protected void resetSweetSpot()
	{
		this.sweetSpot = this.rand.nextFloat() * this.maxLockpickingRange;
	}

	private static double rotToRot(double par1, double par2)
	{
		double d0 = par2 - par1;
		return d0 < Math.PI ? d0 + Math.PI * 2 : d0 > Math.PI ? d0 - Math.PI * 2 : d0;
	}
}
