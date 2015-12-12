package net.minecraftplus.mcp_torch;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandlerTorch
{
	@SubscribeEvent
	public void onPlayerInteractWithBlock(PlayerInteractEvent parEvent)
	{
		if (!parEvent.world.isRemote)
		{
			if (parEvent.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
			{
				ItemStack playerStack = parEvent.entityPlayer.getCurrentEquippedItem();
				if (playerStack != null)
				{
					Item item = playerStack.getItem();
					if (item == Item.getItemFromBlock(Blocks.torch))
					{
						Block block = _Torch.litTorch;
						Item.getItemFromBlock(block).onItemUse(new ItemStack(block), parEvent.entityPlayer, parEvent.world, parEvent.pos, parEvent.face, 0F, 0F, 0F);
						if (!parEvent.entityPlayer.capabilities.isCreativeMode)
						{
							playerStack.stackSize--;
						}
					}
				}
			}
		}
	}
}
