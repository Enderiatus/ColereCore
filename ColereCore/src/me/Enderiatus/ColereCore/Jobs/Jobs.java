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
			p.sendMessage("§bMeslek §7» §eMesleðin seviye atladý. Mevcut seviyen: §6"+pS.getJobsLevel());
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
			return "&8Ýþsiz";
		case FARMER:
			return "&2Çiftçi";
		case FISHER:
			return "&dBalýkçý";
		case HUNTER:
			return "&cAvcý";
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
			return "&&&c&lDaha hýzlý geliþmek ve para kazanmak için&&&c&lhemen bir meslek seçmelisin!";
		case FARMER:
			return "&&&ePara kazanma yöntemi:&&  &7Ekin toplayarak para kazanýr."
			+ "&&&eMeslek Özelliði: &&  &6&lHassas El"
			+ "&&&eÖzellik bilgisi: "
			+ "&&  &7Ekin toplarken ekstra ot ve bitki düþürebilir."
			+ "&&  &7Düþen eþyalar ile yeni iksirler alýnabilir."
			+ "&&&7Özelliðin þansý seviyeye baðlý olarak &e0.1% &7artar.";
		case FISHER:
			return "&&&ePara kazanma yöntemi:&&  &7Balýk tutarak para kazanabilir."
			+ "&&&eMeslek Özelliði: &&  &6&lBalýkçý Ruhu"
			+ "&&&eÖzellik bilgisi: "
			+ "&&  &7Balýk tutarken oltasýna ekstra ganimet takýlabilir."
			+ "&&  &7Bu ganimetler yüksek fiyattan satýlabilir. (Rütbe eþyalarý vs.)"
			+ "&&&7Özelliðin þansý seviyeye baðlý olarak &e0.1% &7artar.";
		case HUNTER:
			return "&&&ePara kazanma yöntemi:&&  &7Yaratýk ve tapýnak kaçkýný öldürerek para kazanabilir."
			+ "&&&eMeslek Özelliði: &&  &6&lTitiz Avcý"
			+ "&&&eÖzellik bilgisi: "
			+ "&&  &7Tapýnak kaçkýnlarýndan ekstra eþya düþürebilir."
			+ "&&  &7Düþen eþyalar kaçkýnýn düþürebileceði eþyalardýr."
			+ "&&&7Özelliðin þansý seviyeye baðlý olarak &e0.1% &7artar.";
		case LUMBERJACK:
			return "&&&ePara kazanma yöntemi:&&  &7Aðaç keserek para kazanabilir."
			+ "&&&eMeslek Özelliði: &&  &6&lToplayýcý"
			+ "&&&eÖzellik bilgisi: "
			+ "&&  &7Aðaç yapraklarýndan dal ve öz düþürebilir."
			+ "&&  &7Düþen eþyalar ile yeni iksirler alýnabilir."
			+ "&&&7Özelliðin þansý seviyeye baðlý olarak &e0.1% &7artar.";
		case MINER:
			return "&&&ePara kazanma yöntemi:&&  &7Maden cevheri kýrarak para kazanabilir."
					+ "&&&eMeslek Özelliði: &&  &6&lMadenci Þansý"
					+ "&&&eÖzellik bilgisi: "
					+ "&&  &7Maden kýrýnca 1-5 arasý ekstra iþlenmemiþ maden düþer."
					+ "&&&7Özelliðin þansý seviyeye baðlý olarak &e1% &7artar.";
		default:
			return null;
		}
	}
	
	
	public static void openJobsMenu(Player p) {
		Inventory jobInventory = Bukkit.createInventory(null, InventoryType.HOPPER, "§3Meslek Menüsü");//Bukkit.createInventory(null, 27, "§9Ýþleme Menüsü");
		jobInventory.setItem(0, ItemCreator.createItem(Material.SIGN, (byte) 1, "&9Ne bu meslekler?", 
				"&&&7Bu dünyada meslekler sadece para deðil, ekstra özellikte veriyor."
				+ "&&&7Özellikler meslek seviyenize baðlý olarak rastgele devreye giriyor."
				+ "&&&7Örneðin; madenci mesleðinde &eMadenci Ruhu &7özelliðinin ilk seviyede"
				+ "&&&7devreye girme þansý &e1%&7'dir. Fakat 50. seviyede bu oran &e10%&7 olur."
				+ "&&&7Meslekler hakkýnda detaylý bilgi için &eMeslek Listesine &7bakabilirsiniz."
				+ "&&&&&6&lDaha hýzlý geliþmek için bir meslek seçmeye ne dersin?"));
		jobInventory.setItem(1, ItemCreator.createItem(Material.BOOK, (byte)1, "&eMeslek Listesi", 
				"&&&7Meslek listesini ve özelliklerini görmek için týklayýn."));
		
		PlayerStatus pS = StatusManager.PLAYER_STATUS.get(p);
		jobInventory.setItem(3, ItemCreator.createItem(Jobs.getJobDisplayItem(pS.getPlayerJob()), (byte)1, "&6Meslek: "+Jobs.getJobDisplayName(pS.getPlayerJob()), 
				Jobs.getJobAbout(pS.getPlayerJob())));
		jobInventory.setItem(4, ItemCreator.createItem(Material.TOTEM_OF_UNDYING, (byte)1, "&9Meslek Durumunuz", "&&&7Meslek: "+Jobs.getJobDisplayName(pS.getPlayerJob())
			+"&&  &7Meslek Seviyeniz: &e"+pS.getJobsLevel()
			+"&&  &7Meslek XP'niz: &e"+pS.getJobsXP()
			+"&&  &7Sonraki seviye için gerekli XP: &e"+Jobs.getNextLevelXP(pS.getJobsLevel())
			+"&&&&  &6&lMeslek seviyeniz arttýkça özelliklerin þansý artar."));
		p.openInventory(jobInventory);
	}
	
	
}
