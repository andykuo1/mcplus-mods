package net.minecraftplus.mcp_ice_box;

import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftplus._api.dictionary.Colors;
import net.minecraftplus.mcp_rotten.EventHandlerRotten;

public class EventHandlerIceBox
{
	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent parEvent)
	{
		if (parEvent.entityPlayer.openContainer instanceof ContainerIceBox)
		{
			ContainerIceBox iceBox = (ContainerIceBox) parEvent.entityPlayer.openContainer;
			if (TileEntityIceBox.containsIceInInventory(iceBox.dispenser))
			{
				if (EventHandlerRotten.isRotItem(parEvent.itemStack.getItem()))
				{
					parEvent.toolTip.add(Colors.STR_LIGHT_CYAN + "Frozen");
				}
			}
		}
	}

}
