package net.minecraftplus.blowpipe;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftplus._api.registry.IconRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlowpipe extends ItemBow
{
	public static final Item[] PROJECTILES = new Item[] {Items.wheat_seeds, Items.pumpkin_seeds, Items.melon_seeds};

	public ItemBlowpipe()
	{
		this.setMaxDamage(114);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister parIconRegistry)
	{
		this.itemIcon = IconRegistry.add(parIconRegistry, this);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack parItemStack, World parWorld, EntityPlayer parEntityPlayer, int parItemUseDuration)
	{
		int j = this.getMaxItemUseDuration(parItemStack) - parItemUseDuration;

		ArrowLooseEvent event = new ArrowLooseEvent(parEntityPlayer, parItemStack, j);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled())
		{
			return;
		}
		j = event.charge;

		boolean flag = parEntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, parItemStack) > 0;

		if (flag || this.hasProjectileItem(parEntityPlayer))
		{
			Item projectile = this.getProjectileItem(parEntityPlayer);

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
			parWorld.playSoundAtEntity(parEntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

			if (!flag)
			{
				parEntityPlayer.inventory.consumeInventoryItem(projectile);
			}

			parWorld.spawnEntityInWorld(entityseeds);
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack parItemStack, World parWorld, EntityPlayer parEntityPlayer)
	{
		ArrowNockEvent event = new ArrowNockEvent(parEntityPlayer, parItemStack);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled())
		{
			return event.result;
		}

		if (parEntityPlayer.capabilities.isCreativeMode || this.hasProjectileItem(parEntityPlayer))
		{
			parEntityPlayer.setItemInUse(parItemStack, this.getMaxItemUseDuration(parItemStack));
		}

		return parItemStack;
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return Items.paper == par2ItemStack.getItem() ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	private boolean hasProjectileItem(EntityPlayer parEntityPlayer)
	{
		return this.getProjectileItem(parEntityPlayer) != null || parEntityPlayer.capabilities.isCreativeMode;
	}

	private Item getProjectileItem(EntityPlayer parEntityPlayer)
	{
		if (parEntityPlayer.capabilities.isCreativeMode) return this.PROJECTILES[0];
		for(Item item : this.PROJECTILES)
		{
			if (parEntityPlayer.inventory.hasItem(item))
			{
				return item;
			}
		}

		return null;
	}
}