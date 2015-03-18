package net.minecraftplus.cart;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelChest;
import net.minecraft.util.ResourceLocation;
import net.minecraftplus._api.tool.ResTool;
import net.minecraftplus.cart.cargo.Cargo;
import net.minecraftplus.cart.cargo.CargoChest;
import net.minecraftplus.cart.cargo.CargoEnderChest;
import net.minecraftplus.cart.cargo.CargoFurnace;
import net.minecraftplus.cart.cargo.CargoJukeBox;
import net.minecraftplus.cart.cargo.CargoWorkbench;

import org.lwjgl.opengl.GL11;

public class RenderCargo
{
	private ModelBase modelBlock = new ModelBlock();
	private ModelBase modelChest = new ModelChest();
	private ResourceLocation textureChest = ResTool.getResource("normal", ResTool.ENTITIES + "/chest", true);
	private ResourceLocation textureEnderChest = ResTool.getResource("ender", ResTool.ENTITIES + "/chest", true);

	private ResourceLocation textureFurnaceIdle = ResTool.getResource("cargo.furnace.idle", ResTool.BLOCKS, ModCart.INSTANCE);
	private ResourceLocation textureFurnaceBurning = ResTool.getResource("cargo.furnace.burning", ResTool.BLOCKS, ModCart.INSTANCE);

	private ResourceLocation textureJukeBox = ResTool.getResource("cargo.jukebox", ResTool.BLOCKS, ModCart.INSTANCE);

	private ResourceLocation textureWorkbench = ResTool.getResource("cargo.workbench", ResTool.BLOCKS, ModCart.INSTANCE);

	public void render(Cargo parCargo)
	{
		if (parCargo instanceof CargoChest)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(this.textureChest);
			GL11.glTranslatef(0.06F, 0.4F, 0.06F);
			((ModelChest) this.modelChest).renderAll();
		}
		else if (parCargo instanceof CargoEnderChest)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(this.textureEnderChest);
			GL11.glTranslatef(0.06F, 0.4F, 0.06F);
			((ModelChest) this.modelChest).renderAll();
		}
		else if (parCargo instanceof CargoFurnace)
		{
			if (((CargoFurnace) parCargo).isBurning())
			{
				Minecraft.getMinecraft().renderEngine.bindTexture(((CargoFurnace) parCargo).isBurning() ? this.textureFurnaceBurning : this.textureFurnaceIdle);
				this.modelBlock.render(parCargo.getCart(), 0, 0, 0, 0, 0, 0);
			}
		}
		else if (parCargo instanceof CargoJukeBox)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(this.textureJukeBox);
			this.modelBlock.render(parCargo.getCart(), 0, 0, 0, 0, 0, 0);
		}
		else if (parCargo instanceof CargoWorkbench)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(this.textureWorkbench);
			this.modelBlock.render(parCargo.getCart(), 0, 0, 0, 0, 0, 0);
		}
		else
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(null);
			this.modelBlock.render(parCargo.getCart(), 0, 0, 0, 0, 0, 0);
		}
	}
}
