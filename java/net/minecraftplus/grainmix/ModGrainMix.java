package net.minecraftplus.grainmix;

import net.minecraft.init.Items;
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

@Mod(modid = MCP.MCPID + ModGrainMix.MODID, version = ModGrainMix.VERSION, dependencies = "required-after:" + MCP.MODID)
public class ModGrainMix extends MCP
{
	public static final String MODID = "grainmix";
	public static final String VERSION = "1.2.2";

	@Instance(MCP.MCPID + MODID)
	public static ModGrainMix INSTANCE;

	@SidedProxy(clientSide = MCP.MCPDIR + "." + MODID + "." + MCP.CLIENTPROXY, serverSide = MCP.MCPDIR + "." + MODID + "." + MCP.COMMONPROXY)
	public static Proxy proxy;

	public static final Item grainMix = new ItemFoodstuff(5, 0.2F, false).setReturnItem(Items.bowl).setMaxStackSize(1).setUnlocalizedName("grain_mix");

	public ModGrainMix()
	{
		super(MODID, VERSION);
	}

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		super.PreInitialize(parEvent);

		MCP.ITEMS().add(grainMix).setOreDict("foodGrainMix");
		MCP.ITEMS().initialize();

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
