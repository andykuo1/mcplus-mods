package net.minecraftplus.loom;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftplus._api.tool.ResTool;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiLoom extends GuiContainer
{
	private static final ResourceLocation resLoom = ResTool.getResource("container_loom", ResTool.GUI, ModLoom.INSTANCE);
	
	private TileEntityLoom loomInventory;

	public GuiLoom(EntityPlayer parEntityPlayer, TileEntityLoom parTileEntity)
	{
		super(new ContainerLoom(parEntityPlayer, parTileEntity));
		this.loomInventory = parTileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		String s = this.loomInventory.hasCustomInventoryName() ? this.loomInventory.getInventoryName() : I18n.format(this.loomInventory.getInventoryName());

		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(resLoom);

		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		int i1;

		if (this.loomInventory.isBurning())
		{
			i1 = this.loomInventory.getBurnTimeRemainingScaled(12);
			this.drawTexturedModalRect(k + 57, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
		}

		i1 = this.loomInventory.getCookProgressScaled(24);
		this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
	}
}