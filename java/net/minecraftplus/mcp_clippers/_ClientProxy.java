package net.minecraftplus.mcp_clippers;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftplus._api.MCC;

@SideOnly(Side.CLIENT)
public class _ClientProxy extends _CommonProxy
{
	@Override
	public void Initialize()
	{
		MCC.entity(EntityChickenNaked.class, new RenderChickenNaked(new ModelChickenNaked(), 0.3F));
		
		MCC.item(_Clippers.clippers);
		
		super.Initialize();
	}
}