package net.minecraftplus.mcp_beetroot;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandlerBeetroot
{
	@SubscribeEvent
	public void onHoeEvent(UseHoeEvent parEvent)
	{
		if (parEvent.world.isRemote || parEvent.entityPlayer.capabilities.isCreativeMode) return;

		IBlockState block = parEvent.world.getBlockState(parEvent.pos);
		if (block.equals(Blocks.grass.getDefaultState()) || block.equals(Blocks.dirt.getDefaultState()))
		{
			if (parEvent.world.rand.nextInt(18) == 0)
			{
				parEvent.world.spawnEntityInWorld(new EntityItem(parEvent.world, parEvent.pos.getX(), parEvent.pos.getY(), parEvent.pos.getZ(), new ItemStack(_Beetroot.beetrootSeeds)));
			}
		}
	}
}
