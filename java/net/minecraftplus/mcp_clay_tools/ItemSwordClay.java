package net.minecraftplus.mcp_clay_tools;


public class ItemSwordClay extends ItemSwordDyeable
{
	public ItemSwordClay(ToolMaterial material)
	{
		super(material);
	}

	@Override
	protected int getDefaultColor()
	{
		return ItemToolClay.COLOR;
	}
}
