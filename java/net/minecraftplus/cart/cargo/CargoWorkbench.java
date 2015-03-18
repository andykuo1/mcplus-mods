package net.minecraftplus.cart.cargo;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftplus.cart.EntityCart;

public class CargoWorkbench extends Cargo
{
	public CargoWorkbench(EntityCart par1EntityCart)
	{
		super(par1EntityCart);
	}

	public int getCargoID()
	{
		return 2;
	}

	public void interact(EntityPlayer par1EntityPlayer)
	{
		this.openGui(par1EntityPlayer);
	}

	public void dropDeadItems()
	{
		if (!this.getWorldObj().isRemote)
		{
			this.theCart.entityDropItem(new ItemStack(Blocks.crafting_table), 0.0F);
		}
	}
}
