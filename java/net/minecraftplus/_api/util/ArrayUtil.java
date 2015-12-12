package net.minecraftplus._api.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtil
{
	public static final <E> boolean valid(E[] parArray)
	{
		return parArray != null && parArray.length != 0;
	}

	public static final <E> boolean validInstanceOf(E[] parArray, Class parSuperClass)
	{
		assert(parArray != null);
		assert(parSuperClass != null);

		for(int i = 0; i < parArray.length; ++i)
		{
			if (parArray[i] != null && !parSuperClass.isAssignableFrom(parArray[i].getClass())) return false;
		}

		return true;
	}

	public static final <E> boolean validElements(E[] parArray)
	{
		assert(parArray != null);

		for(int i = 0; i < parArray.length; ++i)
		{
			if (parArray[i] == null) return false;
		}

		return true;
	}

	public static final <E> boolean validMinLength(E[] parArray, int parLength)
	{
		assert(parArray != null);

		return parArray.length >= parLength;
	}

	public static final <E> boolean validMinLength(E[][] parArray, int parLength1, int parLength2)
	{
		assert(parArray != null);

		if (parArray.length < parLength1) return false;

		for(int i = 0; i < parArray.length; ++i)
		{
			if (parArray[i].length < parLength2) return false;
		}

		return true;
	}

	public static final <E> boolean validMaxLength(E[] parArray, int parLength)
	{
		assert(parArray != null);

		return parArray.length <= parLength;
	}

	public static final <E> boolean validMaxLength(E[][] parArray, int parLength1, int parLength2)
	{
		assert(parArray != null);

		if (parArray.length > parLength1) return false;

		for(int i = 0; i < parArray.length; ++i)
		{
			if (parArray[i].length > parLength2) return false;
		}

		return true;
	}

	public static final <E> List<E> put(E[] parArray, E... parElements)
	{
		assert(parArray != null);

		List<E> dump = new ArrayList<E>();
		int i = indexOf(parArray, null);
		for(E e : parElements)
		{
			if (i != -1)
			{
				parArray[i] = e;
				i = indexOfRange(parArray, null, i, parArray.length);
			}
			else
			{
				dump.add(e);
			}
		}

		return dump;
	}

	public static final <E> E[] append(E[] parArray, E... parElements)
	{
		assert(parArray != null);

		E[] result = Arrays.copyOf(parArray, parArray.length + parElements.length);
		for(int i = parArray.length; i < result.length; ++i)
		{
			result[i] = parElements[i - parArray.length];
		}

		return result;
	}

	public static <E> List<E[]> findCombinations(E[] parArray, int parLength)
	{
		List<E[]> list = new ArrayList<E[]>();
		combinations(parArray, parLength, 0, Arrays.copyOf(parArray, parLength), list);
		return list;
	}

	private static <E> void combinations(E[] parArray, int parLength, int parPosition, E[] parOutput, List<E[]> parResult)
	{
		if (parLength == 0)
		{
			parResult.add(Arrays.copyOf(parOutput, parOutput.length));
			return;
		}

		for (int i = parPosition; i <= parArray.length - parLength; i++)
		{
			parOutput[parOutput.length - parLength] = parArray[i];
			combinations(parArray, parLength - 1, i + 1, parOutput, parResult);
		}
	}


	public static final <E> boolean containsInstanceOf(E[] parArray, Class parSuperClass)
	{
		assert(parArray != null);
		assert(parSuperClass != null);

		for(int i = 0; i < parArray.length; ++i)
		{
			if (parArray[i] != null && parSuperClass.isAssignableFrom(parArray[i].getClass()))
			{
				return true;
			}
		}

		return false;
	}

	public static final <E> boolean contains(E[] parArray, E parValue)
	{
		assert(parArray != null);

		for(int i = 0; i < parArray.length; ++i)
		{
			if (parValue == null ? parArray[i] == null : parValue.equals(parArray[i]))
			{
				return true;
			}
		}

		return false;
	}

	public static final boolean contains(int[] parArray, int parValue)
	{
		assert(parArray != null);

		for(int i = 0; i < parArray.length; ++i)
		{
			if (parArray[i] == parValue)
			{
				return true;
			}
		}

		return false;
	}

	public static final boolean contains(boolean[] parArray, boolean parValue)
	{
		assert(parArray != null);

		for(int i = 0; i < parArray.length; ++i)
		{
			if (parArray[i] == parValue)
			{
				return true;
			}
		}

		return false;
	}

	public static final <E> int indexOf(E[] parArray, E parValue)
	{
		return indexOfRange(parArray, parValue, 0, parArray.length);
	}

	public static final int indexOf(int[] parArray, int parValue)
	{
		assert(parArray != null);

		for(int i = 0; i < parArray.length; ++i)
		{
			if (parValue == parArray[i])
			{
				return i;
			}
		}

		return -1;
	}

	public static final <E> int indexOfRange(E[] parArray, E parValue, int parLowerBound, int parUpperBound)
	{
		assert(parArray != null);
		assert(parLowerBound >= 0);
		assert(parUpperBound <= parArray.length);

		for(int i = parLowerBound; i < parUpperBound; ++i)
		{
			if (parValue == null ? parArray[i] == null : parValue.equals(parArray[i]))
			{
				return i;
			}
		}

		return -1;
	}
}
