package me.Enderiatus.ColereCore.Jobs;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import me.Enderiatus.ColereCore.Status.PlayerStatus;
import me.Enderiatus.ColereCore.Status.StatusManager;
import me.Enderiatus.ColereCore.Utils.ItemCreator;

public enum Jobs {
	JOBLESS, MINER, LUMBERJACK, FARMER, FISHER, HUNTER;
	
	public static void checkLevelUP(Player p) {
		PlayerStatus pS = StatusManager.PLAYER_STATUS.get(p);
		if(pS.getJobsXP() >= getNextLevelXP(pS.getJobsLevel())) {
			pS.setJobsLevel(pS.getJobsLevel()+1);
			pS.setJobsXP(0);
			p.sendMessage("�bMeslek �7� �eMesle�in seviye atlad�. Mevcut seviyen: �6"+pS.getJobsLevel());
		}
	}
	
	public static void addJobLevelXP(Player p, int amountXP) {
		PlayerStatus pS = StatusManager.PLAYER_STATUS.get(p);
		if(pS.getJobsLevel() >= 50) 
			return;
		pS.setJobsXP(pS.getJobsXP()+(amountXP*pS.getJobsLevel()));
		checkLevelUP(p);
	}
	
	public static int getNextLevelXP(int currentLevel) {
		return (int) (Math.pow(5*currentLevel, 3)/2)+(currentLevel*2);
		/*
		 * Seviye 1: 251 -10x10 - 100x100x100
		 * Seviye 10: 10160
		 * 150+
		 * Seviye 50: 250 200
		 * 
		 */
	}
	
	public static int getJobBlockMultiplier(Material block) {
		switch (block) {
		case EMERALD_ORE:
			return 8;
		case DIAMOND_ORE:
			return 6;
		case GOLD_ORE:
			return 4;
		case LAPIS_ORE:
			return 3;
		case REDSTONE_ORE:
			return 3;
		case IRON_ORE:
			return 2;
		case COAL_ORE:
			return 1;
		
		default:
			return 0;
		}
	}
	
	public static int getJobMobMultiplier(EntityType entity) {
		switch (entity) {
		// ZOMBIE
		case ZOMBIE:
			return 1;
		case HUSK:
			return 2;
		case DROWNED:
			return 2;
		case PIG_ZOMBIE:
			return 1;
		// SKELETON
		case SKELETON:
			return 1;
		case WITHER_SKELETON:
			return 3;
		case STRAY:
			return 2;
		// OTHER
		case CREEPER:
			return 2;
		case ENDERMAN:
			return 3;
		// SPIDER
		case SPIDER:
			return 1;
		case CAVE_SPIDER:
			return 1;
		//NETHER
		case BLAZE:
			return 2;
		case GHAST:
			return 3;
		case MAGMA_CUBE:
			return 1;
		// SEA
		case ELDER_GUARDIAN:
			return 5;
		case GUARDIAN:
			return 2;
		
		case EVOKER:
			return 5;
		case VINDICATOR:
			return 5;
		case WITCH:
			return 2;
		
		default:
			return 0;
		}
	}
	
	public static String getJobDisplayName(Jobs j) {
		switch(j) {
		case JOBLESS:
			return "&8��siz";
		case FARMER:
			return "&2�ift�i";
		case FISHER:
			return "&dBal�k��";
		case HUNTER:
			return "&cAvc�";
		case LUMBERJACK:
			return "&aOduncu";
		case MINER:
			return "&bMadenci";
		default:
			return null;
		}
	}
	
	public static Material getJobDisplayItem(Jobs j) {
		switch(j) {
		case JOBLESS:
			return Material.COBBLESTONE;
		case FARMER:
			return Material.DIAMOND_HOE;
		case FISHER:
			return Material.FISHING_ROD;
		case HUNTER:
			return Material.BOW;
		case LUMBERJACK:
			return Material.DIAMOND_AXE;
		case MINER:
			return Material.DIAMOND_PICKAXE;
		default:
			return null;
		}
	}
	
