package net.minecraftplus.mcp_clay_tools;

import java.util.Set;

import net.minecraftplus._api.minecraft.base.ItemToolDyeable;

public abstract class ItemToolClay extends ItemToolDyeable
{
	public static int COLOR = 0x9C655A;
	
	protected ItemToolClay(float parAttackDamage, ToolMaterial parMaterial, Set parEffectiveBlocks)
	{
		super(parAttackDamage, parMaterial, parEffectiveBlocks);
	}

	@Override
	protected int getDefaultColor()
	{
		return COLOR;
	}
}
