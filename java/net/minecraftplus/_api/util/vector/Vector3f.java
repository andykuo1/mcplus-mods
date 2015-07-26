package net.minecraftplus._api.util.vector;

public class Vector3f
{
	private float x;
	private float y;
	private float z;

	public Vector3f(float parX, float parY, float parZ)
	{
		this.x = parX;
		this.y = parY;
		this.z = parZ;
	}

	public float dot(Vector3f parVector)
	{
		return this.x * parVector.getX() + this.y * parVector.getY() + this.z * parVector.getZ();
	}

	public Vector3f cross(Vector3f parVector)
	{
		float x_ = this.y * parVector.getZ() - this.z * parVector.getY();
		float y_ = this.z * parVector.getX() - this.x * parVector.getZ();
		float z_ = this.x * parVector.getY() - this.y * parVector.getX();

		return new Vector3f(x_, y_, z_);
	}

	public Vector3f normalize()
	{
		float length = this.length();
		return new Vector3f(this.x / length, this.y / length, this.z / length);
	}

	public Vector3f rotate(float parAngle, Vector3f parAxis)
	{
		float sinHalfAngle = (float)Math.sin(Math.toRadians(parAngle / 2));
		float cosHalfAngle = (float)Math.cos(Math.toRadians(parAngle / 2));

		float rX = parAxis.getX() * sinHalfAngle;
		float rY = parAxis.getY() * sinHalfAngle;
		float rZ = parAxis.getZ() * sinHalfAngle;
		float rW = cosHalfAngle;

		Quaternion rotation = new Quaternion(rX, rY, rZ, rW);
		Quaternion conjugate = rotation.conjugate();

		Quaternion w = rotation.mul(this).mul(conjugate);

		return new Vector3f(w.getX(), w.getY(), w.getZ());
	}

	public Vector3f add(Vector3f parVector)
	{
		return new Vector3f(this.x + parVector.x, this.y + parVector.y, this.z + parVector.z);
	}

	public Vector3f sub(Vector3f parVector)
	{
		return new Vector3f(this.x - parVector.x, this.y - parVector.y, this.z - parVector.z);
	}

	public Vector3f mul(Vector3f parVector)
	{
		return new Vector3f(this.x * parVector.x, this.y * parVector.y, this.z * parVector.z);
	}

	public Vector3f div(Vector3f parVector)
	{
		return new Vector3f(this.x / parVector.x, this.y / parVector.y, this.z / parVector.z);
	}

	public Vector3f add(float parFloat)
	{
		return new Vector3f(this.x + parFloat, this.y + parFloat, this.z + parFloat);
	}

	public Vector3f sub(float parFloat)
	{
		return new Vector3f(this.x - parFloat, this.y - parFloat, this.z - parFloat);
	}

	public Vector3f mul(float parFloat)
	{
		return new Vector3f(this.x * parFloat, this.y * parFloat, this.z * parFloat);
	}

	public Vector3f div(float parFloat)
	{
		return new Vector3f(this.x / parFloat, this.y / parFloat, this.z / parFloat);
	}

	public Vector3f abs()
	{
		return new Vector3f(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z));
	}

	public float sum()
	{
		return this.x + this.y + this.z;
	}

	public float diff()
	{
		return this.x - this.y - this.z;
	}

	public float prod()
	{
		return this.x * this.y * this.z;
	}

	public float quot()
	{
		return this.x / this.y / this.z;
	}

	public float length()
	{
		return (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
	}

	public float max()
	{
		return Math.max(this.x, Math.max(this.y, this.z));
	}

	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}

	public float getZ()
	{
		return z;
	}

	public void setX(float parX)
	{
		this.x = parX;
	}

	public void setY(float parY)
	{
		this.y = parY;
	}

	public void setZ(float parZ)
	{
		this.z = parZ;
	}

	public void addX(float parX)
	{
		this.x += parX;
	}

	public void addY(float parY)
	{
		this.y += parY;
	}

	public void addZ(float parZ)
	{
		this.z += parZ;
	}

	@Override
	public boolean equals(Object parObject)
	{
		if (parObject instanceof Vector3f)
		{
			return Float.compare(this.x, ((Vector3f) parObject).x) == 0 && Float.compare(this.y, ((Vector3f) parObject).y) == 0 && Float.compare(this.z, ((Vector3f) parObject).z) == 0;
		}

		return super.equals(parObject);
	}

	@Override
	public String toString()
	{
		return "(" + this.x + "," + this.y + "," + this.z + ")";
	}
}
