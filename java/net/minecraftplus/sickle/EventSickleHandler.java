package net.minecraftplus.sickle;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventSickleHandler
{
	@SubscribeEvent
	public void onBreakSpeed(BreakSpeed par1Event)
	{
		if (par1Event.block instanceof BlockCrops)
		{
			if (par1Event.entityPlayer.getCurrentEquippedItem() != null && par1Event.entityPlayer.getCurrentEquippedItem().getItem() == ModSickle.sickle)
			{
				if (!isFullGrownCrop(par1Event.entity.worldObj, par1Event.x, par1Event.y, par1Event.z, par1Event.block))
				{
					par1Event.newSpeed = 0F;
				}
			}
		}
	}

	@SubscribeEvent
	public void onBlockBreak(BreakEvent par1Event)
	{
		if (par1Event.block instanceof BlockCrops)
		{
			if (par1Event.getPlayer().getCurrentEquippedItem() != null && par1Event.getPlayer().getCurrentEquippedItem().getItem() == ModSickle.sickle)
			{
				if (!isFullGrownCrop(par1Event.world, par1Event.x, par1Event.y, par1Event.z, par1Event.block))
				{
					par1Event.setCanceled(true);
				}
			}
		}
	}

	public static boolean isFullGrownCrop(World par1World, int par2, int par3, int par4, Block par5Block)
	{
		return par5Block instanceof BlockCrops ? !((BlockCrops) par5Block).func_149851_a(par1World, par2, par3, par4, par1World.isRemote) : false;
	}
}
