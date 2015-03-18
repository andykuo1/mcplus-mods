package net.minecraftplus.lumber;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventTreeBreakHandler
{
	public static boolean breakLeaves;

	@SubscribeEvent
	public void onBlockEvent(BlockEvent parEvent)
	{
		if (!parEvent.world.isRemote && parEvent.block instanceof BlockLog)
		{
			Block block = parEvent.block;
			World world = parEvent.world;
			EntityPlayer player = world.getClosestPlayer(parEvent.x, parEvent.y, parEvent.z, 8D);

			if (player == null || player.capabilities.isCreativeMode || player.isSneaking())
			{
				return;
			}

			if (player.getEquipmentInSlot(0) != null && this.isAxeTool(player.getEquipmentInSlot(0)))
			{
				int x = parEvent.x;
				int y = parEvent.y;
				int z = parEvent.z;

				ArrayList<int[]> list = new ArrayList<int[]>();
				list.add(new int[]{x, y, z});

				while(!list.isEmpty())
				{
					int[] ints = list.get(0);
					x = ints[0];
					y = ints[1];
					z = ints[2];
					list.remove(ints);

					int j = 0;
					while(world.getBlock(x, y + j, z) instanceof BlockLog || (breakLeaves && world.getBlock(x, y + j, z) instanceof BlockLeaves))
					{
						for(int i = x - 1; i <= x + 1; i++)
						{
							for(int k = z - 1; k <= z + 1; k++)
							{
								if (i == x && k == z) continue;
								if (world.getBlock(i, y + j, k) instanceof BlockLog || (breakLeaves && world.getBlock(i, y + j, k) instanceof BlockLeaves))
								{
									list.add(new int[]{i, y + j, k});
								}
							}
						}

						world.getBlock(x, y + j, z).breakBlock(world, x, y + j, z, block, world.getBlockMetadata(x, y + j, z));
						ArrayList<ItemStack> stacks = world.getBlock(x, y + j, z).getDrops(world, x, y + j, z, world.getBlockMetadata(x, y + j, z), 1);
						world.setBlockToAir(x, y + j, z);

						for(ItemStack itemstack : stacks)
						{
							Entity entity = new EntityItem(world, x + 0.5F, y + j + 0.5F, z + 0.5F, itemstack);
							world.spawnEntityInWorld(entity);
						}

						j++;
					}

					for(int i = x - 1; i <= x + 1; i++)
					{
						for(int k = z - 1; k <= z + 1; k++)
						{
							if (i == x && k == z) continue;
							if (world.getBlock(i, y + j, k) instanceof BlockLog || (breakLeaves && world.getBlock(i, y + j, k) instanceof BlockLeaves))
							{
								list.add(new int[]{i, y + j, k});
							}
						}
					}
				}
			}
		}
	}

	private boolean isAxeTool(ItemStack par1ItemStack)
	{
		if (par1ItemStack != null)
		{
			if (par1ItemStack.getItem() instanceof ItemTool)
			{
				ItemTool tool = (ItemTool) par1ItemStack.getItem();
				if (tool.func_150893_a(par1ItemStack, Blocks.log) > 1F)
				{
					return true;
				}
			}
		}

		return false;
	}
}
