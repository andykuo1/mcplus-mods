package net.minecraftplus.firepit;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftplus._api.tool.ResTool;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiFirePit extends GuiContainer
{
	private static final ResourceLocation firePitGuiTextures = ResTool.getResource("furnace", ResTool.GUI + "/container", true);
	private TileEntityFirePit tileFirePit;

	public GuiFirePit(InventoryPlayer par1InventoryPlayer, TileEntityFirePit par2TileEntityFirePit)
	{
		super(new ContainerFirePit(par1InventoryPlayer, par2TileEntityFirePit));
		this.tileFirePit = par2TileEntityFirePit;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		String s = this.tileFirePit.hasCustomInventoryName() ? this.tileFirePit.getInventoryName() : I18n.format(this.tileFirePit.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(firePitGuiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		int i1;

		if (this.tileFirePit.isBurning())
		{
			i1 = this.tileFirePit.getBurnTimeRemainingScaled(12);
			this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
		}

		i1 = this.tileFirePit.getCookProgressScaled(24);
		this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
	}
}