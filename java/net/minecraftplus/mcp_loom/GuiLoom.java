package net.minecraftplus.mcp_loom;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftplus._api.dictionary.Assets;

//@GuiFurnace
@SideOnly(Side.CLIENT)
public class GuiLoom extends GuiContainer
{
	private static final ResourceLocation loomGuiTextures = new ResourceLocation(Assets.resource(_Loom.MODID, "textures/gui/container_loom.png"));
	/** The player inventory bound to this GUI. */
	private final InventoryPlayer playerInventory;
	private IInventory tileLoom;

	public GuiLoom(InventoryPlayer playerInv, IInventory loomInv)
	{
		super(new ContainerLoom(playerInv, loomInv));
		this.playerInventory = playerInv;
		this.tileLoom = loomInv;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		String s = this.tileLoom.getDisplayName().getUnformattedText();
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(loomGuiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		int i1;

		if (TileEntityLoom.isBurning(this.tileLoom))
		{
			i1 = this.func_175382_i(13);
			this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
		}

		i1 = this.func_175381_h(24);
		this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
	}

	private int func_175381_h(int p_175381_1_)
	{
		int j = this.tileLoom.getField(2);
		int k = this.tileLoom.getField(3);
		return k != 0 && j != 0 ? j * p_175381_1_ / k : 0;
	}

	private int func_175382_i(int p_175382_1_)
	{
		int j = this.tileLoom.getField(1);

		if (j == 0)
		{
			j = 200;
		}

		return this.tileLoom.getField(0) * p_175382_1_ / j;
	}
}