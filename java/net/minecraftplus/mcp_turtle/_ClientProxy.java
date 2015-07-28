package net.minecraftplus.mcp_turtle;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftplus._api.MCC;

@SideOnly(Side.CLIENT)
public class _ClientProxy extends _CommonProxy
{
	@Override
	public void Initialize()
	{
		MCC.entity(EntityTurtle.class, new RenderTurtle(Minecraft.getMinecraft().getRenderManager(), new ModelTurtle(), 0.4F));

		super.Initialize();
	}
}