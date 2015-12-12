package net.minecraftplus._api.util.vector;

public class Vec3i
{
	public int x, y, z;

	public Vec3i()
	{
		this.x = this.y = this.z = 0;
	}

	public Vec3i(int xyz)
	{
		this.x = this.y = this.z = xyz;
	}

	public Vec3i(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vec3i(Vec3i vec)
	{
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
	}

	public void set(Vec3i vec)
	{
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
	}

	public Vec3i normalize()
	{
		int x = this.x == 0 ? 0 : this.x / Math.abs(this.x);
		int y = this.y == 0 ? 0 : this.y / Math.abs(this.y);
		int z = this.z == 0 ? 0 : this.z / Math.abs(this.z);

		return new Vec3i(x, y, z);
	}

	public Vec3i negate()
	{
		return new Vec3i(-this.x, -this.y, -this.z);
	}

	public Vec3i add(Vec3i vec)
	{
		Log.ASSERT(vec != null);

		return new Vec3i(this.x + vec.x, this.y + vec.y, this.z + vec.z);
	}

	public Vec3i sub(Vec3i vec)
	{
		Log.ASSERT(vec != null);

		return new Vec3i(this.x - vec.x, this.y - vec.y, this.z - vec.z);
	}

	public Vec3i mul(int i)
	{
		return new Vec3i(this.x * i, this.y * i, this.z * i);
	}

	public Vec3i div(int i)
	{
		Log.ASSERT(i != 0);

		return new Vec3i(this.x / i, this.y / i, this.z / i);
	}

	public float length()
	{
		return (float)Math.sqrt(this.lengthSqu());
	}

	public float lengthSqu()
	{
		return this.x * this.x + this.y * this.y + this.z * this.z;
	}

	public Vec3i setX(int x)
	{
		return new Vec3i(x, this.y, this.z);
	}

	public Vec3i setX(Vec3i x) { return setX(x.x); }

	public Vec3i setY(int y)
	{
		return new Vec3i(this.x, y, this.z);
	}

	public Vec3i setY(Vec3i y) { return setY(y.y); }

	public Vec3i setZ(int z)
	{
		return new Vec3i(this.x, this.y, z);
	}

	public Vec3i setZ(Vec3i z) { return setZ(z.z); }

	public Vec3i setXY(int x, int y)
	{
		return new Vec3i(x, y, this.z);
	}

	public Vec3i setXY(Vec2i xy) { return setXY(xy.x, xy.y); }

	public Vec3i setXY(Vec3i xy) { return setXY(xy.x, xy.y); }

	public Vec3i setXZ(int x, int z)
	{
		return new Vec3i(x, this.y, z);
	}

	public Vec3i setXZ(Vec2i xz) { return setXZ(xz.x, xz.y); }

	public Vec3i setXZ(Vec3i xz) { return setXZ(xz.x, xz.z); }

	public Vec3i setYZ(int y, int z)
	{
		return new Vec3i(this.x, y, z);
	}

	public Vec3i setYZ(Vec2i yz) { return setYZ(yz.x, yz.y); }

	public Vec3i setYZ(Vec3i yz) { return setYZ(yz.y, yz.z); }

	public Vec2i xy()
	{
		return new Vec2i(this.x, this.y);
	}

	public Vec2i xz()
	{
		return new Vec2i(this.x, this.z);
	}

	public Vec2i yz()
	{
		return new Vec2i(this.y, this.z);
	}

	public Vec3f toVec3f()
	{
		return new Vec3f(this.x, this.y , this.z);
	}
}
