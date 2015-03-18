package net.minecraftplus.soulextractor;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftplus._api.tool.ResTool;
import net.minecraftplus._common.GuiContainerBase;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSoulExtractor extends GuiContainerBase
{
	private static final ResourceLocation textureSoulExtractor = ResTool.getResource("container_soulextractor", ResTool.GUI, ModSoulExtractor.INSTANCE);
	private TileEntitySoulExtractor soulExtractorInventory;
	private ContainerSoulExtractor containerSoulExtractor;

	public GuiSoulExtractor(EntityPlayer par1EntityPlayer, TileEntitySoulExtractor par2TileEntitySoulExtractor)
	{
		super(new ContainerSoulExtractor(par1EntityPlayer, par2TileEntitySoulExtractor), par1EntityPlayer.inventory, par2TileEntitySoulExtractor);

		this.soulExtractorInventory = par2TileEntitySoulExtractor;
		this.containerSoulExtractor = (ContainerSoulExtractor) this.inventorySlots;
	}

	@Override
	public void initGui()
	{	
		super.initGui();
		this.buttonList.clear();
		GuiButton button = new GuiButton(0, this.guiLeft + 98, this.guiTop + 60, 60, 20, "Extract");
		button.enabled = false;
		this.buttonList.add(button);
	}

	@Override
	public void updateScreen()
	{
		super.updateScreen();
		if (this.soulExtractorInventory.canAbsorb(this.containerSoulExtractor.player))
		{
			((GuiButton) this.buttonList.get(0)).enabled = true;
		}
		else
		{
			((GuiButton) this.buttonList.get(0)).enabled = false;
		}
	}

	@Override
	public void actionPerformed(GuiButton par1GuiButton)
	{
		switch(par1GuiButton.id)
		{
		case 0:
			this.soulExtractorInventory.absorbSoul(this.containerSoulExtractor.player);
			this.containerSoulExtractor.sendAbsorbSoulPacket(this.containerSoulExtractor.player, this.containerSoulExtractor.windowId);
			break;
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		String s = this.soulExtractorInventory.hasCustomInventoryName() ? this.soulExtractorInventory.getInventoryName() : I18n.format(this.soulExtractorInventory.getInventoryName());
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.drawInventoryName(this.invDown, 8, this.ySize - 96 + 2);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(textureSoulExtractor);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		int i1;

		i1 = this.soulExtractorInventory.getExtractProgressScaled(65);
		this.drawTexturedModalRect(k + 47, l + 34, 176, 0, i1 + 1, 17);
	}
}
