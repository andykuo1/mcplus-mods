package net.minecraftplus._api.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftplus._api.IMod;
import net.minecraftplus._api.mod.MCP;
import net.minecraftplus._api.registry.ItemRegistry.DummyItemBase;
import net.minecraftplus._api.tool.ResTool;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Registry for {@link Item}, {@link Block}, and {@link ItemStack} as items, localization, and ore dictionary
 * */
public class ItemRegistry extends RegistryList<DummyItemBase>
{
	public static final ItemRegistry INSTANCE = new ItemRegistry();

	//**************** Registry Setup ****************//

	protected IMod defaultMod;

	@Override
	public void initialize()
	{
		for(DummyItemBase dummy : this.registry)
		{
			////////////////////////////////////////////////////////////THIS IS SO IT IS CALLED FIRST, BEFORE ALL
			if (dummy.flagId) continue;
			////////////////////////////////////////////////////////////THIS IS SO IT IS CALLED FIRST, BEFORE ALL

			if (dummy instanceof DummyItem)
			{
				DummyItem item = (DummyItem) dummy;

				if (item.hasIconDir())
				{
					item.getItem().setTextureName(item.getIconDir() + ":" + item.getIcon());
				}
				else
				{
					item.getItem().setTextureName(ResTool.getResourceDir(item.getMod()) + ":" + item.getItem().getUnlocalizedName());
				}
				
				this.register(item.getItem(), item.getName(), item.isLocalRequired() ? item.getLocalName() : null, item.getOreDict());

				////////////////////////////////////////////////////////////THIS IS SO IT IS CALLED FIRST, BEFORE ALL
				item.flagId = true;
				////////////////////////////////////////////////////////////THIS IS SO IT IS CALLED FIRST, BEFORE ALL
			}
			else if (dummy instanceof DummyBlock)
			{
				DummyBlock block = (DummyBlock) dummy;

				if (block.hasIconDir())
				{
					block.getItem().setBlockTextureName(block.getIconDir() + ":" + block.getIcon());
				}
				else
				{
					block.getItem().setBlockTextureName(ResTool.getResourceDir(block.getMod()) + ":" + block.getItem().getUnlocalizedName());
				}

				this.register(block.getItem(), block.getItemBlock(), block.getName(), block.isLocalRequired() ? block.getLocalName() : null, block.getOreDict(), block.getItemBlockArgs());

				////////////////////////////////////////////////////////////THIS IS SO IT IS CALLED FIRST, BEFORE ALL
				block.flagId = true;
				////////////////////////////////////////////////////////////THIS IS SO IT IS CALLED FIRST, BEFORE ALL
			}
			else if (dummy instanceof DummyItemStack)
			{
				DummyItemStack itemstack = (DummyItemStack) dummy;
				this.register(itemstack.getItem(), itemstack.getName(), itemstack.isLocalRequired() ? itemstack.getLocalName() : null, itemstack.getOreDict());

				////////////////////////////////////////////////////////////THIS IS SO IT IS CALLED FIRST, BEFORE ALL
				itemstack.flagId = true;
				////////////////////////////////////////////////////////////THIS IS SO IT IS CALLED FIRST, BEFORE ALL
			}
		}
	}

	public IMod setDefaultMod(IMod parMod)
	{
		return this.defaultMod = parMod;
	}

	public boolean isDefaultMod(IMod parMod)
	{
		return this.defaultMod == parMod;
	}

	//**************** Simplified Functions ****************//

	public DummyItem add(Item parItem)
	{
		return this.add(parItem, parItem.getUnlocalizedName());
	}

	public DummyBlock add(Block parBlock)
	{
		return this.add(parBlock, parBlock.getUnlocalizedName());
	}

	public DummyBlock add(Block parBlock, String parName)
	{
		return this.add(parBlock, ItemBlock.class, parName);
	}

	public DummyBlock add(Block parBlock, Class<? extends ItemBlock> parItemBlock)
	{
		return this.add(parBlock, parItemBlock, parBlock.getUnlocalizedName());
	}

	//**************** Base Functions ****************//

	@Override
	public DummyItemBase add(DummyItemBase parItem)
	{
		parItem.setModDir(this.defaultMod);
		super.add(parItem);

		return parItem;
	}

	public DummyItem add(Item parItem, String parName)
	{
		DummyItem item;
		this.add(item = new DummyItem(parItem, parName));
		return item;
	}

	public DummyBlock add(Block parBlock, Class<? extends ItemBlock> parItemBlock, String parName)
	{
		DummyBlock block;
		this.add(block = new DummyBlock(parBlock, parItemBlock, parName));
		return block;
	}

	public DummyItemStack add(ItemStack parItemStack, String parName)
	{
		DummyItemStack itemstack;
		this.add(itemstack = new DummyItemStack(parItemStack, parName));
		return itemstack;
	}

	//**************** Accessor Functions ****************//

	public DummyItemBase get(Item parItem)
	{
		return super.get(parItem);
	}

	public DummyItemBase get(Block parBlock)
	{
		return super.get(parBlock);
	}

	public DummyItemBase get(ItemStack parItemStack)
	{
		return super.get(parItemStack);
	}

	//**************** Register Functions ****************//

