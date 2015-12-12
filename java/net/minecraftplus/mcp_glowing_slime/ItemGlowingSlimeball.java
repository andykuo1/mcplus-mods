package net.minecraftplus.mcp_glowing_slime;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGlowingSlimeball extends Item
{
	//@ItemSnowball
	public ItemGlowingSlimeball()
	{
		this.maxStackSize = 16;
		this.setCreativeTab(CreativeTabs.tabMisc);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
	{
		if (!playerIn.capabilities.isCreativeMode)
		{
			--itemStackIn.stackSize;
		}

		worldIn.playSoundAtEntity(playerIn, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

		if (!worldIn.isRemote)
		{
			worldIn.spawnEntityInWorld(new EntityGlowingSlimeball(worldIn, playerIn));
		}

		return itemStackIn;
	}
}
