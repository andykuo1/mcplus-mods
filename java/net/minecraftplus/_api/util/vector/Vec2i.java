package net.minecraftplus._api.util.vector;

public final class Vec2i
{
	public int x, y;

	public Vec2i()
	{
		this.x = this.y = 0;
	}

	public Vec2i(int xy)
	{
		this.x = this.y = xy;
	}

	public Vec2i(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public Vec2i(Vec2i vec)
	{
		this.x = vec.x;
		this.y = vec.y;
	}

	public void set(Vec2i vec)
	{
		this.x = vec.x;
		this.y = vec.y;
	}

	public Vec2i normalize()
	{
		int x = this.x == 0 ? 0 : this.x / Math.abs(this.x);
		int y = this.y == 0 ? 0 : this.y / Math.abs(this.y);

		return new Vec2i(x, y);
	}

	public Vec2i negate()
	{
		return new Vec2i(-this.x, -this.y);
	}

	public Vec2i add(Vec2i vec)
	{
		Log.ASSERT(vec != null);

		return new Vec2i(this.x + vec.x, this.y + vec.y);
	}

	public Vec2i sub(Vec2i vec)
	{
		Log.ASSERT(vec != null);

		return new Vec2i(this.x - vec.x, this.y - vec.y);
	}

	public Vec2i mul(int i)
	{
		return new Vec2i(this.x * i, this.y * i);
	}

	public Vec2i div(int i)
	{
		Log.ASSERT(i != 0);

		return new Vec2i(this.x / i, this.y / i);
	}

	public Vec2i remainder(int i)
	{
		return new Vec2i(this.x % i, this.y % i);
	}

	public Vec2i abs()
	{
		return new Vec2i(Math.abs(this.x), Math.abs(this.y));
	}

	public int sum()
	{
		return this.x + this.y;
	}

	public int diff()
	{
		return this.x - this.y;
	}

	public int prod()
	{
		return this.x * this.y;
	}

	public int quot()
	{
		Log.ASSERT(this.y != 0);

		return this.x / this.y;
	}

	public float length()
	{
		return (float)Math.sqrt(this.lengthSqu());
	}

	public float lengthSqu()
	{
		return this.x * this.x + this.y * this.y;
	}

	public Vec2i setX(int x)
	{
		return new Vec2i(x, this.y);
	}

	public Vec2i setY(int y)
	{
		return new Vec2i(this.x, y);
	}

	public Vec2f toVec2f()
	{
		return new Vec2f(this.x , this.y);
	}

	public Vec3i toVec3i(int z)
	{
		return new Vec3i(this.x, this.y, z);
	}

	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Vec2i)
		{
			Vec2i vec = (Vec2i)o;
			return this.x == vec.x && this.y == vec.y;
		}

		return super.equals(o);
	}

	@Override
	public String toString()
	{
		return "[" + this.x + "," + this.y + "]";
	}
}
