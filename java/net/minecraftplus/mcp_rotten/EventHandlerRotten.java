package net.minecraftplus.mcp_rotten;

import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftplus._api.dictionary.Colors;

public class EventHandlerRotten
{
	public static final long TICK = 50;

	public static boolean ENABLE_COUNTDOWN = true;

	public static int NEW_DAYS = 2;
	public static int OLD_DAYS = 3;

	@SubscribeEvent
	public void onPlayerOpenContainer(PlayerOpenContainerEvent parEvent)
	{
		World world = parEvent.entity.worldObj;
		if ((int)world.getWorldTime() % TICK != 0) return;

		Container container = parEvent.entityPlayer.openContainer;

		for(int i = 0; i < container.inventorySlots.size(); ++i)
		{
			Slot slot = container.getSlot(i);
			ItemStack itemstack = slot.getStack();

			if (itemstack != null && isRotItem(itemstack.getItem()))
			{
				int time = getTime(world);
				NBTTagCompound tagcompound = itemstack.getTagCompound();
				if (tagcompound == null) itemstack.setTagCompound(tagcompound = new NBTTagCompound());

				if (!tagcompound.hasKey("rottime"))
				{
					tagcompound.setInteger("rottime", time);
				}
				else
				{
					int newTime = getTotalRotTime(world, itemstack);
					if (newTime < 0)
					{
						tagcompound.setInteger("rottime", newTime = newTime - getSavedRotTime(world, itemstack));
					}
				}

				tagcompound.setInteger("worldtime", time);
			}
		}
	}

	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent parEvent)
	{
		if (isRotItem(parEvent.itemStack.getItem()))
		{
			parEvent.toolTip.add(getRotState(parEvent.entity.worldObj, parEvent.itemStack));
		}
	}

	@SubscribeEvent
	public void onItemUse(PlayerUseItemEvent.Finish parEvent)
	{
		if (isRotItem(parEvent.item.getItem()))
		{
			if (parEvent.item.getTagCompound() == null || !parEvent.item.getTagCompound().hasKey("rottime")) return;

			int time = getTotalRotTime(parEvent.entity.worldObj, parEvent.item);
			if (time <= NEW_DAYS)
			{
				return;
			}
			else if (time <= OLD_DAYS)
			{
				float mult = 1;
				if (parEvent.item.getItem() instanceof ItemFood) mult = ((ItemFood)parEvent.item.getItem()).getHealAmount(parEvent.item);
				parEvent.entityPlayer.addPotionEffect(new PotionEffect(Potion.hunger.id, (int)(200 * mult)));
			}
			else
			{
				float mult = 1;
				if (parEvent.item.getItem() instanceof ItemFood) mult = ((ItemFood)parEvent.item.getItem()).getHealAmount(parEvent.item);
				parEvent.entityPlayer.addPotionEffect(new PotionEffect(Potion.poison.id, (int)(50 * mult)));
				parEvent.entityPlayer.addPotionEffect(new PotionEffect(Potion.hunger.id, (int)(200 * mult)));
			}
		}
	}

	public static int getTime(World world)
	{
		//TODO: Does NOT this work if you freeze time
		return (int) world.getWorldTime() / 6000;
	}

	public static int getSavedRotTime(World world, ItemStack itemstack)
	{
		NBTTagCompound nbttagcompound = itemstack.getTagCompound();
		if (nbttagcompound == null) itemstack.setTagCompound(nbttagcompound = new NBTTagCompound());
		return nbttagcompound.hasKey("rottime") ?  nbttagcompound.hasKey("worldtime") ? nbttagcompound.getInteger("worldtime") - nbttagcompound.getInteger("rottime") : 0 : 0;
	}

	public static int getTotalRotTime(World world, ItemStack itemstack)
	{
		NBTTagCompound nbttagcompound = itemstack.getTagCompound();
		if (nbttagcompound == null) itemstack.setTagCompound(nbttagcompound = new NBTTagCompound());
		return nbttagcompound.hasKey("rottime") ?  getTime(world) - nbttagcompound.getInteger("rottime") : 0;
	}

	public static String getRotState(World world, ItemStack itemstack)
	{
		String state;
		int daysOld = getTotalRotTime(world, itemstack) / 4;
		int daysNext = NEW_DAYS;

		if (daysOld < daysNext)
		{
			state = Colors.STR_LIGHT_GREEN + I18n.format("state.food.new");

			if (ENABLE_COUNTDOWN && daysOld > 0)
			{
				state += " (" + (daysNext - daysOld) + " " + I18n.format("string.days_left") + ")";
			}
		}
		else if (daysOld == daysNext)
		{
			state = Colors.STR_YELLOW + I18n.format("state.food.new") + " (" + I18n.format("state.food.rotting") + ")";	
		}
		else if (daysOld < (daysNext += OLD_DAYS))
		{
			state = Colors.STR_YELLOW + I18n.format("state.food.old");

			if (ENABLE_COUNTDOWN && daysOld - NEW_DAYS > 0)
			{
				state += " (" + (daysNext - daysOld) + " " + I18n.format("string.days_left") + ")";
			}
		}
		else if (daysOld == daysNext)
		{
			state = Colors.STR_ORANGE + I18n.format("state.food.old") + " (" + I18n.format("state.food.rotting") + ")";
		}
		else
		{
			state = Colors.STR_LIGHT_RED + I18n.format("state.food.rotten");
		}

		return state;
	}

	public static boolean isRotItem(Item item)
	{
		return item instanceof ItemFood;
	}
}
