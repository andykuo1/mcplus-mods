package net.minecraftplus.firepit;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.MCP;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.MCPID + ModFirePit.MODID, version = ModFirePit.VERSION, dependencies = "required-after:" + MCP.MODID)
public class ModFirePit extends MCP
{
	public static final String MODID = "firepit";
	public static final String VERSION = "1.0.4";

	@Instance(MCP.MCPID + MODID)
	public static ModFirePit INSTANCE;

	@SidedProxy(clientSide = MCP.MCPDIR + "." + MODID + "." + MCP.CLIENTPROXY, serverSide = MCP.MCPDIR + "." + MODID + "." + MCP.COMMONPROXY)
	public static Proxy proxy;

	public static final Block firePit = new BlockFirePit(false).setBlockName("fire_pit");
	public static final Block firePitLit = new BlockFirePit(true).setBlockName("fire_pit_lit");

	public ModFirePit()
	{
		super(MODID, VERSION);
	}

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		super.PreInitialize(parEvent);

		MCP.ITEMS().add(firePit);
		MCP.ITEMS().add(firePitLit).setLocalName(null);
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
