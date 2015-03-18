package net.minecraftplus.woodenbucket;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import cpw.mods.fml.common.eventhandler.Event;

public class ItemWoodenBucket extends Item
{
	/** field for checking if the bucket has been filled. */
	private Block containedLiquid;

	public ItemWoodenBucket(Block par1Block)
	{
		this.containedLiquid = par1Block;
		this.setMaxStackSize(1);
		this.setMaxDamage(42);
		this.setCreativeTab(CreativeTabs.tabMisc);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, false);

		if (movingobjectposition == null)
		{
			return par1ItemStack;
		}
		else
		{
			FillBucketEvent event = new FillBucketEvent(par3EntityPlayer, par1ItemStack, par2World, movingobjectposition);
			if (MinecraftForge.EVENT_BUS.post(event))
			{
				return par1ItemStack;
			}

			if (event.getResult() == Event.Result.ALLOW)
			{
				if (par3EntityPlayer.capabilities.isCreativeMode)
				{
					return par1ItemStack;
				}

				if (--par1ItemStack.stackSize <= 0)
				{
					return event.result;
				}

				if (!par3EntityPlayer.inventory.addItemStackToInventory(event.result))
				{
					par3EntityPlayer.dropPlayerItemWithRandomChoice(event.result, false);
				}

				return par1ItemStack;
			}

			if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
			{
				int i = movingobjectposition.blockX;
				int j = movingobjectposition.blockY;
				int k = movingobjectposition.blockZ;

				if (!par2World.canMineBlock(par3EntityPlayer, i, j, k))
				{
					return par1ItemStack;
				}

				if (this.containedLiquid == Blocks.air)
				{
					return this.copyAttributesToNewItem(par1ItemStack, ModWoodenBucket.woodenBucketEmpty);
				}

				if (movingobjectposition.sideHit == 0)
				{
					--j;
				}

				if (movingobjectposition.sideHit == 1)
				{
					++j;
				}

				if (movingobjectposition.sideHit == 2)
				{
					--k;
				}

				if (movingobjectposition.sideHit == 3)
				{
					++k;
				}

				if (movingobjectposition.sideHit == 4)
				{
					--i;
				}

				if (movingobjectposition.sideHit == 5)
				{
					++i;
				}

				if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack))
				{
					return par1ItemStack;
				}

				if (this.tryPlaceContainedLiquid(par2World, i, j, k) && !par3EntityPlayer.capabilities.isCreativeMode)
				{
					return this.emptyBucket(par1ItemStack, par3EntityPlayer);
				}
			}

			return par1ItemStack;
		}
	}

	public boolean tryPlaceContainedLiquid(World par1World, int par2, int par3, int par4)
	{
		if (this.containedLiquid == Blocks.air)
		{
			return false;
		}
		else
		{
			Material material = par1World.getBlock(par2, par3, par4).getMaterial();
			boolean flag = !material.isSolid();

			if (!par1World.isAirBlock(par2, par3, par4) && !flag)
			{
				return false;
			}
			else
			{
				if (par1World.provider.isHellWorld && this.containedLiquid == Blocks.flowing_water)
				{
					par1World.playSoundEffect((double)((float)par2 + 0.5F), (double)((float)par3 + 0.5F), (double)((float)par4 + 0.5F), "random.fizz", 0.5F, 2.6F + (par1World.rand.nextFloat() - par1World.rand.nextFloat()) * 0.8F);

					for (int l = 0; l < 8; ++l)
					{
						par1World.spawnParticle("largesmoke", (double)par2 + Math.random(), (double)par3 + Math.random(), (double)par4 + Math.random(), 0.0D, 0.0D, 0.0D);
					}
				}
				else
				{
					if (!par1World.isRemote && flag && !material.isLiquid())
					{
						par1World.func_147480_a(par2, par3, par4, true);
					}

					par1World.setBlock(par2, par3, par4, this.containedLiquid, 0, 3);
				}

				return true;
			}
		}
	}

	public ItemStack emptyBucket(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer)
	{
		if (!this.damageItem(par1ItemStack, par2EntityPlayer)) return par1ItemStack;

		return this.copyAttributesToNewItem(par1ItemStack, ModWoodenBucket.woodenBucketEmpty);
	}

	public boolean damageItem(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer)
	{
		par1ItemStack.damageItem(1, par2EntityPlayer);

		if (par1ItemStack.getItemDamage() >= par1ItemStack.getMaxDamage())
		{
			par2EntityPlayer.renderBrokenItemStack(par1ItemStack);
			par1ItemStack.stackSize--;
			return false;
		}

		return true;
	}

	public ItemStack copyAttributesToNewItem(ItemStack par1ItemStack, Item par2Item)
	{
		ItemStack itemstack = new ItemStack(par2Item);
		itemstack.setItemDamage(par1ItemStack.getItemDamage());
		itemstack.setStackDisplayName(par1ItemStack.getDisplayName());
		return itemstack;
	}

	@Override
	public ItemStack getContainerItem(ItemStack par1ItemStack)
	{
		return this.copyAttributesToNewItem(par1ItemStack, this.getContainerItem());
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		Item item = par1ItemStack.getItem();
		return item == Items.stick || item == Items.bowl || item == ModWoodenBucket.woodenBucketEmpty ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@Override
	public boolean isDamageable()
	{
		return true;
	}
}
