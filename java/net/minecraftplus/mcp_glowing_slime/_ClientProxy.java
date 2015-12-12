package net.minecraftplus.mcp_glowing_slime;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftplus._api.MCC;

@SideOnly(Side.CLIENT)
public class _ClientProxy extends _CommonProxy
{
	public static RenderItem renderItem;

	@Override
	public void Initialize()
	{
		MCC.item(_Glowing_Slime.glowingSlimeball);
		MCC.block(_Glowing_Slime.glowingSlime);

		MCC.entity(EntityGlowingSlimeball.class, new RenderSnowball(Minecraft.getMinecraft().getRenderManager(), _Glowing_Slime.glowingSlimeball, Minecraft.getMinecraft().getRenderItem()));

		super.Initialize();
	}
}