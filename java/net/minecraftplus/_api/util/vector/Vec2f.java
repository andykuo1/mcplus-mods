package net.minecraftplus._api.util.vector;

public final class Vec2f
{
	public float x, y;

	public Vec2f()
	{
		this.x = this.y = 0;
	}

	public Vec2f(float xy)
	{
		this.x = this.y = xy;
	}

	public Vec2f(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public Vec2f(Vec2f vec)
	{
		this.x = vec.x;
		this.y = vec.y;
	}

	public void set(Vec2f vec)
	{
		this.x = vec.x;
		this.y = vec.y;
	}

	public Vec2f normalize()
	{
		float dist = this.length();
		if (dist == 0) dist = 1;

		float x = this.x / dist;
		float y = this.y / dist;

		return new Vec2f(x, y);
	}

	public Vec2f negate()
	{
		return new Vec2f(-this.x, -this.y);
	}

	public Vec2f add(Vec2f vec)
	{
		Log.ASSERT(vec != null);

		return new Vec2f(this.x + vec.x, this.y + vec.y);
	}

	public Vec2f sub(Vec2f vec)
	{
		Log.ASSERT(vec != null);

		return new Vec2f(this.x - vec.x, this.y - vec.y);
	}

	public Vec2f mul(float i)
	{
		return new Vec2f(this.x * i, this.y * i);
	}

	public Vec2f div(float i)
	{
		Log.ASSERT(i != 0);

		return new Vec2f(this.x / i, this.y / i);
	}

	public Vec2f abs()
	{
		return new Vec2f(Math.abs(this.x), Math.abs(this.y));
	}

	public float sum()
	{
		return this.x + this.y;
	}

	public float diff()
	{
		return this.x - this.y;
	}

	public float prod()
	{
		return this.x * this.y;
	}

	public float quot()
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

	public Vec2f setX(float x)
	{
		return new Vec2f(x, this.y);
	}

	public Vec2f setY(float y)
	{
		return new Vec2f(this.x, y);
	}

	public Vec2i toVec2i()
	{
		return new Vec2i((int)this.x , (int)this.y);
	}

	public Vec3f toVec3f(float z)
	{
		return new Vec3f(this.x, this.y, z);
	}

	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Vec2f)
		{
			Vec2f vec = (Vec2f)o;
			return Float.compare(this.x, vec.x) == 0 && Float.compare(this.y, vec.y) == 0;
		}
		else if (o instanceof Vec2i)
		{
			Vec2i vec = (Vec2i)o;
			return Float.compare(this.x, vec.x) == 0 && Float.compare(this.y, vec.y) == 0;
		}

		return super.equals(o);
	}

	@Override
	public String toString()
	{
		return "[" + this.x + "," + this.y + "]";
	}
}
