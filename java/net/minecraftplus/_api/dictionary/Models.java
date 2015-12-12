package net.minecraftplus._api.dictionary;

import java.util.Map;

import net.minecraftplus._api.util.ArrayUtil;
import net.minecraftplus._api.util.collection.SmallMap;
import net.minecraftplus._api.util.json.JSONMap;
import net.minecraftplus._api.util.vector.Log;
import net.minecraftplus._api.util.vector.Matrix3f;
import net.minecraftplus._api.util.vector.Vec3f;
import net.minecraftplus._api.util.vector.Vec4f;

public class Models
{
	public static final String BOTTOM = "bottom";
	public static final String TOP = "top";
	public static final String SIDE = "side";
	public static final String END = "end";
	public static final String BODY = "body";
	public static final String INSIDE = "inside";
	public static final String NORTH = "north";
	public static final String EAST = "east";
	public static final String SOUTH = "south";
	public static final String WEST = "west";
	public static final String ALL = "all";
	public static final String TEXTURE = "texture";
	public static final String PARTICLE = "particle";

	public static final String SLAB = "slab";
	public static final String CROSS = "cross";
	public static final String CROSSOVERLAY = "crossoverlay";
	public static final String LINE = "line";
	public static final String LINEOVERLAY = "lineoverlay";
	public static final String BARS = "bars";
	public static final String PANE = "pane";
	public static final String EDGE = "edge";
	public static final String GLASS = "glass";
	public static final String RAIL = "rail";

	public static final String UNLIT = "unlit";
	public static final String LIT = "lit";
	public static final String LOCK = "lock";
	public static final String TORCH = "torch";
	public static final String FIRE = "fire";
	public static final String PORTAL = "portal";

	public static final String WOOL = "wool";
	public static final String OBSIDIAN = "obsidian";
	public static final String BEACON = "beacon";
	public static final String WATER = "water";

	public static final String PLANT = "plant";
	public static final String STEM = "stem";
	public static final String UPPERSTEM = "upperstem";
	public static final String CROP = "crop";

	public static final String WALL = "wall";
	public static final String LEVER = "lever";
	public static final String UNSTICKY = "unsticky";
	public static final String PLATFORM = "platform";

	public static final JSONMap ITEM_HELMET_BOOTS(String... parTextures)
	{
		return ITEM(
				"builtin/generated",
				parTextures,
				new Matrix3f(new float[][]{
						{-90, 0, 0},
						{0, 1F, -2.25F},
						{0.55F, 0.55F, 0.55F}
				}),
				new Matrix3f(new float[][]{
						{0, -135, 25},
						{0, 4, 2},
						{1.7F, 1.7F, 1.7F}
				}),
				null
				);
	}

	public static final JSONMap ITEM_TOOLS(String... parTextures)
	{
		return ITEM(
				"builtin/generated",
				parTextures,
				new Matrix3f(new float[][]{
						{0, 90, -35},
						{0, 1.25F, -3.5F},
						{0.85F, 0.85F, 0.85F}
				}),
				new Matrix3f(new float[][]{
						{0, -135, 25},
						{0, 4, 2},
						{1.7F, 1.7F, 1.7F}
				}),
				null
				);
	}

	public static final JSONMap ITEM_BOW(String... parTextures)
	{
		return ITEM(
				"builtin/generated",
				parTextures,
				new Matrix3f(new float[][]{
						{5, -100, -45},
						{0.75F, 0, 0.25F},
						{1, 1, 1}
				}),
				new Matrix3f(new float[][]{
						{0, -135, 25},
						{0, 4, 2},
						{1.7F, 1.7F, 1.7F}
				}),
				null
				);
	}

	public static final JSONMap ITEM_BLOCK(String parParent)
	{
		return ITEM(
				parParent,
				null,
				new Matrix3f(new float[][]{
						{10, -45, 170},
						{0, 1.5F, -2.75F},
						{0.375F, 0.375F, 0.375F}
				}),
				null,
				null
				);
	}

	public static final JSONMap ITEM_BASE(String... parTextures)
	{
		return ITEM(
				"builtin/generated",
				parTextures,
				new Matrix3f(new float[][]{
						{-90, 0, 0},
						{0, 1, -3},
						{0.55F, 0.55F, 0.55F}
				}),
				new Matrix3f(new float[][]{
						{0, -135, 25},
						{0, 4, 2},
						{1.7F, 1.7F, 1.7F}
				}),
				null
				);
	}

	public static final JSONMap ITEM(String parParent, String[] parTextures, Matrix3f parThirdPerson, Matrix3f parFirstPerson, Vec3f parGui)
	{
		JSONMap map = new JSONMap();
		map.put("parent", parParent);

		if (ArrayUtil.valid(parTextures))
		{
			JSONMap textures = new JSONMap();

			for(int i = 0; i < parTextures.length; ++i)
			{
				textures.put("layer" + i, parTextures[i]);
			}

			map.put("textures", textures);
		}

		if (parThirdPerson != null || parFirstPerson != null || parGui != null)
		{
			JSONMap display = new JSONMap();

			if (parThirdPerson != null)
			{
				JSONMap thirdperson = new JSONMap();
				thirdperson.put("rotation", parThirdPerson.get(0, 0), parThirdPerson.get(0, 1), parThirdPerson.get(0, 2));
				thirdperson.put("translation", parThirdPerson.get(1, 0), parThirdPerson.get(1, 1), parThirdPerson.get(1, 2));
				thirdperson.put("scale", parThirdPerson.get(2, 0), parThirdPerson.get(2, 1), parThirdPerson.get(2, 2));
				display.put("thirdperson", thirdperson);
			}

			if (parFirstPerson != null)
			{
				JSONMap firstperson = new JSONMap();
				firstperson.put("rotation", parFirstPerson.get(0, 0), parFirstPerson.get(0, 1), parFirstPerson.get(0, 2));
				firstperson.put("translation", parFirstPerson.get(1, 0), parFirstPerson.get(1, 1), parFirstPerson.get(1, 2));
				firstperson.put("scale", parFirstPerson.get(2, 0), parFirstPerson.get(2, 1), parFirstPerson.get(2, 2));
				display.put("firstperson", firstperson);
			}

			if (parGui != null)
			{
				JSONMap gui = new JSONMap();
				gui.put("rotation", (int) parGui.x, (int) parGui.y, (int) parGui.z);
				display.put("gui", gui);
			}

			map.put("display", display);
		}

		return map;
	}

