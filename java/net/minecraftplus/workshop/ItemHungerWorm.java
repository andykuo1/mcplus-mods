package net.minecraftplus.workshop;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemFood;
import net.minecraftplus._api.registry.IconRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHungerWorm extends ItemFood
{
	public ItemHungerWorm()
	{
		super(-2, 0.0F, false);
		this.setCreativeTab(ModWorkshop.tabWorkshop);
		this.setAlwaysEdible();
	}
}
