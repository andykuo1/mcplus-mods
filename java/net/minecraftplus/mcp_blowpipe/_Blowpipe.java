package net.minecraftplus.mcp_blowpipe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemSoup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
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
import net.minecraftplus._api.dictionary.FuelTimes;
import net.minecraftplus._api.dictionary.Models;
import net.minecraftplus._api.dictionary.Recipes;
import net.minecraftplus._api.dictionary.Resources;
import net.minecraftplus._api.factory.ModelFactory;
import net.minecraftplus._api.util.ArrayUtil;

@Mod(modid = _Blowpipe.MODID, version = _Blowpipe.VERSION, dependencies = "required-after:mcp_api")
public class _Blowpipe extends _Mod
{
	public static final String MODID = "mcp_blowpipe";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Blowpipe INSTANCE;

	public _Blowpipe() {}

	public static final Item blowpipe = new ItemBlowpipe().setUnlocalizedName("blowpipe");
	public static final Item grainMix = new ItemSoup(5).setMaxStackSize(1).setUnlocalizedName("grain_mix");

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.item(blowpipe);
		MCP.item(grainMix);

		super.PreInitialize(parEvent);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		MCP.fuel(blowpipe, FuelTimes.UNIT);

		GameRegistry.addShapedRecipe(new ItemStack(blowpipe, 1),
				"#Y",
				"YX",
				Character.valueOf('#'), Items.stick,
				Character.valueOf('X'), Items.gunpowder,
				Character.valueOf('Y'), Items.paper);

		GameRegistry.addShapedRecipe(new ItemStack(blowpipe, 1),
				"XY",
				"Y#",
				Character.valueOf('#'), Items.stick,
				Character.valueOf('X'), Items.gunpowder,
				Character.valueOf('Y'), Items.paper);

		GameRegistry.addShapedRecipe(new ItemStack(blowpipe, 1),
				"Y#",
				"XY",
				Character.valueOf('#'), Items.stick,
				Character.valueOf('X'), Items.gunpowder,
				Character.valueOf('Y'), Items.paper);

		GameRegistry.addShapedRecipe(new ItemStack(blowpipe, 1),
				"YX",
				"#Y",
				Character.valueOf('#'), Items.stick,
				Character.valueOf('X'), Items.gunpowder,
				Character.valueOf('Y'), Items.paper);

		proxy.Initialize();
		super.Initialize(parEvent);
	}

	@EventHandler
	@Override
	public void PostInitialize(FMLPostInitializationEvent parEvent)
	{
		//Get all seed items
		List<ItemSeeds> seedList = new ArrayList<ItemSeeds>();
		Iterator iter = Item.itemRegistry.getKeys().iterator();
		while(iter.hasNext())
		{
			Object obj = Item.itemRegistry.getObject(iter.next());
			if (obj instanceof ItemSeeds) seedList.add((ItemSeeds) obj);
		}

		//Grain mix recipe
		for(ItemSeeds[] recipe : ArrayUtil.findCombinations(seedList.toArray(new ItemSeeds[seedList.size()]), 3))
		{
			MCP.recipe(Recipes.SHAPELESS(new ItemStack(grainMix),
					recipe[0],
					recipe[1],
					recipe[2],
					Items.bowl));
		}

		//Seed fuels
		for(ItemSeeds seed : seedList)
		{
			MCP.fuel(seed, seedBurnTime);
		}

		super.PostInitialize(parEvent);
	}

	public static int seedBurnTime = FuelTimes.UNIT / 8;

	@Override
	public void Configure(Configuration parConfiguration)
	{
		ArrayList<Item> projectiles = new ArrayList<Item>();
		Property prop0 = parConfiguration.get("Projectiles", "itemID", new String[] {"minecraft:wheat_seeds", "minecraft:pumpkin_seeds", "minecraft:melon_seeds"});
		Property prop1 = parConfiguration.get("SeedFuel", "burnTime", FuelTimes.UNIT / 8);

		for(String id : prop0.getStringList())
		{
			Item item = Item.getByNameOrId(id);
			if (item != null) projectiles.add(item);
		}
		ItemBlowpipe.PROJECTILES = projectiles.toArray(new Item[projectiles.size()]);

		seedBurnTime = prop1.getInt();

		super.Configure(parConfiguration);
	}

	@Override
	public void Munge()
	{
		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(blowpipe) + ".json", Models.ITEM_BOW(
				Resources.ofTexture(blowpipe)
				).toJSON());
		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(grainMix) + ".json", Models.ITEM_BASE(
				Resources.ofTexture(grainMix)
				).toJSON());

		super.Munge();
	}
}