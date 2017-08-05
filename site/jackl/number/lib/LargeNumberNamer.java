package site.jackl.number.lib;

import java.io.IOException;
import java.io.StringReader;

public class LargeNumberNamer {
	static int remaining;
	private static final String[] tensNames = { "", " ten", " twenty", " thirty", " forty", " fifty", " sixty",
			" seventy", " eighty", " ninety" };

	private static final String[] numNames = { "", " one", " two", " three", " four", " five", " six", " seven",
			" eight", " nine", " ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen", " sixteen",
			" seventeen", " eighteen", " nineteen" };

	private static final String[] bigNames = { "thousand", "million", "billion", "trillion", "quadrillion",
			"quintillion", "sextillion", "septillion", "octillion", "nonillion" };

	private static final String[] specialPrefixes = { "ni", "mi", "bi", "tri", "quadri", "quinti",
			"sexti", "septi", "octi", "noni" };

	public static void printName(String number) {
		number = number.replaceAll("\\n", "");
		
		if (!number.matches("[\\s\\S]*\\d[\\s\\S]*"))
		{
			System.out.println("");
			return;
		}
		
		if (number.matches("\\D*-.+"))
		{
			print("negative");
		}

		String toProcessString = number.replaceAll("\\D+", "");
		
		if (toProcessString.equals("0"))
		{
			print(" zero");
			System.out.print("\n");
			return;
		}
	
		
		remaining = toProcessString.length();
		StringReader toProcess = new StringReader(toProcessString);
		boolean unfinished = true;
		while (unfinished) {
			if (remaining <= 3) {
				String name = convertLessThanOneThousand(Integer.parseInt(read(toProcess, 3)));
				if (!name.equals("0")) {
					print(name);
				}

				unfinished = false;
			} else if (remaining <= 33) {
				switch ((remaining - 1) % 3) {
				case 2:
					String largeName2 = bigNames[((remaining) / 3) - 2];
					String smallName2 = convertLessThanOneThousand(Integer.parseInt(read(toProcess, 3)));
					

					if (!smallName2.equals("0")) {
						print(smallName2 + " " + largeName2);
					}
					
					break;
				case 1:
					String largeName1 = bigNames[((remaining + 1) / 3) - 2];
					String smallName1 = convertLessThanOneThousand(Integer.parseInt(read(toProcess, 2)));
					
					if (!smallName1.equals("0")) {
						print (smallName1 + " " + largeName1);

					}
					
					break;
				case 0:
					String largeName0 = bigNames[((remaining + 2) / 3) - 2];
					String smallName0 = convertLessThanOneThousand(Integer.parseInt(read(toProcess, 1)));
					

					if (!smallName0.equals("0")) {
						print( smallName0 + " " + largeName0);
					}
					
					break;
				}
			} else {
				switch ((remaining - 1) % 3) {
				case 2:
					String largeName2 = findNthIllion("" + ((remaining) - 6) / 3);
					String smallName2 = convertLessThanOneThousand(Integer.parseInt(read(toProcess, 3)));
					
					if (!smallName2.equals("0")) {
						print( smallName2 + " " + largeName2);
					}
					
					break;
				case 1:
					String largeName1 = findNthIllion("" + ((remaining + 1) - 6) / 3);
					String smallName1 = convertLessThanOneThousand(Integer.parseInt(read(toProcess, 2)));
					
					if (!smallName1.equals("0")) {
						print( smallName1 + " " + largeName1);
					}
					
					break;
				case 0:
					String largeName0 = findNthIllion("" + ((remaining + 2) - 6) / 3);
					String smallName0 = convertLessThanOneThousand(Integer.parseInt(read(toProcess, 1)));
					
					if (!smallName0.equals("0")) {
						print (smallName0 + " " + largeName0);
					}
					
					break;
				}
			}
		}
		System.out.print("\n");
	}

