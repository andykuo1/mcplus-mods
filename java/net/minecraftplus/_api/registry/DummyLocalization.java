package net.minecraftplus._api.registry;

import net.minecraftplus._api.tool.LangTool;

public class DummyLocalization extends Dummy<String>
{
	protected final String prefix;
	protected final String suffix;

	private String localization;

	public DummyLocalization(String parPrefix, String parSuffix, String parName)
	{
		super(LangTool.trim(parName));
		this.prefix = parPrefix;
		this.suffix = parSuffix;

		this.localization = LangTool.translate(this.getUnlocalName());
	}

	public DummyLocalization setLocalName(String parLocalization)
	{
		this.localization = parLocalization;
		return this;
	}

	protected boolean isLocalRequired()
	{
		return this.localization != null && !this.localization.isEmpty();
	}

	protected String getUnlocalName()
	{
		return this.prefix + this.getName();
	}

	protected String toUnlocalName()
	{
		return this.prefix + this.getName() + this.suffix;
	}

	protected String getLocalName()
	{
		return this.localization;
	}

	protected String getName()
	{
		return this.id();
	}
}