package net.minecraftplus.satchel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftplus._common.ContainerBase;

public class ContainerEnderSatchel extends ContainerBase
{
	private final EntityPlayer player;
	private final ItemStack enderSatchel;

	protected final int rows;
	protected final int columns;

	public ContainerEnderSatchel(EntityPlayer parEntityPlayer, ItemStack parItemStack)
	{
		super(parEntityPlayer.inventory, ItemEnderSatchel.getItemChest(parItemStack, parEntityPlayer));
		this.player = parEntityPlayer;
		this.enderSatchel = parItemStack;

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
		return parEntityPlayer.getCurrentEquippedItem() == this.enderSatchel;
	}
}
