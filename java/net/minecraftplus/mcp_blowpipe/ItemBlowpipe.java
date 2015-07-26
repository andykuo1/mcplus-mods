package net.minecraftplus.mcp_blowpipe;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftplus._api.dictionary.Sounds;
import net.minecraftplus._api.minecraft.util.InventoryUtil;

public class ItemBlowpipe extends ItemBow
{
	public static Item[] PROJECTILES = new Item[] {Items.wheat_seeds, Items.pumpkin_seeds, Items.melon_seeds};

	public ItemBlowpipe()
	{
		this.setMaxDamage(114);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack parItemStack, World parWorld, EntityPlayer parEntityPlayer, int parItemUseDuration)
	{
		//Compare to: @ItemBow
		int j = this.getMaxItemUseDuration(parItemStack) - parItemUseDuration;
		ArrowLooseEvent event = new ArrowLooseEvent(parEntityPlayer, parItemStack, j);
		if (MinecraftForge.EVENT_BUS.post(event)) return;
		j = event.charge;

		boolean flag = parEntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, parItemStack) > 0;

		ItemStack[] projectiles = InventoryUtil.getItems(parEntityPlayer.inventory, PROJECTILES);

		if (flag || projectiles.length > 0)
		{
			Item projectile = projectiles.length > 0 ? projectiles[0].getItem() : Items.wheat_seeds;

			float f = (float)j / 20.0F;
			f = (f * f + f * 2.0F) / 3.0F;

			if ((double)f < 0.1D)
			{
				return;
			}

			if (f > 1.0F)
			{
				f = 1.0F;
			}

			EntitySeeds entityseeds = new EntitySeeds(parWorld, parEntityPlayer, f * 2.0F, projectile);

			if (f == 1.0F)
			{
				entityseeds.setIsCritical(true);
			}

			int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, parItemStack);

			if (k > 0)
			{
				entityseeds.setDamage(entityseeds.getDamage() + (double)k * 0.5D + 0.5D);
			}

			int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, parItemStack);

			if (l > 0)
			{
				entityseeds.setKnockbackStrength(l);
			}

			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, parItemStack) > 0)
			{
				entityseeds.setFire(100);
			}

			parItemStack.damageItem(1, parEntityPlayer);
			parWorld.playSoundAtEntity(parEntityPlayer, Sounds.RANDOM_BOW, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

			if (!flag)
			{
				parEntityPlayer.inventory.consumeInventoryItem(projectile);
			}

			//This must be created on both sides to render correctly
			parWorld.spawnEntityInWorld(entityseeds);

			parEntityPlayer.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack parItemStack, World parWorld, EntityPlayer parEntityPlayer)
	{
		//Compare to: @ItemBow
		ArrowNockEvent event = new ArrowNockEvent(parEntityPlayer, parItemStack);
		if (MinecraftForge.EVENT_BUS.post(event)) return event.result;

		if (parEntityPlayer.capabilities.isCreativeMode || InventoryUtil.hasItems(parEntityPlayer.inventory, PROJECTILES))
		{
			parEntityPlayer.setItemInUse(parItemStack, this.getMaxItemUseDuration(parItemStack));
		}

		return parItemStack;
	}

	@Override
	public boolean getIsRepairable(ItemStack parItemStack, ItemStack parRepairItem)
	{
		return Items.paper == parRepairItem.getItem() ? true : super.getIsRepairable(parItemStack, parRepairItem);
	}
}
