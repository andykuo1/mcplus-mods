package net.minecraftplus.mcp_battle_hearts;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftplus._api.MCP;
import net.minecraftplus._api.base._Mod;

@Mod(modid = _Battle_Hearts.MODID, version = _Battle_Hearts.VERSION, dependencies = "required-after:mcp_api")
public class _Battle_Hearts extends _Mod
{
	public static final String MODID = "mcp_battle_hearts";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Battle_Hearts INSTANCE;

	public _Battle_Hearts() {}

	public static EventHandlerBattleHearts eventHandler = new EventHandlerBattleHearts();

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.eventHandler(eventHandler);

		MCP.packet(PacketMaxHealth.class);
		
		MCP.lang("commands.setmaxhealth.usage", "/setmaxhealth <player> [health]");

		super.PreInitialize(parEvent);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		proxy.Initialize();
		super.Initialize(parEvent);
	}

	@EventHandler
	@Override
	public void PostInitialize(FMLPostInitializationEvent parEvent)
	{
		super.PostInitialize(parEvent);
	}


	@Override
	public void Configure(Configuration parConfiguration)
	{
		Property propMin = parConfiguration.get("GENERAL", "defaultHealth", 4);
		Property propMax = parConfiguration.get("GENERAL", "maxHealth", 60);
		Property propExp = parConfiguration.get("GENERAL", "expPerHealth", 5);
		Property propLevel = parConfiguration.get("GENERAL", "healthPerLevel", 1);
		Property propDeath = parConfiguration.get("GENERAL", "healthPerDeath", 2);
		Property propReset = parConfiguration.get("GENERAL", "resetOnDeath", false);
		Property propSleep = parConfiguration.get("GENERAL", "healOnSleep", false);

		ExtendedPropertyHealth.HP_MAX_BASE = propMin.getInt() * 2;
		ExtendedPropertyHealth.HP_MAX_MAX = propMax.getInt() * 2 - ExtendedPropertyHealth.HP_MAX_BASE;
		ExtendedPropertyHealth.EXP_PER_UP = propExp.getInt();
		ExtendedPropertyHealth.HP_MAX_UP = propLevel.getInt() * 2;
		ExtendedPropertyHealth.HP_MAX_DOWN = propDeath.getInt() * 2;
		ExtendedPropertyHealth.RESET_ON_SLEEP = propSleep.getBoolean();
		ExtendedPropertyHealth.RESET_ON_DEATH = propReset.getBoolean();

		super.Configure(parConfiguration);
	}

	@Override
	public void Munge()
	{
		super.Munge();
	}
}