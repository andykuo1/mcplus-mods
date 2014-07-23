package com.minecraftplus.modRegion;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import com.minecraftplus._base.registry.LanguageRegistry;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class GuiBiomeName extends Gui
{
	private BiomeGenBase currentBiome; 
	private boolean changeBiome;
	private int tickCounter = 0;
	private final int maxTickCounter = 50;

	private Minecraft mc;
	private String worldName = "New World";
	public static boolean initWorldTitle = false;
	private int tickCounter1 = this.maxTickCounter * 2;

	private final int x;
	private final int y;
	private final float scaleFactor;

	private ArrayList<BiomeGenBase> prevBiome = new ArrayList<BiomeGenBase>();

	public GuiBiomeName(int par1, int par2, float par3)
	{
		this.mc = Minecraft.getMinecraft();
		this.x = par1;
		this.y = par2;
		this.scaleFactor = par3 > 1 ? 1F : par3 < 0.1F ? 0.1F : par3;
	}

	@SubscribeEvent(priority = EventPriority.LOW)
	public void onRender(RenderGameOverlayEvent par1Event)
	{
		if(par1Event.isCancelable() || par1Event.type != ElementType.EXPERIENCE) return;

		EntityPlayer player = this.mc.thePlayer;
		if (player == null) return;

		if (!this.initWorldTitle)
		{
			this.mc.mcProfiler.startSection("overlayMessage");

			ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
			float xPosition = (float) (res.getScaledWidth() / 2);
			float yPosition = (float) (res.getScaledHeight() / 2) - (res.getScaledHeight() / 3F);

			float scale = (mc.displayWidth / res.getScaledWidth()) * 1.75F;
			float unscale = 1F / scale;

			this.initWorldTitle = !renderStringFade(this.mc.fontRenderer, this.worldName, 0xFFFFFF, xPosition, yPosition, scale, this.tickCounter1, this.maxTickCounter * 3, this.maxTickCounter * 2);
			if (!this.initWorldTitle)
			{
				this.tickCounter1--;
			}
			else
			{
				this.tickCounter1 = 0;
			}

			this.mc.mcProfiler.endSection();

			return;
		}
		else if (this.tickCounter1 < this.maxTickCounter * 2)
		{
			this.tickCounter1++;
			return;
		}

		BiomeGenBase biome = this.getCurrentBiome(player, this.mc.theWorld);

		if (biome != null && this.currentBiome != biome && !this.changeBiome)
		{
			boolean flag = true;

			while(this.prevBiome.size() > 4)
			{
				this.prevBiome.remove(0);
			}

			for(BiomeGenBase pastbiome : this.prevBiome)
			{
				if (biome == pastbiome)
				{
					flag = false;
				}
			}

			if (flag)
			{
				this.currentBiome = biome;
				this.prevBiome.add(biome);
				this.changeBiome = true;
				this.tickCounter = this.maxTickCounter;
			}
		}

		if (this.changeBiome)
		{
			this.mc.mcProfiler.startSection("overlayMessage");

			ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
			float xPosition = (float)((res.getScaledWidth() / 2) + (res.getScaledWidth() / 3.2F * this.x));
			float yPosition = (float) (res.getScaledHeight() / 2) - (res.getScaledHeight() / 3F * this.y);

			float scale = (this.mc.displayWidth / res.getScaledWidth()) * 1.325F * this.scaleFactor;
			float unscale = 1F / scale;

			if (this.y < 0 && this.x == 0)
			{
				yPosition = res.getScaledHeight() - 59;
			}

			String biomeName = this.currentBiome.biomeName;
			if (!StringUtils.isNullOrEmpty(I18n.format(biomeName)))
			{
				biomeName = I18n.format(biomeName);
			}

			this.changeBiome = renderStringFade(this.mc.fontRenderer, biomeName, 0xFFFFFF, xPosition, yPosition, scale, this.tickCounter, this.maxTickCounter * 2, this.maxTickCounter);
			if (this.changeBiome)
			{
				this.tickCounter--;
			}
			else
			{
				this.changeBiome = false;
				this.tickCounter = 0;
			}

			this.mc.mcProfiler.endSection();
		}
	}

	private BiomeGenBase getCurrentBiome(EntityPlayer par1EntityPlayer, World par2World)
	{
		int i = MathHelper.floor_double(par1EntityPlayer.posX);
		int j = MathHelper.floor_double(par1EntityPlayer.posY);
		int k = MathHelper.floor_double(par1EntityPlayer.posZ);

		if (par2World != null && par2World.blockExists(i, j, k))
		{
			Chunk chunk = par2World.getChunkFromBlockCoords(i, k);
			return chunk.getBiomeGenForWorldCoords(i & 15, k & 15, par2World.getWorldChunkManager());
		}

		return null;
	}

	public static boolean renderStringFade(FontRenderer par1FontRenderer, String par2String, int par3, float par4, float par5, float par6, int par7, int par8, int par9)
	{
		boolean flag = true;
		float f = 0F;
		if (par7 > 0)
		{
			f = (float) (par9 - par7) / (float) par9;
			f += 0.0625F;
		}
		else if (par7 > -par8)
		{
			f = 1F;
		}
		else if (par7 + par8 > -(par9 - 4))
		{
			f = (float) (par9 + (par7 + par8)) / (float) par9;
		}
		else
		{
			f = 0.0625F;
			flag = false;
		}

		int opacity = (int) (f * 256.0F);
		if (opacity > 255) opacity = 255;

		float scale = par6;
		float unscale = 1 / par6;

		GL11.glPushMatrix();
		GL11.glTranslatef(par4 + 1.625F, par5 + 1.525F, 0.0F);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glScalef(scale, scale, scale);
		par1FontRenderer.drawString(par2String, -par1FontRenderer.getStringWidth(par2String) / 2, -4, 0x444444 | ((int) (opacity * 0.6F) << 24));
		GL11.glScalef(unscale, unscale, unscale);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslatef(par4, par5, 0.0F);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glScalef(scale, scale, scale);
		par1FontRenderer.drawString(par2String, -par1FontRenderer.getStringWidth(par2String) / 2, -4, par3 | (opacity << 24));
		GL11.glScalef(unscale, unscale, unscale);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();

		return flag;
	}

	public void reset(int par1)
	{
		this.initWorldTitle = false;
		this.tickCounter1 = this.maxTickCounter * 2;

		this.currentBiome = null;
		this.changeBiome = false;
		this.tickCounter = 0;

		this.prevBiome.clear();
		this.worldName = par1 == -1 ? "The Nether" : par1 == 1 ? "The End" : MinecraftServer.getServer().worldServers[0].getWorldInfo().getWorldName();
		if (!StringUtils.isNullOrEmpty(I18n.format("world." + LanguageRegistry.getNameUnlocal(this.worldName))) && !I18n.format("world." + LanguageRegistry.getNameUnlocal(this.worldName)).contains("world."))
		{
			this.worldName = I18n.format("world." + LanguageRegistry.getNameUnlocal(this.worldName));
		}
	}
}
