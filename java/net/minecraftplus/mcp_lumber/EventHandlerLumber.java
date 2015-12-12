package net.minecraftplus.mcp_lumber;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandlerLumber
{
	public static boolean BREAK_LEAVES = false;

	@SubscribeEvent
	public void onBlockEvent(BlockEvent parEvent)
	{
		if (!parEvent.world.isRemote && parEvent.state.getBlock() instanceof BlockLog)
		{
			World world = parEvent.world;
			EntityPlayer player = world.getClosestPlayer(parEvent.pos.getX(), parEvent.pos.getY(), parEvent.pos.getZ(), 8D);

			if (player == null || player.capabilities.isCreativeMode || player.isSneaking())
			{
				return;
			}

			if (player.getEquipmentInSlot(0) != null && this.isAxeTool(player.getEquipmentInSlot(0)))
			{
				int x = parEvent.pos.getX();
				int y = parEvent.pos.getY();
				int z = parEvent.pos.getZ();

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

					IBlockState state = null;
					Block block = null;
					while((block = (state = world.getBlockState(new BlockPos(x, y + j, z))).getBlock()) instanceof BlockLog || (BREAK_LEAVES && block instanceof BlockLeaves))
					{
						for(int i = x - 1; i <= x + 1; i++)
						{
							for(int k = z - 1; k <= z + 1; k++)
							{
								if (i == x && k == z) continue;
								if ((block = (state = world.getBlockState(new BlockPos(x, y + j, z))).getBlock()) instanceof BlockLog || (BREAK_LEAVES && block instanceof BlockLeaves))
								{
									list.add(new int[]{i, y + j, k});
								}
							}
						}

						BlockPos pos = new BlockPos(x, y + j, z);
						block = (state = world.getBlockState(pos)).getBlock();
						block.breakBlock(world, pos, state);
						List<ItemStack> stacks = block.getDrops(world, pos, state, 1);
						world.setBlockToAir(pos);

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
							if ((block = (state = world.getBlockState(new BlockPos(x, y + j, z))).getBlock()) instanceof BlockLog || (BREAK_LEAVES && block instanceof BlockLeaves))
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
				if (tool.getDigSpeed(par1ItemStack, Blocks.log.getDefaultState()) > 1F)
				{
					return true;
				}
			}
		}

		return false;
	}
}
