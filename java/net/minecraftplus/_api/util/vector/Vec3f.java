package net.minecraftplus._api.util.vector;

public class Vec3f
{
	public float x, y, z;

	public Vec3f()
	{
		this.x = this.y = this.z = 0;
	}

	public Vec3f(float xyz)
	{
		this.x = this.y = this.z = xyz;
	}

	public Vec3f(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vec3f(Vec3f vec)
	{
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
	}

	public void set(Vec3f vec)
	{
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
	}

	public float dot(Vec3f vec)
	{
		return this.x * vec.x + this.y * vec.y + this.z * vec.z;
	}

	public Vec3f cross(Vec3f vec)
	{
		float x_ = this.y * vec.z - this.z * vec.y;
		float y_ = this.z * vec.x - this.x * vec.z;
		float z_ = this.x * vec.y - this.y * vec.x;

		return new Vec3f(x_, y_, z_);
	}

	public Vec3f rotate(float angle, Vec3f axis)
	{
		float sinHalfAngle = (float)Math.sin(Math.toRadians(angle / 2));
		float cosHalfAngle = (float)Math.cos(Math.toRadians(angle / 2));

		float rX = axis.x * sinHalfAngle;
		float rY = axis.y * sinHalfAngle;
		float rZ = axis.z * sinHalfAngle;
		float rW = cosHalfAngle;

		Vec4f rotation = new Vec4f(rX, rY, rZ, rW);
		Vec4f conjugate = rotation.conjugate();

		Vec4f w = rotation.mul(this).mul(conjugate);

		return new Vec3f(w.x, w.y, w.z);
	}

	public Vec3f normalize()
	{
		float dist = this.length();
		if (dist == 0) dist = 1;

		float x = this.x / dist;
		float y = this.y / dist;
		float z = this.z / dist;

		return new Vec3f(x, y, z);
	}

	public Vec3f negate()
	{
		return new Vec3f(-this.x, -this.y, -this.z);
	}

	public Vec3f add(Vec3f vec)
	{
		Log.ASSERT(vec != null);

		return new Vec3f(this.x + vec.x, this.y + vec.y, this.z + vec.z);
	}

	public Vec3f sub(Vec3f vec)
	{
		Log.ASSERT(vec != null);

		return new Vec3f(this.x - vec.x, this.y - vec.y, this.z - vec.z);
	}

	public Vec3f mul(float i)
	{
		return new Vec3f(this.x * i, this.y * i, this.z * i);
	}

	public Vec3f div(float i)
	{
		Log.ASSERT(i != 0);

		return new Vec3f(this.x / i, this.y / i, this.z / i);
	}

	public float length()
	{
		return (float)Math.sqrt(this.lengthSqu());
	}

	public float lengthSqu()
	{
		return this.x * this.x + this.y * this.y + this.z * this.z;
	}

	public Vec3f setX(float x)
	{
		return new Vec3f(x, this.y, this.z);
	}

	public Vec3f setX(Vec3f x) { return setX(x.x); }

	public Vec3f setY(float y)
	{
		return new Vec3f(this.x, y, this.z);
	}

	public Vec3f setY(Vec3f y) { return setY(y.y); }

	public Vec3f setZ(float z)
	{
		return new Vec3f(this.x, this.y, z);
	}

	public Vec3f setZ(Vec3f z) { return setZ(z.z); }

	public Vec3f setXY(float x, float y)
	{
		return new Vec3f(x, y, this.z);
	}

	public Vec3f setXY(Vec2f xy) { return setXY(xy.x, xy.y); }

	public Vec3f setXY(Vec3f xy) { return setXY(xy.x, xy.y); }

	public Vec3f setXZ(float x, float z)
	{
		return new Vec3f(x, this.y, z);
	}

	public Vec3f setXZ(Vec2i xz) { return setXZ(xz.x, xz.y); }

	public Vec3f setXZ(Vec3f xz) { return setXZ(xz.x, xz.z); }

	public Vec3f setYZ(float y, float z)
	{
		return new Vec3f(this.x, y, z);
	}

	public Vec3f setYZ(Vec2f yz) { return setYZ(yz.x, yz.y); }

	public Vec3f setYZ(Vec3f yz) { return setYZ(yz.y, yz.z); }

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

	public Vec3i toVec3i()
	{
		return new Vec3i((int) this.x, (int) this.y , (int) this.z);
	}

	public Vec4f toVec4f(float w)
	{
		return new Vec4f(this.x, this.y , this.z, w);
	}
}
