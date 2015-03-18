package net.minecraftplus._api.registry;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftplus._api.IMod;
import net.minecraftplus._api.mod.MCP;
import net.minecraftplus._api.registry.LivingRegistry.DummyEntity;
import net.minecraftplus._api.registry.LivingRegistry.DummyEntity.Spawn;
import cpw.mods.fml.common.registry.EntityRegistry;

/**
 * Registry for {@link Entity} as entities
 * */
public class LivingRegistry extends RegistryList<DummyEntity>
{
	public static final LivingRegistry INSTANCE = new LivingRegistry();

	//**************** Registry Setup ****************//

	@Override
	public void initialize()
	{
		for(DummyEntity entity : this.registry)
		{
			if (entity instanceof DummyEntityCustom)
			{
				DummyEntityCustom entityCustom = (DummyEntityCustom) entity;
				this.register(entityCustom.getEntity(), entityCustom.getName(), entityCustom.toUnlocalName(), entityCustom.isLocalRequired() ? entityCustom.getLocalName() : null, entityCustom.setEntityID(entityCustom.getEntityID()).getEntityID(), entityCustom.getMod(), entityCustom.getTrackingRange(), entityCustom.getUpdateFrequency(), entityCustom.isUpdateVelocity());
			}
			else if (entity instanceof DummyEntity)
			{
				this.register(entity.getEntity(), entity.getName(), entity.toUnlocalName(), entity.isLocalRequired() ? entity.getLocalName() : null, entity.getEntityID(), entity.getBackground(), entity.getForeground());
			}

			if (entity.getSpawnList() != null)
			{
				for(Spawn spawn : entity.getSpawnList())
				{
					this.register((Class<? extends EntityLiving>) entity.getEntity(), spawn.probability, spawn.min, spawn.max, spawn.creature, spawn.biomes);
				}
			}
		}
	}

	//**************** Simplified Functions ****************//

	public DummyEntityCustom add(Class<? extends Entity> parEntity, String parTranslation, int parTrackingRange, int parUpdateFreq, boolean parVelocity)
	{
		return this.add(parEntity, parTranslation, MCP.INSTANCE, parTrackingRange, parUpdateFreq, parVelocity);
	}

	//**************** Base Functions ****************//

	@Override
	public DummyEntity add(DummyEntity parDummyEntity)
	{
		return super.add(parDummyEntity);
	}

	public DummyEntity add(Class<? extends Entity> parEntity, String parTranslation)
	{
		return this.add(new DummyEntity(parEntity, parTranslation));
	}

	public DummyEntityCustom add(Class<? extends Entity> parEntity, String parTranslation, IMod parMod, int parTrackingRange, int parUpdateFreq, boolean parVelocity)
	{
		return (DummyEntityCustom) this.add(new DummyEntityCustom(parEntity, parTranslation, parTrackingRange, parUpdateFreq, parVelocity).setMod(parMod));
	}

	//**************** Register Functions ****************//

	public void register(Class<? extends Entity> parEntity, String parName, String parUnlocalName, String parLocalName, int parEntityID, IMod parMod, int parTrackingRange, int parUpdateFrequency, boolean parVelocity)
	{
		EntityRegistry.registerModEntity(parEntity, parName, parEntityID, parMod, parTrackingRange, parUpdateFrequency, parVelocity);

		if (parLocalName != null && !parLocalName.isEmpty())
		{
			MCP.LANGS().add(parUnlocalName, parLocalName);
		}
	}

	public void register(Class<? extends Entity> parEntity, String parName, String parUnlocalName, String parLocalName, int parEntityID, int parBackground, int parForeground)
	{
		if (parBackground < 0 && parForeground < 0)
		{
			EntityRegistry.registerGlobalEntityID(parEntity, parName, parEntityID);
		}
		else
		{
			EntityRegistry.registerGlobalEntityID(parEntity, parName, parEntityID, parBackground, parForeground);
		}

		if (parLocalName != null && !parLocalName.isEmpty())
		{
			MCP.LANGS().add(parUnlocalName, parLocalName);
		}
	}

	public void register(Class<? extends EntityLiving> parEntity, int parProbability, int parMin, int parMax, EnumCreatureType parCreatureType, BiomeGenBase... parBiomGenBases)
	{
		EntityRegistry.addSpawn(parEntity, parProbability, parMin, parMax, parCreatureType, parBiomGenBases);
	}

	//**************** Dummy Objects ****************//

	public static class DummyEntity extends DummyLocalization
	{
		private final Class<? extends Entity> entity;
		private int entityID;

		private int backgroundColor = -1;
		private int foregroundColor = -1;

		private final boolean custom;

		private IMod mod;
		private int trackingRange;
		private int updateFreq;
		private boolean velocity;

		private ArrayList<Spawn> spawnlist;

		public DummyEntity(Class<? extends Entity> parEntity, String parName)
		{
			super("entity.", ".name", parName);

			this.entity = parEntity;
			this.entityID = -1;

			this.custom = false;
		}

		public DummyEntity setEntityID(int parEntityID)
		{
			this.entityID = parEntityID;
			return this;
		}

		public DummyEntity setColor(int parBackground, int parForeground)
		{
			this.backgroundColor = parBackground;
			this.foregroundColor = parForeground;
			return this;
		}

		public DummyEntity addSpawn(int parProbability, int parMin, int parMax, EnumCreatureType parCreature, BiomeGenBase... parBiomes)
		{
			if (this.spawnlist == null) this.spawnlist = new ArrayList<Spawn>();
			this.spawnlist.add(new Spawn(parProbability, parMin, parMax, parCreature, parBiomes));
			return this;
		}

		protected Class<? extends Entity> getEntity()
		{
			return this.entity;
		}

		protected int getEntityID()
		{
			return this.entityID == -1 ? EntityRegistry.findGlobalUniqueEntityId() : this.entityID;
		}

		protected int getBackground()
		{
			return this.backgroundColor;
		}

		protected int getForeground()
		{
			return this.foregroundColor;
		}

		protected List<Spawn> getSpawnList()
		{
			return this.spawnlist;
		}

		protected class Spawn
		{
			public final int probability;
			public final int min;
			public final int max;
			public final EnumCreatureType creature;
			public final BiomeGenBase[] biomes;

			public Spawn(int parProbability, int parMin, int parMax, EnumCreatureType parCreature, BiomeGenBase... parBiomes)
			{
				this.probability = parProbability;
				this.min = parMin;
				this.max = parMax;
				this.creature = parCreature;
				this.biomes = parBiomes;
			}
		}
	}
	public static class DummyEntityCustom extends DummyEntity
	{
		private IMod mod;
		private final int trackingRange;
		private final int updateFreq;
		private final boolean velocity;

		public DummyEntityCustom(Class<? extends Entity> parEntity, String parTranslation, int parTrackingRange, int parUpdateFrequency, boolean parVelocity)
		{
			super(parEntity, parTranslation);
			this.mod = MCP.INSTANCE;
			this.trackingRange = parTrackingRange;
			this.updateFreq = parUpdateFrequency;
			this.velocity = parVelocity;
		}

		public DummyEntityCustom setMod(IMod parMod)
		{
			this.mod = parMod;
			return this;
		}

		protected IMod getMod()
		{
			return this.mod;
		}

		protected int getTrackingRange()
		{
			return this.trackingRange;
		}

		protected int getUpdateFrequency()
		{
			return this.updateFreq;
		}

		protected boolean isUpdateVelocity()
		{
			return this.velocity;
		}
	}
}
