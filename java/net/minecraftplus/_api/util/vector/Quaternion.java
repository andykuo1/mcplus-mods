package net.minecraftplus._api.util.vector;

public class Quaternion
{
	private float x;
	private float y;
	private float z;
	private float w;

	public Quaternion(float parX, float parY, float parZ, float parW)
	{
		this.x = parX;
		this.y = parY;
		this.z = parZ;
		this.w = parW;
	}

	public Quaternion normalize()
	{
		float length = length();
		return new Quaternion(this.x / length, this.y / length, this.z / length, this.w / length);
	}

	public Quaternion conjugate()
	{
		return new Quaternion(-this.x, -this.y, -this.z, this.w);
	}

	public Quaternion mul(Quaternion parQuaternion)
	{
		float w_ = this.w * parQuaternion.getW() - this.x * parQuaternion.getX() - this.y * parQuaternion.getY() - this.z * parQuaternion.getZ();
		float x_ = this.x * parQuaternion.getW() + this.w * parQuaternion.getX() + this.y * parQuaternion.getZ() - this.z * parQuaternion.getY();
		float y_ = this.y * parQuaternion.getW() + this.w * parQuaternion.getY() + this.z * parQuaternion.getX() - this.x * parQuaternion.getZ();
		float z_ = this.z * parQuaternion.getW() + this.w * parQuaternion.getZ() + this.x * parQuaternion.getY() - this.y * parQuaternion.getX();

		return new Quaternion(x_, y_, z_, w_);
	}

	public Quaternion mul(Vector3f parVector)
	{
		float w_ = -this.x * parVector.getX() - this.y * parVector.getY() - this.z * parVector.getZ();
		float x_ = this.w * parVector.getX() + this.y * parVector.getZ() - this.z * parVector.getY();
		float y_ = this.w * parVector.getY() + this.z * parVector.getX() - this.x * parVector.getZ();
		float z_ = this.w * parVector.getZ() + this.x * parVector.getY() - this.y * parVector.getX();

		return new Quaternion(x_, y_, z_, w_);
	}

	public float length()
	{
		return (float) Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w);
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

	public float getW()
	{
		return w;
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

	public void setW(float parW)
	{
		this.w = parW;
	}
}
