package site.jackl.number.cli;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import site.jackl.number.lib.LargeNumberNamer;

public class Main {

	public static void main(String[] args) {
		if (args.length > 0)
		{
			StringBuilder argsBuilder = new StringBuilder();
			for (String arg : args)
			{
				argsBuilder.append(arg);
			}
			LargeNumberNamer.printName(argsBuilder.toString());
		}
		else
		{
			StringBuilder builder = new StringBuilder();
			byte[] buffer = new byte[1024];
			int read;
			try {
				while((read = System.in.read(buffer)) != -1)
				{
					builder.append(new String(buffer, 0, read, Charset.forName("UTF-8")));
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			LargeNumberNamer.printName(builder.toString());
			
		}
	}

}
