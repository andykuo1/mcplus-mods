package net.minecraftplus.workshop;

import java.io.File;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftplus._api.mod.MCP;
import net.minecraftplus._api.tool.ResTool;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCraftingConstructor extends GuiContainer
{
	private static final ResourceLocation craftingTableGuiTextures = ResTool.getResource("crafting_table", "textures/gui/container", true);

	private GuiTextField titleField;
	private int clearScreen;

	private int flashAlpha;

	private int[] invStackSize = new int[9];

	public GuiCraftingConstructor(InventoryPlayer par1InventoryPlayer, World par2World, int par3, int par4, int par5)
	{
		super(new ContainerCraftingConstructor(par1InventoryPlayer, par2World, par3, par4, par5));
	}

	@Override
	public void initGui()
	{
		super.initGui();
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(1, this.guiLeft + 100, this.guiTop + 59, 64, 18, "Craft"));
		this.buttonList.add(new GuiButton(2, this.guiLeft + 156, this.guiTop - 24, 20, 20, "<-"));
		this.titleField = new GuiTextField(this.fontRendererObj, this.guiLeft, this.guiTop - 24, 152, 20);
		this.titleField.setFocused(false);
		this.titleField.setMaxStringLength(24);
		this.titleField.setText("container.crafting");
	}

	@Override
	public void keyTyped(char par1, int par2)
	{
		if (this.titleField.isFocused())
		{
			this.titleField.textboxKeyTyped(par1, par2);
		}
		else
		{
			super.keyTyped(par1, par2);
		}
	}

	@Override
	public void mouseClicked(int par1, int par2, int par3)
	{
		super.mouseClicked(par1, par2, par3);
		this.titleField.mouseClicked(par1, par2, par3);
	}

	public String getTitle()
	{
		return this.titleField.getText();
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton)
	{
		super.actionPerformed(par1GuiButton);
		switch(par1GuiButton.id)
		{
		case 1:
			this.setClearScreen();
			break;
		case 2:
			if (this.inventorySlots.getSlot(0).getHasStack())
			{
				this.titleField.setText(this.inventorySlots.getSlot(0).getStack().getItem().getUnlocalizedName()+".name");
			}
			else
			{
				this.titleField.setText("");
			}
			break;
		}
	}

	private void takeCraftingImage()
	{
		File file = ImageHandler.getImageFile(this.mc, "Recipe - " + I18n.format(this.getTitle()));
		int X = (int) (this.mc.displayWidth * ((float) (this.guiLeft + 22) / this.width));
		int Y = (int) (this.mc.displayHeight * ((float) (this.guiTop + 92) / this.height));
		int WIDTH = (130 * 2);
		int HEIGHT = (72 * 2);

		ImageHandler.capture(file, X, Y, WIDTH, HEIGHT);
	}

	private void setClearScreen()
	{
		this.clearScreen = 5;

		for(Object obj : this.buttonList)
		{
			((GuiButton) obj).visible = false;
		}

		for(int i = 0; i < 9; i++)
		{
			Slot slot = this.inventorySlots.getSlot(i + 1);
			if (slot.getStack() != null)
			{
				this.invStackSize[i] = slot.getStack().stackSize;
				slot.getStack().stackSize = 1;
			}
		}

		this.flashAlpha = 0;
	}

	private void repopulateScreen()
	{
		for(Object obj : this.buttonList)
		{
			((GuiButton) obj).visible = true;
		}

		for(int i = 0; i < 9; i++)
		{
			Slot slot = this.inventorySlots.getSlot(i + 1);
			if (slot.getStack() != null)
			{
				slot.getStack().stackSize = this.invStackSize[i];
			}
		}

		this.clearScreen = 0;
	}

	private boolean isClearScreen()
	{
		return this.clearScreen > 0;
	}

	private boolean isClearScreenComplete()
	{
		return --this.clearScreen <= 0;
	}

	@Override
	public void updateScreen()
	{
		super.updateScreen();
		if (this.isClearScreen())
		{
			if (this.isClearScreenComplete())
			{
				this.takeCraftingImage();
				this.flashAlpha = 300;
				this.repopulateScreen();
			}
		}
	}

	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		super.drawScreen(par1, par2, par3);
		if (this.isClearScreen())
		{
			return;
		}

		this.titleField.drawTextBox();

		if (this.flashAlpha > 0)
		{
			int alpha = this.flashAlpha > 255 ? 255 : this.flashAlpha;
			GL11.glTranslatef(0F, 0F, 1000F);
			this.drawRect(-this.guiLeft, -this.guiTop, this.width, this.height, (alpha << 24) + 0xFFFFFF);
			this.flashAlpha -= 10;
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
	{
		this.fontRendererObj.drawString(I18n.format(this.getTitle()), 28, 6, 4210752);

		if (this.isClearScreen())
		{
			return;
		}

		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);

		if (this.mc.gameSettings.guiScale != 2 && (int) (((float) this.width / this.mc.displayWidth) * 10) != 5)
		{
			this.fontRendererObj.drawString("*GUI Scale is NOT Normal*", 25, 169, 0xFF0000);
		}
		else
		{
			this.fontRendererObj.drawString("GUI Scale is Normal", 40, 169, 0xFFFFFF);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(craftingTableGuiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
}
