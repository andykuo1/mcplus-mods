package net.minecraftplus.quiver;

import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftplus._api.tool.ResTool;
import net.minecraftplus._common.GuiContainerBase;
import net.minecraftplus.satchel.ModSatchel;

import org.lwjgl.opengl.GL11;

public class GuiQuiver extends GuiContainerBase
{
	private static final ResourceLocation resSatchelContainer = ResTool.getResource("container_satchel", ResTool.GUI, ModSatchel.INSTANCE);

	private final EntityPlayer player;
	private final ItemStack quiver;

	private float field_147033_y;
	private float field_147032_z;

	public GuiQuiver(EntityPlayer parEntityPlayer, ItemStack parItemStack)
	{
		super(new ContainerQuiver(parEntityPlayer, parItemStack), parEntityPlayer.inventory, ItemQuiver.getItemChest(parItemStack));

		this.player = parEntityPlayer;
		this.quiver = parItemStack;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.drawInventoryName(this.invUp, 8, 6);
		this.drawInventoryName(this.invDown, 8, this.ySize - 96 + 3);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(resSatchelContainer);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

		this.drawTexturedModalRect(k + 61, l + 17, 0, this.ySize, (18 * this.getContainer().columns), (18 * this.getContainer().rows));

		GuiInventory.func_147046_a(k + 34, l + 66, 24, (float)(k + 34) - this.field_147033_y, (float)(l + 95 - 66) - this.field_147032_z, this.player);
	}

	/*@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(res_genericBlank);
		int var1 = (this.width - this.xSize) / 2;
		int var2 = (this.height - this.ySize) / 2 + this.upperInventory.getBaseHeight();
		drawTexturedModalRect(var1, var2 + 17, 0, 17, this.xSize, 74 + this.upperInventory.getHeight() - 17);

		this.mc.renderEngine.bindTexture(res_generic);
		drawTexturedModalRect(var1, var2, 0, 0, this.xSize, 17);
		drawTexturedModalRect(var1, var2 + 18 + this.upperInventory.getHeight(), 0, 126, this.xSize, 96);

		int i = this.upperInventory.getRows();
		int k = this.upperInventory.getColumns();

		for (int j = 1; j < i; j++)
		{
			drawTexturedModalRect(var1 + 7 + this.upperInventory.getCustomAlignment(), var2 + j * 18 - 1, 7, 17, (this.upperInventory.getLengthInventory() * 18), 18);
		}

		drawTexturedModalRect(var1 + 7 + this.upperInventory.getCustomAlignment(), var2 + i * 18 - 1, 7, 17, k * 18, 18);
	}*/

	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
	{
		this.field_147033_y = (float)p_73863_1_;
		this.field_147032_z = (float)p_73863_2_;

		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}

	@Override
	protected ContainerQuiver getContainer()
	{
		return (ContainerQuiver) super.getContainer();
	}
}
