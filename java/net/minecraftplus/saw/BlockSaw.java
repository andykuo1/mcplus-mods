package net.minecraftplus.saw;

import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.WEST;

import java.util.ArrayList;
import java.util.Random;

import javax.vecmath.Point3i;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockWood;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftplus._api.registry.IconRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSaw extends Block
{
	@SideOnly(Side.CLIENT)
	protected IIcon[] blockIcons;

	protected BlockSaw()
	{
		super(Material.wood);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setHardness(2.0F);
		this.setStepSound(soundTypeWood);
	}

	@Override
	public int getRenderType()
	{
		return ClientProxy.renderBlockSaw.getRenderId();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2)
	{
		int k = par2 & 7;
		return par1 == par2 ? this.blockIcons[1] : par1 == ForgeDirection.OPPOSITES[par2] ? this.blockIcons[0] : this.blockIcon;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IIconRegister)
	{
		this.blockIcon = IconRegistry.add(par1IIconRegister, this, "", "side");

		this.blockIcons = new IIcon[2];
		this.blockIcons[0] = IconRegistry.add(par1IIconRegister, this, "", "bottom");
		this.blockIcons[1] = IconRegistry.add(par1IIconRegister, this, "", "top");
	}

	@Override
	public int onBlockPlaced(World parWorld, int parPosX, int parPosY, int parPosZ, int parSide, float parHitX, float parHitY, float parHitZ, int p_149660_9_)
	{
		int j1 = p_149660_9_;

		if ((p_149660_9_ == 0 || parSide == 2) && parWorld.isSideSolid(parPosX, parPosY, parPosZ + 1, NORTH))
		{
			j1 = 3;
		}

		if ((j1 == 0 || parSide == 3) && parWorld.isSideSolid(parPosX, parPosY, parPosZ - 1, SOUTH))
		{
			j1 = 2;
		}

		if ((j1 == 0 || parSide == 4) && parWorld.isSideSolid(parPosX + 1, parPosY, parPosZ, WEST))
		{
			j1 = 5;
		}

		if ((j1 == 0 || parSide == 5) && parWorld.isSideSolid(parPosX - 1, parPosY, parPosZ, EAST))
		{
			j1 = 4;
		}

		boolean flag = parWorld.getBlock(parPosX, parPosY, parPosZ) instanceof BlockWood;

		if (parSide == 0)
		{
			j1 = flag ? 1 : 0;
		}

		if (parSide == 1)
		{
			j1 = flag ? 0 : 1;
		}

		return j1;
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		boolean flag = super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
		if (this.isOccupied(par1World, par2, par3, par4))
		{
			if (!par1World.isRemote)
			{
				Point3i point = this.getOccupiedPoint(par1World, par2, par3, par4);
				if (par1World.rand.nextBoolean())
				{
					par1World.playAuxSFX(2001, point.x, point.y, point.z, Block.getIdFromBlock(this.getOccupied(par1World, par2, par3, par4)));
				}

				if (par1World.rand.nextInt(14) == 0)
				{
					par1World.scheduleBlockUpdate(par2, par3, par4, this, this.tickRate(par1World));
				}

				par1World.playSoundEffect((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "random.wood_click", 0.8F, par1World.rand.nextFloat() * 0.15F + 0.6F);
			}

			return true;
		}
		else
		{
			if (par5EntityPlayer.getCurrentEquippedItem() != null)
			{
				if (Block.getBlockFromItem(par5EntityPlayer.getCurrentEquippedItem().getItem()) instanceof BlockLog)
				{
					if (!this.isOccupied(par1World, par2, par3, par4))
					{
						if (par6 == par1World.getBlockMetadata(par2, par3, par4))
						{
							return false;
						}
					}
				}
			}

			if (!par1World.isRemote)
			{
				par1World.playAuxSFX(1001, par2, par3, par4, 0);
			}
			return true;
		}
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		Block block = this.getOccupied(par1World, par2, par3, par4);
		if (block instanceof BlockLog)
		{
			if (!par1World.isRemote)
			{
				Point3i point = this.getOccupiedPoint(par1World, par2, par3, par4);
				par1World.playAuxSFX(2001, point.x, point.y, point.z, Block.getIdFromBlock(this.getOccupied(par1World, par2, par3, par4)));
				this.breakWood(par1World, point.x, point.y, point.z, block, par1World.getBlockMetadata(point.x, point.y, point.z));
			}
		}
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5Block)
	{
		boolean flag = par1World.isBlockIndirectlyGettingPowered(par2, par3, par4) || par1World.isBlockIndirectlyGettingPowered(par2, par3 + 1, par4);
		if (!flag) return;

		if (!this.isOccupied(par1World, par2, par3, par4))
		{
			if (!par1World.isRemote)
			{
				par1World.playAuxSFX(1001, par2, par3, par4, 0);
			}

			return;
		}

		if (!par1World.isRemote)
		{
			Point3i point = this.getOccupiedPoint(par1World, par2, par3, par4);
			if (par1World.rand.nextBoolean())
			{
				par1World.playAuxSFX(2001, point.x, point.y, point.z, Block.getIdFromBlock(this.getOccupied(par1World, par2, par3, par4)));
			}

			if (par1World.rand.nextInt(14) == 0)
			{
				par1World.scheduleBlockUpdate(par2, par3, par4, this, this.tickRate(par1World));
			}
			par1World.playSoundEffect((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "random.wood_click", 0.8F, par1World.rand.nextFloat() * 0.15F + 0.6F);
		}
	}

	private boolean isOccupied(World par1World, int par2, int par3, int par4)
	{
		return this.getOccupied(par1World, par2, par3, par4) != null;
	}

	private Block getOccupied(World par1World, int par2, int par3, int par4)
	{
		Point3i point = this.getOccupiedPoint(par1World, par2, par3, par4);
		if (point == null) return null;
		int x = point.x, y = point.y, z = point.z;

		Block block = par1World.getBlock(x, y, z);
		if (block instanceof BlockLog)
		{
			return block;
		}

		return null;
	}

	private Point3i getOccupiedPoint(World par1World, int par2, int par3, int par4)
	{
		int meta = par1World.getBlockMetadata(par2, par3, par4);
		int x = 0, y = 0, z = 0;

		switch(meta)
		{
		case 0: //DOWN
			x = par2;
			y = par3 - 1;
			z = par4;
			return new Point3i(x, y, z);
		case 1: //UP
			x = par2;
			y = par3 + 1;
			z = par4;
			return new Point3i(x, y, z);
		case 2: //SOUTH
			x = par2;
			y = par3;
			z = par4 - 1;
			return new Point3i(x, y, z);
		case 3: //NORTH
			x = par2;
			y = par3;
			z = par4 + 1;
			return new Point3i(x, y, z);
		case 4: //LEFT
			x = par2 - 1;
			y = par3;
			z = par4;
			return new Point3i(x, y, z);
		case 5: //RIGHT
			x = par2 + 1;
			y = par3;
			z = par4;
			return new Point3i(x, y, z);
		}

		return null;
	}

	private void breakWood(World par1World, int par2, int par3, int par4, Block par5Block, int par6)
	{
		ArrayList<ItemStack> itemstacks = par5Block.getDrops(par1World, par2, par3, par4, par6, 0);
		for(ItemStack itemstack : itemstacks)
		{
			if (itemstack != null)
			{
				ItemStack result;
				Block block = Block.getBlockFromItem(itemstack.getItem());
				result = ModSaw.woodPlanks.get(new WoodPlank(block, itemstack.getItemDamage()));
				if (result != null)
				{
					result.stackSize = par1World.rand.nextBoolean() ? 4 : par1World.rand.nextInt(3) + 1;
				}
				else
				{
					result = itemstack;
				}

				par1World.spawnEntityInWorld(new EntityItem(par1World, par2 + 0.5F, par3 + 0.5F, par4 + 0.5F, result));
			}
		}

		par1World.setBlockToAir(par2, par3, par4);
	}
}
