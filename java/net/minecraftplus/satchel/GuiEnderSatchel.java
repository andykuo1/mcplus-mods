package net.minecraftplus.satchel;

import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftplus._api.tool.ResTool;
import net.minecraftplus._common.GuiContainerBase;

import org.lwjgl.opengl.GL11;

public class GuiEnderSatchel extends GuiContainerBase
{
	private static final ResourceLocation resSatchelContainer = ResTool.getResource("container_satchel", ResTool.GUI, ModSatchel.INSTANCE);

	private final EntityPlayer player;
	private final ItemStack enderSatchel;

	private float field_147033_y;
	private float field_147032_z;

	public GuiEnderSatchel(EntityPlayer parEntityPlayer, ItemStack parItemStack)
	{
		super(new ContainerEnderSatchel(parEntityPlayer, parItemStack), parEntityPlayer.inventory, ItemEnderSatchel.getItemChest(parItemStack, parEntityPlayer));

		this.player = parEntityPlayer;
		this.enderSatchel = parItemStack;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
	{
		this.drawInventoryName(this.invUp, 8, 6);
		this.drawInventoryName(this.invDown, 8, this.ySize - 96 + 3);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(resSatchelContainer);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

		this.drawTexturedModalRect(k + 61, l + 17, 0, this.ySize, (18 * this.getContainer().columns), (18 * this.getContainer().rows));

		GuiInventory.func_147046_a(k + 34, l + 66, 24, (float)(k + 34) - this.field_147033_y, (float)(l + 95 - 66) - this.field_147032_z, this.player);
	}

	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
	{
		this.field_147033_y = (float)p_73863_1_;
		this.field_147032_z = (float)p_73863_2_;

		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}

	@Override
	protected ContainerEnderSatchel getContainer()
	{
		return (ContainerEnderSatchel) super.getContainer();
	}
}
