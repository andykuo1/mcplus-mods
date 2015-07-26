package net.minecraftplus.mcp_clippers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.IShearable;
import net.minecraftplus._api.dictionary.Sounds;
import net.minecraftplus._api.minecraft.util.EntityLivingUtil;

public class ItemClippers extends ItemShears
{
	public ItemClippers()
	{
		this.setMaxDamage(149);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack parItemStack, EntityPlayer parPlayer, EntityLivingBase parEntity)
	{
		if (!parEntity.worldObj.isRemote)
		{
			if (parPlayer.isSneaking())
			{
				Random rand = new Random();

				ItemStack itemstack = new ItemStack(Items.name_tag);
				itemstack.setStackDisplayName(parEntity.getCustomNameTag());
				EntityItem entityitem = parEntity.entityDropItem(itemstack, 1.0F);
				entityitem.motionY += rand.nextFloat() * 0.05F;
				entityitem.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
				entityitem.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;

				parEntity.setCustomNameTag("");
				parItemStack.damageItem(1, parPlayer);

				return true;
			}
			else if (parEntity instanceof IShearable)
			{
				//Compare To: @ItemShears
				IShearable shearable = (IShearable) parEntity;
				BlockPos pos = new BlockPos(parEntity.posX, parEntity.posY, parEntity.posZ);
				if (shearable.isShearable(parItemStack, parEntity.worldObj, pos))
				{
					List<ItemStack> drops = shearable.onSheared(parItemStack, parEntity.worldObj, pos,
							EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, parItemStack));
					Random rand = new Random();

					drops = this.getDrops(drops, rand);
					for(ItemStack stack : drops)
					{
						EntityItem ent = parEntity.entityDropItem(stack, 1.0F);
						ent.motionY += rand.nextFloat() * 0.05F;
						ent.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
						ent.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
					}
					parItemStack.damageItem(1, parEntity);
				}
				return true;
			}
		}

		if (parEntity instanceof EntityChicken && !(parEntity instanceof EntityChickenNaked) && !parEntity.isChild())
		{
			EntityChicken entitychicken = (EntityChicken) parEntity;
			entitychicken.worldObj.playSoundAtEntity(parPlayer, Sounds.MOB_SHEEP_SHEAR, 1.0F, 1.0F);

			if (!entitychicken.worldObj.isRemote)
			{
				Random rand = new Random();
				int i = 1 + rand.nextInt(2);
				for (int j = 0; j < i; j++)
				{
					EntityItem entityitem = entitychicken.entityDropItem(new ItemStack(Items.feather), 1F);
					entityitem.motionY += rand.nextFloat() * 0.05F;
					entityitem.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
					entityitem.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
				}

				EntityLivingUtil.replace(entitychicken, new EntityChickenNaked(entitychicken.worldObj));
				parItemStack.damageItem(1, parPlayer);
			}

			return true;
		}

		return super.itemInteractionForEntity(parItemStack, parPlayer, parEntity);
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, net.minecraft.entity.player.EntityPlayer player)
	{
		//Compare To: @ItemShears
		if (player.worldObj.isRemote)
		{
			return false;
		}
		Block block = player.worldObj.getBlockState(pos).getBlock();
		if (block instanceof net.minecraftforge.common.IShearable)
		{
			net.minecraftforge.common.IShearable target = (net.minecraftforge.common.IShearable)block;
			if (target.isShearable(itemstack, player.worldObj, pos))
			{
				java.util.List<ItemStack> drops = target.onSheared(itemstack, player.worldObj, pos,
						net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.enchantment.Enchantment.fortune.effectId, itemstack));
				java.util.Random rand = new java.util.Random();

				drops = this.getDrops(drops, rand);
				for(ItemStack stack : drops)
				{
					float f = 0.7F;
					double d  = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
					double d1 = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
					double d2 = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
					net.minecraft.entity.item.EntityItem entityitem = new net.minecraft.entity.item.EntityItem(player.worldObj, (double)pos.getX() + d, (double)pos.getY() + d1, (double)pos.getZ() + d2, stack);
					entityitem.setDefaultPickupDelay();
					player.worldObj.spawnEntityInWorld(entityitem);
				}

				itemstack.damageItem(1, player);
				player.addStat(net.minecraft.stats.StatList.mineBlockStatArray[Block.getIdFromBlock(block)], 1);
			}
		}
		return false;
	}

	private static List<ItemStack> getDrops(List<ItemStack> parList, Random parRandom)
	{
		List<ItemStack> list = new ArrayList<ItemStack>();
		for(ItemStack itemstack : parList)
		{
			if (Block.getBlockFromItem(itemstack.getItem()) == Blocks.wool && parRandom.nextInt(8) == 0)
			{
				itemstack = new ItemStack(Items.string, 1 + parRandom.nextInt(1));
			}
			else if (Block.getBlockFromItem(itemstack.getItem()) == Blocks.leaves)
			{
				if (itemstack.getItemDamage() == 0 && parRandom.nextInt(12) == 0)
				{
					itemstack = new ItemStack(Items.apple);
				}
			}

			list.add(itemstack);
		}

		return list;
	}
}