	public static String getJobAbout(Jobs j) {
		switch(j) {
		case JOBLESS:
			return "&&&c&lDaha h�zl� geli�mek ve para kazanmak i�in&&&c&lhemen bir meslek se�melisin!";
		case FARMER:
			return "&&&ePara kazanma y�ntemi:&&  &7Ekin toplayarak para kazan�r."
			+ "&&&eMeslek �zelli�i: &&  &6&lHassas El"
			+ "&&&e�zellik bilgisi: "
			+ "&&  &7Ekin toplarken ekstra ot ve bitki d���rebilir."
			+ "&&  &7D��en e�yalar ile yeni iksirler al�nabilir."
			+ "&&&7�zelli�in �ans� seviyeye ba�l� olarak &e0.1% &7artar.";
		case FISHER:
			return "&&&ePara kazanma y�ntemi:&&  &7Bal�k tutarak para kazanabilir."
			+ "&&&eMeslek �zelli�i: &&  &6&lBal�k�� Ruhu"
			+ "&&&e�zellik bilgisi: "
			+ "&&  &7Bal�k tutarken oltas�na ekstra ganimet tak�labilir."
			+ "&&  &7Bu ganimetler y�ksek fiyattan sat�labilir. (R�tbe e�yalar� vs.)"
			+ "&&&7�zelli�in �ans� seviyeye ba�l� olarak &e0.1% &7artar.";
		case HUNTER:
			return "&&&ePara kazanma y�ntemi:&&  &7Yarat�k ve tap�nak ka�k�n� �ld�rerek para kazanabilir."
			+ "&&&eMeslek �zelli�i: &&  &6&lTitiz Avc�"
			+ "&&&e�zellik bilgisi: "
			+ "&&  &7Tap�nak ka�k�nlar�ndan ekstra e�ya d���rebilir."
			+ "&&  &7D��en e�yalar ka�k�n�n d���rebilece�i e�yalard�r."
			+ "&&&7�zelli�in �ans� seviyeye ba�l� olarak &e0.1% &7artar.";
		case LUMBERJACK:
			return "&&&ePara kazanma y�ntemi:&&  &7A�a� keserek para kazanabilir."
			+ "&&&eMeslek �zelli�i: &&  &6&lToplay�c�"
			+ "&&&e�zellik bilgisi: "
			+ "&&  &7A�a� yapraklar�ndan dal ve �z d���rebilir."
			+ "&&  &7D��en e�yalar ile yeni iksirler al�nabilir."
			+ "&&&7�zelli�in �ans� seviyeye ba�l� olarak &e0.1% &7artar.";
		case MINER:
			return "&&&ePara kazanma y�ntemi:&&  &7Maden cevheri k�rarak para kazanabilir."
					+ "&&&eMeslek �zelli�i: &&  &6&lMadenci �ans�"
					+ "&&&e�zellik bilgisi: "
					+ "&&  &7Maden k�r�nca 1-5 aras� ekstra i�lenmemi� maden d��er."
					+ "&&&7�zelli�in �ans� seviyeye ba�l� olarak &e1% &7artar.";
		default:
			return null;
		}
	}
	
	
	public static void openJobsMenu(Player p) {
		Inventory jobInventory = Bukkit.createInventory(null, InventoryType.HOPPER, "�3Meslek Men�s�");//Bukkit.createInventory(null, 27, "�9��leme Men�s�");
		jobInventory.setItem(0, ItemCreator.createItem(Material.SIGN, (byte) 1, "&9Ne bu meslekler?", 
				"&&&7Bu d�nyada meslekler sadece para de�il, ekstra �zellikte veriyor."
				+ "&&&7�zellikler meslek seviyenize ba�l� olarak rastgele devreye giriyor."
				+ "&&&7�rne�in; madenci mesle�inde &eMadenci Ruhu &7�zelli�inin ilk seviyede"
				+ "&&&7devreye girme �ans� &e1%&7'dir. Fakat 50. seviyede bu oran &e10%&7 olur."
				+ "&&&7Meslekler hakk�nda detayl� bilgi i�in &eMeslek Listesine &7bakabilirsiniz."
				+ "&&&&&6&lDaha h�zl� geli�mek i�in bir meslek se�meye ne dersin?"));
		jobInventory.setItem(1, ItemCreator.createItem(Material.BOOK, (byte)1, "&eMeslek Listesi", 
				"&&&7Meslek listesini ve �zelliklerini g�rmek i�in t�klay�n."));
		
		PlayerStatus pS = StatusManager.PLAYER_STATUS.get(p);
		jobInventory.setItem(3, ItemCreator.createItem(Jobs.getJobDisplayItem(pS.getPlayerJob()), (byte)1, "&6Meslek: "+Jobs.getJobDisplayName(pS.getPlayerJob()), 
				Jobs.getJobAbout(pS.getPlayerJob())));
		jobInventory.setItem(4, ItemCreator.createItem(Material.TOTEM_OF_UNDYING, (byte)1, "&9Meslek Durumunuz", "&&&7Meslek: "+Jobs.getJobDisplayName(pS.getPlayerJob())
			+"&&  &7Meslek Seviyeniz: &e"+pS.getJobsLevel()
			+"&&  &7Meslek XP'niz: &e"+pS.getJobsXP()
			+"&&  &7Sonraki seviye i�in gerekli XP: &e"+Jobs.getNextLevelXP(pS.getJobsLevel())
			+"&&&&  &6&lMeslek seviyeniz artt�k�a �zelliklerin �ans� artar."));
		p.openInventory(jobInventory);
	}
	
	
}
