package me.Enderiatus.ColereCore.Jobs;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.Enderiatus.ColereCore.Status.PlayerStatus;
import me.Enderiatus.ColereCore.Status.StatuManager;

public enum Jobs {
	JOBLESS, MINER, LUMBERJACK, FARMER, FISHER, HUNTER;
	
	public static void checkLevelUP(Player p) {
		PlayerStatus pS = StatuManager.PLAYER_STATUS.get(p);
		if(pS.getJobsXP() >= getNextLevelXP(pS.getJobsLevel())) {
			pS.setJobsLevel(pS.getJobsLevel()+1);
			pS.setJobsXP(0);
			p.sendMessage("&bMeslek §7» §eMesleðin seviye atladý. Mevcut seviyen: §6"+pS.getJobsLevel());
		}
	}
	
	public static int getNextLevelXP(int currentLevel) {
		return 150+(((10*currentLevel)^3)/2)+(currentLevel*2);
		/*
		 * Seviye 1: 251 -10x10 - 100x100x100
		 * Seviye 10: 10160
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
	
	public static String getJobDisplayName(Jobs j) {
		switch(j) {
		case JOBLESS:
			return "Ýþsiz";
		case FARMER:
			return "Çiftçi";
		case FISHER:
			return "Balýkçý";
		case HUNTER:
			return "Avcý";
		case LUMBERJACK:
			return "Oduncu";
		case MINER:
			return "Madenci";
		default:
			return null;
		}
	}
	
	public static String getJobAbout(Jobs j) {
		switch(j) {
		case JOBLESS:
			return "Ýþsiz";
		case FARMER:
			return "Tarla iþleyerek para kazanýr.";
		case FISHER:
			return "Balýk tutarak para kazanabilir.";
		case HUNTER:
			return "Yaratýk öldürerek para kazanabilir.";
		case LUMBERJACK:
			return "Odun keserek para kazanabilir.";
		case MINER:
			return "Maden cevheri kýrarak para kazanabilir.";
		default:
			return null;
		}
	}
	
	
}
