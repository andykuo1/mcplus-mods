package com.minecraftplus.modLock;

import java.util.Random;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CopyOfGuiLock extends GuiContainer
{
	private final int BACK_SIZEX = 176;
	private final int BACK_SIZEY = 88;
	private final int LOCK_SIZE = 64;

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

	private static final ResourceLocation back = new ResourceLocation("minecraftplus:textures/gui/blank.png");
	private static final ResourceLocation lockBase = new ResourceLocation("minecraftPlus:textures/gui/lockBase.png");

	private ContainerLock containerLock;
	private EntityLock entityLock;
	private int entitylock_id;
	private static final Random rand = new Random();

	private GuiLockpick lockpick;

	public CopyOfGuiLock(EntityPlayer par1EntityPlayer, EntityLock par2EntityLock)
	{
		super(new ContainerLock(par1EntityPlayer, par2EntityLock));
		this.lockpick = new GuiLockpick(this, par1EntityPlayer, par2EntityLock);
		
		this.lockHealthMax = (float) ((rand.nextDouble() * 3) + this.lockDamageSpeed);
		this.lockHealth = this.lockHealthMax;

		this.entityLock = par2EntityLock;
		this.containerLock = (ContainerLock) this.inventorySlots;

		if (par2EntityLock != null)
		{
			this.entitylock_id = par2EntityLock.getEntityId();
		}

		this.setCoolDown(this.lockCoolDownMax / 2, false);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		//LockPick
		if (this.isLockVisible())
		{
			int backX = (this.width - BACK_SIZEX) / 2;
			int backY = (this.height - BACK_SIZEY) / 2;

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

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
	{
		//BlankCanvas
		this.mc.renderEngine.bindTexture(back);

		int backX = (this.width - BACK_SIZEX) / 2;
		int backY = (this.height - BACK_SIZEY) / 2;
		int lockX = this.width / 2;
		int lockY = this.height / 2;

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(backX, backY, 0, 0, BACK_SIZEX, BACK_SIZEY);

		//LockBase
		this.mc.renderEngine.bindTexture(lockBase);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(lockX - (LOCK_SIZE / 2), lockY - (LOCK_SIZE / 2), LOCK_SIZE, 0, LOCK_SIZE, LOCK_SIZE);

		//Info
		this.fontRendererObj.drawString("Iron Lock", backX + 8, backY + 8, 4210752);
		//drawString(fontRenderer, Integer.toString(this.lockAngle), backX+20, backY+20, 0x6A5ACD);
		//drawString(fontRenderer, Integer.toString(this.openAngle), backX+20, backY+40, 0x6A5ACD);
		//drawString(fontRenderer, Integer.toString(this.pickBreakAngle), backX+20, backY+60, 0x6A5ACD);

		//LockCap
		GL11.glPushMatrix();
		this.mc.renderEngine.bindTexture(lockBase);

		GL11.glTranslatef(lockX, lockY, 0);
		GL11.glRotatef(this.pickAngle, 0, 0, 1);
		GL11.glTranslatef(-backX, -backY, 0);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(backX - (LOCK_SIZE / 2), backY - (LOCK_SIZE / 2), 0, 0, LOCK_SIZE, LOCK_SIZE);

		//LockHole
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(backX - (LOCK_SIZE / 2), backY - (LOCK_SIZE / 2), LOCK_SIZE * 2, 0, LOCK_SIZE, LOCK_SIZE);
		GL11.glPopMatrix();

		//LockTumbler
		GL11.glPushMatrix();
		this.mc.renderEngine.bindTexture(lockBase);

		GL11.glTranslatef(lockX - 2, lockY + 4, 0);
		GL11.glRotatef(this.pickAngle, 0F, 0F, 1F);
		GL11.glTranslatef(0, 12, 0);
		GL11.glRotatef(-this.pickAngle - 70, 0F, 0F, 1F);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(0, 0, 10, LOCK_SIZE, 10, 93);
		GL11.glPopMatrix();
	}

	public float getCoolDownPercent()
	{
		return (float)this.lockCoolDown / (float)this.lockCoolDownMax;
	}

	@Override
	public void initGui()
	{	
		super.initGui();
		this.buttonList.clear();
		GuiButton button = new GuiButton(0, (this.width - BACK_SIZEX) / 2, (this.height / 2) + (BACK_SIZEY / 2) + 5, 80, 20, "Open");
		this.buttonList.add(button);
		button.enabled = false;
		this.buttonList.add(new GuiButton(1, ((this.width + BACK_SIZEX) / 2) - 80, (this.height / 2) + (BACK_SIZEY / 2) + 5, 80, 20, "Cancel"));

		this.openAngle = (int) (this.lockRange - (Math.random() * this.lockRange));
	}

	@Override
	public void updateScreen()
	{
		super.updateScreen();
		if (this.canLockTurn())
		{
			this.lockAngleCur = angleTo(this.width / 2, this.height / 2, Mouse.getX() / 2, this.height - Mouse.getY() / 2);
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
				GuiButton button = (GuiButton) this.buttonList.get(0);
				button.enabled = true;
			}
		}

		if (!this.pickAble)
		{
			this.updateCoolDown();
		}

		if (this.mc.theWorld.getEntityByID(this.entitylock_id) == null)
		{
			this.entityLock = null;
			this.closeGui();
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

					int range = (int) (Math.abs(angleToAngle(this.lockAngle, this.openAngle)));

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
			//this.getLockOnServer(this.entitylock_id).setBreakLockpick((byte)1);
			//this.lockHealth = (float) (this.rand.nextDouble() * this.lockHealthMax) + this.lockDamageSpeed;
			this.containerLock.sendLockOpenPacket(this.containerLock.entityPlayer, this.containerLock.windowId, 2);
			this.entityLock.breakCloseLock(this.containerLock.entityPlayer);
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

	public void actionPerformed(GuiButton button)
	{
		switch(button.id)
		{
		case 0: //Open
			this.containerLock.sendLockOpenPacket(this.containerLock.entityPlayer, this.containerLock.windowId, 1);
			this.entityLock.breakOpenLock(this.containerLock.entityPlayer);
			this.closeGui();
			break;
		case 1: //Cancel
			this.closeGui();
			break;
		}
	}

	public void closeGui()
	{
		this.mc.thePlayer.closeScreen();
	}

	public boolean doesGuiPauseGame()
	{
		return false;
	}

	public static double angleToAngle(double par0Angle, double par1Angle)
	{
		double var0 = par1Angle - par0Angle;
		if (var0 < -180.0D)
		{
			var0 += 360.0D;
		}
		else
		{
			if (var0 > 180.0D)
			{
				var0 -= 360.0D;
			}
		}

		return var0;
	}

	public static double angleTo(float x0, float y0, float x1, float y1)
	{
		return (Math.toDegrees(Math.atan2(y0 - y1, x1 - x0)) + 360) % 360;
	}

	public static double rotateTo(double par0Iteration, int par1Range, double par2Target, double par3Current)
	{
		double var0;
		for(int i = 0; i < par0Iteration; i++)
		{
			var0 = angleToAngle(par3Current, par2Target);
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
