package net.minecraftplus._api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
import net.minecraftplus._api.dictionary.Assets;
import net.minecraftplus._api.dictionary.Names;
import net.minecraftplus._api.minecraft.FuelHandler;
import net.minecraftplus._api.minecraft.Packet;
import net.minecraftplus._api.minecraft.PacketHandler;
import net.minecraftplus._api.util.StringUtil;

/**Minecraft Project*/
public final class MCP
{
	private MCP() {}

	public static final ModContainer mod()
	{
		return Loader.instance().activeModContainer();
	}

	public static final boolean isClientSide()
	{
		return !MinecraftServer.getServer().isDedicatedServer();
	}

	public static final boolean isServerSide()
	{
		return MinecraftServer.getServer().isDedicatedServer();
	}

	/**Register the event handler with {@link FMLCommonHandler} and {@link MinecraftForge}*/
	public static final Object eventHandler(Object parEventHandler)
	{
		FMLCommonHandler.instance().bus().register(parEventHandler);
		MinecraftForge.EVENT_BUS.register(parEventHandler);
		return parEventHandler;
	}

	/**Register the gui handler with network registry under active mod container*/
	public static final IGuiHandler guiHandler(IGuiHandler parGuiHandler)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(MCP.mod().getMod(), parGuiHandler);
		return parGuiHandler;
	}

	public static final void lang(String parID, String parLocalized)
	{
		Assets.EN_US.put(parID, parLocalized);
	}

	/**Register the item with unlocalized name*/
	public static final Item item(Item parItem)
	{
		GameRegistry.registerItem(parItem, Names.strip(parItem.getUnlocalizedName()));
		Assets.EN_US.put(parItem.getUnlocalizedName() + ".name", Names.of(parItem));
		return parItem;
	}

	/**Register the block of {@link ItemBlock} with unlocalized name*/
	public static final Block block(Block parBlock)
	{
		return block(parBlock, ItemBlock.class);
	}

	/**Register the block of item class with additional constructor arguments with unlocalized name*/
	public static final Block block(Block parBlock, Class<? extends ItemBlock> parClass, Object...parArgs)
	{
		GameRegistry.registerBlock(parBlock, parClass, Names.strip(parBlock.getUnlocalizedName()), parArgs);
		Assets.EN_US.put(parBlock.getUnlocalizedName() + ".name", Names.of(parBlock));
		return parBlock;
	}

	/**Register the recipe*/
	public static final void recipe(IRecipe parRecipe)
	{
		ModContainer mod = MCP.mod();
		if (RecipeSorter.getCategory(parRecipe) == Category.UNKNOWN)
		{
			RecipeSorter.register(Assets.resource(mod.getModId(), StringUtil.camelToUnderscore(parRecipe.getClass().getSimpleName())), parRecipe.getClass(), Category.SHAPED, "before:minecraft:shapeless");
		}
		GameRegistry.addRecipe(parRecipe);
	}

	/**Register the itemstack's item and metadata as fuel to burn for passed-in burn time*/
	public static final void fuel(ItemStack parItemStack, int parBurnTime)
	{
		FuelHandler.INSTANCE.put(parItemStack.getItem(), parItemStack.getMetadata(), parBurnTime);
	}

	/**Register the item as fuel to burn for passed-in burn time*/
	public static final void fuel(Item parItem, int parBurnTime)
	{
		FuelHandler.INSTANCE.put(parItem, parBurnTime);
	}

	/**Register the item to smelt to item stack with amount of experience*/
	public static final void smelt(Item parItem, ItemStack parItemStack, float parExp)
	{
		GameRegistry.addSmelting(parItem, parItemStack, parExp);
	}

	/**Register the block to smelt to item stack with amount of experience*/
	public static final void smelt(Block parBlock, ItemStack parItemStack, float parExp)
	{
		GameRegistry.addSmelting(parBlock, parItemStack, parExp);
	}

	/**Register the item stack to smelt to item stack with amount of experience*/
	public static final void smelt(ItemStack parStack, ItemStack parItemStack, float parExp)
	{
		GameRegistry.addSmelting(parStack, parItemStack, parExp);
	}

	/**Register the packet with packet handler*/
	public static final void packet(Class<? extends Packet> parPacketClass)
	{
		PacketHandler.INSTANCE.add(parPacketClass);
	}
}
