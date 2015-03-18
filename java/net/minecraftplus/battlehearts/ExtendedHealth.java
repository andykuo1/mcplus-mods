package net.minecraftplus.battlehearts;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedHealth implements IExtendedEntityProperties
{
	public static int HP_MAX_BASE = 8;
	public static int HP_MAX_UP = 2;
	public static int HP_MAX_DOWN = 4;
	public static int HP_MAX_MAX = 60;

	public static boolean RESET_ON_SLEEP = false;
	public static boolean RESET_ON_DEATH = false;
	public static int EXP_PER_UP = 5;

	public final static String PROP_NAME = "ExtendedHealth";

	private final EntityPlayer player;
	private int maxHealth;

	public ExtendedHealth(EntityPlayer parEntityPlayer)
	{
		this.player = parEntityPlayer;
		this.maxHealth = 0;
	}

	public static ExtendedHealth register(EntityPlayer parEntityPlayer)
	{
		ExtendedHealth prop = new ExtendedHealth(parEntityPlayer);
		parEntityPlayer.registerExtendedProperties(ExtendedHealth.PROP_NAME, prop);
		return prop;
	}

	public static ExtendedHealth get(EntityPlayer parEntityPlayer)
	{
		return (ExtendedHealth) parEntityPlayer.getExtendedProperties(PROP_NAME);
	}

	public void copy(ExtendedHealth parProperty)
	{
		this.maxHealth = parProperty.maxHealth;
	}

	@Override
	public void saveNBTData(NBTTagCompound parNBTTagCompound)
	{
		NBTTagCompound nbttagcompound = new NBTTagCompound();

		nbttagcompound.setInteger("MaxHealth", this.maxHealth);
		parNBTTagCompound.setTag(PROP_NAME, nbttagcompound);
	}

	@Override
	public void loadNBTData(NBTTagCompound parNBTTagCompound)
	{
		NBTTagCompound nbttagcompound = (NBTTagCompound) parNBTTagCompound.getTag(PROP_NAME);

		this.maxHealth = nbttagcompound.getInteger("MaxHealth");
	}

	@Override
	public void init(Entity parEntity, World parWorld) {}

	public void setHealth(int parHealth)
	{
		this.maxHealth = parHealth;
	}

	public int getHealth()
	{
		return this.maxHealth;
	}
}