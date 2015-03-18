package net.minecraftplus.claytools;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftplus._api.registry.IconRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHoeClay extends ItemHoe implements IDyeable.Item
{
	@SideOnly(Side.CLIENT)
	private IIcon itemIcon1;

	public ItemHoeClay(ToolMaterial par1ToolMaterial)
	{
		super(par1ToolMaterial);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IIconRegister)
	{
		super.registerIcons(par1IIconRegister);
		this.itemIcon1 = IconRegistry.add(par1IIconRegister, this, "", "overlay");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamageForRenderPass(int par1, int par2)
	{
		return par2 == 1 ? this.itemIcon1 : super.getIconFromDamageForRenderPass(par1, par2);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
	{
		return Dyeable.Item.getColorFromItemStack(par1ItemStack, par2 == 1 ? 2 : 1, 0xFFFFFF);
	}

	@Override
	public boolean hasColor(ItemStack par1ItemStack)
	{
		return Dyeable.Item.hasColor(par1ItemStack);
	}

	@Override
	public int getColor(ItemStack par1ItemStack)
	{
		return Dyeable.Item.getColor(par1ItemStack, 0xAFB9CE);
	}

	@Override
	public void setColor(ItemStack par1ItemStack, int par2)
	{
		Dyeable.Item.setColor(par1ItemStack, par2);
	}

	@Override
	public void removeColor(ItemStack par1ItemStack)
	{
		Dyeable.Item.removeColor(par1ItemStack);
	}
}
