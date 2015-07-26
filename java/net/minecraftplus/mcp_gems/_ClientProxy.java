package net.minecraftplus.mcp_gems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftplus._api.MCC;
import net.minecraftplus._api.MCP;
import net.minecraftplus._api.dictionary.Resources;

@SideOnly(Side.CLIENT)
public class _ClientProxy extends _CommonProxy
{
	@Override
	public void Initialize()
	{
		MCC.item(_Gems.ruby);
		MCC.item(_Gems.sapphire);
		MCC.item(_Gems.amethyst);

		MCC.block(_Gems.rubyOre);
		MCC.block(_Gems.sapphireOre);
		MCC.block(_Gems.amethystOre);
		MCC.block(_Gems.rubyBlock);
		MCC.block(_Gems.sapphireBlock);
		MCC.block(_Gems.amethystBlock);

		super.Initialize();
	}
}