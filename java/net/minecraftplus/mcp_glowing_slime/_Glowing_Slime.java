package net.minecraftplus.mcp_glowing_slime;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
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
import net.minecraftplus._api.MCF;
import net.minecraftplus._api.MCP;
import net.minecraftplus._api.base._Mod;
import net.minecraftplus._api.dictionary.Models;
import net.minecraftplus._api.dictionary.Recipes;
import net.minecraftplus._api.dictionary.Resources;
import net.minecraftplus._api.dictionary.Variants;
import net.minecraftplus._api.util.vector.Vec3f;
import net.minecraftplus._api.util.vector.Vec4f;

@Mod(modid = _Glowing_Slime.MODID, version = _Glowing_Slime.VERSION, dependencies = "required-after:mcp_api")
public class _Glowing_Slime extends _Mod
{
	public static final String MODID = "mcp_glowing_slime";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Glowing_Slime INSTANCE;

	public _Glowing_Slime() {}

	public static final Block glowingSlime = new BlockGlowingSlime(true).setUnlocalizedName("glowing_slime");
	public static final Block dimmingSlime = new BlockGlowingSlime(false).setUnlocalizedName("dimming_slime");
	public static final Item glowingSlimeball = new ItemGlowingSlimeball().setUnlocalizedName("glowing_slimeball");

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.lang("entity.mcp_glowing_slime.GlowingSlimeball.name", "Glowing Slimeball");

		MCP.block(glowingSlime);
		MCP.block(dimmingSlime);
		MCP.item(glowingSlimeball);

		super.PreInitialize(parEvent);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		MCP.recipe(Recipes.SHAPELESS(new ItemStack(glowingSlimeball), Items.slime_ball, Items.glowstone_dust, Items.gunpowder));

		proxy.Initialize();
		super.Initialize(parEvent);
	}

	@EventHandler
	@Override
	public void PostInitialize(FMLPostInitializationEvent parEvent)
	{
		super.PostInitialize(parEvent);
	}


	@Override
	public void Configure(Configuration parConfiguration)
	{
		super.Configure(parConfiguration);

		Property propDecay = parConfiguration.get("GENERAL", "enableDecay", true);

		BlockGlowingSlime.ENABLE_DECAY = propDecay.getBoolean();
	}

	@Override
	public void Munge()
	{
		MCF.makeItemModel(Resources.of(glowingSlimeball), Models.ITEM_BASE(
				Resources.ofTexture(glowingSlimeball)));

		MCF.makeItemModel(Resources.of(glowingSlime), Models.ITEM_BLOCK(Resources.ofModelParent(glowingSlime)));
		MCF.makeBlockModel(Resources.of(glowingSlime), Models.BLOCK_TEXTURE_ELEMENTS(new String[][]{
				new String[] {"particle", "blocks/slime"},
				new String[] {"texture", Resources.ofTexture(glowingSlime)},
				new String[] {"center", "blocks/glowstone"}},
				Models.BLOCK_ELEMENT(
						new Vec3f(5, 0, 5),
						new Vec3f(11, 3, 11),
						Models.BLOCK_ELEMENT_FACE("down", new Vec4f(5, 5, 11, 11), "#texture", "down", 0),
						Models.BLOCK_ELEMENT_FACE("up", new Vec4f(5, 11, 11, 5), "#texture", null, 0),
						Models.BLOCK_ELEMENT_FACE("north", new Vec4f(5, 13, 11, 16), "#texture", null, 0),
						Models.BLOCK_ELEMENT_FACE("south", new Vec4f(5, 13, 11, 16), "#texture", null, 0),
						Models.BLOCK_ELEMENT_FACE("west", new Vec4f(5, 13, 11, 16), "#texture", null, 0),
						Models.BLOCK_ELEMENT_FACE("east", new Vec4f(5, 13, 11, 16), "#texture", null, 0)
						),
						Models.BLOCK_ELEMENT(
								new Vec3f(6, 0, 6),
								new Vec3f(10, 2, 10),
								Models.BLOCK_ELEMENT_FACE("down", new Vec4f(6, 6, 10, 10), "#center", "down", 0),
								Models.BLOCK_ELEMENT_FACE("up", new Vec4f(6, 10, 10, 6), "#center", null, 0),
								Models.BLOCK_ELEMENT_FACE("north", new Vec4f(6, 13, 10, 15), "#center", null, 0),
								Models.BLOCK_ELEMENT_FACE("south", new Vec4f(6, 13, 10, 15), "#center", null, 0),
								Models.BLOCK_ELEMENT_FACE("west", new Vec4f(6, 13, 10, 15), "#center", null, 0),
								Models.BLOCK_ELEMENT_FACE("east", new Vec4f(6, 13, 10, 15), "#center", null, 0))
				));
		MCF.makeVariant(Resources.of(glowingSlime), Variants.DIRECTIONAL(
				new Object[] {Resources.ofModel(glowingSlime), 180, 0, 0},
				new Object[] {Resources.ofModel(glowingSlime), 0, 0, 0},
				new Object[] {Resources.ofModel(glowingSlime), 90, 0, 0},
				new Object[] {Resources.ofModel(glowingSlime), 90, 180, 0},
				new Object[] {Resources.ofModel(glowingSlime), 90, 270, 0},
				new Object[] {Resources.ofModel(glowingSlime), 90, 90, 0}));

		MCF.makeItemModel(Resources.of(dimmingSlime), Models.ITEM_BLOCK(Resources.ofModelParent(glowingSlime)));
		MCF.makeVariant(Resources.of(dimmingSlime), Variants.DIRECTIONAL(
				new Object[] {Resources.ofModel(glowingSlime), 180, 0, 0},
				new Object[] {Resources.ofModel(glowingSlime), 0, 0, 0},
				new Object[] {Resources.ofModel(glowingSlime), 90, 0, 0},
				new Object[] {Resources.ofModel(glowingSlime), 90, 180, 0},
				new Object[] {Resources.ofModel(glowingSlime), 90, 270, 0},
				new Object[] {Resources.ofModel(glowingSlime), 90, 90, 0}));

		super.Munge();
	}
}