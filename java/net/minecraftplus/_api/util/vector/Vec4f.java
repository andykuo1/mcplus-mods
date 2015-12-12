package net.minecraftplus._api.util.vector;

public class Vec4f
{
	public float x, y, z, w;

	public Vec4f()
	{
		this.x = this.y = this.z = this.w = 0;
	}

	public Vec4f(float xyzw)
	{
		this.x = this.y = this.z = this.w = xyzw;
	}

	public Vec4f(float x, float y, float z, float w)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public Vec4f(Vec4f vec)
	{
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
		this.w = vec.w;
	}

	public void set(Vec4f vec)
	{
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
		this.w = vec.w;
	}

	public Vec4f normalize()
	{
		float dist = this.length();
		if (dist == 0) dist = 1;

		float x = this.x / dist;
		float y = this.y / dist;
		float z = this.z / dist;
		float w = this.w / dist;

		return new Vec4f(x, y, z, w);
	}

	public Vec4f negate()
	{
		return new Vec4f(-this.x, -this.y, -this.z, -this.w);
	}

	public Vec4f conjugate()
	{
		return new Vec4f(-this.x, -this.y, -this.z, this.w);
	}

	public Vec4f mul(Vec4f vec)
	{
		float w_ = this.w * vec.w - this.x * vec.x - this.y * vec.y - this.z * vec.z;
		float x_ = this.x * vec.w + this.w * vec.x + this.y * vec.z - this.z * vec.y;
		float y_ = this.y * vec.w + this.w * vec.y + this.z * vec.x - this.x * vec.z;
		float z_ = this.z * vec.w + this.w * vec.z + this.x * vec.y - this.y * vec.x;

		return new Vec4f(x_, y_, z_, w_);
	}

	public Vec4f mul(Vec3f vec)
	{
		return this.mul(vec.toVec4f(0));
	}

	public Vec4f add(Vec4f vec)
	{
		Log.ASSERT(vec != null);

		return new Vec4f(this.x + vec.x, this.y + vec.y, this.z + vec.z, this.w + vec.w);
	}

	public Vec4f sub(Vec4f vec)
	{
		Log.ASSERT(vec != null);

		return new Vec4f(this.x - vec.x, this.y - vec.y, this.z - vec.z, this.w - vec.w);
	}

	public Vec4f mul(float i)
	{
		return new Vec4f(this.x * i, this.y * i, this.z * i, this.w * i);
	}

	public Vec4f div(float i)
	{
		Log.ASSERT(i != 0);

		return new Vec4f(this.x / i, this.y / i, this.z / i, this.w / i);
	}

	public float length()
	{
		return (float)Math.sqrt(this.lengthSqu());
	}

	public float lengthSqu()
	{
		return this.x * this.x + this.y * this.y + this.z * this.z;
	}

	public Vec2f xy()
	{
		return new Vec2f(this.x, this.y);
	}

	public Vec2f xz()
	{
		return new Vec2f(this.x, this.z);
	}

	public Vec2f yz()
	{
		return new Vec2f(this.y, this.z);
	}

	public Vec3f xyz()
	{
		return new Vec3f(this.x, this.y, this.z);
	}

	public Vec3f xyw()
	{
		return new Vec3f(this.x, this.y, this.w);
	}

	public Vec3f xzw()
	{
		return new Vec3f(this.x, this.z, this.w);
	}

	public Vec3f yzw()
	{
		return new Vec3f(this.y, this.z, this.w);
	}
}
