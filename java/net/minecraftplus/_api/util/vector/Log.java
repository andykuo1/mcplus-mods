package net.minecraftplus._api.util.vector;

import java.io.PrintStream;

public class Log
{
	private Log() {}

	public static void ASSERT(boolean condition)
	{
		ASSERT(condition, "");
	}

	public static void ASSERT(boolean condition, Object message)
	{
		if (!condition)
		{
			String s = message.toString();
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			String className = stackTraceElements[0].getClassName();
			int lineNumber = stackTraceElements[0].getLineNumber();
			throw new AssertionError("<" + className + " @line: " + lineNumber + "> Expected true, but was: " + condition + (s.isEmpty() ? "" : " - " + s));
		}
	}

	public static final void O(Object... message)
	{
		print(System.out, message);
	}

	public static final void E(Object... message)
	{
		print(System.err, message);
	}

	private static final Object NULL = new Object();
	private static final String SEPARATOR = ", ";

	private static final void print(PrintStream printStream, Object... message)
	{
		Object p = null;
		for(Object o : message)
		{
			if (o == null)
			{
				if (p == NULL) printStream.print(SEPARATOR);
			}
			else if (p != null && o.getClass().isAssignableFrom(p.getClass()) && !(o instanceof String))
			{
				printStream.print(SEPARATOR);
			}

			printStream.print(o);
			p = o == null ? NULL : o;
		}

		printStream.println();
	}

	public static final void STACKTRACE()
	{
		new Exception().printStackTrace();
	}
}
