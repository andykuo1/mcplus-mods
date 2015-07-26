package net.minecraftplus.mcp_pigeon;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftplus._api.dictionary.Assets;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiPigeon extends GuiContainer
{
	private static final ResourceLocation pigeonContainerGui = new ResourceLocation(Assets.resource(_Pigeon.MODID, "textures/gui/container_pigeon.png"));

	private final EntityPlayer player;
	private final EntityPigeon pigeon;
	private boolean sending;

	private final IInventory invUp;
	private final IInventory invDown;

	private float field_147033_y;
	private float field_147032_z;
	private GuiTextField fldPlayer;
	private GuiButton btnSend;

	public GuiPigeon(EntityPlayer parEntityPlayer, EntityPigeon parEntityPigeon)
	{
		super(new ContainerPigeon(parEntityPlayer, parEntityPigeon));

		this.player = parEntityPlayer;
		this.pigeon = parEntityPigeon;
		this.sending = false;

		this.invUp = this.pigeon.getChest();
		this.invDown = this.player.inventory;

		this.allowUserInput = false;
	}

	@Override
	public void initGui()
	{
		super.initGui();
		this.sending = !this.pigeon.isSent();
		this.fldPlayer = new GuiTextField(0, this.fontRendererObj, this.guiLeft + 81, this.guiTop + 48 + 6, 86, 15);
		this.fldPlayer.setEnabled(this.sending);
		this.buttonList.add(this.btnSend = new GuiButton(1, this.guiLeft + 26, this.guiTop + 50, 53, 20, this.sending ? "Send" : "Return"));
	}

	@Override
	protected void actionPerformed(GuiButton parButton)
	{
		switch(parButton.id)
		{
		case 1:
			if (parButton.displayString.equals("Send"))
			{
				this.pigeon.send(this.fldPlayer.getText());
				this.player.closeScreen();
			}
			else if (parButton.displayString.equals("Return"))
			{
				this.pigeon.unsend();
				this.player.closeScreen();
			}
			break;
		}
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException
	{
		if (!this.fldPlayer.textboxKeyTyped(typedChar, keyCode))
		{
			super.keyTyped(typedChar, keyCode);
		}
	}

	@Override
	protected void mouseClicked(int x, int y, int btn) throws IOException
	{
		super.mouseClicked(x, y, btn);
		this.fldPlayer.mouseClicked(x, y, btn);
	}

	@Override
	public void updateScreen()
	{
		super.updateScreen();
		this.fldPlayer.updateCursorCounter();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
	{
		this.fontRendererObj.drawString(this.invUp.hasCustomName() ? this.invUp.getName() : I18n.format(this.invUp.getName(), new Object[0]), 8, 6, 4210752);
		this.fontRendererObj.drawString(this.invDown.hasCustomName() ? this.invDown.getName() : I18n.format(this.invDown.getName(), new Object[0]), 8, this.ySize - 96 + 3, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(pigeonContainerGui);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

		if (this.pigeon.isChested())
		{
			this.drawTexturedModalRect(k + 79, l + 17, 0, this.ySize, (18 * this.getContainer().columns), (18 * this.getContainer().rows));
		}

		GuiInventory.drawEntityOnScreen(k + 51, l + 48, 40, (float)(k + 51) - this.field_147033_y, (float)(l + 75 - 50) - this.field_147032_z, this.pigeon);
	}

	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
	{
		this.field_147033_y = p_73863_1_;
		this.field_147032_z = p_73863_2_;

		if (this.sending)
		{
			String friend = this.fldPlayer.getText();
			this.btnSend.enabled = false;
			this.fldPlayer.setTextColor(0x666666);
			if (friend != null)
			{
				EntityPlayer entity = this.player.worldObj.getPlayerEntityByName(friend);
				if (entity != null)
				{
					this.btnSend.enabled = true;
					this.fldPlayer.setTextColor(0xFFFFFF);
				}
			}
		}
		else
		{
			this.btnSend.enabled = true;
		}

		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);

		this.fldPlayer.drawTextBox();
	}

	protected ContainerPigeon getContainer()
	{
		return (ContainerPigeon)this.inventorySlots;
	}
}
