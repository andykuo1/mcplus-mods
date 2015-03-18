package net.minecraftplus.cabbage;

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

@Mod(modid = MCP.MCPID + ModCabbage.MODID, version = ModCabbage.VERSION, dependencies = "required-after:" + MCP.MODID)
public class ModCabbage extends MCP
{
	public static final String MODID = "cabbage";
	public static final String VERSION = "1.0.0";

	@Instance(MCP.MCPID + MODID)
	public static ModCabbage INSTANCE;

	@SidedProxy(clientSide = MCP.MCPDIR + "." + MODID + "." + MCP.CLIENTPROXY, serverSide = MCP.MCPDIR + "." + MODID + "." + MCP.COMMONPROXY)
	public static Proxy proxy;

	public static final Block cabbages = new BlockCabbage().setBlockName("cabbages");

	public static final Item cabbage = new ItemFoodstuff(2, 0.4F).setUnlocalizedName("cabbage");
	public static final Item cabbageSeeds = new ItemSeeds(cabbages, Blocks.farmland).setUnlocalizedName("cabbage_seeds");

	public ModCabbage()
	{
		super(MODID, VERSION);
	}

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		super.PreInitialize(parEvent);

		MCP.ITEMS().add(cabbages).setOreDict("cropCabbage");
		MCP.ITEMS().add(cabbage).setOreDict("cropCabbage");
		MCP.ITEMS().add(cabbageSeeds).setOreDict("seedCabbage");
		MCP.ITEMS().initialize();

		MinecraftForge.addGrassSeed(new ItemStack(cabbageSeeds), 1);

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
