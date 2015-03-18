package net.minecraftplus.quartz;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
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

@Mod(modid = MCP.MCPID + ModQuartz.MODID, version = ModQuartz.VERSION, dependencies = "required-after:" + MCP.MODID)
public class ModQuartz extends MCP
{
	public static final String MODID = "quartz";
	public static final String VERSION = "1.3.3";

	@Instance(MCP.MCPID + MODID)
	public static ModQuartz INSTANCE;

	@SidedProxy(clientSide = MCP.MCPDIR + "." + MODID + "." + MCP.CLIENTPROXY, serverSide = MCP.MCPDIR + "." + MODID + "." + MCP.COMMONPROXY)
	public static Proxy proxy;

	//NAME, BLOCKLEVEL, DURABILITY, SPEED, DAMAGE, ENCHANTABLITY
	public static ToolMaterial toolQuartz = EnumHelper.addToolMaterial("QUARTZ", 0, 342, 3.0F, 2.0F, 4);
	public static final Item swordQuartz = new ItemSwordQuartz(toolQuartz).setUnlocalizedName("quartz_sword");
	public static final Item shovelQuartz = new ItemSpadeQuartz(toolQuartz).setUnlocalizedName("quartz_shovel");
	public static final Item pickaxeQuartz = new ItemPickaxeQuartz(toolQuartz).setUnlocalizedName("quartz_pickaxe");
	public static final Item axeQuartz = new ItemAxeQuartz(toolQuartz).setUnlocalizedName("quartz_axe");
	public static final Item hoeQuartz = new ItemHoeQuartz(toolQuartz).setUnlocalizedName("quartz_hoe");

	//NAME, DEFENSE, DAMAGE-SPREAD, ENCHANTABILITY
	public static ArmorMaterial armorQuartz = EnumHelper.addArmorMaterial("QUARTZ", 13, new int[] { 3, 4, 4, 2 }, 10);
	public static final Item helmetQuartz = new ItemArmorQuartz(armorQuartz, 1, 0).setUnlocalizedName("quartz_helmet");
	public static final Item plateQuartz = new ItemArmorQuartz(armorQuartz, 1, 1).setUnlocalizedName("quartz_chestplate");
	public static final Item legsQuartz = new ItemArmorQuartz(armorQuartz, 1, 2).setUnlocalizedName("quartz_leggings");
	public static final Item bootsQuartz = new ItemArmorQuartz(armorQuartz, 1, 3).setUnlocalizedName("quartz_boots");

	public ModQuartz()
	{
		super(MODID, VERSION);
	}

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		super.PreInitialize(parEvent);

		MCP.ITEMS().add(swordQuartz);
		MCP.ITEMS().add(shovelQuartz);
		MCP.ITEMS().add(pickaxeQuartz);
		MCP.ITEMS().add(axeQuartz);
		MCP.ITEMS().add(hoeQuartz);
		MCP.ITEMS().add(helmetQuartz);
		MCP.ITEMS().add(plateQuartz);
		MCP.ITEMS().add(legsQuartz);
		MCP.ITEMS().add(bootsQuartz);
		MCP.ITEMS().initialize();

		toolQuartz.setRepairItem(new ItemStack(Items.quartz));
		armorQuartz.customCraftingMaterial = Items.quartz;


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
