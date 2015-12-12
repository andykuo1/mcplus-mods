package net.minecraftplus._api.util.vector;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class BufferUtil
{
	private BufferUtil(){}

	public static ByteBuffer createByteBuffer(byte[] parArray)
	{
		ByteBuffer result = ByteBuffer.allocateDirect(parArray.length).order(ByteOrder.nativeOrder());
		result.put(parArray).flip();
		return result;
	}

	public static FloatBuffer createFloatBuffer(float[] parArray)
	{
		FloatBuffer result = ByteBuffer.allocateDirect(parArray.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
		result.put(parArray).flip();
		return result;
	}

	public static IntBuffer createIntBuffer(int[] parArray)
	{
		IntBuffer result = ByteBuffer.allocateDirect(parArray.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
		result.put(parArray).flip();
		return result;
	}
}
