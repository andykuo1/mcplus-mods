package com.minecraftplus.modRotten;

import java.util.ArrayList;
import java.util.List;

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
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventFoodDecayHandler
{
	private static List<Item> cookedFoods = new ArrayList<Item>();
	private static List<Item> craftedFoods = new ArrayList<Item>();

	public static long decayTime = 24000;
	public static final long tickWait = 50;

	@SubscribeEvent
	public void onItemUse(PlayerUseItemEvent.Finish parEvent)
	{
		if (isFood(parEvent.item.getItem()))
		{
			String state = getFoodState(parEvent.entity.worldObj, parEvent.item);
			if (state.equals("ROTTEN"))
			{
				parEvent.entityPlayer.addPotionEffect(new PotionEffect(Potion.hunger.id, 300 * getDaysStale(parEvent.entity.worldObj, parEvent.item)));
				parEvent.entityPlayer.addPotionEffect(new PotionEffect(Potion.poison.id, 100 * getDaysRotten(parEvent.entity.worldObj, parEvent.item)));
			}
			else if (state.equals("STALE"))
			{
				parEvent.entityPlayer.addPotionEffect(new PotionEffect(Potion.hunger.id, 200 * getDaysStale(parEvent.entity.worldObj, parEvent.item)));
			}
		}
	}

	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent parEvent)
	{
		ItemStack itemstack = parEvent.itemStack;
		if (itemstack != null && isFood(itemstack.getItem()))
		{
			if (itemstack.getTagCompound() == null) return;

			int days = getDaysOld(parEvent.entity.worldObj, itemstack);
			if (days > 0) parEvent.toolTip.add(days + " Day" + (days == 1 ? "" : "s") + " Old");
		}
	}

	@SubscribeEvent()
	public void onContainerOpen(PlayerOpenContainerEvent parEvent)
	{
		if (parEvent.entity.worldObj.getWorldTime() % tickWait != 0 || parEvent.entityPlayer.capabilities.isCreativeMode ) return;
		Container container = parEvent.entityPlayer.openContainer;

		for(int i = 0; i < container.inventorySlots.size(); i++)
		{
			Slot slot = container.getSlot(i);
			ItemStack itemstack = slot.getStack();
			if (itemstack != null && isFood(itemstack.getItem()))
			{
				if (itemstack.getTagCompound() == null)
				{
					itemstack.stackTagCompound = new NBTTagCompound();
				}

				NBTTagCompound nbttagcompound = itemstack.getTagCompound();
				if (nbttagcompound.getLong("decayTime") <= 0 || getDaysOld(parEvent.entityPlayer.worldObj, itemstack) < 0)
				{
					startDecay(parEvent.entity.worldObj, nbttagcompound);
				}
				else
				{
					String state = getFoodState(parEvent.entity.worldObj, itemstack);
					String display = itemstack.getDisplayName();
					if (state.equals("ROTTEN"))
					{
						if (!display.endsWith(" (Rotten)"))
						{
							if (display.endsWith(" (Stale)"))
							{
								display = display.substring(0, display.length() - 8);
							}

							itemstack.setStackDisplayName(display + " (Rotten)");
						}
					}
					else if (state.equals("STALE"))
					{
						if (!display.endsWith(" (Stale)"))
						{
							if (display.endsWith(" (Rotten)"))
							{
								display = display.substring(0, display.length() - 9);
							}

							itemstack.setStackDisplayName(display + " (Stale)");
						}
					}
					else if (itemstack.hasDisplayName())
					{
						if (display.endsWith(" (Stale)"))
						{
							display = display.substring(0, display.length() - 8);
							itemstack.setStackDisplayName(display);
						}

						if (display.endsWith(" (Rotten)"))
						{
							display = display.substring(0, display.length() - 9);
							itemstack.setStackDisplayName(display);
						}

						if (itemstack.getDisplayName().equals(itemstack.getItem().getItemStackDisplayName(itemstack)))
						{
							itemstack.func_135074_t();
						}
					}
				}
			}
		}
	}

	public static boolean isFood(Item par1Item)
	{
		return par1Item instanceof ItemFood;
	}

	public static int getDaysRotten(World par1World, ItemStack par2ItemStack)
	{
		return getDaysOld(par1World, par2ItemStack) - (getHealAmount(par2ItemStack) * 2);
	}

	public static int getDaysStale(World par1World, ItemStack par2ItemStack)
	{
		return getDaysOld(par1World, par2ItemStack) - (getHealAmount(par2ItemStack));
	}

	public static String getFoodState(World par1World, ItemStack par2ItemStack)
	{
		if (!par2ItemStack.hasTagCompound()) return "";

		int healamt = getHealAmount(par2ItemStack);
		long daysOld = getDaysOld(par1World, par2ItemStack);

		return daysOld > healamt ? daysOld > healamt * 2 ? "ROTTEN" : "STALE" : "";
	}

	public static boolean isCookedFood(Item par1Item)
	{
		for(Item item : cookedFoods)
		{
			if (item == par1Item)
			{
				return true;
			}
		}

		return false;
	}

	public static boolean isCraftedFood(Item par1Item)
	{
		for(Item item : craftedFoods)
		{
			if (item == par1Item)
			{
				return true;
			}
		}

		return false;
	}

	public static boolean isRawFood(Item par1Item)
	{
		return !(isCraftedFood(par1Item) || isRawFood(par1Item));
	}

	public static void addCookedFood(Item par1Item)
	{
		cookedFoods.add(par1Item);
	}

	public static void addCraftedFood(Item par1Item)
	{
		craftedFoods.add(par1Item);
	}

	public static int getHealAmount(ItemStack par1ItemStack)
	{
		return isCraftedFood(par1ItemStack.getItem()) ? 6 : isCookedFood(par1ItemStack.getItem()) ? 4 : 2;
	}

	public static long getWorldTime(World par1World)
	{
		return par1World.getWorldTime();
	}

	/**
	 * -----------------
	 * DECAY CALCULATIONS
	 * -----------------
	 * **/
	
	public static void startDecay(World par1World, NBTTagCompound par2NBTTagCompound)
	{
		par2NBTTagCompound.setLong("decayTime", getWorldTime(par1World) / 12000);
	}
	
	public static int getDaysOld(World par1World, ItemStack par2ItemStack)
	{
		return (int) (((getWorldTime(par1World) / 12000) - par2ItemStack.getTagCompound().getLong("decayTime")) / 2);
	}
}
