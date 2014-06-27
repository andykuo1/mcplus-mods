package com.minecraftplus.modLock;

import java.util.Random;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiLock extends GuiContainer
{
	private final int BACK_SIZEX = 176;
	private final int BACK_SIZEY = 88;
	private final int LOCK_SIZE = 64;

	private static final ResourceLocation back = new ResourceLocation("minecraftplus:textures/gui/blank.png");
	private static final ResourceLocation lockBase = new ResourceLocation("minecraftplus:textures/gui/lockBase.png");

	private ContainerLock containerLock;
	private EntityLock entityLock;
	private int entitylock_id;
	private static final Random rand = new Random();

	private GuiLockpick lockpick;

	public GuiLock(EntityPlayer par1EntityPlayer, EntityLock par2EntityLock)
	{
		super(new ContainerLock(par1EntityPlayer, par2EntityLock));
		this.lockpick = new GuiLockpick(this, par1EntityPlayer, par2EntityLock);

		this.entityLock = par2EntityLock;
		this.containerLock = (ContainerLock) this.inventorySlots;

		if (par2EntityLock != null)
		{
			this.entitylock_id = par2EntityLock.getEntityId();
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		//LockPick
		this.lockpick.drawGuiContainerForegroundLayer(x, y);
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
		GL11.glRotatef(this.lockpick.getPickAngle(), 0, 0, 1);
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
		GL11.glRotatef(this.lockpick.getPickAngle(), 0F, 0F, 1F);
		GL11.glTranslatef(0, 12, 0);
		GL11.glRotatef(-this.lockpick.getPickAngle() - 70, 0F, 0F, 1F);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(0, 0, 10, LOCK_SIZE, 10, 93);
		GL11.glPopMatrix();
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
	}

	@Override
	public void updateScreen()
	{
		super.updateScreen();
		this.lockpick.updateScreen();

		if (this.lockpick.enableOpen)
		{
			((GuiButton) this.buttonList.get(0)).enabled = true;
		}

		if (this.mc.theWorld.getEntityByID(this.entitylock_id) == null)
		{
			this.entityLock = null;
			this.closeGui();
		}
	}

	public void actionPerformed(GuiButton button)
	{
		switch(button.id)
		{
		case 0: //Open
			ContainerLock.sendLockOpenPacket(this.containerLock.entityPlayer, this.containerLock.windowId, 1);
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
}
