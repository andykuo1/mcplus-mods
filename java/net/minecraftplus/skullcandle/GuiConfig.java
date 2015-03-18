package net.minecraftplus.skullcandle;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftplus._api.mod.MCP;

public class GuiConfig extends cpw.mods.fml.client.config.GuiConfig
{
	public GuiConfig(GuiScreen par1GuiScreen)
	{
		super(par1GuiScreen, new ConfigElement(ModSkullCandle.configHandler.getConfig().getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), MCP.MCPID + ModSkullCandle.MODID, false, false, MCP.MCPID + ModSkullCandle.MODID);
	}
}
