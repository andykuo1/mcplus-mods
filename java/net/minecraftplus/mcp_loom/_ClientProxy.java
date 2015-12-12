package net.minecraftplus.mcp_loom;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftplus._api.MCC;
import net.minecraftplus._api.dictionary.Assets;

@SideOnly(Side.CLIENT)
public class _ClientProxy extends _CommonProxy
{
	@Override
	public void Initialize()
	{
		MCC.block(_Loom.loom);
		MCC.block(_Loom.workingLoom);
		
		MCC.tileEntity(TileEntityLoom.class, new TileEntitySpecialRendererLoom());

		super.Initialize();
	}
}