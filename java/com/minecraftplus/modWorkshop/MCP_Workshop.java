package com.minecraftplus.modWorkshop;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._base.registry.LanguageRegistry;
import com.minecraftplus._base.registry.Registry;
import com.minecraftplus._common.item.ItemBase;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = "MCP_" + MCP_Workshop.MODBASE, name = "MC+ " + MCP_Workshop.MODBASE, version = "1.0.0")
public class MCP_Workshop extends MCP
{
	protected static final String MODBASE = "Workshop";

	@Instance("MCP_" + MCP_Workshop.MODBASE)
	public static MCP_Workshop INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
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
	public static final Item devourer = new ItemBase(tabWorkshop).setUnlocalizedName("devourer");

	public static final Block craftingConstructor = new BlockCraftingConstructor().setBlockName("crafting_constructor");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.1");

		LanguageRegistry.add(tabWorkshop);

		ItemRegistry.add(craftingConstructor);
		ItemRegistry.add(hungerWorm);
		ItemRegistry.add(heartWorm);
		ItemRegistry.add(devourer);

		Registry.addGuiHandler(this, new GuiHandler());

		proxy.register(Registry.RENDER);
		proxy.register(Registry.ENTITY);
		proxy.register(Registry.CUSTOM_ENTITY);
	}

	@EventHandler
	@Override
	public void loadInit(FMLInitializationEvent par1Event)
	{
		MCP.initEvent(par1Event);

		proxy.register(Registry.RECIPE);
	}

	@EventHandler
	@Override
	public void postInit(FMLPostInitializationEvent par1Event)
	{

	}
}