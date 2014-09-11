package com.minecraftplus.modSkullCandle;

import com.minecraftplus._base.MCP;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class GuiConfig extends cpw.mods.fml.client.config.GuiConfig
{
	public GuiConfig(GuiScreen par1GuiScreen)
	{
		super(par1GuiScreen, new ConfigElement(MCP_SkullCandle.configHandler.getConfig().getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), MCP.D + MCP_SkullCandle.MODBASE, false, false, MCP.PRE + MCP_SkullCandle.MODBASE);
	}
}
