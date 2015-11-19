package ca.reivax.javablocks;

public class TestInterfaceExtendImpl extends Test implements TestInterfaceExtends {

	public void print(String text) 
	{
		System.out.println(text);
		
		standard("from proxy");
	}

}