	private static String convertLessThanOneThousand(int number) {
		if (number == 0) {
			return "0";
		}
		String soFar;

		if (number % 100 < 20) {
			soFar = numNames[number % 100];
			number /= 100;
		} else {
			soFar = numNames[number % 10];
			number /= 10;

			soFar = tensNames[number % 10] + ((soFar.length() > 0) ? ("-" + soFar.trim()) : (""));

			number /= 10;
		}
		if (number == 0)
			return soFar;
		return numNames[number] + " hundred" + soFar;
	}

	public static String findNthIllion(String number) {
		number = new StringBuilder(number).reverse().toString();
		String[] numbersGroup = number.split("(?<=\\G...)");
		LatinRoot finished = new LatinRoot("", null, null);

		for (int f = numbersGroup.length - 1; f >= 0; f--) {
			int parsed;
			if ((parsed = Integer.parseInt(new StringBuilder(numbersGroup[f]).reverse().toString())) < 10) {
				finished = finished.concatenate(new LatinRoot(specialPrefixes[parsed], null, null));
			} else {
				String[] numbers = numbersGroup[f].split("");
				for (int i = 0; i < numbers.length; i++) {
					LatinRoot root = getRoot((int) ((Integer.parseInt(numbers[i])) * (Math.pow(10, i))));
					if (root != null) {
						finished = finished.concatenate(root);
					}
				}
			}
			if (f != 0)
			{
				finished.endGroup();
			}
		}
		return finished.getNumber();
	}

	private static LatinRoot getRoot(int number) {
		switch (number) {
		case 1:
			return new LatinRoot("un", "", "");
		case 2:
			return new LatinRoot("duo", "", "");
		case 3:
			return new LatinRoot("tre", "exception", "");
		case 4:
			return new LatinRoot("quattuor", "", "");
		case 5:
			return new LatinRoot("quinqua", "", "");
		case 6:
			return new LatinRoot("se", "xs", "");
		case 7:
			return new LatinRoot("septe", "nm", "");
		case 8:
			return new LatinRoot("octo", "", "");
		case 9:
			return new LatinRoot("nove", "nm", "");
		case 10:
			return new LatinRoot("deci", "", "n");
		case 20:
			return new LatinRoot("viginti", "", "ms");
		case 30:
			return new LatinRoot("triginta", "", "ns");
		case 40:
			return new LatinRoot("quadraginta", "", "ns");
		case 50:
			return new LatinRoot("quinquaginta", "", "ns");
		case 60:
			return new LatinRoot("sexaginta", "", "n");
		case 70:
			return new LatinRoot("septuaginta", "", "n");
		case 80:
			return new LatinRoot("octoginta", "", "mx");
		case 90:
			return new LatinRoot("nonaginta", "", "");
		case 100:
			return new LatinRoot("centi", "", "nx");
		case 200:
			return new LatinRoot("ducenti", "", "n");
		case 300:
			return new LatinRoot("trecenti", "", "ns");
		case 400:
			return new LatinRoot("quadringenti", "", "ns");
		case 500:
			return new LatinRoot("quingenti", "", "ns");
		case 600:
			return new LatinRoot("sescenti", "", "n");
		case 700:
			return new LatinRoot("septingenti", "", "n");
		case 800:
			return new LatinRoot("octingenti", "", "mx");
		case 900:
			return new LatinRoot("nongenti", "", "");

		}
		return null;
	}
	
	private static String read(StringReader r, int amount)
	{
		if (remaining < amount)
		{
			amount = remaining;
		}
		char[] buf = new char[amount];
		try {
			r.read(buf);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		remaining -= amount;
		return new String(buf);
	}
	
	static boolean firstString = true;
	private static void print(String string)
	{
		if (firstString)
		{
			if (string.startsWith(" "))
			{
				string = string.substring(1);
			}
			System.out.print(string);
			firstString = false;
		}
		else
		{
			System.out.print(string);
		}
	}
}