	public static final JSONMap BLOCK_MULTI_TEXTURE(String parTextureParticle, String parTextureDown, String parTextureUp, String parTextureNorth, String parTextureEast, String parTextureSouth, String parTextureWest)
	{
		return BLOCK("block/cube",
				new String[]{"particle", parTextureParticle},
				new String[]{"down", parTextureDown},
				new String[]{"up", parTextureUp},
				new String[]{"north", parTextureNorth},
				new String[]{"east", parTextureEast},
				new String[]{"south", parTextureSouth},
				new String[]{"west", parTextureWest});
	}

	public static final JSONMap BLOCK_CROP(String parTexture)
	{
		return BLOCK("block/crop",
				new String[]{"crop", parTexture});
	}

	public static final JSONMap BLOCK_COLUMN(String parTextureEnd, String parTextureSide)
	{
		return BLOCK("block/cube_column",
				new String[]{"end", parTextureEnd},
				new String[]{"side", parTextureSide});
	}

	public static final JSONMap BLOCK_COLUMN_SIDE(String parTextureEnd, String parTextureSide)
	{
		return BLOCK("block/column_side",
				new String[]{"end", parTextureEnd},
				new String[]{"side", parTextureSide});
	}

	public static final JSONMap BLOCK_BASE(String parTexture)
	{
		return BLOCK("block/cube_all",
				new String[]{"all", parTexture});
	}

	public static final JSONMap BLOCK(String parParent, String[]... parTextures)
	{
		return BLOCK(parParent,
				true,
				entryMap(parTextures));
	}

	public static final JSONMap BLOCK(String parParent, boolean parAmbientOcclusion, Map<String, String> parTextures, JSONMap... parElements)
	{
		JSONMap map = new JSONMap();

		if (parParent != null)
		{
			map.put("parent", parParent);
		}

		if (!parAmbientOcclusion)
		{
			map.put("ambientocclusion", parAmbientOcclusion);
		}

		if (parTextures != null)
		{
			JSONMap textures = new JSONMap();
			for(String id : parTextures.keySet())
			{
				textures.put(id, parTextures.get(id));
			}
			map.put("textures", textures);
		}

		if (ArrayUtil.valid(parElements))
		{
			map.put("elements", parElements);
		}

		return map;
	}

	public static final JSONMap BLOCK_TEXTURE_ELEMENTS(String[][] parTextures, JSONMap... parElements)
	{
		Log.ASSERT(ArrayUtil.validMaxLength(parTextures, Integer.MAX_VALUE, 2));
		return BLOCK(null, true, entryMap(parTextures), parElements);
	}

	public static final JSONMap BLOCK_ELEMENT(Vec3f parFrom, Vec3f parTo, JSONMap parDown, JSONMap parUp, JSONMap parNorth, JSONMap parSouth, JSONMap parWest, JSONMap parEast)
	{
		JSONMap map = new JSONMap();
		map.put("from", (int) parFrom.x, (int) parFrom.y, (int) parFrom.z);
		map.put("to", (int) parTo.x, (int) parTo.y, (int) parTo.z);

		JSONMap faces = new JSONMap();
		if (parDown != null) faces.put("down", parDown.get("down"));
		if (parUp != null) faces.put("up", parUp.get("up"));
		if (parNorth != null) faces.put("north", parNorth.get("north"));
		if (parSouth != null) faces.put("south", parSouth.get("south"));
		if (parWest != null) faces.put("west", parWest.get("west"));
		if (parEast != null) faces.put("east", parEast.get("east"));

		map.put("faces", faces);
		return map;
	}

	public static final JSONMap BLOCK_ELEMENT_FACE(String parID, Vec4f parUV, String parTexture, String parCullFace, int parRotation)
	{
		JSONMap map = new JSONMap();
		JSONMap face = new JSONMap();
		face.put("uv", parUV.x, parUV.y, parUV.z, parUV.w);
		face.put("texture", parTexture);

		if (parCullFace != null)
		{
			face.put("cullface", parCullFace);
		}

		if (parRotation != 0)
		{
			face.put("rotation", parRotation);
		}

		map.put(parID, face);
		return map;
	}

	public static final Map<String, String> entryMap(String[]... parTextures)
	{
		if (!ArrayUtil.valid(parTextures)) return null;

		SmallMap<String, String> textures = new SmallMap<String, String>();

		for(int i = 0; i < parTextures.length; ++i)
		{
			textures.put(parTextures[i][0], parTextures[i][1]);
		}

		return textures;
	}
}
