package me.Enderiatus.ColereCore.Processing;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.Enderiatus.ColereCore.Utils.ItemCreator;


public enum RefineBlockType {
	IRON_ORE, GOLD_ORE, DIAMOND_ORE, EMERALD_ORE;
	
	public static boolean contains(String rbtName) {
		for(RefineBlockType rbt : values()) {
			if(rbt.toString().equalsIgnoreCase(rbtName)) return true;
		}return false;
	}
	
	public static ItemStack refineMaterialType(String materialName) {
		if(materialName.contains("Elmas")) {
			return new ItemStack(Material.DIAMOND, 0);
		}else if(materialName.contains("Z�mr�t")) {
			return new ItemStack(Material.EMERALD, 0);
		}else if(materialName.contains("Alt�n")) {
			return new ItemStack(Material.GOLD_NUGGET, 0);
		}else if(materialName.contains("Demir")) {
			return new ItemStack(Material.IRON_NUGGET, 0);
		}return null;
	}
	
	public static ItemStack getRefineBlockunRefinedItem(RefineBlockType rbt) {
		switch (rbt) {
		case DIAMOND_ORE:
			return ItemCreator.createItem(Material.COAL, (byte)1
					, "�7��lenmemi� �bElmas", "&&&6&lHmm, �st� biraz fosille�mi� gibi duruyor..&&&&&7Bu e�yay� kullanmak i�in i�lemen gerekiyor.&&&7E�yay� i�lemek i�in &e&nMadenci Loncas�na &7git.");
		case EMERALD_ORE:
			return ItemCreator.createItem(Material.COAL, (byte)1
					,"�7��lenmemi� �2Z�mr�t", "&&&6&lHmm, �st� biraz fosille�mi� gibi duruyor..&&&&&7Bu e�yay� kullanmak i�in i�lemen gerekiyor.&&&7E�yay� i�lemek i�in &e&nMadenci Loncas�na &7git.");
		case GOLD_ORE:
			return ItemCreator.createItem(Material.COAL, (byte)1
					, "�7��lenmemi� �eAlt�n", "&&&6&lHmm, �st� biraz fosille�mi� gibi duruyor..&&&&&7Bu e�yay� kullanmak i�in i�lemen gerekiyor.&&&7E�yay� i�lemek i�in &e&nMadenci Loncas�na &7git.");
		case IRON_ORE:
			return ItemCreator.createItem(Material.COAL, (byte)1
					, "�7��lenmemi� �fDemir", "&&&6&lHmm, �st� biraz fosille�mi� gibi duruyor..&&&&&7Bu e�yay� kullanmak i�in i�lemen gerekiyor.&&&7E�yay� i�lemek i�in &e&nMadenci Loncas�na &7git.");
		default:
			return null;
		}
	}
	
	public static ItemStack getRefineBlockRefinedItem(RefineBlockType rbt) {
		switch (rbt) {
		case DIAMOND_ORE:
			return new ItemStack(Material.DIAMOND);
		case EMERALD_ORE:
			return new ItemStack(Material.EMERALD);
		case GOLD_ORE:
			return new ItemStack(Material.GOLD_NUGGET);
		case IRON_ORE:
			return new ItemStack(Material.IRON_NUGGET);
		default:
			return null;

		}
	}
}

	
/*	public static Map<Material, ItemStack> unRefineDropType = new HashMap<Material, ItemStack>();

	public static void setRafineDropType() {
		unRefineDropType.put(Material.DIAMOND_ORE, ItemCreator.createItem(Material.COAL, (byte)1
				, "�7��lenmemi� �bElmas", "&&&6&lHmm, �st� biraz fosille�mi� gibi duruyor&&&6Bu e�yay� i�lemelisin.&&&7E�yay� i�lemek i�in &e&nMadenci Loncas�na &7git."));
		unRefineDropType.put(Material.EMERALD_ORE, ItemCreator.createItem(Material.COAL, (byte)1
				, "�7��lenmemi� �2Z�mr�t", "&&&6�st� biraz fosille�mi� gibi duruyor.&&&6Bu e�yay� i�lemen gerekli.&&&7E�yay� i�lemek i�in &eMadenci Loncas�na &7git."));
		unRefineDropType.put(Material.IRON_ORE, ItemCreator.createItem(Material.COAL, (byte)1
				, "�7��lenmemi� �fDemir", "&&&6�st� biraz fosille�mi� gibi duruyor.&&&6Bu e�yay� i�lemen gerekli.&&&7E�yay� i�lemek i�in &eMadenci Loncas�na &7git."));
		unRefineDropType.put(Material.GOLD_ORE, ItemCreator.createItem(Material.COAL, (byte)1
				, "�7��lenmemi� �eAlt�n", "&&&6�st� biraz fosille�mi� gibi duruyor.&&&6Bu e�yay� i�lemen gerekli.&&&7E�yay� i�lemek i�in &eMadenci Loncas�na &7git."));
	}*/
	

