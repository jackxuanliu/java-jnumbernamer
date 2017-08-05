package site.jackl.number.lib;

class LatinRoot {
	private String root;
	private String concatenationCharacters;
	private String concatenationRequirements;

	protected LatinRoot(String root, String concatenationCharacters, String concatenationRequirements) {
		this.root = root;
		this.concatenationCharacters = concatenationCharacters;
		this.concatenationRequirements = concatenationRequirements;
	}

	protected LatinRoot concatenate(LatinRoot rootToJoin) {
		if (concatenationCharacters != null && rootToJoin.concatenationRequirements != null && !concatenationCharacters.equals("exception")) {

			String[] chars = concatenationCharacters.split("");
			for (int i = 0; i < chars.length; i++) {
				if (rootToJoin.concatenationRequirements.contains(chars[i])) {
					return new LatinRoot(root + chars[i] + rootToJoin.root, rootToJoin.concatenationCharacters,
							rootToJoin.concatenationRequirements);
				}
			}

		} else if (concatenationCharacters != null && rootToJoin.concatenationRequirements != null && concatenationCharacters.equals("exception") && (rootToJoin.concatenationRequirements.contains("s") || rootToJoin.concatenationRequirements.contains("x"))) {
			return new LatinRoot(root + "s" + rootToJoin.root, rootToJoin.concatenationCharacters,
					rootToJoin.concatenationRequirements);
		}
		return new LatinRoot(root + rootToJoin.root, rootToJoin.concatenationCharacters,
				rootToJoin.concatenationRequirements);
	}

	protected String getNumber() {
		if (!root.substring(root.length() - 1).equals("i")) {
			root = root.substring(0, root.length() - 1) + "i";
		}
		if (root.substring(root.length() - 3, root.length()).equals("lli")) {
			return root + "on";
		}
		return root + "llion";
	}
	
	protected void endGroup()
	{
		if (!root.substring(root.length() - 1).equals("i")) {
			root = root.substring(0, root.length() - 1) + "i";
		}
		root += "lli";
	}

}
