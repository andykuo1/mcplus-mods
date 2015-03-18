package net.minecraftplus.workshop;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.MCP;
import net.minecraftplus._common.CreativeTabsBase;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.MCPID + ModWorkshop.MODID, version = ModWorkshop.VERSION, dependencies = "required-after:" + MCP.MODID)
public class ModWorkshop extends MCP
{
	public static final String MODID = "workshop";
	public static final String VERSION = "1.0.0";

	@Instance(MCP.MCPID + MODID)
	public static ModWorkshop INSTANCE;

	@SidedProxy(clientSide = MCP.MCPDIR + "." + MODID + "." + MCP.CLIENTPROXY, serverSide = MCP.MCPDIR + "." + MODID + "." + MCP.COMMONPROXY)
	public static Proxy proxy;

	public static final CreativeTabs tabWorkshop = new CreativeTabsBase("workshop", Item.getItemFromBlock(Blocks.anvil));
	public static final Item hungerWorm = new ItemHungerWorm().setUnlocalizedName("hunger_worm");
	public static final Item heartWorm = new ItemHeartWorm().setUnlocalizedName("heart_worm");

	public static final Block craftingConstructor = new BlockCraftingConstructor().setBlockName("crafting_constructor");
	public static final Item bookLang = new ItemBookLanguage().setUnlocalizedName("book_of_language");

	public ModWorkshop()
	{
		super(MODID, VERSION);
	}

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		super.PreInitialize(parEvent);

		MCP.LANGS().add(tabWorkshop);

		MCP.ITEMS().add(craftingConstructor).setIcon("crafting_table", MCP.MINECRAFT);
		MCP.ITEMS().add(hungerWorm);
		MCP.ITEMS().add(heartWorm);
		MCP.ITEMS().add(bookLang).setIcon("book_normal", MCP.MINECRAFT).setLocalName("Book of Language");
		MCP.ITEMS().initialize();

		this.addGuiHandler(new GuiHandler());

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
