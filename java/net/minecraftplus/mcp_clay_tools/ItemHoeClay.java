package net.minecraftplus.mcp_clay_tools;

public class ItemHoeClay extends ItemHoeDyeable
{
	public ItemHoeClay(ToolMaterial par1ToolMaterial)
	{
		super(par1ToolMaterial);
	}

	@Override
	protected int getDefaultColor()
	{
		return ItemToolClay.COLOR;
	}
}
