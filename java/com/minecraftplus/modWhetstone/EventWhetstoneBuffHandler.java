package com.minecraftplus.modWhetstone;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventWhetstoneBuffHandler
{
	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent parEvent)
	{
		ItemStack itemstack = parEvent.itemStack;
		if (itemstack != null && itemstack.getItem() instanceof ItemTool && itemstack.hasTagCompound())
		{
			int i = itemstack.getTagCompound().getInteger("WhetstoneAttr");
			if (i > 0)
			{
				parEvent.toolTip.remove(1);
				parEvent.toolTip.add(1, "+" + (1 + (i / 8)) + " Sharpened Damage");
			}
		}
	}

	@SubscribeEvent
	public void onEntityAttack(AttackEntityEvent par1Event)
	{
		ItemStack itemstack = par1Event.entityPlayer.getCurrentEquippedItem();
		if (itemstack != null && itemstack.getItem() instanceof ItemTool && itemstack.hasTagCompound())
		{
			NBTTagCompound nbttagcompound = itemstack.getTagCompound();
			int i = nbttagcompound.getInteger("WhetstoneAttr");
			if (i - 1 == 0)
			{
				par1Event.entityPlayer.renderBrokenItemStack(itemstack);
			}

			if (par1Event.entity.worldObj.isRemote) return;

			if (i > 0)
			{
				par1Event.target.attackEntityFrom(DamageSource.causePlayerDamage(par1Event.entityPlayer), 1 + (i / 8));

				if (par1Event.entity.worldObj.rand.nextBoolean())
				{
					nbttagcompound.setInteger("WhetstoneAttr", i - 1);
				}
			}
			else
			{
				nbttagcompound.removeTag("WhetstoneAttr");
				if (nbttagcompound.hasNoTags())
				{
					itemstack.setTagCompound(null);
				}
			}
		}
	}

	@SubscribeEvent
	public void onBreakSpeed(BreakSpeed par1Event)
	{
		ItemStack itemstack = par1Event.entityPlayer.getCurrentEquippedItem();
		if (itemstack != null && itemstack.getItem() instanceof ItemTool && itemstack.hasTagCompound())
		{
			NBTTagCompound nbttagcompound = itemstack.getTagCompound();
			int i = nbttagcompound.getInteger("WhetstoneAttr");
			if (i > 0)
			{
				par1Event.newSpeed = par1Event.originalSpeed * (i / 16F);
				if (par1Event.newSpeed < par1Event.originalSpeed * 1.4F) par1Event.newSpeed = par1Event.originalSpeed * 1.4F;
			}
		}
	}

	@SubscribeEvent
	public void onBlockBreak(BreakEvent par1Event)
	{
		ItemStack itemstack = par1Event.getPlayer().getCurrentEquippedItem();
		if (itemstack != null && itemstack.getItem() instanceof ItemTool && itemstack.hasTagCompound())
		{
			NBTTagCompound nbttagcompound = itemstack.getTagCompound();
			int i = nbttagcompound.getInteger("WhetstoneAttr");
			if (i - 1 == 0)
			{
				par1Event.getPlayer().renderBrokenItemStack(itemstack);
			}

			if (par1Event.world.isRemote) return;
			if (i > 0)
			{
				if (par1Event.world.rand.nextInt(4) == 0)
				{
					nbttagcompound.setInteger("WhetstoneAttr", i - 1);
				}
			}
			else
			{
				nbttagcompound.removeTag("WhetstoneAttr");
				if (nbttagcompound.hasNoTags())
				{
					itemstack.setTagCompound(null);
				}
			}
		}
	}
}
