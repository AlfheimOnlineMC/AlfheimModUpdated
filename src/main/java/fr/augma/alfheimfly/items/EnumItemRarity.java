package fr.augma.alfheimfly.items;

public enum EnumItemRarity {
	Commun("§fCommune", 25),
	Rare("§aRare", 35),
	Epique("§bEpique", 50),
	Mythique("§dMythique", 65),
	Unique("§4Unique", 80);
	
	String label;
	int maxLevel;
	
	private EnumItemRarity(String label, int maxLevel) {
		this.label = label;
		this.maxLevel = maxLevel;
	}
	
	String getLabel() {
		return this.label;
	}
	
	public int getMaxLevel() {
		return this.maxLevel;
	}
}
