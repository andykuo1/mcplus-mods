package com.minecraftplus.modClayWallSlab;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemClayWallSlab extends ItemBlock
{
	private final boolean isDoubleSlab;
	private final BlockClayWallSlabNS halfClayWallSlabNS;
	private final BlockClayWallSlabNS doubleClayWallSlabNS;
	private final BlockClayWallSlabNS1 halfClayWallSlabNS1;
	private final BlockClayWallSlabNS1 doubleClayWallSlabNS1;
	private final BlockClayWallSlabNS2 halfClayWallSlabNS2;
	private final BlockClayWallSlabNS2 doubleClayWallSlabNS2;
	private final BlockClayWallSlabWE halfClayWallSlabWE;
	private final BlockClayWallSlabWE doubleClayWallSlabWE;
	private final BlockClayWallSlabWE1 halfClayWallSlabWE1;
	private final BlockClayWallSlabWE1 doubleClayWallSlabWE1;
	private final BlockClayWallSlabWE2 halfClayWallSlabWE2;
	private final BlockClayWallSlabWE2 doubleClayWallSlabWE2;

	public ItemClayWallSlab(Block par1Block)
	{
		super(par1Block);
		this.halfClayWallSlabNS = (BlockClayWallSlabNS) MCP_ClayWallSlab.clayWallHalfSlabNS;
		this.doubleClayWallSlabNS = (BlockClayWallSlabNS) MCP_ClayWallSlab.clayWallDoubleSlabNS;
		this.halfClayWallSlabNS1 = (BlockClayWallSlabNS1) MCP_ClayWallSlab.clayWallHalfSlabNS1;
		this.doubleClayWallSlabNS1 = (BlockClayWallSlabNS1) MCP_ClayWallSlab.clayWallDoubleSlabNS1;
		this.halfClayWallSlabNS2 = (BlockClayWallSlabNS2) MCP_ClayWallSlab.clayWallHalfSlabNS2;
		this.doubleClayWallSlabNS2 = (BlockClayWallSlabNS2) MCP_ClayWallSlab.clayWallDoubleSlabNS2;
		this.halfClayWallSlabWE = (BlockClayWallSlabWE) MCP_ClayWallSlab.clayWallHalfSlabWE;
		this.doubleClayWallSlabWE = (BlockClayWallSlabWE) MCP_ClayWallSlab.clayWallDoubleSlabWE;
		this.halfClayWallSlabWE1 = (BlockClayWallSlabWE1) MCP_ClayWallSlab.clayWallHalfSlabWE1;
		this.doubleClayWallSlabWE1 = (BlockClayWallSlabWE1) MCP_ClayWallSlab.clayWallDoubleSlabWE1;
		this.halfClayWallSlabWE2 = (BlockClayWallSlabWE2) MCP_ClayWallSlab.clayWallHalfSlabWE2;
		this.doubleClayWallSlabWE2 = (BlockClayWallSlabWE2) MCP_ClayWallSlab.clayWallDoubleSlabWE2;
		this.isDoubleSlab = false;

		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int par1)
	{
		return Block.getBlockFromItem(this).getIcon(2, par1);
	}

	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		String str = "";
		if (Block.getBlockFromItem(par1ItemStack.getItem()) instanceof BlockClayWallSlabNS)
		{
			str = this.halfClayWallSlabNS.func_150002_b(par1ItemStack.getItemDamage());
		}
		else if (Block.getBlockFromItem(par1ItemStack.getItem()) instanceof BlockClayWallSlabNS1)
		{
			str = this.halfClayWallSlabNS1.func_150002_b(par1ItemStack.getItemDamage());
		}
		else if (Block.getBlockFromItem(par1ItemStack.getItem()) instanceof BlockClayWallSlabNS2)
		{
			str = this.halfClayWallSlabNS2.func_150002_b(par1ItemStack.getItemDamage());
		}
		else if (Block.getBlockFromItem(par1ItemStack.getItem()) instanceof BlockClayWallSlabWE)
		{
			str = this.halfClayWallSlabWE.func_150002_b(par1ItemStack.getItemDamage());
		}
		else if (Block.getBlockFromItem(par1ItemStack.getItem()) instanceof BlockClayWallSlabWE1)
		{
			str = this.halfClayWallSlabWE1.func_150002_b(par1ItemStack.getItemDamage());
		}
		else if (Block.getBlockFromItem(par1ItemStack.getItem()) instanceof BlockClayWallSlabWE2)
		{
			str = this.halfClayWallSlabWE2.func_150002_b(par1ItemStack.getItemDamage());
		}

		return str;
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if (this.isDoubleSlab)
		{
			return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
		}
		else if (par1ItemStack.stackSize == 0)
		{
			return false;
		}
		else if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
		{
			return false;
		}
		else
		{
			Block block = par3World.getBlock(par4, par5, par6);
			int i1 = par3World.getBlockMetadata(par4, par5, par6);
			int j1 = i1 & 7;
			boolean flag = (i1 & 8) != 0;

			if ((par7 == 1 && !flag || par7 == 0 && flag) && this.isSingleSlab(block) && j1 + ((BlockClayWallSlab) block).getColorRange() == par1ItemStack.getItemDamage())
			{
				Block block1 = this.getDoubleWallSlab((BlockClayWallSlab) block);

				if (par3World.checkNoEntityCollision(block1.getCollisionBoundingBoxFromPool(par3World, par4, par5, par6)) && par3World.setBlock(par4, par5, par6, block1, j1, 3))
				{
					par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), block1.stepSound.func_150496_b(), (block1.stepSound.getVolume() + 1.0F) / 2.0F, block1.stepSound.getPitch() * 0.8F);
					--par1ItemStack.stackSize;
				}

				return true;
			}
			else
			{
				return this.func_150946_a(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7) ? true : super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean func_150936_a(World par1World, int p_150936_2_, int p_150936_3_, int p_150936_4_, int p_150936_5_, EntityPlayer p_150936_6_, ItemStack p_150936_7_)
	{
		int i1 = p_150936_2_;
		int j1 = p_150936_3_;
		int k1 = p_150936_4_;
		Block block = par1World.getBlock(p_150936_2_, p_150936_3_, p_150936_4_);
		int l1 = par1World.getBlockMetadata(p_150936_2_, p_150936_3_, p_150936_4_);
		int i2 = l1 & 7;
		boolean flag = (l1 & 8) != 0;

		if ((p_150936_5_ == 1 && !flag || p_150936_5_ == 0 && flag) && this.isSingleSlab(block) && i2 == p_150936_7_.getItemDamage())
		{
			return true;
		}
		else
		{
			if (p_150936_5_ == 0)
			{
				--p_150936_3_;
			}

			if (p_150936_5_ == 1)
			{
				++p_150936_3_;
			}

			if (p_150936_5_ == 2)
			{
				--p_150936_4_;
			}

			if (p_150936_5_ == 3)
			{
				++p_150936_4_;
			}

			if (p_150936_5_ == 4)
			{
				--p_150936_2_;
			}

			if (p_150936_5_ == 5)
			{
				++p_150936_2_;
			}

			Block block1 = par1World.getBlock(p_150936_2_, p_150936_3_, p_150936_4_);
			int j2 = par1World.getBlockMetadata(p_150936_2_, p_150936_3_, p_150936_4_);
			i2 = j2 & 7;
			return this.isSingleSlab(block1) ? true : super.func_150936_a(par1World, i1, j1, k1, p_150936_5_, p_150936_6_, p_150936_7_);
		}
	}

	private boolean func_150946_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7)
	{
		if (par7 == 0)
		{
			--par5;
		}

		if (par7 == 1)
		{
			++par5;
		}

		if (par7 == 2)
		{
			--par6;
		}

		if (par7 == 3)
		{
			++par6;
		}

		if (par7 == 4)
		{
			--par4;
		}

		if (par7 == 5)
		{
			++par4;
		}

		Block block = par3World.getBlock(par4, par5, par6);
		int i1 = par3World.getBlockMetadata(par4, par5, par6);
		int j1 = i1 & 7;

		if (this.isSingleSlab(block) && j1 + ((BlockClayWallSlab) block).getColorRange() == par1ItemStack.getItemDamage())
		{
			Block block1 = this.getDoubleWallSlab((BlockClayWallSlab) block);
			if (par3World.checkNoEntityCollision(block1.getCollisionBoundingBoxFromPool(par3World, par4, par5, par6)) && par3World.setBlock(par4, par5, par6, block1, j1, 3))
			{
				par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), block1.stepSound.func_150496_b(), (block1.stepSound.getVolume() + 1.0F) / 2.0F, block1.stepSound.getPitch() * 0.8F);
				--par1ItemStack.stackSize;
			}

			return true;
		}
		else
		{
			return false;
		}
	}

	private Block getDoubleWallSlab(BlockClayWallSlab par1BlockWallSlab)
	{
		if (par1BlockWallSlab instanceof BlockClayWallSlabNS)
		{
			return this.doubleClayWallSlabNS;
		}
		else if (par1BlockWallSlab instanceof BlockClayWallSlabNS1)
		{
			return this.doubleClayWallSlabNS1;
		}
		else if (par1BlockWallSlab instanceof BlockClayWallSlabNS2)
		{
			return this.doubleClayWallSlabNS2;
		}
		else if (par1BlockWallSlab instanceof BlockClayWallSlabWE)
		{
			return this.doubleClayWallSlabWE;
		}
		else if (par1BlockWallSlab instanceof BlockClayWallSlabWE1)
		{
			return this.doubleClayWallSlabWE1;
		}
		else
		{
			return this.doubleClayWallSlabWE2;
		}
	}

	public static boolean isSingleSlab(Block par1Block)
	{
		return par1Block instanceof BlockClayWallSlab ? !((BlockClayWallSlab) par1Block).isDoubleSlab : false;
	}

	public static boolean isNorthSouth(Block par1Block)
	{
		return par1Block instanceof BlockClayWallSlabNS || par1Block instanceof BlockClayWallSlabNS1 || par1Block instanceof BlockClayWallSlabNS2;
	}
}