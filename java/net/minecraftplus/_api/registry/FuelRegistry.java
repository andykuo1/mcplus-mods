package net.minecraftplus._api.registry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.handler.ItemFuelHandler;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Registry for {@link IFuelHandler} as in-game commands
 * */
public class FuelRegistry extends RegistryList<IFuelHandler> implements IFuelHandler
{
	public static final FuelRegistry INSTANCE = new FuelRegistry();

	//**************** Registry Setup ****************//

	private final ItemFuelHandler fuelHandler;

	public FuelRegistry()
	{
		this.fuelHandler = new ItemFuelHandler();
	}

	@Override
	public void initialize()
	{
		this.register(this);
	}

	@Override
	public int getBurnTime(ItemStack parItemStack)
	{
		for(IFuelHandler handler : this.registry)
		{
			int i = handler.getBurnTime(parItemStack);
			if (i != 0)
			{
				return i;
			}
		}

		return this.fuelHandler.getBurnTime(parItemStack);
	}

	//**************** Base Functions ****************//

	@Override
	public IFuelHandler add(IFuelHandler parFuelHandler)
	{
		return super.add(parFuelHandler);
	}

	public void add(Item parItem, Integer parBurnTime)
	{
		this.fuelHandler.add(parItem, parBurnTime);
	}

	//**************** Register Functions ****************//

	public void register(IFuelHandler parFuelHandler)
	{
		GameRegistry.registerFuelHandler(parFuelHandler);
	}

	public void register(Item parItem, Integer parBurnTime)
	{
		this.add(parItem, parBurnTime);
	}
}