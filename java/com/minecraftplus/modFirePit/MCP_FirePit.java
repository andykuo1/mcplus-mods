package com.minecraftplus.modFirePit;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.MCPMod;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._base.registry.ModRegistry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.D + MCP_FirePit.MODBASE, name = MCP.PRE + MCP_FirePit.MODBASE, version = "1.0.3", dependencies = MCP.DEPENDENCY)
public class MCP_FirePit implements MCPMod
{
	protected static final String MODBASE = "FirePit";

	@Instance(MCP.D + MCP_FirePit.MODBASE)
	public static MCP_FirePit INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	public static final Block firePit = new BlockFirePit(false).setBlockName("fire_pit");
	public static final Block firePitLit = new BlockFirePit(true).setBlockName("fire_pit_lit");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		ItemRegistry.add(firePit);
		ItemRegistry.addUnLocal(firePitLit);

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
		Object[] objlist = FurnaceRecipes.smelting().getSmeltingList().keySet().toArray();

		for(Object obj : objlist)
		{
			ItemStack itemstack = (ItemStack) obj;
			ItemStack itemstack1 = (ItemStack) FurnaceRecipes.smelting().getSmeltingList().get(obj);
			if (itemstack.getItem() instanceof ItemFood || itemstack1.getItem() instanceof ItemFood || itemstack1.getItem() == Items.coal)
			{
				FirePitRecipes.smelting().addSmelting(itemstack, itemstack1, FurnaceRecipes.smelting().func_151398_b(itemstack1));
			}
		}
	}
}