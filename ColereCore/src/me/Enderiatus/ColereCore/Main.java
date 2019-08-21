package me.Enderiatus.ColereCore;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.Enderiatus.ColereCore.Commands.JobsCommand;
import me.Enderiatus.ColereCore.Events.PBlockBreak;
import me.Enderiatus.ColereCore.Events.PDamage;
import me.Enderiatus.ColereCore.Events.PJobMenuClick;
import me.Enderiatus.ColereCore.Events.PJoinEvent;
import me.Enderiatus.ColereCore.Events.PLeaveEvent;
import me.Enderiatus.ColereCore.Events.PPotionMenu;
import me.Enderiatus.ColereCore.Events.PProcessingInvClick;
import me.Enderiatus.ColereCore.Events.PProcessingInvClose;
import me.Enderiatus.ColereCore.Events.PStatuInvClick;
import me.Enderiatus.ColereCore.Items.CustomItemManager;
import me.Enderiatus.ColereCore.Items.CustomPotionManager;
import me.Enderiatus.ColereCore.Jobs.Events.FarmerFarmEvent;
import me.Enderiatus.ColereCore.Jobs.Events.FisherFishEvent;
import me.Enderiatus.ColereCore.Jobs.Events.LumberjackWoodEvent;
import me.Enderiatus.ColereCore.Jobs.Events.MMDeath;
import me.Enderiatus.ColereCore.Minigame.TempleEvent.MMSpawn;
import me.Enderiatus.ColereCore.Minigame.TempleEvent.TempleEvent;
import me.Enderiatus.ColereCore.Mobs.CustomMobManager;
import me.Enderiatus.ColereCore.Status.StatusManager;

public class Main extends JavaPlugin{
	
	private File mobFile, itemFile, potionFile;
	private FileConfiguration mobConfig, itemConfig, potionConfig;
	
	private static Main instance;
	
	
	public void onEnable() {
		instance = this;
		checkFiles();
		registerEvents();
		TempleEvent.startTempleTimer();
		StatusManager.loadStatus();
		CustomItemManager.loadAllItems();;
		CustomPotionManager.loadAllPotions();
		CustomMobManager.loadAllMobs();
		getCommand("meslek").setExecutor(new JobsCommand(this));
	}

	public static Main getInstance() {
		return instance;
	}
	
	
	
	
	private void registerEvents() {
		Bukkit.getServer().getPluginManager().registerEvents(new PLeaveEvent(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PJoinEvent(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PStatuInvClick(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PDamage(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PBlockBreak(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PProcessingInvClick(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PProcessingInvClose(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PPotionMenu(this), this);
		
		Bukkit.getServer().getPluginManager().registerEvents(new MMSpawn(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new MMDeath(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new LumberjackWoodEvent(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new FarmerFarmEvent(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new FisherFishEvent(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PJobMenuClick(this), this);
	//	Bukkit.getServer().getPluginManager().registerEvents(new CustomMobSpawn(this), this);
	}

	
	
	public void checkFiles() {
		try {
			if(!getDataFolder().exists()) {
				getDataFolder().mkdirs();
			}
			File playerData = new File(getDataFolder()+"/playerData");
			mobFile = new File(getDataFolder(), "mobs.yml");
			itemFile = new File(getDataFolder(), "jobitems.yml");
			potionFile = new File(getDataFolder(), "potions.yml");
			if(!playerData.exists()) {
				playerData.mkdirs();
			}if(!mobFile.exists()) {
				mobFile.createNewFile();
			}if(!itemFile.exists()) {
				itemFile.createNewFile();
			}if(!potionFile.exists()) {
				potionFile.createNewFile();
			}
		} catch (IOException e) {
			getLogger().warning("[Colere Klan] Dosyalar olusturulurken bir hata olustu!" + e);
		}
		mobConfig = YamlConfiguration.loadConfiguration(mobFile);
		potionConfig = YamlConfiguration.loadConfiguration(potionFile);
		itemConfig = YamlConfiguration.loadConfiguration(itemFile);
	}

	
	
	
	
	public FileConfiguration getMobConfig() {
		return mobConfig;
	}

	public void setMobConfig(FileConfiguration mobConfig) {
		this.mobConfig = mobConfig;
	}

	public FileConfiguration getItemConfig() {
		return itemConfig;
	}

	public void setItemConfig(FileConfiguration itemConfig) {
		this.itemConfig = itemConfig;
	}

	public File getPotionFile() {
		return potionFile;
	}

	public void setPotionFile(File potionFile) {
		this.potionFile = potionFile;
	}

	public FileConfiguration getPotionConfig() {
		return potionConfig;
	}

	public void setPotionConfig(FileConfiguration potionConfig) {
		this.potionConfig = potionConfig;
	}


}
