package net.minecraftplus._api.dictionary;

import net.minecraftforge.fml.common.FMLCommonHandler;

import com.ibm.icu.impl.InvalidFormatException;


public final class Exceptions
{
	private Exceptions() {};

	private static final void errorInfo(Exception parException, Object[] parArgs)
	{
		System.err.println("------------------------------------------------------------------------------------------");
		System.err.println("////////////////////////////////////// - MC+ Mods - //////////////////////////////////////");
		System.err.println("------------------------------------------------------------------------------------------");
		System.err.println("");
		for(int i = 0; i < parArgs.length; ++i)
		{
			System.err.println(parArgs[i]);
		}
		System.err.println("");
		parException.printStackTrace();
		System.err.println("");
		System.err.println("------------------------------------------------------------------------------------------");
		System.err.println("//////////////////////////////////////////////////////////////////////////////////////////");
		System.err.println("------------------------------------------------------------------------------------------");
	}

	public static final void IllegalArgs(boolean parCondition, Object...parArgs)
	{
		if (parCondition) return;

		try
		{
			throw new IllegalArgumentException();
		}
		catch (IllegalArgumentException e)
		{
			errorInfo(e, parArgs);
			FMLCommonHandler.instance().exitJava(1, false);
		}
	}

	public static final void InvalidFormat(boolean parCondition, 	Object...parArgs)
	{
		if (parCondition) return;

		try
		{
			throw new InvalidFormatException();
		}
		catch (InvalidFormatException e)
		{
			errorInfo(e, parArgs);
			FMLCommonHandler.instance().exitJava(1, false);
		}
	}
}
