package net.minecraftplus.quartz;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftplus._api.tool.ResTool;

public class ItemArmorQuartz extends ItemArmor
{
	private int potionCoolDown;
	private final int potionCoolDownMax = 200;

	private static Random rand = new Random();

	public ItemArmorQuartz(ArmorMaterial par2ArmorMaterial, int par2, int par3)
	{
		super(par2ArmorMaterial, par2, par3);
	}

	@Override
	public void onArmorTick(World par1World, EntityPlayer par2EntityPlayer, ItemStack par3ItemStack)
	{
		if (!par1World.isRemote)
		{
			if (par2EntityPlayer.isBurning())
			{
				par2EntityPlayer.extinguish();
			}

			if (ItemToolQuartz.isInNether(par2EntityPlayer))
			{
				if (par2EntityPlayer.getActivePotionEffect(Potion.fireResistance) == null)
				{
					par2EntityPlayer.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 90));
					if (par2EntityPlayer.isBurning())
					{
						int var1 = par2EntityPlayer.isInsideOfMaterial(Material.lava) ? 16 : 4;
						InventoryPlayer inv = par2EntityPlayer.inventory;

						for (int i = 0; i < inv.armorInventory.length; ++i)
						{
							if (inv.armorInventory[i] != null && inv.armorInventory[i].getItem() instanceof ItemArmor)
							{
								inv.armorInventory[i].damageItem(inv.armorInventory[i].getItem() instanceof ItemArmorQuartz ? var1 / 2 : var1, par2EntityPlayer);
								if (inv.armorInventory[i].stackSize <= 0)
								{
									inv.armorInventory[i] = null;
								}
							}
						}
					}
				}
			}
			else
			{
				if (par2EntityPlayer.getActivePotionEffect(Potion.fireResistance) == null)
				{
					if (par2EntityPlayer.isInWater())
					{
						par2EntityPlayer.attackEntityFrom(DamageSource.inFire, 1F);
					}
					else if (par2EntityPlayer.isWet() && this.potionCoolDown-- <= 0 && getHelmet(par2EntityPlayer) == null)
					{
						par2EntityPlayer.attackEntityFrom(DamageSource.inFire, 0.5F);
						this.potionCoolDown = this.potionCoolDownMax;
					}
				}
			}
		}
		else
		{
			if (ItemToolQuartz.isInNether(par2EntityPlayer) && this.rand.nextInt(6) == 0)
			{
				par1World.spawnParticle("smoke", par2EntityPlayer.posX + this.rand.nextFloat() - 0.5F, par2EntityPlayer.posY + (this.rand.nextFloat() * 2) - 1.8F, par2EntityPlayer.posZ + this.rand.nextFloat() - 0.5F, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public String getArmorTexture(ItemStack par1ItemStack, Entity par2Entity, int par3, String par4)
	{
		int layer = par3 == 2 ? 2 : 1;
		return ResTool.getResource("item.quartz_armor.layer_" + layer, ResTool.ARMORS, ModQuartz.INSTANCE).toString();
	}

	private static ItemStack getHelmet(EntityPlayer par1EntityPlayer)
	{
		return par1EntityPlayer.getCurrentArmor(3);
	}

	private static ItemStack getBoots(EntityPlayer par1EntityPlayer)
	{
		return par1EntityPlayer.getCurrentArmor(0);
	}
}
