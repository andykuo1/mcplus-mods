package net.minecraftplus.clippers;

import java.util.ArrayList;
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
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class ItemClippers extends ItemShears
{
	public ItemClippers()
	{
		this.setMaxDamage(149);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity)
	{
		if (!entity.worldObj.isRemote && !((EntityLiving) entity).getCustomNameTag().isEmpty() && (!(entity instanceof EntityChicken) || (entity instanceof EntityChicken && player.isSneaking())))
		{
			Random rand = new Random();
			ItemStack stack = new ItemStack(Items.name_tag);

			stack.setStackDisplayName(((EntityLiving) entity).getCustomNameTag());

			EntityItem ent = entity.entityDropItem(stack, 1.0F);
			ent.motionY += rand.nextFloat() * 0.05F;
			ent.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
			ent.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;

			((EntityLiving)entity).setCustomNameTag("");
			itemstack.damageItem(1, player);

			return true;
		}

		if (entity instanceof EntityChicken && !(entity instanceof EntityChickenNaked) && !entity.isChild())
		{
			entity.worldObj.playSoundAtEntity(player, "mob.sheep.shear", 1.0F, 1.0F);

			if (!entity.worldObj.isRemote)
			{
				Random rand = new Random();

				int i = 1 + rand.nextInt(2);
				for (int j = 0; j < i; j++)
				{
					EntityItem ent = entity.entityDropItem(new ItemStack(Items.feather), 1F);
					ent.motionY += rand.nextFloat() * 0.05F;
					ent.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
					ent.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
				}

				replaceEntity((EntityLiving)entity, new EntityChickenNaked(entity.worldObj));
				itemstack.damageItem(1, player);

				return true;
			}
		}
		else if (!entity.worldObj.isRemote && entity instanceof IShearable && ((IShearable)entity).isShearable(itemstack, entity.worldObj, (int)entity.posX, (int)entity.posY, (int)entity.posZ))
		{
			ArrayList<ItemStack> drops = ((IShearable)entity).onSheared(itemstack, entity.worldObj, (int)entity.posX, (int)entity.posY, (int)entity.posZ,
					EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, itemstack));

			Random rand = new Random();
			for(ItemStack stack : drops)
			{
				extractFromStack(stack, rand);

				EntityItem ent = entity.entityDropItem(stack, 1.0F);
				ent.motionY += rand.nextFloat() * 0.05F;
				ent.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
				ent.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
			}

			itemstack.damageItem(1, entity);
			itemstack.damageItem(1, player);
			return true;
		}

		return false;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, Block par3Block, int par4, int par5, int par6, EntityLivingBase par7EntityLivingBase)
	{
		if (par3Block != Blocks.leaves && par3Block != Blocks.web && par3Block != Blocks.tallgrass && par3Block != Blocks.vine && par3Block != Blocks.tripwire && !(par3Block instanceof IShearable))
		{
			return super.onBlockDestroyed(par1ItemStack, par2World, par3Block, par4, par5, par6, par7EntityLivingBase);
		}

		return true;
	}

	@Override
	public boolean func_150897_b(Block par1Block)
	{
		return par1Block == Blocks.web || par1Block == Blocks.redstone_wire || par1Block == Blocks.tripwire;
	}

	@Override
	public float func_150893_a(ItemStack par1ItemStack, Block par2Block)
	{
		return par2Block != Blocks.web && par2Block != Blocks.leaves ? (par2Block == Blocks.wool ? 5.0F : super.func_150893_a(par1ItemStack, par2Block)) : 15.0F;
	}

	@Override
	public boolean onBlockStartBreak(ItemStack par1ItemStack, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) 
	{
		if (par5EntityPlayer.worldObj.isRemote)
		{
			return false;
		}

		Block block = par5EntityPlayer.worldObj.getBlock(par2, par3, par4);

		if (block instanceof IShearable)
		{
			IShearable target = (IShearable) block;
			if (target.isShearable(par1ItemStack, par5EntityPlayer.worldObj, par2, par3, par4))
			{
				ArrayList<ItemStack> drops = target.onSheared(par1ItemStack, par5EntityPlayer.worldObj, par2, par3, par4,
						EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, par1ItemStack));
				Random rand = new Random();

				for(ItemStack stack : drops)
				{
					stack = extractFromStack(stack, rand);

					float f = 0.7F;
					double d  = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
					double d1 = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
					double d2 = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
					EntityItem entityitem = new EntityItem(par5EntityPlayer.worldObj, (double)par2 + d, (double)par3 + d1, (double)par4 + d2, stack);
					entityitem.delayBeforeCanPickup = 10;
					par5EntityPlayer.worldObj.spawnEntityInWorld(entityitem);
				}

				par1ItemStack.damageItem(1, par5EntityPlayer);
				par1ItemStack.damageItem(1, par5EntityPlayer);
				par5EntityPlayer.addStat(StatList.mineBlockStatArray[Block.getIdFromBlock(block)], 1);
			}
		}

		return false;
	}

	private static ItemStack extractFromStack(ItemStack par1ItemStack, Random par2Random)
	{
		if (Block.getBlockFromItem(par1ItemStack.getItem()) == Blocks.wool && par2Random.nextInt(8) == 0)
		{
			par1ItemStack = new ItemStack(Items.string, 1 + par2Random.nextInt(1));
		}
		else if (Block.getBlockFromItem(par1ItemStack.getItem()) == Blocks.leaves)
		{
			if (par1ItemStack.getItemDamage() == 0 && par2Random.nextInt(12) == 0)
			{
				par1ItemStack = new ItemStack(Items.apple);
			}
		}
		return par1ItemStack;
	}

	public static EntityLiving replaceEntity(EntityLiving par1Entity, EntityLiving par2Entity)
	{
		par1Entity.setDead();
		par2Entity.setLocationAndAngles(par1Entity.posX, par1Entity.posY, par1Entity.posZ, par1Entity.rotationYaw, par1Entity.rotationPitch);
		par2Entity.setHealth(par1Entity.getHealth());
		par2Entity.renderYawOffset = par1Entity.renderYawOffset;
		par2Entity.setCustomNameTag(par1Entity.getCustomNameTag());
		par1Entity.worldObj.spawnEntityInWorld(par2Entity);
		par1Entity.worldObj.spawnParticle("largeexplode", par1Entity.posX, par1Entity.posY + (double)(par1Entity.height / 2.0F), par1Entity.posZ, 0.0D, 0.0D, 0.0D);

		return par2Entity;
	}
}
