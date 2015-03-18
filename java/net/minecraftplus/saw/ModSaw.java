package net.minecraftplus.saw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.MCP;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.MCPID + ModSaw.MODID, version = ModSaw.VERSION, dependencies = "required-after:" + MCP.MODID)
public class ModSaw extends MCP
{
	public static final String MODID = "saw";
	public static final String VERSION = "1.0.2";

	@Instance(MCP.MCPID + MODID)
	public static ModSaw INSTANCE;

	@SidedProxy(clientSide = MCP.MCPDIR + "." + MODID + "." + MCP.CLIENTPROXY, serverSide = MCP.MCPDIR + "." + MODID + "." + MCP.COMMONPROXY)
	public static Proxy proxy;

	public static final Map<WoodPlank, ItemStack> woodPlanks = new HashMap<WoodPlank, ItemStack>();

	public static final Block saw = new BlockSaw().setBlockName("saw");

	public ModSaw()
	{
		super(MODID, VERSION);
	}

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		super.PreInitialize(parEvent);

		MCP.ITEMS().add(saw);
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

		List objects = CraftingManager.getInstance().getRecipeList();

		for(Object obj : objects)
		{
			if (obj instanceof ShapedRecipes)
			{
				ItemStack[] recipe = ((ShapedRecipes) obj).recipeItems;
				if (recipe.length == 1 && recipe[0] != null)
				{
					Block block = Block.getBlockFromItem(recipe[0].getItem());
					if (block instanceof BlockLog)
					{
						WoodPlank woodplank = new WoodPlank(block, recipe[0].getItemDamage());
						woodPlanks.put(woodplank, ((ShapedRecipes) obj).getRecipeOutput());
					}
				}
			}

			if (obj instanceof ShapelessRecipes)
			{
				List recipe = ((ShapelessRecipes) obj).recipeItems;
				if (recipe.size() == 1 && recipe.get(0) instanceof ItemStack)
				{
					Block block = Block.getBlockFromItem(((ItemStack) recipe.get(0)).getItem());
					if (block instanceof BlockLog)
					{
						WoodPlank woodplank = new WoodPlank(block, ((ItemStack) recipe.get(0)).getItemDamage());
						woodPlanks.put(woodplank, ((ShapelessRecipes) obj).getRecipeOutput());
					}
				}
			}
		}

		List<IRecipe> trashBin = new ArrayList<IRecipe>();

		for(Object obj : objects)
		{
			IRecipe recipe = (IRecipe) obj;
			if (recipe.getRecipeOutput() != null)
			{
				if (recipe.getRecipeOutput().getItem() == Item.getItemFromBlock(Blocks.planks))
				{
					trashBin.add(recipe);
				}
			}
		}

		for(IRecipe recipe : trashBin)
		{
			CraftingManager.getInstance().getRecipeList().remove(recipe);
		}
	}
}
