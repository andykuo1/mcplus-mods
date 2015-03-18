package net.minecraftplus.cart.cargo;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraftplus.cart.EntityCart;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CargoEnderChest extends Cargo
{
	@SideOnly(Side.CLIENT)
	private int randomTick = 0;

	public CargoEnderChest(EntityCart par1EntityCart)
	{
		super(par1EntityCart);
	}

	public int getCargoID()
	{
		return 4;
	}

	public void onUpdate()
	{
		if (this.getWorldObj().isRemote && this.randomTick-- <= 0)
		{
			this.randomTick = this.rand.nextInt(10) + 10;
			for (int l = 0; l < 3; ++l)
			{
				double d0 = (double)((float)this.getTileX() + this.rand.nextFloat());
				double d1 = (double)((float)this.getTileY() + this.rand.nextFloat());
				d0 = (double)((float)this.getTileZ() + this.rand.nextFloat());
				double d2 = 0.0D;
				double d3 = 0.0D;
				double d4 = 0.0D;
				int i1 = this.rand.nextInt(2) * 2 - 1;
				int j1 = this.rand.nextInt(2) * 2 - 1;
				d2 = ((double)this.rand.nextFloat() - 0.5D) * 0.125D;
				d3 = ((double)this.rand.nextFloat() - 0.5D) * 0.125D;
				d4 = ((double)this.rand.nextFloat() - 0.5D) * 0.125D;
				double d5 = (double)this.getTileZ() + 0.5D + 0.25D * (double)j1;
				d4 = (double)(this.rand.nextFloat() * 1.0F * (float)j1);
				double d6 = (double)this.getTileX() + 0.5D + 0.25D * (double)i1;
				d2 = (double)(this.rand.nextFloat() * 1.0F * (float)i1);
				this.getWorldObj().spawnParticle("portal", d6, d1, d5, d2, d3, d4);
			}
		}
	}

	public void interact(EntityPlayer par1EntityPlayer)
	{
		InventoryEnderChest inventoryenderchest = par1EntityPlayer.getInventoryEnderChest();

		if (inventoryenderchest != null)
		{
			par1EntityPlayer.displayGUIChest(inventoryenderchest);
		}
	}

	public void dropDeadItems()
	{
		if (!this.getWorldObj().isRemote)
		{
			this.theCart.entityDropItem(new ItemStack(Blocks.ender_chest), 0.0F);
		}
	}
}
