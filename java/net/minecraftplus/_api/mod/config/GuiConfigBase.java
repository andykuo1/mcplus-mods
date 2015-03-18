package net.minecraftplus._api.mod.config;

import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftplus._api.IConfigMod;
import net.minecraftplus._api.IMod;
import net.minecraftplus._api.mod.MCP;
import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;

/**
 * Called by {@link IModGuiFactory} to construct and set {@link IMod} to load mod config.
 * <br>
 * <br>Only <code>{@link Configuration}.CATEGORY_GENERAL</code> is displayed!
 * */
public class GuiConfigBase extends GuiConfig
{
	/**The mod that the config is displayed.
	 * <br>
	 * <br>Must set BEFORE calling constructor!
	 * */
	public static IConfigMod mod;

	/**Construct GuiConfig through {@link IModGuiFactory}.
	 * <br>
	 * <br>Do <b>NOT OVERRIDE</b> this!*/
	public GuiConfigBase(GuiScreen parGuiScreen)
	{
		super(parGuiScreen, new ConfigElement(GuiConfigBase.mod.getConfig().getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), MCP.MCPID + mod.getModID(), false, false, MCP.getModName(GuiConfigBase.mod));
	}

	/**Override this to make a custom {@link GuiConfig}*/
	protected GuiConfigBase(GuiScreen parGuiScreen, List<IConfigElement> parConfigElements, String parModID, String parConfigID, boolean parRequireWorldRestart, boolean parRequireMCRestart, String parTitle)
	{
		super(parGuiScreen, parConfigElements, parModID, parConfigID, parRequireWorldRestart, parRequireMCRestart, parTitle);
	}
}