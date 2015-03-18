package net.minecraftplus._api.tool;

import net.minecraft.util.ResourceLocation;
import net.minecraftplus._api.IMod;
import net.minecraftplus._api.mod.MCP;
import net.minecraftplus._api.registry.IconRegistry.DummyIcon;

public class ResTool
{
	public static final String ITEMS = "textures/items";
	public static final String BLOCKS = "textures/blocks";
	public static final String ENTITIES = "textures/entity";
	public static final String GUI = "textures/gui";
	public static final String ARMORS = "textures/armors";
	
	/**MinecraftPlus resource directory*/
	public static String getResourceDir(IMod parMod)
	{
		return parMod == null || parMod == MCP.INSTANCE ? MCP.MODID : parMod == MCP.MINECRAFT ? parMod.getModID() : "mcp_" + parMod.getModID();
	}

	/**
	 * Get {@link ResourceLocation} to texture
	 * 
	 * @param parObject the texture name
	 * @param parResPath the path to the texture
	 * @param parMod the mod registered under
	 * @return the location of the texture
	 * */
	public static ResourceLocation getResource(String parObject, String parResPath, IMod parMod)
	{
		DummyIcon item = new DummyIcon(parObject, parObject);
		MCP.ICONS().add(item, parMod);

		return ResTool.getResource(parObject, ResTool.getResourceDir(parMod), parResPath);
	}

	/**
	 * Get {@link ResourceLocation} to texture
	 * 
	 * @param parObject the texture name
	 * @param parResPath the path to the texture
	 * @param parIsMinecraftDir if path is in vanilla Minecraft. False if in MinecraftPlus
	 * 
	 * @return the location of the texture
	 * */
	public static ResourceLocation getResource(String parObject, String parResPath, boolean parIsMinecraftDir)
	{
		if (parIsMinecraftDir)
		{
			return ResTool.getResource(parObject, parResPath, MCP.MINECRAFT);
		}
		else
		{
			return ResTool.getResource(parObject, parResPath, MCP.INSTANCE);
		}
	}

	private static ResourceLocation getResource(String parObject, String parModDir, String parResPath)
	{
		return new ResourceLocation(parModDir + ":" + (!parResPath.isEmpty() ? parResPath + "/" : "") + parObject + ".png");
	}
}
