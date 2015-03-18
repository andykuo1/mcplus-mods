package net.minecraftplus.sweetpotato;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeedFood;
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

@Mod(modid = MCP.MCPID + ModSweetPotato.MODID, version = ModSweetPotato.VERSION, dependencies = "required-after:" + MCP.MODID)
public class ModSweetPotato extends MCP
{
	public static final String MODID = "sweetpotato";
	public static final String VERSION = "1.0.2";

	@Instance(MCP.MCPID + MODID)
	public static ModSweetPotato INSTANCE;

	@SidedProxy(clientSide = MCP.MCPDIR + "." + MODID + "." + MCP.CLIENTPROXY, serverSide = MCP.MCPDIR + "." + MODID + "." + MCP.COMMONPROXY)
	public static Proxy proxy;

	public static final Block sweetPotatoes = new BlockSweetPotato().setBlockName("sweet_potatoes");

	public static final Item sweetPotato = new ItemSeedFood(4, 0.2F, sweetPotatoes, Blocks.farmland).setUnlocalizedName("sweet_potato");
	public static final Item bakedSweetPotato = new ItemFoodstuff(8, 0.8F).setUnlocalizedName("baked_sweet_potato");

	public ModSweetPotato()
	{
		super(MODID, VERSION);
	}

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		super.PreInitialize(parEvent);

		MCP.ITEMS().add(sweetPotatoes).setOreDict("cropSweetPotato");
		MCP.ITEMS().add(sweetPotato).setOreDict("cropSweetPotato");
		MCP.ITEMS().add(bakedSweetPotato).setOreDict("foodBakedSweetPotato");
		MCP.ITEMS().initialize();

		this.addEventHandler(MinecraftForge.EVENT_BUS, new EventSweetPotatoDropHandler());

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
