package net.minecraftplus.berries;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.MCP;
import net.minecraftplus._common.ItemFoodstuff;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.MCPID + ModBerries.MODID, version = ModBerries.VERSION, dependencies = "required-after:" + MCP.MODID)
public class ModBerries extends MCP
{
	public static final String MODID = "berries";
	public static final String VERSION = "1.2.0";

	@Instance(MCP.MCPID + MODID)
	public static ModBerries INSTANCE;

	@SidedProxy(clientSide = MCP.MCPDIR + "." + MODID + "." + MCP.CLIENTPROXY, serverSide = MCP.MCPDIR + "." + MODID + "." + MCP.COMMONPROXY)
	public static Proxy proxy;

	public static final Item raspberry = new ItemFoodstuff(2, 0.1F).setUnlocalizedName("raspberry");
	public static final Item blueberry = new ItemFoodstuff(2, 0.1F).setUnlocalizedName("blueberry");
	public static final Item blackberry = new ItemFoodstuff(2, 0.1F).setUnlocalizedName("blackberry");
	public static final Item cranberry = new ItemFoodstuff(2, 0.1F).setUnlocalizedName("cranberry");
	public static final Block raspberryBush = new BlockBerryBush(raspberry).setBlockName("raspberry_bush");
	public static final Block blueberryBush = new BlockBerryBush(blueberry).setBlockName("blueberry_bush");
	public static final Block blackberryBush = new BlockBerryBush(blackberry).setBlockName("blackberry_bush");
	public static final Block cranberryBush = new BlockBerryBush(cranberry).setBlockName("cranberry_bush");

	public ModBerries()
	{
		super(MODID, VERSION);
	}

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		super.PreInitialize(parEvent);

		MCP.ITEMS().add(raspberry).setOreDict("fruitRaspberry");
		MCP.ITEMS().add(blueberry).setOreDict("fruitBlueberry");
		MCP.ITEMS().add(blackberry).setOreDict("fruitBlackberry");
		MCP.ITEMS().add(cranberry).setOreDict("fruitCranberry");

		MCP.ITEMS().add(raspberryBush).setOreDict("cropRaspberry");
		MCP.ITEMS().add(blueberryBush).setOreDict("cropBlueberry");
		MCP.ITEMS().add(blackberryBush).setOreDict("cropBlackberry");
		MCP.ITEMS().add(cranberryBush).setOreDict("cropCranberry");
		MCP.ITEMS().initialize();

		MCP.WORLDGENS().add(new WorldGenBerryBush(raspberryBush));
		MCP.WORLDGENS().add(new WorldGenBerryBush(blueberryBush));
		MCP.WORLDGENS().add(new WorldGenBerryBush(blackberryBush));
		MCP.WORLDGENS().add(new WorldGenBerryBush(cranberryBush));

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
}
