package net.minecraftplus.mcp_torch;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftplus._api.MCC;
import net.minecraftplus.mcp_sweet_potato._Sweet_Potato;

@SideOnly(Side.CLIENT)
public class _ClientProxy extends _CommonProxy
{
	@Override
	public void Initialize()
	{
		MCC.block(_Torch.litTorch);
		MCC.block(_Torch.unlitTorch);
		
		super.Initialize();
	}
}