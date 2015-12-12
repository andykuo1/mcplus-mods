package net.minecraftplus.mcp_satchel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerEnderSatchel extends ContainerBase
{
	private final EntityPlayer player;
	private final ItemStack enderSatchel;

	protected final int rows;
	protected final int columns;

	public ContainerEnderSatchel(EntityPlayer parEntityPlayer, IInventory parInventory, ItemStack parItemStack)
	{
		super(parEntityPlayer.inventory, parInventory);
		this.player = parEntityPlayer;
		this.enderSatchel = parItemStack;

		this.invUp.openInventory(this.player);

		byte numRows = 3;
		int i = (numRows - 4) * 18;

		this.rows = this.findRows(this.invUp.getSizeInventory(), 6);
		this.columns = 6;

		this.addChestSlotsToContainer(this.invUp, 62, 18, 0, this.rows, this.columns);
		this.addPlayerSlotsToContainer(this.invDown, 8, 102 + i);
	}

	@Override
	protected Slot getNewSlot(IInventory parInventory, int parSlotIndex, int parOffsetX, int parOffsetY)
	{
		return new Slot(parInventory, parSlotIndex, parOffsetX, parOffsetY);
	}

	@Override
	public boolean canInteractWith(EntityPlayer parEntityPlayer)
	{
		return parEntityPlayer.getCurrentEquippedItem() == this.enderSatchel;
	}
}
