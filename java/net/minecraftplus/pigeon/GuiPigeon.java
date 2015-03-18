package net.minecraftplus.pigeon;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftplus._api.tool.ResTool;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiPigeon extends GuiContainer
{
	private static final ResourceLocation resPigeonContainer = ResTool.getResource("container_pigeon", ResTool.GUI, ModPigeon.INSTANCE);

	private final EntityPlayer player;
	private final EntityPigeon pigeon;

	private final IInventory invUp;
	private final IInventory invDown;

	private float field_147033_y;
	private float field_147032_z;
	private GuiButton btnSend;

	public GuiPigeon(EntityPlayer parEntityPlayer, EntityPigeon parEntityPigeon)
	{
		super(new ContainerPigeon(parEntityPlayer, parEntityPigeon));

		this.player = parEntityPlayer;
		this.pigeon = parEntityPigeon;

		this.invUp = this.pigeon.getAnimalChest();
		this.invDown = this.player.inventory;

		this.allowUserInput = false;
	}

	@Override
	public void initGui()
	{
		super.initGui();
		this.buttonList.add(this.btnSend = new GuiButton(1, this.guiLeft + 26, this.guiTop + 50, 53, 20, this.player == this.pigeon.getOwner() ? "Send" : "Return"));
		this.btnSend.enabled = false;
	}

	@Override
	protected void actionPerformed(GuiButton parButton)
	{
		switch(parButton.id)
		{
		case 1:
			/*
			if (this.player == this.pigeon.getOwner())
			{
				String name = this.pigeon.getAddress();
				EntityPlayer receiver = this.player.worldObj.getPlayerEntityByName(name);
				if (receiver != null)
				{
					this.pigeon.sendTo(receiver);
				}
			}*/
			break;
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
	{
		this.fontRendererObj.drawString(this.invUp.hasCustomInventoryName() ? this.invUp.getInventoryName() : I18n.format(this.invUp.getInventoryName(), new Object[0]), 8, 6, 4210752);
		this.fontRendererObj.drawString(this.invDown.hasCustomInventoryName() ? this.invDown.getInventoryName() : I18n.format(this.invDown.getInventoryName(), new Object[0]), 8, this.ySize - 96 + 3, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(resPigeonContainer);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

		if (this.pigeon.isChested())
		{
			this.drawTexturedModalRect(k + 79, l + 17, 0, this.ySize, (18 * this.getContainer().columns), (18 * this.getContainer().rows));
		}

		GuiInventory.func_147046_a(k + 51, l + 48, 40, (float)(k + 51) - this.field_147033_y, (float)(l + 75 - 50) - this.field_147032_z, this.pigeon);
	}

	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
	{
		this.field_147033_y = (float)p_73863_1_;
		this.field_147032_z = (float)p_73863_2_;

		this.btnSend.enabled = false;
		ItemStack itemstack = this.getContainer().getSlot(0).getStack();
		if (itemstack != null && itemstack.hasDisplayName())
		{
			String name = itemstack.getDisplayName();
			if (this.player.worldObj.getPlayerEntityByName(name) != null)
			{
				this.btnSend.enabled = true;
			}
		}

		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}

	protected ContainerPigeon getContainer()
	{
		return (ContainerPigeon)this.inventorySlots;
	}
}
