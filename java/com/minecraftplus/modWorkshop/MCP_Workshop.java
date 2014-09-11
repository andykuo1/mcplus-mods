package com.minecraftplus.modWorkshop;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.MCPMod;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._base.registry.LanguageRegistry;
import com.minecraftplus._base.registry.ModRegistry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = MCP.D + MCP_Workshop.MODBASE, name = MCP.PRE + MCP_Workshop.MODBASE, version = "1.0.0", dependencies = MCP.DEPENDENCY)
public class MCP_Workshop implements MCPMod
{
	protected static final String MODBASE = "Workshop";

	@Instance("MCP_" + MCP_Workshop.MODBASE)
	public static MCP_Workshop INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	public static CreativeTabs tabWorkshop = new CreativeTabs("workshop")
	{
		@SideOnly(Side.CLIENT)
		@Override
		public Item getTabIconItem()
		{
			return Item.getItemFromBlock(Blocks.anvil);
		}
	};

	public static final Item hungerWorm = new ItemHungerWorm().setUnlocalizedName("hunger_worm");
	public static final Item heartWorm = new ItemHeartWorm().setUnlocalizedName("heart_worm");

	public static final Block craftingConstructor = new BlockCraftingConstructor().setBlockName("crafting_constructor");

	public static final Item bookLang = new ItemBookLanguage().setUnlocalizedName("book_of_language");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		LanguageRegistry.add(tabWorkshop, "workshop");
		LanguageRegistry.add(bookLang, "Book of Language");

		ItemRegistry.add(craftingConstructor);
		ItemRegistry.add(hungerWorm);
		ItemRegistry.add(heartWorm);
		ItemRegistry.addUnLocal(bookLang);

		ModRegistry.addGuiHandler(this, new GuiHandler());

		proxy.register();
	}

	@EventHandler
	@Override
	public void mainInit(FMLInitializationEvent par1Event)
	{

	}

	@EventHandler
	@Override
	public void postInit(FMLPostInitializationEvent par1Event)
	{

	}
}