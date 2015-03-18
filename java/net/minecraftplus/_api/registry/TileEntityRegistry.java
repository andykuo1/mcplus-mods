package net.minecraftplus._api.registry;

import net.minecraft.tileentity.TileEntity;
import net.minecraftplus._api.mod.MCP;
import net.minecraftplus._api.registry.TileEntityRegistry.DummyTileEntity;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Registry for {@link TileEntity} as tile entities
 * */
public class TileEntityRegistry extends RegistryList<DummyTileEntity>
{
	public static final TileEntityRegistry INSTANCE = new TileEntityRegistry();

	//**************** Registry Setup ****************//

	@Override
	public void initialize()
	{
		for(DummyTileEntity tileentity : this.registry)
		{
			this.register(tileentity.getTileEntity(), tileentity.getUnlocalName(), tileentity.toUnlocalName(), tileentity.isLocalRequired() ? tileentity.getLocalName() : null);
		}
	}

	//**************** Base Functions ****************//

	@Override
	public DummyTileEntity add(DummyTileEntity parDummyTileEntity)
	{
		return super.add(parDummyTileEntity);
	}

	public DummyTileEntity add(Class<? extends TileEntity> parEntity, String parName)
	{
		DummyTileEntity tileentity;
		this.add(tileentity = new DummyTileEntity(parEntity, parName));
		return tileentity;
	}

	//**************** Register Functions ****************//

	public void register(Class<? extends TileEntity> parTileEntity, String parName, String parUnlocalName, String parLocalName)
	{
		GameRegistry.registerTileEntity(parTileEntity, parName);

		if (parLocalName != null && !parLocalName.isEmpty())
		{
			MCP.LANGS().add(parUnlocalName, parLocalName);
		}
	}

	//**************** Dummy Objects ****************//

	public static class DummyTileEntity extends DummyLocalization
	{
		private final Class<? extends TileEntity> tileEntity;

		public DummyTileEntity(Class<? extends TileEntity> parTileEntity, String parName)
		{
			super("container.", "", parName);
			this.tileEntity = parTileEntity;
		}

		protected Class<? extends TileEntity> getTileEntity()
		{
			return this.tileEntity;
		}
	}
}
