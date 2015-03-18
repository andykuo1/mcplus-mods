package net.minecraftplus.skullcandle;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
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

@Mod(modid = MCP.MCPID + ModSkullCandle.MODID, version = ModSkullCandle.VERSION, dependencies = "required-after:" + MCP.MODID)
public class ModSkullCandle extends MCP implements IConfigMod
{
	public static final String MODID = "skullcandle";
	public static final String VERSION = "1.0.2";

	@Instance(MCP.MCPID + MODID)
	public static ModSkullCandle INSTANCE;

	@SidedProxy(clientSide = MCP.MCPDIR + "." + MODID + "." + MCP.CLIENTPROXY, serverSide = MCP.MCPDIR + "." + MODID + "." + MCP.COMMONPROXY)
	public static Proxy proxy;

	public static GuiConfigHandler configHandler;
	public static boolean isTorchVariant;

	public static final Block skullCandles = new BlockSkullCandle().setBlockName("skull_candles");
	public static final Block redstoneSkullCandles = new BlockRedstoneSkullCandle().setBlockName("redstone_skull_candles");
	public static final Item skullCandle = new ItemSkullCandle().setUnlocalizedName("skull_candle");
	public static final Item redstoneSkullCandle = new ItemRedstoneSkullCandle().setUnlocalizedName("redstone_skull_candle");

	public ModSkullCandle()
	{
		super(MODID, VERSION);
	}

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		super.PreInitialize(parEvent);

		MCP.LANGS().add("item.skull_candle.skeleton.name", "Skeleton Skull Candle");
		MCP.LANGS().add("item.skull_candle.wither.name", "Wither Skeleton Skull Candle");
		MCP.LANGS().add("item.skull_candle.zombie.name", "Zombie Skull Candle");
		MCP.LANGS().add("item.skull_candle.char.name", "Skull Candle");
		MCP.LANGS().add("item.skull_candle.creeper.name", "Creeper Skull Candle");

		MCP.LANGS().add("item.redstone_skull_candle.skeleton.name", "Powered Skeleton Skull Candle");
		MCP.LANGS().add("item.redstone_skull_candle.wither.name", "Powered Wither Skeleton Skull Candle");
		MCP.LANGS().add("item.redstone_skull_candle.zombie.name", "Powered Zombie Skull Candle");
		MCP.LANGS().add("item.redstone_skull_candle.char.name", "Powered Skull Candle");
		MCP.LANGS().add("item.redstone_skull_candle.creeper.name", "Powered Creeper Skull Candle");

		MCP.ITEMS().add(skullCandles);
		MCP.ITEMS().add(redstoneSkullCandles);
		MCP.ITEMS().add(skullCandle);
		MCP.ITEMS().add(redstoneSkullCandle);
		MCP.ITEMS().initialize();

		this.addEventHandler(FMLCommonHandler.instance().bus(), configHandler = new GuiConfigHandler(new Configuration(parEvent.getSuggestedConfigurationFile()), this, MCP.MCPID + this.getModID()));
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
		String s = parConfig.getString("Model Type", Configuration.CATEGORY_GENERAL, "TORCH", "", new String[] {"TORCH", "GLOWING"});
		isTorchVariant = "TORCH".equals(s);
	}
}
