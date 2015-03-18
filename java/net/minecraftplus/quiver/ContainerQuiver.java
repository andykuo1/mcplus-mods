package net.minecraftplus.quiver;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftplus._common.ContainerBase;

public class ContainerQuiver extends ContainerBase
{
	private final EntityPlayer player;
	private final ItemStack quiver;

	protected final int rows;
	protected final int columns;

	public ContainerQuiver(EntityPlayer parEntityPlayer, ItemStack parItemStack)
	{
		super(parEntityPlayer.inventory, ItemQuiver.getItemChest(parItemStack));

		this.player = parEntityPlayer;
		this.quiver = parItemStack;

		this.invUp.openInventory();

		byte numRows = 3;
		int i = (numRows - 4) * 18;

		this.rows = this.findRows(this.invUp.getSizeInventory(), 6);
		this.columns = 6;

		this.addChestSlotsToContainer(this.invUp, 62, 18, 0, this.rows, this.columns);
		this.addPlayerSlotsToContainer(this.invDown, 8, 102 + i);
	}

	@Override
	protected SlotQuiver getNewSlot(IInventory parInventory, int parSlotIndex, int parOffsetX, int parOffsetY)
	{
		return new SlotQuiver(parInventory, parSlotIndex, parOffsetX, parOffsetY);
	}

	@Override
	public boolean canInteractWith(EntityPlayer parEntityPlayer)
	{
		return parEntityPlayer.getCurrentEquippedItem() == this.quiver || parEntityPlayer.getEquipmentInSlot(1) == this.quiver;
	}
}
