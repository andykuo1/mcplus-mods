package net.minecraftplus._api.factory;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftplus._api.dictionary.Assets;
import net.minecraftplus._api.factory.writer.ResourceWriter;
import net.minecraftplus._api.util.ArrayUtil;

/**Create proxy files*/
public class ProxyFactory
{
	public static void write(String parDirectory, String parFileName, String parPackage, String[] parImports, Class parParent, String parModID, Side parSide)
	{
		String[] imports = parImports;
		if (parParent != null) imports = ArrayUtil.append(parImports, parParent.getName());

		write(parDirectory, parFileName, parPackage, imports, parParent.getSimpleName(), parModID, parSide);
	}

	public static void write(String parDirectory, String parFileName, String parPackage, String[] parImports, String parParent, String parModID, Side parSide)
	{
		StringBuilder str = new StringBuilder();

		String modpackage = parPackage;
		String modclass = parFileName.substring(0, parFileName.length() - 5);
		String imports = "";

		if (parImports != null)
		{
			StringBuilder importStr = new StringBuilder();
			for(String s : parImports)
			{
				importStr.append("\nimport " + s + ";");
			}

			imports += importStr.toString();
		}

		str.append("package " + modpackage + "." + parModID + ";"
				+ "\n"
				+ "" + (parSide.isClient() ? "\nimport net.minecraftforge.fml.relauncher.Side;" + "\nimport net.minecraftforge.fml.relauncher.SideOnly;" : "")
				+ "" + (!imports.isEmpty() ? imports : "")
				+ "\n"
				+ "" + (parSide.isClient() ? "\n@SideOnly(Side.CLIENT)" : "")
				+ "\npublic class " + modclass + (parParent != null ? " extends " + parParent : "")
				+ "\n{"
				+ "" + (parParent != null ? "\n\t@Override" : "")
				+ "\n\tpublic void Initialize()"
				+ "\n\t{"
				+ "\n\t\t//TODO: Add " + (parSide.isServer() ? "server-" : "client-") + "side operations for " + parModID.toUpperCase()
				+ "\n\t\t"
				+ "" + (parParent != null ? "\n\t\tsuper.Initialize();" : "")
				+ "\n\t}"
				+ "\n}"
				);

		ResourceWriter.write(parDirectory, parFileName, str.toString());
	}
}
