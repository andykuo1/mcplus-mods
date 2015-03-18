package net.minecraftplus.woodenbucket;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.MCP;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.MCPID + ModWoodenBucket.MODID, version = ModWoodenBucket.VERSION, dependencies = "required-after:" + MCP.MODID)
public class ModWoodenBucket extends MCP
{
	public static final String MODID = "woodenbucket";
	public static final String VERSION = "1.2.1";

	@Instance(MCP.MCPID + MODID)
	public static ModWoodenBucket INSTANCE;

	@SidedProxy(clientSide = MCP.MCPDIR + "." + MODID + "." + MCP.CLIENTPROXY, serverSide = MCP.MCPDIR + "." + MODID + "." + MCP.COMMONPROXY)
	public static Proxy proxy;

	public static final Item woodenBucketEmpty = new ItemWoodenBucketEmpty().setUnlocalizedName("wooden_bucket");
	public static final Item woodenBucketWater = new ItemWoodenBucket(Blocks.flowing_water).setContainerItem(woodenBucketEmpty).setUnlocalizedName("wooden_water_bucket");
	public static final Item woodenBucketMilk = new ItemWoodenBucketMilk().setContainerItem(woodenBucketEmpty).setUnlocalizedName("wooden_milk_bucket");

	public ModWoodenBucket()
	{
		super(MODID, VERSION);
	}

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		super.PreInitialize(parEvent);

		MCP.ITEMS().add(woodenBucketEmpty);
		MCP.ITEMS().add(woodenBucketWater);
		MCP.ITEMS().add(woodenBucketMilk);
		MCP.ITEMS().initialize();

		this.addEventHandler(MinecraftForge.EVENT_BUS, new EventFillCauldronHandler());

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
