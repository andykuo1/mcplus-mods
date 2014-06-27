package com.minecraftplus.modWorkshop;

import java.io.File;

import net.minecraft.client.Minecraft;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.event.FMLEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.FMLEventChannel;

public class EventScreenshotHandler
{
	private boolean isPressed;
	
	@SubscribeEvent()
	public void onRenderTick(TickEvent.RenderTickEvent par1Event)
	{
		if (Minecraft.getMinecraft().thePlayer == null) return;
		if (Minecraft.getMinecraft().thePlayer.openContainer == null) return;
		
		if (Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindScreenshot.getKeyCode()))
		{
			if (!this.isPressed)
			{
				File file = ImageHandler.getImageFile(Minecraft.getMinecraft(), "screenshot-gui");
				ImageHandler.capture(file, 0, 0, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
				this.isPressed = true;
			}
		}
		else
		{
			this.isPressed = false;
		}
	}
}
