package com.minecraftplus._base.modinfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import net.minecraft.client.Minecraft;

import com.minecraftplus._base.MCP;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ModContainer;

public class ModInfo
{
	private static final String logo = "";
	private static final String url = "http://www.minecraftforum.net/topic/1806521-172-mc-mods-satchels-turtles-quivers-and-more/";
	
	public static void createModInfo(MCP par1MCP, String par2Description)
	{
		ModContainer mod = FMLCommonHandler.instance().findContainerFor(par1MCP);
		File dir = new File(Minecraft.getMinecraft().mcDataDir, "mod_workshop/" + mod.getName());
		dir.mkdir();
		File file = new File(dir, "mcmod.info");
		
		try
		{
			file.createNewFile();
			Writer writer = new BufferedWriter(new FileWriter(file));
			writer.write("[");
			writer.write("\n{");
			writer.write("\n\"modid\": \"" + mod.getModId() + "\"" + ",");
			writer.write("\n\"name\": \"" + mod.getName() + "\"" + ",");
			writer.write("\n\"description\": \"" + par2Description + "\"" + ",");
			writer.write("\n\"version\": \"" + mod.getVersion() + "\"" + ",");
			writer.write("\n\"mcversion\": \"" + "1.7.2" + "\"" + ",");
			writer.write("\n\"url\": \"" + url + "\"" + ",");
			writer.write("\n\"authors\": " + "[" + "\"andykuo1\"" + "]" + ",");
			writer.write("\n\"credits\": \"" + "andykuo1" + "\"" + ",");
			writer.write("\n\"logoFile\": \"" + logo + "\"" + ",");
			writer.write("\n\"dependencies\": " + "[" + "\"Forge\"" + "]" + ",");
			writer.write("\n}");
			writer.write("\n]");
			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
