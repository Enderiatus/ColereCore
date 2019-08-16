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
		STATUS.put(13, new Status("extraHealth","Can", 0, 6, 10, "&&&7Ekstra kalp kazandýrýr.&&&7Seviye baþýna hasar ek kalp: &e0.5&&", Material.MAGMA_CREAM));
		STATUS.put(20, new Status("evasionChance", "Kaçýnma", 0, 3, 30, "&&&7Oklarý savuþturmaya yarar.&&&7Seviye baþýna kaçýnma artýþý: &e1%&&", Material.ARROW));
		STATUS.put(22, new Status("blockChance","Blok", 0, 3, 30, "&&&7Aldýðýnýz hasarý yok sayar.&&&7Seviye baþýna hasar bloklama artýþý: &e1%&&", Material.SHIELD));
		STATUS.put(24, new Status("criticChance","Kritik", 0, 2, 20,"&&&7Ýki kat hasar verir.&&&7Seviye baþýna kritik artýþý: &e1%&&", Material.BLAZE_POWDER));
		STATUS.put(29, new Status("extraSpeed","Hýz", 0, 6, 5, "&&&7Daha hýzlý koþarsýnýz.&&&7Seviye baþýna hýz artýþý: &e1%&&", Material.FEATHER));
		STATUS.put(31, new Status("enchantLuck","Büyünün Baþarýlý Olma Þansý", 25, 1, 80, "&&&7Büyülerinizin baþarýlý olma ihtimalini belirler.&&&7Seviye baþýna þans artýþý: &e1%&&", Material.ENCHANTING_TABLE));
		STATUS.put(33, new Status("extraEnchant","Efsunlama Þansý", 0, 10, 5,"&&&7Eþya büyülendiðinde ekstra büyüler ekler.&&&7Seviye baþýna efsunlama þansý artýþý: &e1%&&", Material.ENCHANTED_BOOK));
		STATUS.put(38, new Status("refinedOre","Ýþlenmiþ Çýkma Ýhtimali", 0, 2, 40,"&&&7Madenler iþlenmiþ olarak düþer.&&&7Seviye baþýna þans artýþý: &e1%&&", Material.COAL));
		STATUS.put(40, new Status("miningLuck","Þans", 0, 5, 6, "&&&7Düþen eþya miktarýný arttýrýr.&&&7Seviye baþýna düþen eþya artýþý: &e1&&", Material.NETHER_STAR));
		STATUS.put(42, new Status("processingRate","Ýþleme", 20, 1, 100,"&&&7Eþyalarý iþleme þansýnýzý arttýrýr.&&&7Seviye baþýna iþleme artýþý: &e1%&&", Material.CAULDRON));
	}
	
	public static void openStatuInventory(Player p) {
		Inventory inv = Bukkit.createInventory(null, 45, "§6Statü Geliþtirme Menüsü");
		ItemStack infoPaper = new ItemStack(Material.PAPER);
		ItemMeta itemMeta = infoPaper.getItemMeta();
		itemMeta.setDisplayName("§7Kalan Statü Puaný: §c"+PLAYER_STATUS.get(p).getLeftPoint());
		ArrayList<String> itemLore = new ArrayList<String>();
		itemLore.add(""); itemLore.add("§eStatü puanlarýnýzý buradan geliþtirebilirsiniz.");
		itemLore.add("§7Statü puanlarýný elde etme yöntemleri:"); itemLore.add("    §7- §cYaratýk/Boss öldürme"); itemLore.add("    §7- §aMadencilik"); itemLore.add("    §7- §9Çeþitli görevler");
		itemLore.add("§6Karakterinizi geliþtirmek için statü puaný kasmalýsýnýz.");
		itemMeta.setLore(itemLore);
		infoPaper.setItemMeta(itemMeta);
		inv.setItem(4, infoPaper);
		for(int sID : STATUS.keySet()) {
				inv.setItem(sID, ItemCreator.createItem(STATUS.get(sID).getInvItem(), (byte)1, 
						"&7Statü: &c"+STATUS.get(sID).getFullName(),
						STATUS.get(sID).getStatuInfo()+"&7Statünün mevcut seviyesi: &a"
						+getStatuLevelWithID(p, sID)+"&7/&c"+STATUS.get(sID).getMaxLevel()
						+"&&&7Seviye atlatmak için gerekli puan: &e"+STATUS.get(sID).getNeedXPPerLevel()));
		}
		p.openInventory(inv);
	} 
	
	public static void addPlayerStatuLevel(Player p, int sID) {
		p.sendMessage("§5Statü §8» §e"+STATUS.get(sID).getFullName()+" §7yeteneðinizi yükselttiniz.");
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
