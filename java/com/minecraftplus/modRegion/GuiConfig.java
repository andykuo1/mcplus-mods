package com.minecraftplus.modRegion;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

import com.minecraftplus._base.MCP;

public class GuiConfig extends cpw.mods.fml.client.config.GuiConfig
{
	public GuiConfig(GuiScreen par1GuiScreen)
	{
		super(par1GuiScreen, new ConfigElement(MCP_Region.configHandler.getConfig().getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), MCP.D + MCP_Region.MODBASE, false, false, MCP.PRE + MCP_Region.MODBASE);
	}
}
