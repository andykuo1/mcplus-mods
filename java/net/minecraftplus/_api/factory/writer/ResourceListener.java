package net.minecraftplus._api.factory.writer;

public interface ResourceListener
{
	public void onResourceOpen(ResourceWriter parResourceWriter, String parDirectory, String parFileName);

	public void onResourceClose(ResourceWriter parResourceWriter, String parDirectory, String parFileName);

	public void onResourceDump(ResourceWriter parResourceWriter, String parDirectory, String parFileName);
}
