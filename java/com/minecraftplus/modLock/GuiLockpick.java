package com.minecraftplus.modLock;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiLockpick extends Gui
{
	protected final Minecraft mc = Minecraft.getMinecraft();
	protected final Random rand = new Random();
	protected GuiContainer theScreen;
	protected EntityPlayer thePlayer;
	protected EntityLock theLock;

	private final int BACK_SIZEX = 176;
	private final int BACK_SIZEY = 88;
	private final int LOCK_SIZE = 64;

	private ResourceLocation res_Lockpick = new ResourceLocation("minecraftplus:textures/gui/gui_lockpick.png");
	private static final ResourceLocation lockBase = new ResourceLocation("minecraftPlus:textures/gui/lockBase.png");

	private float lockHealthMax;
	private float lockHealth;
	private final float lockDamageSpeed = 0.2F;
	private int lockCoolDown = 0;
	private final int lockCoolDownMax = 35;
	private final int lockCoolDownWaitForVisible = 20;
	private boolean lockVisible = false;

	private boolean pickAble = true;
	private int pickAngle = 0;
	private final int pickSpeed = 2;
	private final int pickRange = 120;
	private boolean pickBreakAble = false;
	private int pickBreakAngle = 0;
	private final int pickBreakAngleMin = 11;

	private int openAngle = 0;
	private final int openRange = this.pickBreakAngleMin - 1;
	private final double openMaxRange = this.pickRange / 2;

	private boolean lockAngleAble = true;
	private int lockAngle = 0;
	private double lockAngleCur = 0.0D;
	private final int lockAngleSpeed = this.pickSpeed * 8;
	private final double lockRange = 180 + this.openMaxRange + this.openRange;

	public boolean enableOpen = false;

	public GuiLockpick(GuiContainer par1GuiContainer, EntityPlayer par2EntityPlayer, EntityLock par3EntityLock)
	{
		this.theScreen = par1GuiContainer;
		this.thePlayer = par2EntityPlayer;
		this.theLock = par3EntityLock;

		this.lockHealthMax = (float) ((rand.nextDouble() * 3) + this.lockDamageSpeed);
		this.lockHealth = this.lockHealthMax;

		this.setCoolDown(this.lockCoolDownMax / 2, false);
	}

	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		//LockPick
		if (this.isLockVisible())
		{
			int backX = (this.theScreen.width - BACK_SIZEX) / 2;
			int backY = (this.theScreen.height - BACK_SIZEY) / 2;

			GL11.glPushMatrix();
			this.mc.renderEngine.bindTexture(lockBase);

			GL11.glTranslatef(88, 78, 0);
			GL11.glRotatef(-this.lockAngle - 90, 0, 0, 1);
			GL11.glScalef((this.getCoolDownPercent() * 2) + 1F, (this.getCoolDownPercent() * 2) + 1F, 1F);
			GL11.glTranslatef(-backX, -backY + 4, 0);

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			drawTexturedModalRect(backX, backY, 0, LOCK_SIZE, 9, 93);
			GL11.glPopMatrix();
		}
	}

	public float getCoolDownPercent()
	{
		return (float)this.lockCoolDown / (float)this.lockCoolDownMax;
	}

	public void initGui()
	{
		this.openAngle = (int) (this.lockRange - (Math.random() * this.lockRange));
	}

	public void updateScreen()
	{
		if (this.canLockTurn())
		{
			this.lockAngleCur = rotToPos(this.theScreen.width / 2, this.theScreen.height / 2, Mouse.getX() / 2, this.theScreen.height - Mouse.getY() / 2);
		}

		if (this.lockAngleCur > this.lockRange && this.lockAngleCur < ((360 - this.lockRange) / 2) + this.lockRange)
		{
			this.lockAngleCur = this.lockRange;
		}

		if (this.lockAngleCur < 0.0D || this.lockAngleCur >= ((360 - this.lockRange) / 2) + this.lockRange)
		{
			this.lockAngleCur = 0;
		}

		this.lockAngle = (int) rotateTo(this.lockAngleSpeed, 1, this.lockAngleCur, this.lockAngle);
		if (!this.canLockOpen())
		{
			this.handlePick();

			if (this.canLockOpen())
			{
				this.enableOpen = true;
			}
		}

		if (!this.pickAble)
		{
			this.updateCoolDown();
		}
	}

	public void handlePick()
	{
		if (Mouse.isButtonDown(0) || Mouse.isButtonDown(1))
		{
			if (this.pickAble == true)
			{
				if (this.pickBreakAble == false)
				{
					//Start lockpicking
					this.pickBreakAble = true;
					this.lockAngleAble = false;

					int range = (int) (Math.abs(rotToRot(this.lockAngle, this.openAngle)));

					//Set breakpoint
					if (range <= this.openRange)
					{
						this.pickBreakAngle = -1;
					}
					else
					{
						if (range + 5 > this.openMaxRange)
							this.pickBreakAngle = this.pickBreakAngleMin;
						else
							this.pickBreakAngle = (int) (this.pickRange - ((range / this.openMaxRange) * this.pickRange));
					}
				}
				else
				{
					//Update pick rotation
					this.pickAngle += this.pickSpeed;

					//Continue lockpicking
					if (pickBreakAngle < 0)
					{
						if (this.pickAngle % 15 == 0 || this.rand.nextInt(16) == 0)
						{
							this.mc.thePlayer.playSound("mob.chicken.step", 0.7F, 0.8F);
						}

						//Open chest
						if (this.canLockOpen())
						{
							this.mc.thePlayer.playSound("mob.chicken.step", 0.7F, 0.8F);
							this.lockAngleAble = true;
						}
					}
					else
					{
						//If reached breakpoint
						if (this.pickAngle > this.pickBreakAngle)
						{
							this.pickAngle -= this.pickSpeed * 2;
							this.mc.thePlayer.playSound("random.click", 0.9F, 0.5F);
							this.lockHealth -= this.lockDamageSpeed;
							if (this.lockHealth <= 0F && this.rand.nextInt(4) == 0)
								this.breakPick();
						}
						else
						{
							float var0 = (float) ((this.pickBreakAngle - this.pickAngle) / this.openMaxRange);
							float var1 = var0 >= 0.2F ? var0 : 0.2F;
							if (this.pickAngle % 12 == 0 || this.rand.nextInt((int) (var1*10)) == 0)
								this.mc.thePlayer.playSound("mob.chicken.step", 0.6F, 0.8F);
						}
					}
				}
			}
		}
		else
		{
			this.resetPick();
		}
	}

	public void resetPick()
	{
		pickBreakAble = false;
		this.lockAngleAble = true;

		if (this.pickAngle > 0)
			this.pickAngle -= this.pickSpeed * 4;
		else
			this.pickAngle = 0;
	}

	public void breakPick()
	{
		this.setCoolDown(this.lockCoolDownMax, false);
		this.lockAngleAble = false;
	}

	public boolean isLockVisible()
	{
		return this.lockVisible;
	}

	public void setLockVisible(boolean par1)
	{
		this.lockVisible = par1;
	}

	public boolean canLockOpen()
	{
		return this.pickAngle >= this.pickRange;
	}

	public boolean canLockTurn()
	{
		return this.isLockVisible() && this.lockAngleAble;
	}

	public boolean canLockContinue()
	{
		if (!this.mc.thePlayer.capabilities.isCreativeMode && this.lockHealth <= 0)
		{
			ContainerLock.sendLockOpenPacket(this.thePlayer, this.theScreen.inventorySlots.windowId, 2);

			this.theLock.breakCloseLock(this.thePlayer);
			this.lockHealth = (float) (this.rand.nextDouble() * this.lockHealthMax) + this.lockDamageSpeed;
			return false;
		}
		else
		{
			this.lockHealth = (float) (this.rand.nextDouble() * this.lockHealthMax) + this.lockDamageSpeed;
			return true;
		}
	}

	public void setCoolDown(int par1CoolDown, boolean par2Allow)
	{
		this.lockCoolDown = par1CoolDown;
		this.setLockVisible(par2Allow);
		this.pickAble = par2Allow;
		if (par2Allow)
		{
			this.canLockContinue();
			this.lockAngleAble = true;
		}
	}

	public void updateCoolDown()
	{
		if (this.lockCoolDown-- <= 0)
		{
			this.setCoolDown(0, true);
			return;
		}

		if (this.lockCoolDown <= this.lockCoolDownWaitForVisible && !this.isLockVisible())
		{
			this.setLockVisible(true);
			this.canLockContinue();
		}
	}

	public int getPickAngle()
	{
		return this.pickAngle;
	}

	private static double rotToRot(double par1, double par2)
	{
		double d0 = par2 - par1;
		return d0 < Math.PI ? d0 + Math.PI * 2 : d0 > Math.PI ? d0 - Math.PI * 2 : d0;
	}

	public static double rotToPos(float x0, float y0, float x1, float y1)
	{
		return (Math.toDegrees(Math.atan2(y0 - y1, x1 - x0)) + 360) % 360;
	}

	public static double rotateTo(double par0Iteration, int par1Range, double par2Target, double par3Current)
	{
		double var0;
		for(int i = 0; i < par0Iteration; i++)
		{
			var0 = rotToRot(par3Current, par2Target);
			if (var0 <= -par1Range)
			{
				par3Current -= 1.0D;
			}
			if (var0 >= par1Range)
			{
				par3Current += 1.0D;
			}

			par3Current = (par3Current +360) % 360;
		}

		return par3Current;
	}
}