	public void register(Item parItem, String parName, String parLocalName, String parOreDict)
	{
		GameRegistry.registerItem(parItem, parName);
		if (parLocalName != null && !parLocalName.isEmpty())
		{
			MCP.LANGS().add(parItem, parLocalName);
		}

		if (parOreDict != null && !parOreDict.isEmpty())
		{
			OreDictionary.registerOre(parOreDict, parItem);
		}
	}

	public void register(Block parBlock, Class<? extends ItemBlock> parItem, String parName, String parLocalName, String parOreDict, Object[] parItemArgs)
	{
		if (parItemArgs == null || parItemArgs.length == 0)
		{
			GameRegistry.registerBlock(parBlock, parItem, parName);
		}
		else
		{
			GameRegistry.registerBlock(parBlock, parItem, parName, parItemArgs);
		}

		if (parLocalName != null && !parLocalName.isEmpty())
		{
			MCP.LANGS().add(parBlock, parLocalName);
		}

		if (parOreDict != null && !parOreDict.isEmpty())
		{
			OreDictionary.registerOre(parOreDict, parBlock);
		}
	}

	public void register(ItemStack parItemStack, String parName, String parLocalName, String parOreDict)
	{
		GameRegistry.registerCustomItemStack(parName, parItemStack);

		if (parLocalName != null && !parLocalName.isEmpty())
		{
			MCP.LANGS().add(parItemStack.getUnlocalizedName(), parLocalName);
		}

		if (parOreDict != null && !parOreDict.isEmpty())
		{
			OreDictionary.registerOre(parOreDict, parItemStack);
		}
	}

	//**************** Dummy Objects ****************//

	public static class DummyItemBase<ItemType> extends DummyLocalization
	{
		////////////////////////////////////////////////////////////THIS IS SO IT IS CALLED FIRST, BEFORE ALL
		public boolean flagId;
		////////////////////////////////////////////////////////////THIS IS SO IT IS CALLED FIRST, BEFORE ALL

		private final ItemType item;
		private IMod mod;
		private String oreDict;

		private String icon;
		private String iconDir;

		public DummyItemBase(ItemType parItem, String parPrefix, String parName)
		{
			super(parPrefix, ".name", parName);

			this.item = parItem;
			this.mod = MCP.INSTANCE;
		}

		public DummyItemBase<ItemType> setModDir(IMod parMod)
		{
			this.mod = parMod;
			return this;
		}

		public DummyItemBase<ItemType> setOreDict(String parOreDict)
		{
			this.oreDict = parOreDict;
			return this;
		}

		public DummyItemBase<ItemType> setIcon(String parIconName)
		{
			this.icon = parIconName;
			return this;
		}

		public DummyItemBase<ItemType> setIconDir(String parModDir)
		{
			this.iconDir = parModDir;
			return this;
		}

		public DummyItemBase<ItemType> setIcon(String parIconName, IMod parMod)
		{
			this.icon = parIconName;
			this.iconDir = ResTool.getResourceDir(parMod);
			return this;
		}

		protected boolean hasMod()
		{
			return this.mod != null;
		}

		protected boolean hasOreDict()
		{
			return this.oreDict != null && !this.oreDict.isEmpty();
		}

		protected boolean hasIcon()
		{
			return this.icon != null && !this.icon.isEmpty();
		}

		protected boolean hasIconDir()
		{
			return this.iconDir != null && !this.iconDir.isEmpty();
		}

		protected IMod getMod()
		{
			return this.mod;
		}

		protected String getOreDict()
		{
			return this.oreDict;
		}

		protected String getIcon()
		{
			return this.icon;
		}

		protected String getIconDir()
		{
			return this.iconDir;
		}

		protected ItemType getItem()
		{
			return this.item;
		}
	}

	public static class DummyItem extends DummyItemBase<Item>
	{
		public DummyItem(Item parItem, String parName)
		{
			super(parItem, "item.", parName);
			this.setIcon(this.getUnlocalName());
		}

		@Override
		public boolean equals(Object parObject)
		{
			if (parObject instanceof Item)
			{
				return this.getItem() == parObject;
			}

			return super.equals(parObject);
		}
	}

	public static class DummyBlock extends DummyItemBase<Block>
	{
		private final Class<? extends ItemBlock> itemBlock;
		private final String[] itemBlockArgs;

		public DummyBlock(Block parBlock, Class<? extends ItemBlock> parItemBlock, String parName, String... parItemBlockArgs)
		{
			super(parBlock, "tile.", parName);
			this.setIcon(this.getUnlocalName());

			this.itemBlock = parItemBlock;
			this.itemBlockArgs = parItemBlockArgs == null || parItemBlockArgs.length == 0 ? null : parItemBlockArgs;
		}

		protected Class<? extends ItemBlock> getItemBlock()
		{
			return this.itemBlock;
		}

		protected Object[] getItemBlockArgs()
		{
			return this.itemBlockArgs;
		}

		@Override
		public boolean equals(Object parObject)
		{
			if (parObject instanceof Block)
			{
				return this.getItem() == parObject;
			}

			return super.equals(parObject);
		}
	}

	public static class DummyItemStack extends DummyItemBase<ItemStack>
	{
		public DummyItemStack(ItemStack parItemStack, String parName)
		{
			super(parItemStack, "itemstack.", parName);
		}

		@Override
		public boolean equals(Object parObject)
		{
			if (parObject instanceof ItemStack)
			{
				return this.getItem() == parObject;
			}

			return super.equals(parObject);
		}
	}
}
