package net.minecraftplus._api.util.vector;

public class Matrix3f
{
	private float[][] matrix;

	public Matrix3f()
	{
		this.matrix = new float[3][3];
	}

	public Matrix3f(float[][] parMatrix)
	{
		this.matrix = parMatrix;
	}

	public static Matrix3f identity()
	{
		Matrix3f result = new Matrix3f();
		result.matrix[0][0] = 1;	result.matrix[0][1] = 0;	result.matrix[0][2] = 0;
		result.matrix[1][0] = 0;	result.matrix[1][1] = 1;	result.matrix[1][2] = 0;
		result.matrix[2][0] = 0;	result.matrix[2][1] = 0;	result.matrix[2][2] = 1;
		return result;
	}

	public Matrix3f mul(Matrix3f parMatrix)
	{
		Matrix3f result = new Matrix3f();
		for (int y = 0; y < 3; y++)
		{
			for (int x = 0; x < 3; x++)
			{
				float sum = 0F;
				for (int e = 0; e < 3; e++)
				{
					sum += this.matrix[e][x] * parMatrix.matrix[y][e]; 
				}			
				result.matrix[y][x] = sum;
			}
		}
		return result;
	}

	public void set(int parX, int parY, float parValue)
	{
		this.matrix[parX][parY] = parValue;
	}

	public void setMatrix(float[][] parMatrix)
	{
		this.matrix = parMatrix;
	}

	public float get(int parX, int parY)
	{
		return this.matrix[parX][parY];
	}

	public float[][] getMatrix()
	{
		float[][] result = new float[3][3];

		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				result[i][j] = this.matrix[i][j];
			}
		}

		return result;
	}

	public float[] toFloatArray()
	{
		float[] result = new float[3 * 3];
		for(int i = 0; i < this.matrix.length; i++)
		{
			for(int j = 0; j < this.matrix[i].length; j++)
			{
				result[j + i * 3] = this.matrix[i][j];
			}
		}
		return result;
	}
}
