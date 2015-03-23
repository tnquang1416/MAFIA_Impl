package helper;

/**
 * 
 * @author TRAN Nhat Quang
 *
 */
public class Timer {
	private long started;
	
	public Timer()
	{
		this.reset();
	}
	
	public void reset()
	{
		this.started = System.currentTimeMillis();
	}
	
	public long stop()
	{
		return (System.currentTimeMillis() - this.started)/1000;
	}
}
