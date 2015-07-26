package net.minecraftplus._api.factory;

import net.minecraftplus._api.base._Mod;
import net.minecraftplus._api.factory.writer.ResourceWriter;

/**Create mod files*/
public class ModFactory
{
	public static void write(String parDirectory, String parFileName, String parCommonProxy, String parClientProxy, String parPackage, String[] parImports, Class parModParent, String parModID, String parModVersion, String[] parParents, String[] parChildren)
	{
		StringBuilder str = new StringBuilder();

		String modpackage = parPackage;
		String modclass = parFileName.substring(0, parFileName.length() - 5);
		String imports = "";
		String dependencies = "";
		String commonproxy = parCommonProxy;
		String clientproxy = parClientProxy;

		if (parModParent != null)
		{
			imports += "\nimport " + parModParent.getName() + ";";
		}

		if (parImports != null)
		{
			StringBuilder importStr = new StringBuilder();
			for(String s : parImports)
			{
				importStr.append("\nimport " + s + ";");
			}

			imports += importStr.toString();
		}

		if (parParents != null)
		{
			StringBuilder parents = new StringBuilder();
			for(int i = 0; i < parParents.length - 1; ++i)
			{
				parents.append("required-after:" + parParents[i] + "; ");
			}
			parents.append("required-after:" + parParents[parParents.length - 1]);

			if (dependencies.isEmpty()) dependencies += "\"";
			dependencies += parents.toString();
		}

		if (parChildren != null)
		{
			if (dependencies != null) dependencies += "; ";

			StringBuilder children = new StringBuilder();
			for(int i = 0; i < parParents.length - 1; ++i)
			{
				children.append("required-before:" + parParents[i] + "; ");
			}
			children.append("required-before:" + parParents[parParents.length - 1]);

			if (dependencies.isEmpty()) dependencies += "\"";
			dependencies += children.toString();
		}

		if (!dependencies.isEmpty()) dependencies += "\"";

		str.append("package " + modpackage + "." + parModID + ";"
				+ "\n"
				+ "\nimport net.minecraftforge.common.config.Configuration;"
				+ "\nimport net.minecraftforge.fml.common.Mod;"
				+ "\nimport net.minecraftforge.fml.common.Mod.EventHandler;"
				+ "\nimport net.minecraftforge.fml.common.Mod.Instance;"
				+ "\nimport net.minecraftforge.fml.common.SidedProxy;"
				+ "\nimport net.minecraftforge.fml.common.event.FMLInitializationEvent;"
				+ "\nimport net.minecraftforge.fml.common.event.FMLPreInitializationEvent;"
				+ "\nimport net.minecraftforge.fml.common.event.FMLPostInitializationEvent;"
				+ "" + (!imports.isEmpty() ? imports : "")
				+ "\n"
				+ "\n@Mod(modid = " + modclass + ".MODID, version = " + modclass + ".VERSION" + (!dependencies.isEmpty() ? ", dependencies = " + dependencies : "") + ")"
				+ "\npublic class " + modclass + (parModParent != null ? " extends " + parModParent.getSimpleName() : "")
				+ "\n{"
				+ "\n\tpublic static final String MODID = \"" + parModID + "\";"
				+ "\n\tpublic static final String VERSION = \"" + parModVersion + "\";"
				+ "\n"
				+ "\n\t@SidedProxy(serverSide=\"" + modpackage + ".\" + MODID + \"." + commonproxy + "\", clientSide=\"" + modpackage + ".\" + MODID + \"." + clientproxy + "\")"
				+ "\n\tpublic static " + commonproxy + " proxy;"
				+ "\n"
				+ "\n\t@Instance(MODID)"
				+ "\n\tpublic static " + modclass + " INSTANCE;"
				+ "\n"
				+ "\n\tpublic " + modclass + "() {}"
				+ "\n"
				+ "\n\t//TODO: Add registers here for " + parModID.toUpperCase()
				+ "\n"
				+ "\n\t@EventHandler"
				+ "" + (parModParent != null ? "\n\t@Override" : "")
				+ "\n\tpublic void PreInitialize(FMLPreInitializationEvent parEvent)"
				+ "\n\t{"
				+ "\n\t\t//TODO: Add Items, Blocks, EventHandlers, Localizations for " + parModID.toUpperCase()
				+ "\n\t\t"
				+ "" + (parModParent != null ? "\n\t\tsuper.PreInitialize(parEvent);" : "")
				+ "\n\t}"
				+ "\n"
				+ "\n\t@EventHandler"
				+ "" + (parModParent != null ? "\n\t@Override" : "")
				+ "\n\tpublic void Initialize(FMLInitializationEvent parEvent)"
				+ "\n\t{"
				+ "\n\t\t//TODO: Add Recipes for " + parModID.toUpperCase()
				+ "\n\t\t"
				+ "\n\t\tproxy.Initialize();"
				+ "" + (parModParent != null ? "\n\t\tsuper.Initialize(parEvent);" : "")
				+ "\n\t}"
				+ "\n"
				+ "\n\t@EventHandler"
				+ "" + (parModParent != null ? "\n\t@Override" : "")
				+ "\n\tpublic void PostInitialize(FMLPostInitializationEvent parEvent)"
				+ "\n\t{"
				+ "\n\t\t//TODO: Add Communications for " + parModID.toUpperCase()
				+ "\n\t\t"
				+ "" + (parModParent != null ? "\n\t\tsuper.PostInitialize(parEvent);" : "")
				+ "\n\t}"
				+ "\n" + (_Mod.class.isAssignableFrom(parModParent) ? ""
						+ "\n"
						+ "\n\t@Override"
						+ "\n\tpublic void Configure(Configuration parConfiguration)"
						+ "\n\t{"
						+ "\n\t\t//TODO: Add config for " + parModID.toUpperCase()
						+ "\n\t\t"
						+ "\n\t\tsuper.Configure(parConfiguration);"
						+ "\n\t}"
						+ "\n"
						+ "\n\t@Override"
						+ "\n\tpublic void Munge()"
						+ "\n\t{"
						+ "\n\t\t//TODO: Add factory functions for " + parModID.toUpperCase()
						+ "\n\t\t"
						+ "\n\t\tsuper.Munge();"
						+ "\n\t}"
						: "")
						+ "\n}"
				);

		ResourceWriter.write(parDirectory, parFileName, str.toString());
	}

	private static final String getModClassName(String parModName)
	{
		String modclass = "_" + parModName.toLowerCase();
		for(int i = 0; i < modclass.length(); ++i)
		{
			if (modclass.charAt(i) == '_')
			{
				modclass = modclass.substring(0, i + 1) + Character.toUpperCase(modclass.charAt(i + 1)) + modclass.substring(i + 2);
			}
		}
		return modclass;
	}
}
