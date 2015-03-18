package net.minecraftplus._api.registry;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraftplus._api.IMod;
import net.minecraftplus._api.mod.MCP;
import net.minecraftplus._api.registry.ItemRegistry.DummyBlock;
import net.minecraftplus._api.registry.ItemRegistry.DummyItem;
import net.minecraftplus._api.registry.ItemRegistry.DummyItemBase;
import net.minecraftplus._api.tool.ResTool;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Registry for resource locations according to mod directory.
 * <br> Also registers {@link IIcon} for items and blocks.
 * */
@SideOnly(Side.CLIENT)
public class IconRegistry extends RegistryMap<DummyItemBase, IMod>
{
	public static final IconRegistry INSTANCE = new IconRegistry();

	//**************** Registry Setup ****************//

	@Override
	public void initialize()
	{
		for(DummyItemBase dummy : MCP.ITEMS().registry)
		{
			if (dummy.hasMod())
			{
				if (dummy.hasIcon())
				{
					if (dummy.getItem() instanceof Item)
					{
						Item item = (Item) dummy.getItem();
						if (dummy.hasIconDir())
						{
							this.register(item, dummy.getIconDir(), dummy.getIcon());
						}
						else
						{
							this.register(item, ResTool.getResourceDir(dummy.getMod()), dummy.getIcon());
						}
					}
					else if (dummy.getItem() instanceof Block)
					{
						Block block = (Block) dummy.getItem();
						if (dummy.hasIconDir())
						{
							this.register(block, dummy.getIconDir(), dummy.getIcon());
						}
						else
						{
							this.register(block, ResTool.getResourceDir(dummy.getMod()), dummy.getIcon());
						}
					}
				}

				this.add(dummy, dummy.getMod());
			}
		}
	}
	
	//**************** Simplified Functions ****************//

	public static IIcon add(IIconRegister parIconRegistry, Item parItem)
	{
		return IconRegistry.add(parIconRegistry, parItem, "");
	}

	public static IIcon add(IIconRegister parIconRegistry, Block parBlock)
	{
		return IconRegistry.add(parIconRegistry, parBlock, "");
	}

	public static IIcon add(IIconRegister parIconRegistry, Item parItem, String parType, String... parMetas)
	{
		IMod mod = ItemRegistry.INSTANCE.get(parItem).getMod();
		return IconRegistry.add(parIconRegistry, parItem.getUnlocalizedName(), ResTool.getResourceDir(mod), parType, parMetas);
	}

	public static IIcon add(IIconRegister parIconRegistry, Block parBlock, String parType, String... parMetas)
	{
		IMod mod = ItemRegistry.INSTANCE.get(parBlock).getMod();
		return IconRegistry.add(parIconRegistry, parBlock.getUnlocalizedName(), ResTool.getResourceDir(mod), parType, parMetas);
	}

	//**************** Base Functions ****************//

	@Override
	public DummyItemBase add(DummyItemBase parItem, IMod parMod)
	{
		parItem.setModDir(parMod); 
		return super.add(parItem, parMod);
	}

	public DummyItemBase add(DummyItemBase parItem)
	{
		return this.add(parItem, parItem.getMod());
	}

	public static IIcon add(IIconRegister parIconRegistry, DummyItemBase<?> parDummy)
	{
		return IconRegistry.add(parIconRegistry, parDummy.getIcon(), ResTool.getResourceDir(parDummy.getMod()), "", "");
	}

	public static IIcon add(IIconRegister parIconRegistry, String parUnlocalName, String parModDir, String parType, String... parMetas)
	{
		String meta = "";
		for(String str : parMetas)
		{
			meta += "." + str;
		}

		return 	parIconRegistry.registerIcon(parModDir + ":" + parUnlocalName + (!parType.isEmpty() ? "_" + parType : "") + meta);
	}
	
	//**************** Accessor Functions ****************//
	
	public DummyIcon get(String parIcon)
	{
		return (DummyIcon) super.getKey(parIcon);
	}

	//**************** Register Functions ****************//

	public void register(Item parItem, String parIconDir, String parIconName)
	{
		parItem.setTextureName(parIconDir + ":" + parIconName);
	}

	public void register(Block parBlock, String parIconDir, String parIconName)
	{
		parBlock.setBlockTextureName(parIconDir + ":" + parIconName);
	}

	//**************** Dummy Objects ****************//

	public static class DummyIcon extends DummyItemBase<String>
	{
		public DummyIcon(String parItem, String parName)
		{
			super(parItem, "icon.", parName);
		}
		
		@Override
		public boolean equals(Object parObject)
		{
			if (parObject instanceof String)
			{
				return this.getItem().equals(parObject);
			}
			
			return super.equals(parObject);
		}
	}
}