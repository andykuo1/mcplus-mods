package net.minecraftplus.battlehearts;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftplus._api.IConfigMod;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.MCP;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.MCPID + ModBattlehearts.MODID, version = ModBattlehearts.VERSION, dependencies = "required-after:" + MCP.MODID)
public class ModBattlehearts extends MCP implements IConfigMod
{
	public static final String MODID = "battlehearts";
	public static final String VERSION = "1.2.0";

	@Instance(MCP.MCPID + MODID)
	public static ModBattlehearts INSTANCE;

	@SidedProxy(clientSide = MCP.MCPDIR + "." + MODID + "." + MCP.CLIENTPROXY, serverSide = MCP.MCPDIR + "." + MODID + "." + MCP.COMMONPROXY)
	public static Proxy proxy;

	public ModBattlehearts()
	{
		super(MODID, VERSION);

		this.modinfo.addDescription("Be brave. And gain your Battlehearts!");
	}

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		super.PreInitialize(parEvent);

		MCP.PACKETS().add(PacketMaxHealth.class);

		MCP.COMMANDS().add(new CommandMaxHealth());
		MCP.LANGS().add("commands.setmaxhealth.usage", "/setmaxhealth <player> [health]");

		Object obj = new EventHealthHandler();
		this.addEventHandler(MinecraftForge.EVENT_BUS, obj);
		this.addEventHandler(FMLCommonHandler.instance().bus(), obj);

		proxy.register();
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		super.Initialize(parEvent);
	}

	@EventHandler
	@Override
	public void PostInitialize(FMLPostInitializationEvent parEvent)
	{
		super.PostInitialize(parEvent);
	}

	@Override
	public void Configure(Configuration parConfig)
	{
		Property propMin = parConfig.get("GENERAL", "defaultHealth", 4);
		Property propMax = parConfig.get("GENERAL", "maxHealth", 60);
		Property propExp = parConfig.get("GENERAL", "expPerHealth", 5);
		Property propLevel = parConfig.get("GENERAL", "healthPerLevel", 1);
		Property propDeath = parConfig.get("GENERAL", "healthPerDeath", 2);
		Property propReset = parConfig.get("GENERAL", "resetOnDeath", false);
		Property propSleep = parConfig.get("GENERAL", "healOnSleep", false);

		ExtendedHealth.HP_MAX_BASE = propMin.getInt() * 2;
		ExtendedHealth.HP_MAX_MAX = propMax.getInt() * 2 - ExtendedHealth.HP_MAX_BASE;
		ExtendedHealth.EXP_PER_UP = propExp.getInt();
		ExtendedHealth.HP_MAX_UP = propLevel.getInt() * 2;
		ExtendedHealth.HP_MAX_DOWN = propDeath.getInt() * 2;
		ExtendedHealth.RESET_ON_SLEEP = propSleep.getBoolean();
		ExtendedHealth.RESET_ON_DEATH = propReset.getBoolean();
	}
}
