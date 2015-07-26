package net.minecraftplus.mcp_saw;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftplus._api.MCF;
import net.minecraftplus._api.MCP;
import net.minecraftplus._api.base._Mod;
import net.minecraftplus._api.dictionary.Models;
import net.minecraftplus._api.dictionary.Resources;
import net.minecraftplus._api.dictionary.Variants;
import net.minecraftplus._api.factory.ModelFactory;
import net.minecraftplus._api.factory.VariantFactory;

@Mod(modid = _Saw.MODID, version = _Saw.VERSION, dependencies = "required-after:mcp_api")
public class _Saw extends _Mod
{
	public static final String MODID = "mcp_saw";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Saw INSTANCE;

	public _Saw() {}

	public static final Block saw = new BlockSaw().setHardness(2.0F).setStepSound(Block.soundTypeWood).setUnlocalizedName("saw");	

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.block(saw);

		super.PreInitialize(parEvent);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		GameRegistry.addShapedRecipe(new ItemStack(saw),
				"XX",
				"##",
				Character.valueOf('#'), Blocks.log,
				Character.valueOf('X'), Items.flint);

		GameRegistry.addShapedRecipe(new ItemStack(saw),
				"XX",
				"##",
				Character.valueOf('#'), Blocks.log2,
				Character.valueOf('X'), Items.flint);

		proxy.Initialize();
		super.Initialize(parEvent);
	}

	@EventHandler
	@Override
	public void PostInitialize(FMLPostInitializationEvent parEvent)
	{
		List recipes = CraftingManager.getInstance().getRecipeList();
		List dump = new ArrayList<Object>();

		for(Object obj : recipes)
		{
			IRecipe recipe = (IRecipe) obj;
			ItemStack output = recipe.getRecipeOutput();
			if (output == null) continue;

			ItemStack[] stacks = null;
			if (recipe instanceof ShapedRecipes)
			{
				stacks = ((ShapedRecipes) recipe).recipeItems;
			}
			else if (recipe instanceof ShapelessRecipes)
			{
				List<ItemStack> list = ((ShapelessRecipes) recipe).recipeItems;
				stacks = list.toArray(new ItemStack[list.size()]);
			}

			if (stacks == null || stacks.length != 1) continue;
			if (stacks[0] != null)
			{
				ItemStack itemstack = stacks[0];
				Block block = Block.getBlockFromItem(itemstack.getItem());
				if (block instanceof BlockLog)
				{
					WoodRegistry.INSTANCE.put(block.getStateFromMeta(itemstack.getItemDamage()), output);
					dump.add(recipe);
				}
			}
		}

		for(Object recipe : dump)
		{
			CraftingManager.getInstance().getRecipeList().remove(recipe);
		}

		super.PostInitialize(parEvent);
	}


	@Override
	public void Configure(Configuration parConfiguration)
	{
		super.Configure(parConfiguration);
	}

	@Override
	public void Munge()
	{
		ModelFactory.write(MCF.blockModelDirectory(MODID), Resources.of(saw) + ".json", Models.BLOCK_MULTI_TEXTURE(
				Resources.ofTexture(saw, "side"),
				Resources.ofTexture(saw, "bottom"),
				Resources.ofTexture(saw, "top"),
				Resources.ofTexture(saw, "side"),
				Resources.ofTexture(saw, "side"),
				Resources.ofTexture(saw, "side"),
				Resources.ofTexture(saw, "side")
				).toJSON());
		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(saw) + ".json", Models.ITEM_BLOCK(
				Resources.ofModelParent(saw)
				).toJSON());
		VariantFactory.write(MCF.variantDirectory(MODID), Resources.of(saw) + ".json", Variants.DIRECTIONAL(
				new Object[]{Resources.ofModel(saw), 180, 0, 0},
				new Object[]{Resources.ofModel(saw), 0, 0, 0},
				new Object[]{Resources.ofModel(saw), 90, 0, 0},
				new Object[]{Resources.ofModel(saw), 90, 180, 0},
				new Object[]{Resources.ofModel(saw), 90, 270, 0},
				new Object[]{Resources.ofModel(saw), 90, 90, 0})
				.toJSON());

		super.Munge();
	}
}