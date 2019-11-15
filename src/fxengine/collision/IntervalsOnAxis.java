package fxengine.collision;

import fxengine.math.Vec2d;

public final class IntervalsOnAxis
{
	public Interval s1;
	public Interval s2;
	public Vec2d axis;
	IntervalsOnAxis(Interval i1, Interval i2, Vec2d axis)
	{
		this.s1 = i1;
		this.s2 = i2;
		this.axis = axis;
	}
}