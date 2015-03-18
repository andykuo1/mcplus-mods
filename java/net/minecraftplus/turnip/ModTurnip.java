package net.minecraftplus.turnip;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
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

@Mod(modid = MCP.MCPID + ModTurnip.MODID, version = ModTurnip.VERSION, dependencies = "required-after:" + MCP.MODID)
public class ModTurnip extends MCP
{
	public static final String MODID = "turnip";
	public static final String VERSION = "1.1.1";

	@Instance(MCP.MCPID + MODID)
	public static ModTurnip INSTANCE;

	@SidedProxy(clientSide = MCP.MCPDIR + "." + MODID + "." + MCP.CLIENTPROXY, serverSide = MCP.MCPDIR + "." + MODID + "." + MCP.COMMONPROXY)
	public static Proxy proxy;

	public static final Block turnips = new BlockTurnip().setBlockName("turnips");

	public static final Item turnip = new ItemFoodstuff(2, 0.4F).setUnlocalizedName("turnip");
	public static final Item turnipSeeds = new ItemSeeds(turnips, Blocks.farmland).setUnlocalizedName("turnip_seeds");
	public static final Item turnipSoup = new ItemFoodstuff(8, 0.6F).setUnlocalizedName("turnip_soup");

	public ModTurnip()
	{
		super(MODID, VERSION);
	}

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		super.PreInitialize(parEvent);

		MCP.ITEMS().add(turnips).setOreDict("cropTurnip");
		MCP.ITEMS().add(turnip).setOreDict("cropTurnip");
		MCP.ITEMS().add(turnipSeeds).setOreDict("seedTurnip");
		MCP.ITEMS().add(turnipSoup).setOreDict("soupTurnip");
		MCP.ITEMS().initialize();

		MinecraftForge.addGrassSeed(new ItemStack(turnipSeeds), 1);

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
