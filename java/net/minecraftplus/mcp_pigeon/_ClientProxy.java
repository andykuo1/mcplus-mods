package net.minecraftplus.mcp_pigeon;

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
		MCC.entity(EntityPigeon.class, new RenderPigeon(Minecraft.getMinecraft().getRenderManager(), new ModelPigeon(), 0.4F));

		super.Initialize();
	}
}