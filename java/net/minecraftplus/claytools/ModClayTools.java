package net.minecraftplus.claytools;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.MCP;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.MCPID + ModClayTools.MODID, version = ModClayTools.VERSION, dependencies = "required-after:" + MCP.MODID)
public class ModClayTools extends MCP
{
	public static final String MODID = "claytools";
	public static final String VERSION = "1.1.3";

	@Instance(MCP.MCPID + MODID)
	public static ModClayTools INSTANCE;

	@SidedProxy(clientSide = MCP.MCPDIR + "." + MODID + "." + MCP.CLIENTPROXY, serverSide = MCP.MCPDIR + "." + MODID + "." + MCP.COMMONPROXY)
	public static Proxy proxy;

	//NAME, BLOCKLEVEL, DURABILITY, SPEED, DAMAGE, ENCHANTABLITY
	public static ToolMaterial toolClay = EnumHelper.addToolMaterial("CLAY", 1, 104, 4.5F, 1.2F, 4);
	public static final Item swordClay = new ItemSwordClay(toolClay).setUnlocalizedName("clay_sword");
	public static final Item shovelClay = new ItemSpadeClay(toolClay).setUnlocalizedName("clay_shovel");
	public static final Item pickaxeClay = new ItemPickaxeClay(toolClay).setUnlocalizedName("clay_pickaxe");
	public static final Item axeClay = new ItemAxeClay(toolClay).setUnlocalizedName("clay_axe");
	public static final Item hoeClay = new ItemHoeClay(toolClay).setUnlocalizedName("clay_hoe");

	public ModClayTools()
	{
		super(MODID, VERSION);
	}

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		super.PreInitialize(parEvent);

		MCP.ITEMS().add(swordClay);
		MCP.ITEMS().add(shovelClay);
		MCP.ITEMS().add(pickaxeClay);
		MCP.ITEMS().add(axeClay);
		MCP.ITEMS().add(hoeClay);
		MCP.ITEMS().initialize();

		toolClay.setRepairItem(new ItemStack(Items.clay_ball));

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
