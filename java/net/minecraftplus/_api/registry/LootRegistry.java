package net.minecraftplus._api.registry;

import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomFishable;
import net.minecraftforge.common.FishingHooks;
import net.minecraftforge.common.FishingHooks.FishableCategory;
import net.minecraftplus._api.registry.LootRegistry.DummyFishable;

/**
 * Registry for loot, such as fishing {@link ItemStack}
 * */
public class LootRegistry extends RegistryList<DummyFishable>
{
	public static final LootRegistry INSTANCE = new LootRegistry();

	//**************** Registry Setup ****************//

	@Override
	public void initialize()
	{
		for(DummyFishable fishable : this.registry)
		{
			this.register(fishable.getWeightedFishable(), fishable.getCategory());
		}
	}

	//**************** Base Functions ****************//

	@Override
	protected DummyFishable add(DummyFishable parDummyFishable)
	{
		return super.add(parDummyFishable);
	}

	public DummyFishable add(WeightedRandomFishable parFishable, FishableCategory parCategory)
	{
		return this.add(new DummyFishable(parFishable, parCategory));
	}

	public DummyFishable add(ItemStack parItemStack, int parWeight, FishableCategory parCategory)
	{
		return this.add(new WeightedRandomFishable(parItemStack, parWeight), parCategory);
	}

	//**************** Register Functions ****************//

	public void register(WeightedRandomFishable parFishable, FishableCategory parCategory)
	{
		if (parCategory == FishableCategory.FISH)
		{
			FishingHooks.addFish(parFishable);
		}
		else if (parCategory == FishableCategory.JUNK)
		{
			FishingHooks.addJunk(parFishable);
		}
		else if (parCategory == FishableCategory.TREASURE)
		{
			FishingHooks.addTreasure(parFishable);
		}
	}

	//**************** Dummy Objects ****************//

	public static class DummyFishable extends Dummy<WeightedRandomFishable>
	{
		private final FishableCategory category;

		public DummyFishable(WeightedRandomFishable parFishable, FishableCategory parCategory)
		{
			super(parFishable);

			this.category = parCategory;
		}

		protected WeightedRandomFishable getWeightedFishable()
		{
			return this.id();
		}

		protected FishableCategory getCategory()
		{
			return this.category;
		}
	}
}
