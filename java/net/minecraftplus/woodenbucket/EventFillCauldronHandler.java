package net.minecraftplus.woodenbucket;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventFillCauldronHandler
{
	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent parEvent)
	{
		if (parEvent.entity instanceof EntityPlayer && parEvent.action.equals(PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK))
		{
			EntityPlayer entityplayer = (EntityPlayer) parEvent.entity;
			ItemStack itemstack = entityplayer.getHeldItem();
			if (itemstack != null && itemstack.getItem() == ModWoodenBucket.woodenBucketWater)
			{
				World world = entityplayer.getEntityWorld();
				Block block = world.getBlock(parEvent.x, parEvent.y, parEvent.z);
				if (block instanceof BlockCauldron)
				{
					int metadata = world.getBlockMetadata(parEvent.x, parEvent.y, parEvent.z);

					if (metadata < 3)
					{
						if (!entityplayer.capabilities.isCreativeMode)
						{
							itemstack.damageItem(1, entityplayer);
							entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, ModWoodenBucket.woodenBucketWater.getContainerItem(itemstack));
						}

						world.setBlockMetadataWithNotify(parEvent.x, parEvent.y, parEvent.z, MathHelper.clamp_int(3, 0, 3), 2);
						world.func_147453_f(parEvent.x, parEvent.y, parEvent.z, Blocks.cauldron);
					}
				}
			}
		}
	}
}
