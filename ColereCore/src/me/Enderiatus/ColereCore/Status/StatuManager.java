package me.Enderiatus.ColereCore.Status;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Enderiatus.ColereCore.Main;
import me.Enderiatus.ColereCore.Utils.ItemCreator;

public class StatuManager {

	public static HashMap<Player, PlayerStatus> PLAYER_STATUS = new HashMap<Player, PlayerStatus>();
	public static HashMap<Integer, Status> STATUS = new HashMap<Integer, Status>();
	
	public static void loadPlayerStats(Player p) {
		File userFile = new File(Main.getInstance().getDataFolder(), "/playerData/"+p.getName()+".yml");
		FileConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);
		PLAYER_STATUS.put(p, new PlayerStatus(p.getName(), 
										userConfig.getInt("Statu.evasionChance"), 
										userConfig.getInt("Statu.criticChance"), 
										userConfig.getInt("Statu.blockChance"), 
										userConfig.getInt("Statu.extraSpeed"), 
										userConfig.getInt("Statu.extraHealth"), 
										userConfig.getInt("Statu.enchantLuck"), 
										userConfig.getInt("Statu.extraEnchant"), 
										userConfig.getInt("Statu.miningLuck"), 
										userConfig.getInt("Statu.refinedOre"), 
										userConfig.getInt("Statu.processingRate"), 
										userConfig.getInt("Statu.Point"),
										userConfig.getInt("Statu.PointXP"),
										userConfig.getString("Job.Name"),
										userConfig.getInt("Job.Level"),
										userConfig.getInt("Job.LevelXP")));
		Bukkit.getServer().getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				openStatuInventory(p);
				
			}
		}, 20*5);
		
	}

	public static void checkPlayerFile(Player player) {
		File userFile = new File(Main.getInstance().getDataFolder(), "/playerData/"+player.getName()+".yml");
		if(!userFile.exists()) {
			try {
				userFile.createNewFile();
				FileConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);
				userConfig.set("Name", player.getName());
				for(int i : STATUS.keySet()) {
					userConfig.set("Statu."+STATUS.get(i).getYamlName(), STATUS.get(i).getDefaultValue());
				}
				userConfig.set("Statu.Point", 0);
				userConfig.set("Statu.PointXP", 0);
				userConfig.set("Job.Name", "JOBLESS");
				userConfig.set("Job.Level", 0);
				userConfig.set("Job.LevelXP", 0);
				userConfig.save(userFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		loadPlayerStats(player);
	}
	
	public static void loadStatus() {
		STATUS.put(13, new Status("extraHealth","Can", 0, 6, 10, "&&&7Ekstra kalp kazand�r�r.&&&7Seviye ba��na hasar ek kalp: &e0.5&&", Material.MAGMA_CREAM));
		STATUS.put(20, new Status("evasionChance", "Ka��nma", 0, 3, 30, "&&&7Oklar� savu�turmaya yarar.&&&7Seviye ba��na ka��nma art���: &e1%&&", Material.ARROW));
		STATUS.put(22, new Status("blockChance","Blok", 0, 3, 30, "&&&7Ald���n�z hasar� yok sayar.&&&7Seviye ba��na hasar bloklama art���: &e1%&&", Material.SHIELD));
		STATUS.put(24, new Status("criticChance","Kritik", 0, 2, 20,"&&&7�ki kat hasar verir.&&&7Seviye ba��na kritik art���: &e1%&&", Material.BLAZE_POWDER));
		STATUS.put(29, new Status("extraSpeed","H�z", 0, 6, 5, "&&&7Daha h�zl� ko�ars�n�z.&&&7Seviye ba��na h�z art���: &e1%&&", Material.FEATHER));
		STATUS.put(31, new Status("enchantLuck","B�y�n�n Ba�ar�l� Olma �ans�", 25, 1, 80, "&&&7B�y�lerinizin ba�ar�l� olma ihtimalini belirler.&&&7Seviye ba��na �ans art���: &e1%&&", Material.ENCHANTING_TABLE));
		STATUS.put(33, new Status("extraEnchant","Efsunlama �ans�", 0, 10, 5,"&&&7E�ya b�y�lendi�inde ekstra b�y�ler ekler.&&&7Seviye ba��na efsunlama �ans� art���: &e1%&&", Material.ENCHANTED_BOOK));
		STATUS.put(38, new Status("refinedOre","��lenmi� ��kma �htimali", 0, 2, 40,"&&&7Madenler i�lenmi� olarak d��er.&&&7Seviye ba��na �ans art���: &e1%&&", Material.COAL));
		STATUS.put(40, new Status("miningLuck","�ans", 0, 5, 6, "&&&7D��en e�ya miktar�n� artt�r�r.&&&7Seviye ba��na d��en e�ya art���: &e1&&", Material.NETHER_STAR));
		STATUS.put(42, new Status("processingRate","��leme", 20, 1, 100,"&&&7E�yalar� i�leme �ans�n�z� artt�r�r.&&&7Seviye ba��na i�leme art���: &e1%&&", Material.CAULDRON));
	}
	
	public static void openStatuInventory(Player p) {
		Inventory inv = Bukkit.createInventory(null, 45, "�6Stat� Geli�tirme Men�s�");
		ItemStack infoPaper = new ItemStack(Material.PAPER);
		ItemMeta itemMeta = infoPaper.getItemMeta();
		itemMeta.setDisplayName("�7Kalan Stat� Puan�: �c"+PLAYER_STATUS.get(p).getLeftPoint());
		ArrayList<String> itemLore = new ArrayList<String>();
		itemLore.add(""); itemLore.add("�eStat� puanlar�n�z� buradan geli�tirebilirsiniz.");
		itemLore.add("�7Stat� puanlar�n� elde etme y�ntemleri:"); itemLore.add("    �7- �cYarat�k/Boss �ld�rme"); itemLore.add("    �7- �aMadencilik"); itemLore.add("    �7- �9�e�itli g�revler");
		itemLore.add("�6Karakterinizi geli�tirmek i�in stat� puan� kasmal�s�n�z.");
		itemMeta.setLore(itemLore);
		infoPaper.setItemMeta(itemMeta);
		inv.setItem(4, infoPaper);
		for(int sID : STATUS.keySet()) {
				inv.setItem(sID, ItemCreator.createItem(STATUS.get(sID).getInvItem(), (byte)1, 
						"&7Stat�: &c"+STATUS.get(sID).getFullName(),
						STATUS.get(sID).getStatuInfo()+"&7Stat�n�n mevcut seviyesi: &a"
						+getStatuLevelWithID(p, sID)+"&7/&c"+STATUS.get(sID).getMaxLevel()
						+"&&&7Seviye atlatmak i�in gerekli puan: &e"+STATUS.get(sID).getNeedXPPerLevel()));
		}
		p.openInventory(inv);
	} 
	
	public static void addPlayerStatuLevel(Player p, int sID) {
		p.sendMessage("�5Stat� �8� �e"+STATUS.get(sID).getFullName()+" �7yetene�inizi y�kselttiniz.");
		PLAYER_STATUS.get(p)
			.setLeftPoint(PLAYER_STATUS.get(p).getLeftPoint()-STATUS.get(sID).getNeedXPPerLevel());
		PLAYER_STATUS.get(p).setStatuValue(StatuType.valueOf(STATUS.get(sID).getYamlName()), getStatuLevelWithID(p, sID)+1);
	/*	try {
			Field status = PLAYER_STATUS.get(p).getClass().getDeclaredField(STATUS.get(sID).getYamlName());
			boolean isAccesible = status.isAccessible();
			status.setAccessible(true);
			status.setInt(PLAYER_STATUS.get(p), status.getInt(PLAYER_STATUS.get(p))+1);;
			status.setAccessible(isAccesible);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} */
	}
	
	public static int getStatuLevelWithID(Player p, int sID) {
		return PLAYER_STATUS.get(p).getStatuValue(StatuType.valueOf(STATUS.get(sID).getYamlName()));
/*		try {
			Field f = PLAYER_STATUS.get(p).getClass().getDeclaredField(STATUS.get(sID).getYamlName());
			boolean isAccesible = f.isAccessible();
			f.setAccessible(true);
			int level = f.getInt(PLAYER_STATUS.get(p));
			f.setAccessible(isAccesible);
			return level;
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}return 0; */
	}
	
	
	public static void savePlayerFile(Player p) {
		try {
			for(int sID : STATUS.keySet()) {
				PLAYER_STATUS.get(p).getUserConfigration().set("Statu."+STATUS.get(sID).getYamlName(),
						getStatuLevelWithID(p, sID));
			}
			PLAYER_STATUS.get(p).getUserConfigration().set("Statu.Point", PLAYER_STATUS.get(p).getLeftPoint());
			PLAYER_STATUS.get(p).getUserConfigration().set("Statu.PointXP", PLAYER_STATUS.get(p).getPointXP());
			PLAYER_STATUS.get(p).getUserConfigration().set("Job.Name", PLAYER_STATUS.get(p).getPlayerJob().toString());
			PLAYER_STATUS.get(p).getUserConfigration().set("Job.Level", PLAYER_STATUS.get(p).getJobsLevel());
			PLAYER_STATUS.get(p).getUserConfigration().set("Job.LevelXP", PLAYER_STATUS.get(p).getJobsXP());
			PLAYER_STATUS.get(p).getUserConfigration().save(PLAYER_STATUS.get(p).getUserFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	
}
