package net.minecraftplus._api.registry;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftplus._api.handler.RenderSimpleBlockHandler;
import net.minecraftplus._api.registry.RenderRegistry.DummyRender;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Registry for {@link DummyRender} as custom renders for entities, items, and blocks
 * */
@SideOnly(Side.CLIENT)
public class RenderRegistry extends RegistryList<DummyRender>
{
	public static final RenderRegistry INSTANCE = new RenderRegistry();

	//**************** Registry Setup ****************//

	@Override
	public void initialize()
	{
		for(DummyRender dummy : this.registry)
		{
			if (dummy instanceof DummyRenderEntity)
			{
				DummyRenderEntity entity = (DummyRenderEntity) dummy;
				RenderingRegistry.registerEntityRenderingHandler(entity.getEntity(), entity.getRender());
			}
			else if (dummy instanceof DummyRenderTileEntity)
			{
				DummyRenderTileEntity tileentity = (DummyRenderTileEntity) dummy;
				ClientRegistry.bindTileEntitySpecialRenderer(tileentity.getEntity(), tileentity.getRender());
			}
			else if (dummy instanceof DummyRenderItem)
			{
				DummyRenderItem item = (DummyRenderItem) dummy;
				MinecraftForgeClient.registerItemRenderer(item.getEntity(), item.getRender());
			}
			else if (dummy instanceof DummyRenderBlockType)
			{
				DummyRenderBlockType blocktype = (DummyRenderBlockType) dummy;
				RenderingRegistry.registerBlockHandler(blocktype.getEntity(), blocktype.getRender());
			}
		}
	}

	//**************** Simplified Functions ****************//

	public DummyRenderEntity add(Class<? extends Entity> parEntity, Render parRender)
	{
		return (DummyRenderEntity) this.add(new DummyRenderEntity(parEntity, parRender));
	}

	public DummyRenderTileEntity add(Class<? extends TileEntity> parEntity, TileEntitySpecialRenderer parRender)
	{
		return (DummyRenderTileEntity) this.add(new DummyRenderTileEntity(parEntity, parRender));
	}

	public DummyRenderItem add(Item parItem, IItemRenderer parRender)
	{
		return (DummyRenderItem) this.add(new DummyRenderItem(parItem, parRender));
	}

	public DummyRenderBlockType add(int parRenderType, RenderSimpleBlockHandler parRender)
	{
		return (DummyRenderBlockType) this.add(new DummyRenderBlockType(parRenderType, parRender));
	}

	public DummyRenderBlockType add(RenderSimpleBlockHandler parRender)
	{
		return this.add(RenderingRegistry.getNextAvailableRenderId(), parRender);
	}

	//**************** Base Functions ****************//

	@Override
	public DummyRender add(DummyRender parDummyRender)
	{
		return super.add(parDummyRender);
	}

	//**************** Dummy Objects ****************//

	public static class DummyRender<EntityObject, RenderObject> extends Dummy<EntityObject>
	{
		private final RenderObject render;

		public DummyRender(EntityObject parEntityObject, RenderObject parRenderObject)
		{
			super(parEntityObject);

			this.render = parRenderObject;
		}

		protected EntityObject getEntity()
		{
			return this.id();
		}

		protected RenderObject getRender()
		{
			return this.render;
		}
	}

	public static class DummyRenderEntity extends DummyRender<Class<? extends Entity>, Render>
	{
		public DummyRenderEntity(Class<? extends Entity> parEntity, Render parRender)
		{
			super(parEntity, parRender);
		}
	}

	public static class DummyRenderTileEntity extends DummyRender<Class<? extends TileEntity>, TileEntitySpecialRenderer>
	{
		public DummyRenderTileEntity(Class<? extends TileEntity> parTileEntity, TileEntitySpecialRenderer parRender)
		{
			super(parTileEntity, parRender);
		}
	}

	public static class DummyRenderItem extends DummyRender<Item, IItemRenderer>
	{
		public DummyRenderItem(Item parItem, IItemRenderer parItemRenderer)
		{
			super(parItem, parItemRenderer);
		}
	}

	public static class DummyRenderBlockType extends DummyRender<Integer, RenderSimpleBlockHandler>
	{
		public DummyRenderBlockType(Integer parRenderType, RenderSimpleBlockHandler parRenderHandler)
		{
			super(parRenderType, parRenderHandler);
			parRenderHandler.setRenderType(parRenderType);
		}
	}
}
