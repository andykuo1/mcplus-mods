package net.minecraftplus.satchel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftplus._common.ContainerBase;

public class ContainerSatchel extends ContainerBase
{
	private final EntityPlayer player;
	private final ItemStack satchel;

	protected final int rows;
	protected final int columns;

	public ContainerSatchel(EntityPlayer parEntityPlayer, ItemStack parItemStack)
	{
		super(parEntityPlayer.inventory, ItemSatchel.getItemChest(parItemStack));

		this.player = parEntityPlayer;
		this.satchel = parItemStack;

		this.invUp.openInventory();

		byte numRows = 3;
		int i = (numRows - 4) * 18;

		this.rows = this.findRows(this.invUp.getSizeInventory(), 6);
		this.columns = 6;

		this.addChestSlotsToContainer(this.invUp, 62, 18, 0, this.rows, this.columns);
		this.addPlayerSlotsToContainer(this.invDown, 8, 102 + i);
	}

	@Override
	protected SlotSatchel getNewSlot(IInventory parInventory, int parSlotIndex, int parOffsetX, int parOffsetY)
	{
		return new SlotSatchel(parInventory, parSlotIndex, parOffsetX, parOffsetY);
	}

	@Override
	public boolean canInteractWith(EntityPlayer parEntityPlayer)
	{
		return parEntityPlayer.getCurrentEquippedItem() == this.satchel;
	}
}
