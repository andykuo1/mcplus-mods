package net.minecraftplus.trapped;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPressurePlate.Sensitivity;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLadderTrapped extends Block
{
	/** The mob type that can trigger this pressure plate. */
	private Sensitivity triggerSensitivity;

	protected BlockLadderTrapped(Sensitivity par1Sensitivity)
	{
		super(Material.circuits);

		this.setHardness(0.4F);
		this.setStepSound(Block.soundTypeLadder);

		this.setCreativeTab(CreativeTabs.tabRedstone);
		this.setTickRandomly(true);
		this.triggerSensitivity = par1Sensitivity;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int oar4)
	{
		this.setBlockBoundsBasedOnState(par1World, par2, par3, oar4);
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, oar4);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		this.updateLadderBounds(par1IBlockAccess.getBlockMetadata(par2, par3, par4) % 6);
	}

	/**
	 * Update the ladder block bounds based on the given metadata value.
	 */
	public void updateLadderBounds(int par1)
	{
		float f = 0.125F;
		int i = par1 % 6;

		if (i == 2)
		{
			this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
		}

		if (i == 3)
		{
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
		}

		if (i == 4)
		{
			this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}

		if (i == 5)
		{
			this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
		}
	}

	@Override
	public int tickRate(World par1World)
	{
		return 20;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return 8;
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		return (par1World.isSideSolid(par2 - 1, par3, par4, ForgeDirection.EAST)) || (par1World.isSideSolid(par2 + 1, par3, par4, ForgeDirection.WEST)) || (par1World.isSideSolid(par2, par3, par4 - 1, ForgeDirection.SOUTH)) || (par1World.isSideSolid(par2, par3, par4 + 1, ForgeDirection.NORTH));
	}

	@Override
	public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
	{
		int j1 = par9;

		if (((par9 == 0) || (par5 == 2)) && (par1World.isSideSolid(par2, par3, par4 + 1, ForgeDirection.NORTH)))
		{
			j1 = 2;
		}

		if (((j1 == 0) || (par5 == 3)) && (par1World.isSideSolid(par2, par3, par4 - 1, ForgeDirection.SOUTH)))
		{
			j1 = 3;
		}

		if (((j1 == 0) || (par5 == 4)) && (par1World.isSideSolid(par2 + 1, par3, par4, ForgeDirection.WEST)))
		{
			j1 = 4;
		}

		if (((j1 == 0) || (par5 == 5)) && (par1World.isSideSolid(par2 - 1, par3, par4, ForgeDirection.EAST)))
		{
			j1 = 5;
		}

		return j1;
	}


	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5Block)
	{
		int i1 = par1World.getBlockMetadata(par2, par3, par4) % 6;
		boolean flag = false;

		if (i1 == 2 && par1World.isSideSolid(par2, par3, par4 + 1, ForgeDirection.NORTH))
		{
			flag = true;
		}

		if (i1 == 3 && par1World.isSideSolid(par2, par3, par4 - 1, ForgeDirection.SOUTH))
		{
			flag = true;
		}

		if (i1 == 4 && par1World.isSideSolid(par2 + 1, par3, par4, ForgeDirection.WEST))
		{
			flag = true;
		}

		if (i1 == 5 && par1World.isSideSolid(par2 - 1, par3, par4, ForgeDirection.EAST))
		{
			flag = true;
		}

		if (!flag)
		{
			this.dropBlockAsItem(par1World, par2, par3, par4, i1, 0);

			par1World.setBlockToAir(par2, par3, par4);
		}

		super.onNeighborBlockChange(par1World, par2, par3, par4, par5Block);
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (!par1World.isRemote)
		{
			int l = this.getPowerSupply(par1World.getBlockMetadata(par2, par3, par4));

			if (l > 0)
			{
				this.setStateIfMobInteractsWithPlate(par1World, par2, par3, par4, l);
			}
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		if (!par1World.isRemote)
		{
			int l = this.getPowerSupply(par1World.getBlockMetadata(par2, par3, par4));

			if (l == 0)
			{
				this.setStateIfMobInteractsWithPlate(par1World, par2, par3, par4, l);
			}
		}
	}

	/**
	 * Checks if there are mobs on the plate. If a mob is on the plate and it is off, it turns it on, and vice versa.
	 */
	protected void setStateIfMobInteractsWithPlate(World par1World, int par2, int par3, int par4, int par5)
	{
		int i1 = this.getPlateState(par1World, par2, par3, par4);
		boolean flag = par5 > 0;
		boolean flag1 = i1 > 0;

		if (par5 != i1)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, this.getMetaFromWeight(par1World, par2, par3, par4, i1), 1);
			this.func_150064_a_(par1World, par2, par3, par4);
			par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4);
		}

		if (!flag1 && flag)
		{
			par1World.playSoundEffect((double)par2 + 0.5D, (double)par3 + 0.1D, (double)par4 + 0.5D, "random.click", 0.3F, 0.5F);
		}
		else if (flag1 && !flag)
		{
			par1World.playSoundEffect((double)par2 + 0.5D, (double)par3 + 0.1D, (double)par4 + 0.5D, "random.click", 0.3F, 0.6F);
		}

		if (flag1)
		{
			par1World.scheduleBlockUpdate(par2, par3, par4, this, this.tickRate(par1World));
		}
	}

	protected AxisAlignedBB getSensitiveAABB(int par1, int par2, int par3)
	{
		float f = 0.125F;
		return AxisAlignedBB.getBoundingBox((double)((float)par1 + f), (double)par2, (double)((float)par3 + f), (double)((float)(par1 + 1) - f), (double)par2 + 0.25D, (double)((float)(par3 + 1) - f));
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5Block, int par6)
	{
		if (this.getPowerSupply(par6) > 0)
		{
			this.func_150064_a_(par1World, par2, par3, par4);
		}

		super.breakBlock(par1World, par2, par3, par4, par5Block, par6);
	}

	protected void func_150064_a_(World par1World, int par2, int par3, int par4)
	{
		par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this);
		par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this);
		par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this);
		par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this);
		par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this);
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		return this.getPowerSupply(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
	}

	@Override
	public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		return par5 != 1 && par5 != 0 ? this.getPowerSupply(par1IBlockAccess.getBlockMetadata(par2, par3, par4)) : 0;
	}

	protected int getPlateState(World par1World, int par1, int par2, int par3)
	{
		List localList = null;

		if (this.triggerSensitivity == Sensitivity.everything)
		{
			localList = par1World.getEntitiesWithinAABBExcludingEntity(null, this.getSensitiveAABB(par1, par2, par3));
		}

		if (this.triggerSensitivity == Sensitivity.mobs)
		{
			localList = par1World.getEntitiesWithinAABB(EntityLivingBase.class, getSensitiveAABB(par1, par2, par3));
		}

		if (this.triggerSensitivity == Sensitivity.players) 
		{
			localList = par1World.getEntitiesWithinAABB(EntityPlayer.class, getSensitiveAABB(par1, par2, par3));
		}

		if ((localList != null) && (!localList.isEmpty()))
		{
			for (Object object : localList)
			{
				Entity localEntity = (Entity) object;

				if (!localEntity.doesEntityNotTriggerPressurePlate())
				{
					return 15;
				}
			}
		}

		return 0;
	}

	@Override
	public boolean canProvidePower()
	{
		return true;
	}

	/**
	 * Argument is metadata. Returns power level (0-15)
	 */
	protected int getPowerSupply(int par1)
	{
		return par1 >= 6 ? 15 : 0;
	}

	/**
	 * Argument is weight (0-15). Return the metadata to be set because of it.
	 */
	protected int getMetaFromWeight(World par1World, int par2, int par3, int par4, int par5)
	{
		return par5 > 0 ? (par1World.getBlockMetadata(par2, par3, par4) % 6) + 6 : par1World.getBlockMetadata(par2, par3, par4) % 6;
	}

	protected int func_150066_d(int paramInt)
	{
		return paramInt > 0 ? 1 : 0;
	}

	protected int func_150060_c(int paramInt)
	{
		return paramInt == 1 ? 15 : 0;
	}

	@Override
	public int quantityDropped(Random par1Random)
	{
		return 1;
	}

	@Override
	public boolean isLadder(IBlockAccess par1World, int x, int y, int z, EntityLivingBase par5EntityLivingBase)
	{
		return true;
	}
}
