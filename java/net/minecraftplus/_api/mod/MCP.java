package net.minecraftplus._api.mod;

import java.util.ArrayList;

import net.minecraftforge.common.config.Configuration;
import net.minecraftplus._api.IConfigMod;
import net.minecraftplus._api.IMod;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.info.ModInfo;
import net.minecraftplus._api.registry.CommandRegistry;
import net.minecraftplus._api.registry.FuelRegistry;
import net.minecraftplus._api.registry.IconRegistry;
import net.minecraftplus._api.registry.ItemRegistry;
import net.minecraftplus._api.registry.LanguageRegistry;
import net.minecraftplus._api.registry.LivingRegistry;
import net.minecraftplus._api.registry.LootRegistry;
import net.minecraftplus._api.registry.PacketRegistry;
import net.minecraftplus._api.registry.RenderRegistry;
import net.minecraftplus._api.registry.SmeltingRegistry;
import net.minecraftplus._api.registry.TileEntityRegistry;
import net.minecraftplus._api.registry.WorldGenRegistry;
import net.minecraftplus._api.tool.DevTool;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = MCP.MODID, name = MCP.MCPNAME + "Mod API", version = MCP.VERSION)
public class MCP implements IMod
{
	//**************** MCP Statics ****************//

	public static final IMod MINECRAFT = MC.INSTANCE = new MC();
	/**Current Minecraft version when built*/
	public static final String MCVERSION = MINECRAFT.getVersion();

	/**MinecraftPlus Home URL*/
	public static final String HOMEURL = "http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/mod-packs/1289173-mc-mods-satchels-turtles-quivers-and-more";
	/**MinecraftPlus Update URL*/
	public static final String UPDATEURL = "";

	/**Prefix for all MinecraftPlus mod ids*/
	public static final String MCPID = "mc+";
	/**Prefix for all MinecraftPlus mod names*/
	public static final String MCPNAME = "MC+ ";
	/**MinecrafPlus mod directory*/
	public static final String MCPDIR = "net.minecraftplus";

	/**Is in deobfuscated environment?*/
	public static final boolean DEBUGMODE = true;

	public static final String CLIENTPROXY = "ClientProxy";
	public static final String COMMONPROXY = "CommonProxy";

	//**************** Mod API ****************//

	public static final String MODID = "minecraftplus";
	public static final String VERSION = "1.5";

	@Instance(MCP.MODID)
	public static MCP INSTANCE;

	@SidedProxy(clientSide = MCP.MCPDIR + "._api.mod." + MCP.CLIENTPROXY, serverSide = MCP.MCPDIR + "._api.mod." + MCP.COMMONPROXY)
	public static Proxy proxy;

	private static final ArrayList<IMod> MODLIST = new ArrayList<IMod>();

	private final String modid;
	private final String version;
	protected final ModInfo modinfo;

	private Configuration config;

	private final boolean isBaseMod;

	public MCP()
	{
		this(MODID, VERSION);

		this.modinfo.addDescription("The Core of all MC+ Mods!\nGet the Party started!");
	}

	public MCP(String parModID, String parVersion)
	{
		this.modid = parModID;
		this.version = parVersion;
		this.modinfo = new ModInfo(this);

		this.isBaseMod = parModID.equals(MCP.MODID) ? true : false;

		MCP.MODLIST.add(this);
	}

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		if (this.isBaseMod)
		{

		}
		else
		{
			DevTool.createModInfo("mcp_" + this.modid, this.modinfo.getMap());
		}

		if (this.isConfigMod())
		{
			this.config = new Configuration(parEvent.getSuggestedConfigurationFile());
			MCP.configure((IConfigMod)this, this.getConfig());
		}

		MCP.ITEMS().setDefaultMod(this);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		if (this.isBaseMod)
		{
			MCP.ITEMS().initialize();
			MCP.ENTITIES().initialize();

			this.addEventHandler(FMLCommonHandler.instance().bus(), this);

			proxy.register();

			MCP.WORLDGENS().initialize();
			MCP.LOOTS().initialize();
			MCP.PACKETS().initialize();

			MCP.LANGS().initialize();
		}
	}

	@EventHandler
	@Override
	public void PostInitialize(FMLPostInitializationEvent parEvent)
	{
		if (this.isBaseMod)
		{
			MCP.PACKETS().postInitialize();

			DevTool.todo();
		}
	}

	@EventHandler
	public void onServerStart(FMLServerStartingEvent par1Event)
	{
		if (this.isBaseMod)
		{
			MCP.COMMANDS().initialize();
		}
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent parEvent)
	{
		if (this.isBaseMod)
		{
			for(IMod mod : MCP.MODLIST)
			{
				if (parEvent.modID.equals(MCP.MCPID + mod.getModID()))
				{
					MCP.configure((IConfigMod)mod, mod.getConfig());
				}
			}
		}
	}

	@Override
	public final String getModID()
	{
		return this.modid;
	}

	@Override
	public final String getVersion()
	{
		return this.version;
	}

	@Override
	public final boolean isConfigMod()
	{
		return this instanceof IConfigMod;
	}

	@Override
	public Configuration getConfig()
	{
		return this.config;
	}

	protected final void addGuiHandler(IGuiHandler parGuiHandler)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(this, parGuiHandler);
	}

	protected final void addEventHandler(EventBus parEventBus, Object parObject)
	{
		parEventBus.register(parObject);
	}

	//**************** MCP Utilities ****************//

	public static String getModName(IMod parMod)
	{
		return MCP.MCPNAME + (parMod instanceof MCP && ((MCP)parMod).isBaseMod ? "Mod API" : Character.toUpperCase(parMod.getModID().charAt(0)) + parMod.getModID().substring(1));
	}

	public static void configure(IConfigMod parMod, Configuration parConfig)
	{
		parConfig.load();
		parMod.Configure(parConfig);
		if (parConfig.hasChanged())
		{
			parConfig.save();
		}
	}

	//**************** Mod Registry ****************//

	public static final ItemRegistry ITEMS()
	{
		return ItemRegistry.INSTANCE;
	}

	public static final LivingRegistry ENTITIES()
	{
		return LivingRegistry.INSTANCE;
	}

	public static final TileEntityRegistry TILES()
	{
		return TileEntityRegistry.INSTANCE;
	}

	public static final FuelRegistry FUELS()
	{
		return FuelRegistry.INSTANCE;
	}

	public static final SmeltingRegistry SMELTS()
	{
		return SmeltingRegistry.INSTANCE;
	}

	public static final WorldGenRegistry WORLDGENS()
	{
		return WorldGenRegistry.INSTANCE;
	}

	public static final LootRegistry LOOTS()
	{
		return LootRegistry.INSTANCE;
	}

	public static final PacketRegistry PACKETS()
	{
		return PacketRegistry.INSTANCE;
	}

	@SideOnly(Side.CLIENT)
	public static final RenderRegistry RENDERS()
	{
		return RenderRegistry.INSTANCE;
	}

	public static final IconRegistry ICONS()
	{
		return IconRegistry.INSTANCE;
	}

	public static final LanguageRegistry LANGS()
	{
		return LanguageRegistry.INSTANCE;
	}

	public static final CommandRegistry COMMANDS()
	{
		return CommandRegistry.INSTANCE;
	}
}
