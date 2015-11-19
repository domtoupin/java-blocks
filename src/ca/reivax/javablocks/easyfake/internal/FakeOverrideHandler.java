package ca.reivax.javablocks.easyfake.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import ca.reivax.javablocks.easyfake.FakeMethod;
import ca.reivax.javablocks.easyfake.FakeOverride;
import ca.reivax.javablocks.easyfake.FakeReturn;


import javassist.util.proxy.MethodHandler;

public class FakeOverrideHandler implements MethodHandler, FakeOverride
{
	private Map<Method, FakeMethod<?>> methodBodies = new HashMap<Method, FakeMethod<?>>();
	private Method lastMethod;

	private boolean record = true;

	public Object invoke(Object self, Method method, Method proceed, Object[] args) throws Throwable
	{
		if (method.getDeclaringClass().equals(FakeOverride.class))
		{
			return method.invoke(this, args);
		}
		else
		{
			if (methodBodies.containsKey(method))
			{
				return methodBodies.get(method).fake(args);
			}
			else
			{
				lastMethod = method;

				if (!record)
				{
					try
					{
						return proceed.invoke(self, args);
					}
					catch (InvocationTargetException e)
					{
						throw e.getTargetException();
					}
				}
				else
				{
					return null;
				}

			}
		}
	}

	public void with(FakeMethod body)
	{
		methodBodies.put(lastMethod, body);
	}

	public void andReturn(Object returnValue)
	{
		methodBodies.put(lastMethod, new FakeReturn(returnValue));
	}

	public void startFaking()
	{
		record = false;
	}
}
