package com.minecraftplus.modCart;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelChest;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.minecraftplus.modCart.cargo.Cargo;
import com.minecraftplus.modCart.cargo.CargoChest;
import com.minecraftplus.modCart.cargo.CargoEnderChest;
import com.minecraftplus.modCart.cargo.CargoFurnace;
import com.minecraftplus.modCart.cargo.CargoJukeBox;
import com.minecraftplus.modCart.cargo.CargoWorkbench;

public class RenderCargo
{
	private ModelBase modelBlock = new ModelBlock();
	private ModelBase modelChest = new ModelChest();
	private ResourceLocation textureChest = new ResourceLocation("textures/entity/chest/normal.png"); 
	private ResourceLocation textureEnderChest = new ResourceLocation("textures/entity/chest/ender.png");

	private ResourceLocation textureFurnaceIdle = new ResourceLocation("minecraftplus:textures/blocks/cargo.furnace.idle.png");
	private ResourceLocation textureFurnaceBurning = new ResourceLocation("minecraftplus:textures/blocks/cargo.furnace.burning.png");

	private ResourceLocation textureJukeBox = new ResourceLocation("minecraftplus:textures/blocks/cargo.jukebox.png");

	private ResourceLocation textureWorkbench = new ResourceLocation("minecraftplus:textures/blocks/cargo.workbench.png");

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
