package net.minecraftplus.mcp_sickle;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandlerSickle
{
	@SubscribeEvent
	public void onBreakSpeed(BreakSpeed parEvent)
	{
		if (parEvent.state.getBlock() instanceof BlockCrops)
		{
			ItemStack playerItem = parEvent.entityPlayer.getCurrentEquippedItem();
			if (playerItem != null && playerItem.getItem() == _Sickle.sickle)
			{
				if (!isFullGrownCrop(parEvent.entity.worldObj, parEvent.pos, parEvent.state))
				{
					parEvent.newSpeed = 0F;
				}
			}
		}
	}

	@SubscribeEvent
	public void onBlockBreak(BreakEvent parEvent)
	{
		if (parEvent.state.getBlock() instanceof BlockCrops)
		{
			ItemStack playerItem = parEvent.getPlayer().getCurrentEquippedItem();
			if (playerItem != null && playerItem.getItem() == _Sickle.sickle)
			{
				if (!isFullGrownCrop(parEvent.world, parEvent.pos, parEvent.state))
				{
					parEvent.setCanceled(true);
				}
			}
		}
	}

	public static boolean isFullGrownCrop(World parWorld, BlockPos parBlockPos, IBlockState parBlockState)
	{
		return parBlockState.getBlock() instanceof BlockCrops ? !((BlockCrops) parBlockState.getBlock()).canGrow(parWorld, parBlockPos, parBlockState, parWorld.isRemote) : false;
	}
}
