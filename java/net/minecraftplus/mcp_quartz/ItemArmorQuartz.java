package net.minecraftplus.mcp_quartz;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftplus._api.dictionary.Dimensions;

public class ItemArmorQuartz extends ItemArmor
{
	private static Random rand = new Random();

	public ItemArmorQuartz(ArmorMaterial parMaterial, int parRenderIndex, int parArmorType)
	{
		super(parMaterial, parRenderIndex, parArmorType);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		if (player.isWet())
		{
			player.motionX *= 0.9F;
			player.motionZ *= 0.9F;
		}

		if (!world.isRemote)
		{	
			if (player.getActivePotionEffect(Potion.fireResistance) == null)
			{
				if (player.isBurning())
				{
					if (this.rand.nextInt(3) == 0)
					{
						player.extinguish();
					}

					if (player.dimension == Dimensions.NETHER)
					{
						int damage = player.isInLava() ? 16 : 4;

						InventoryPlayer inv = player.inventory;
						for (int i = 0; i < inv.armorInventory.length; ++i)
						{
							ItemStack armor = inv.armorInventory[i];
							if (armor == null)
							{
								player.attackEntityFrom(player.isInLava() ? DamageSource.lava : DamageSource.inFire, damage / 4);
							}
							else if (armor.getItem() instanceof ItemArmorQuartz)
							{
								armor.damageItem(damage / 2, player);
							}
							else if (armor.isItemStackDamageable())
							{
								player.attackEntityFrom(player.isInLava() ? DamageSource.lava : DamageSource.inFire, damage / 8);
								armor.damageItem(damage, player);
							}

							if (armor.getItemDamage() <= 0)
							{
								--armor.stackSize;
							}
						}

						player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 90));
					}
				}
			}
		}
		else
		{
			if (player.dimension == Dimensions.NETHER && this.rand.nextInt(6) == 0)
			{
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, player.posX + this.rand.nextFloat() - 0.4F, player.posY + (this.rand.nextFloat() * 2) - 0.2F, player.posZ + this.rand.nextFloat() - 0.4F, 0.0D, 0.0D, 0.0D);
			}

			if (player.isBurning() && this.rand.nextInt(4) == 0)
			{
				world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX + this.rand.nextFloat() - 0.4F, player.posY + (this.rand.nextFloat() * 2) - 0.2F, player.posZ + this.rand.nextFloat() - 0.4F, 0.0D, 0.0D, 0.0D);
			}
		}
	}
}
