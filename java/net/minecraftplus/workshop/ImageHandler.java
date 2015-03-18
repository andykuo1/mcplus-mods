package net.minecraftplus.workshop;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.FloatBuffer;

import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class ImageHandler
{
	public static void capture(File par1File, int par2, int par3, int par4, int par5)
	{
		// read current buffer
		FloatBuffer imageData = BufferUtils.createFloatBuffer(par4 * par5 * 3); 
		GL11.glReadPixels(par2, par3, par4, par5, GL11.GL_RGB, GL11.GL_FLOAT, imageData);
		imageData.rewind();

		// fill rgbArray for BufferedImage
		int[] rgbArray = new int[par4 * par5];
		for(int y = 0; y < par5; ++y)
		{
			for(int x = 0; x < par4; ++x)
			{
				int r = (int)(imageData.get() * 255) << 16;
				int g = (int)(imageData.get() * 255) << 8;
				int b = (int)(imageData.get() * 255);
				int i = ((par5 - 1) - y) * par4 + x;
				rgbArray[i] = r + g + b;
			}
		}

		// create and save image
		BufferedImage image = new BufferedImage(par4, par5, BufferedImage.TYPE_INT_RGB);
		image.setRGB(0, 0, par4, par5, rgbArray, 0, par4);

		try
		{
			ImageIO.write(image, "png", par1File);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.err.println("Can not save screenshot!");
		}
	}

	public static File getImageFile(Minecraft par1Minecraft, String par2String)
	{
		File dir = new File(par1Minecraft.mcDataDir, "mods_workshop");
		dir.mkdir();

		String fileName = par2String;
		File imageToSave = new File(dir, fileName + ".png");

		int duplicate = 0;
		while(imageToSave.exists())
		{
			imageToSave = new File(dir, fileName + "_" + ++duplicate + ".png");
		}

		return imageToSave;
	}
}
