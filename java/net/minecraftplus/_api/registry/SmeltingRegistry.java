package net.minecraftplus._api.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.registry.SmeltingRegistry.DummySmeltable;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Registry for {@link DummySmeltable} as smelting recipes
 * */
public class SmeltingRegistry extends RegistryList<DummySmeltable>
{
	public static final SmeltingRegistry INSTANCE = new SmeltingRegistry();

	//**************** Registry Setup ****************//

	@Override
	public void initialize()
	{
		for(DummySmeltable dummy : this.registry)
		{
			Object obj = dummy.getInput();
			if (obj instanceof Item)
			{
				this.register((Item) obj, dummy.getOutput(), dummy.getExperience());
			}
			else if (obj instanceof Block)
			{
				this.register((Block) obj, dummy.getOutput(), dummy.getExperience());
			}
			else if (obj instanceof ItemStack)
			{
				this.register((ItemStack) obj, dummy.getOutput(), dummy.getExperience());
			}
		}
	}

	//**************** Base Functions ****************//

	@Override
	protected DummySmeltable add(DummySmeltable parSmeltable)
	{
		return super.add(parSmeltable);
	}

	public DummySmeltable add(Item parItem, ItemStack parOutput, float parExperience)
	{
		DummySmeltable smeltable;
		this.add(smeltable = new DummySmeltable(parItem, parOutput, parExperience));
		return smeltable;
	}

	public DummySmeltable add(Block parBlock, ItemStack parOutput, float parExperience)
	{
		DummySmeltable smeltable;
		this.add(smeltable = new DummySmeltable(parBlock, parOutput, parExperience));
		return smeltable;
	}

	public DummySmeltable add(ItemStack parItemStack, ItemStack parOutput, float parExperience)
	{
		DummySmeltable smeltable;
		this.add(smeltable = new DummySmeltable(parItemStack, parOutput, parExperience));
		return smeltable;
	}

	//**************** Register Functions ****************//

	public void register(Item parItem, ItemStack parOutput, float parExperience)
	{
		GameRegistry.addSmelting(parItem, parOutput, parExperience);
	}

	public void register(Block parBlock, ItemStack parOutput, float parExperience)
	{
		GameRegistry.addSmelting(parBlock, parOutput, parExperience);
	}

	public void register(ItemStack parItemStack, ItemStack parOutput, float parExperience)
	{
		GameRegistry.addSmelting(parItemStack, parOutput, parExperience);
	}

	//**************** Dummy Objects ****************//

	public static class DummySmeltable extends Dummy<Object>
	{
		private final ItemStack itemstack;
		private final float experience;

		private DummySmeltable(Object parObject, ItemStack parOutput, float parExperience)
		{
			super(parObject);
			this.itemstack = parOutput;
			this.experience = parExperience;
		}

		public DummySmeltable(Item parItem, ItemStack parOutput, float parExperience)
		{
			this((Object) parItem, parOutput, parExperience);
		}

		public DummySmeltable(Block parBlock, ItemStack parOutput, float parExperience)
		{
			this((Object) parBlock, parOutput, parExperience);
		}

		public DummySmeltable(ItemStack parItemStack, ItemStack parOutput, float parExperience)
		{
			this((Object) parItemStack, parOutput, parExperience);
		}

		protected Object getInput()
		{
			return this.id();
		}

		protected ItemStack getOutput()
		{
			return this.itemstack;
		}

		protected float getExperience()
		{
			return this.experience;
		}
	}
}
