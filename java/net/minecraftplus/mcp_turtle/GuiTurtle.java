package net.minecraftplus.mcp_turtle;

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
public class GuiTurtle extends GuiContainer
{
	private static final ResourceLocation turtleContainerGui = new ResourceLocation(Assets.resource(_Turtle.MODID, "textures/gui/container_turtle.png"));

	private final EntityPlayer player;
	private final EntityTurtle turtle;

	private final IInventory invUp;
	private final IInventory invDown;

	private float field_147033_y;
	private float field_147032_z;

	public GuiTurtle(EntityPlayer parEntityPlayer, EntityTurtle entityTurtle)
	{
		super(new ContainerTurtle(parEntityPlayer, entityTurtle));

		this.player = parEntityPlayer;
		this.turtle = entityTurtle;

		this.invUp = this.turtle.getChest();
		this.invDown = this.player.inventory;

		this.allowUserInput = false;
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
		this.mc.getTextureManager().bindTexture(turtleContainerGui);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

		if (this.turtle.isChested())
		{
			this.drawTexturedModalRect(k + 79, l + 17, 0, this.ySize, (18 * this.getContainer().columns), (18 * this.getContainer().rows));
		}

		//TURTLE GUI
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		int i1 = (this.width - this.xSize) / 2;
		int j1 = (this.height - this.ySize) / 2;

		if (this.turtle.isDead == false && this.turtle.getHealth() > 0)
		{
			if (this.turtle.getHealth() / this.turtle.getMaxHealth() > 0.5D)
			{
				int k1 = (int) (this.turtle.getMaxHealth() - this.turtle.getHealth()) * 5;
				//Not yet half-dead; Draw all
				this.drawTexturedModalRect(i1 + 12, j1 + 17 + k1, 94, 166 + k1, 7, -k1 + 51);
				this.drawTexturedModalRect(i1 + 23, j1 + 17, 94, 166, 7, 51);
			}
			else
			{
				int k1 = (int) ((this.turtle.getMaxHealth()/2) - this.turtle.getHealth()) * 5;
				//Half-dead; Don't draw other half
				this.drawTexturedModalRect(i1 + 23, j1 + 17 + k1, 94, 166 + k1, 7, -k1 + 51);
			}
		}

		GuiInventory.drawEntityOnScreen(k + 57, l + 58, 30, (float)(k + 51) - this.field_147033_y, (float)(l + 75 - 50) - this.field_147032_z, this.turtle);
	}

	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
	{
		this.field_147033_y = (float)p_73863_1_;
		this.field_147032_z = (float)p_73863_2_;

		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}

	protected ContainerTurtle getContainer()
	{
		return (ContainerTurtle)this.inventorySlots;
	}
}