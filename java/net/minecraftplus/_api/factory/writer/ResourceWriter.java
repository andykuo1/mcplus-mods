package net.minecraftplus._api.factory.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;

import net.minecraftplus._api.MCL;

/**Base class for writing mod resources*/
public class ResourceWriter
{
	protected final File directory;
	protected final File file;

	private ArrayList<ResourceListener> listeners = new ArrayList<ResourceListener>();

	private OutputStreamWriter writer;
	private PrintStream stream;

	private boolean open;

	public ResourceWriter(String parDirectory, String parFileName)
	{
		assert(parDirectory != null);
		assert(parFileName != null);

		this.directory = new File(parDirectory);
		this.file = new File(parDirectory + "\\" + parFileName);
	}

	public boolean exists()
	{
		return this.directory.exists() && this.file.exists();
	}

	public void delete()
	{
		assert(this.exists());

		this.file.deleteOnExit();
	}

	public ResourceWriter open() throws IOException
	{
		return this.open(false);
	}

	public ResourceWriter open(boolean parAppend) throws IOException
	{
		assert(!this.open);

		if (!this.exists())
		{
			this.directory.mkdirs();
			this.file.createNewFile();
		}
		else if (parAppend)
		{
			this.file.delete();
			this.file.createNewFile();
		}

		OutputStream output = new FileOutputStream(file, parAppend);
		this.stream = new PrintStream(output);
		this.writer = new OutputStreamWriter(output);
		this.open = true;

		for(ResourceListener listener : this.listeners)
		{
			listener.onResourceOpen(this, this.directory.getPath(), this.file.getName());
		}

		return this;
	}

	public ResourceWriter add(String parString) throws IOException
	{
		assert(this.open);

		this.writer.append(parString);

		return this;
	}

	public ResourceWriter close() throws IOException
	{
		for(ResourceListener listener : this.listeners)
		{
			listener.onResourceClose(this, this.directory.getPath(), this.file.getName());
		}

		this.writer.close();
		this.open = false;

		for(ResourceListener listener : this.listeners)
		{
			listener.onResourceDump(this, this.directory.getPath(), this.file.getName());
		}

		return null;
	}

	public void addListener(ResourceListener parListener)
	{
		this.listeners.add(parListener);
	}

	public boolean isOpen()
	{
		return this.open;
	}

	public PrintStream toStream()
	{
		return this.stream;
	}

	/**Open, write, and close file in directory*/
	public static final void write(String parDirectory, String parFileName, String parString)
	{
		assert(parDirectory != null);
		assert(parFileName != null);
		assert(parString != null);

		try
		{
			new ResourceWriter(parDirectory, parFileName).open().add(parString).close();
			MCL.info("Created file:", parDirectory + "\\" + parFileName);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			MCL.error("Failed to create mod file:", parDirectory + "\\" + parFileName);
		}
	}
}
