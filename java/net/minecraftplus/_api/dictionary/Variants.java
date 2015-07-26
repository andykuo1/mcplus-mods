package net.minecraftplus._api.dictionary;

import net.minecraftplus._api.util.ArrayUtil;
import net.minecraftplus._api.util.json.JSONMap;

public final class Variants
{
	private Variants() {}

	//public static final JSONMap STAGE(String.. parModelStages);
	//public static final JSONMap DOUBLE(String parModelUpper, String parModelLower);
	//public static final JSONMap DIRECTONAL(String parModelDown, String parModelUp, String parModelNorth, String parModelSouth, String parModelWest, String parModelEast);

	public static final JSONMap DIRECTIONAL(Object[] parDown, Object[] parUp, Object[] parNorth, Object[] parSouth, Object[] parWest, Object[] parEast)
	{
		assert(ArrayUtil.validMinLength(parDown, 4));
		assert(ArrayUtil.validMinLength(parUp, 4));
		assert(ArrayUtil.validMinLength(parNorth, 4));
		assert(ArrayUtil.validMinLength(parSouth, 4));
		assert(ArrayUtil.validMinLength(parWest, 4));
		assert(ArrayUtil.validMinLength(parEast, 4));

		String[] template = new String[]{"model", "x", "y", "z"};

		Object[][] list = new Object[6][template.length + 1];
		list[0][0] = "facing=down";
		ArrayUtil.put(list[0], parDown);

		list[1][0] = "facing=up";
		ArrayUtil.put(list[1], parUp);

		list[2][0] = "facing=north";
		ArrayUtil.put(list[2], parNorth);

		list[3][0] = "facing=south";
		ArrayUtil.put(list[3], parSouth);

		list[4][0] = "facing=west";
		ArrayUtil.put(list[4], parWest);

		list[5][0] = "facing=east";
		ArrayUtil.put(list[5], parEast);

		return BASE(template, list);
	}

	public static final JSONMap CROP(String parModelBase, int parStages)
	{
		Object[][] list = new Object[8][2];

		int inc = 8 / parStages;
		int stage = 0;

		for(int i = 0; i < 8; ++i)
		{
			if (i != 0 && i % inc == 0) stage++;
			list[i][0] = "age=" + i;
			list[i][1] = parModelBase + ".stage_" + stage;
		}

		return MODEL_BASE(list);
	}

	public static final JSONMap COLUMN(String parModelY, String parModelZ, String parModelAll)
	{
		return BASE(new String[]{"model", "y"},
				new Object[]{"axis=y", parModelY, 0},
				new Object[]{"axis=z", parModelZ, 0},
				new Object[]{"axis=x", parModelZ, 90},
				new Object[]{"axis=none", parModelAll, 0});
	}

	public static final JSONMap NORMAL(String parModel)
	{
		return MODEL_BASE(new Object[]{"normal", parModel});
	}

	public static final JSONMap MODEL_BASE(Object[]... parModels)
	{
		return BASE(new String[]{"model"}, parModels);
	}

	public static final JSONMap BASE(String[] parEntryTemplate, Object[]... parModels)
	{
		assert(ArrayUtil.valid(parModels));
		assert(ArrayUtil.validElements(parModels));
		assert(ArrayUtil.validMinLength(parModels, 0, parEntryTemplate.length + 1));

		JSONMap map = new JSONMap();
		JSONMap variants = new JSONMap();

		for(Object[] entry : parModels)
		{
			JSONMap variant = new JSONMap();
			for(int i = 0; i < parEntryTemplate.length; ++i)
			{
				Object obj = entry[i + 1];
				variant.put(parEntryTemplate[i], obj);
			}
			variants.put((String) entry[0], variant);
		}

		map.put("variants", variants);
		return map;
	}
}
