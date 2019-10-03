package debugger.collisions;

public final class Interval {
	
	private double min;
	private double max;
	
	public Interval(double min, double max) {
		// TODO Auto-generated constructor stub
		this.min = min;
		this.max = max;
	}
	
	public boolean overlap(Interval other)
	{
		if(this.min <= other.max && other.min <= this.max)
		{
			return true;
		}
		return false;
	};
}