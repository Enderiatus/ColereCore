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
		}else if(materialName.contains("Zümrüt")) {
			return new ItemStack(Material.EMERALD, 0);
		}else if(materialName.contains("Altýn")) {
			return new ItemStack(Material.GOLD_NUGGET, 0);
		}else if(materialName.contains("Demir")) {
			return new ItemStack(Material.IRON_NUGGET, 0);
		}return null;
	}
	
	public static ItemStack getRefineBlockunRefinedItem(RefineBlockType rbt) {
		switch (rbt) {
		case DIAMOND_ORE:
			return ItemCreator.createItem(Material.COAL, (byte)1
					, "§7Ýþlenmemiþ §bElmas", "&&&6&lHmm, üstü biraz fosilleþmiþ gibi duruyor..&&&&&7Bu eþyayý kullanmak için iþlemen gerekiyor.&&&7Eþyayý iþlemek için &e&nMadenci Loncasýna &7git.");
		case EMERALD_ORE:
			return ItemCreator.createItem(Material.COAL, (byte)1
					,"§7Ýþlenmemiþ §2Zümrüt", "&&&6&lHmm, üstü biraz fosilleþmiþ gibi duruyor..&&&&&7Bu eþyayý kullanmak için iþlemen gerekiyor.&&&7Eþyayý iþlemek için &e&nMadenci Loncasýna &7git.");
		case GOLD_ORE:
			return ItemCreator.createItem(Material.COAL, (byte)1
					, "§7Ýþlenmemiþ §eAltýn", "&&&6&lHmm, üstü biraz fosilleþmiþ gibi duruyor..&&&&&7Bu eþyayý kullanmak için iþlemen gerekiyor.&&&7Eþyayý iþlemek için &e&nMadenci Loncasýna &7git.");
		case IRON_ORE:
			return ItemCreator.createItem(Material.COAL, (byte)1
					, "§7Ýþlenmemiþ §fDemir", "&&&6&lHmm, üstü biraz fosilleþmiþ gibi duruyor..&&&&&7Bu eþyayý kullanmak için iþlemen gerekiyor.&&&7Eþyayý iþlemek için &e&nMadenci Loncasýna &7git.");
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
				, "§7Ýþlenmemiþ §bElmas", "&&&6&lHmm, üstü biraz fosilleþmiþ gibi duruyor&&&6Bu eþyayý iþlemelisin.&&&7Eþyayý iþlemek için &e&nMadenci Loncasýna &7git."));
		unRefineDropType.put(Material.EMERALD_ORE, ItemCreator.createItem(Material.COAL, (byte)1
				, "§7Ýþlenmemiþ §2Zümrüt", "&&&6Üstü biraz fosilleþmiþ gibi duruyor.&&&6Bu eþyayý iþlemen gerekli.&&&7Eþyayý iþlemek için &eMadenci Loncasýna &7git."));
		unRefineDropType.put(Material.IRON_ORE, ItemCreator.createItem(Material.COAL, (byte)1
				, "§7Ýþlenmemiþ §fDemir", "&&&6Üstü biraz fosilleþmiþ gibi duruyor.&&&6Bu eþyayý iþlemen gerekli.&&&7Eþyayý iþlemek için &eMadenci Loncasýna &7git."));
		unRefineDropType.put(Material.GOLD_ORE, ItemCreator.createItem(Material.COAL, (byte)1
				, "§7Ýþlenmemiþ §eAltýn", "&&&6Üstü biraz fosilleþmiþ gibi duruyor.&&&6Bu eþyayý iþlemen gerekli.&&&7Eþyayý iþlemek için &eMadenci Loncasýna &7git."));
	}*/
	

