package net.minecraftplus._api.mod.info;

import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraftplus._api.IMod;
import net.minecraftplus._api.mod.MCP;

public class ModInfo
{
	private final IMod mod;
	private final Map<String, Object> map;

	public ModInfo(IMod parMod)
	{
		this.mod = parMod;
		this.map = new LinkedHashMap<String, Object>();
		this.add("modid", "mc+" + this.mod.getModID());
		this.add("name", MCP.getModName(this.mod));
		this.add("version", this.mod.getVersion());
		this.add("mcversion", MCP.MCVERSION);
		this.add("url", MCP.HOMEURL);
		this.add("updateUrl", MCP.UPDATEURL);
		this.add("authorList", "andykuo1");
		this.add("credits", "By andykuo1 and Special Thanks to You!");
		this.add("logoFile", "");
		this.add("screenshots", "");
		this.add("dependencies", MCP.MODID);
	}

	public void add(String parKey, String... parValues)
	{
		if ("authorList".equals(parKey) || "screenshots".equals(parKey) || "dependencies".equals(parKey))
		{
			this.map.put(parKey, parValues);
		}
		else if (parValues.length == 1)
		{
			this.map.put(parKey, parValues[0]);
		}
		else
		{
			this.map.put(parKey, parValues);
		}
	}

	public void addDescription(String parDescription)
	{
		this.add("description", parDescription);
	}

	public IMod getMod()
	{
		return this.mod;
	}

	public Map<String, Object> getMap()
	{
		return this.map;
	}
}
