/**
 * 
 */
package ca.reivax.javablocks;

import java.util.List;

/**
 * @author xjodoin
 * @author NetAppsID inc.
 * 
 * @version
 * 
 */
public class TotoImpl implements Toto
{
	private boolean intercept = false;
	
	@Interceptor(List.class)
	public void interceptCall()
	{
		intercept = true;
	}
	
	public String testToto()
	{
		return "testToto";
	}
	
	/**
	 * @return the intercept
	 */
	public boolean isIntercept()
	{
		return intercept;
	}
	
	/* (non-Javadoc)
	 * @see ca.reivax.javablocks.Toto#mustAddToList(java.lang.String)
	 */
	public void mustAddToList(String string)
	{
		((List)this).add(string);
	}
	
}
