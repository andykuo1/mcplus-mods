package net.minecraftplus._api.util;

public final class ColorUtil
{
	private ColorUtil() {}

	public static final int rgbToHex(int R, int G, int B)
	{
		return (R << 16) + (G << 8) + B;
	}

	public static final int hexToR(int parColor)
	{
		return (parColor & 0xFF0000) >> 16;
	}

	public static final int hexToG(int parColor)
	{
		return (parColor & 0xFF00) >> 8;
	}

	public static final int hexToB(int parColor)
	{
		return (parColor & 0xFF);
	}

	public static final float hexToFloatR(int parColor)
	{
		return hexToR(parColor) / 255F;
	}

	public static final float hexToFloatG(int parColor)
	{
		return hexToG(parColor) / 255F;
	}

	public static final float hexToFloatB(int parColor)
	{
		return hexToB(parColor) / 255F;
	}
}
