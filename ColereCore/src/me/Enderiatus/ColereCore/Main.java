package me.Enderiatus.ColereCore;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.Enderiatus.ColereCore.Commands.CustomItemCommand;
import me.Enderiatus.ColereCore.Events.PBlockBreak;
import me.Enderiatus.ColereCore.Events.PDamage;
import me.Enderiatus.ColereCore.Events.PJoinEvent;
import me.Enderiatus.ColereCore.Events.PLeaveEvent;
import me.Enderiatus.ColereCore.Events.PProcessingInvClick;
import me.Enderiatus.ColereCore.Events.PProcessingInvClose;
import me.Enderiatus.ColereCore.Events.PStatuInvClick;
import me.Enderiatus.ColereCore.Items.CustomItemManager;
import me.Enderiatus.ColereCore.Jobs.Events.MMSpawn;
import me.Enderiatus.ColereCore.Minigame.TempleEvent.TempleEvent;
import me.Enderiatus.ColereCore.Status.StatuManager;

public class Main extends JavaPlugin{
	
	private File fishDropFile, itemFile;
	private FileConfiguration fishConfig, itemConfig;
	
	private static Main instance;
	
	
	public void onEnable() {
		instance = this;
		checkFiles();
		registerEvents();
		TempleEvent.startTempleTimer();
		StatuManager.loadStatus();
		CustomItemManager.loadCustomItems();
	//	CustomMobManager.loadAllMobs();
		getCommand("ci").setExecutor(new CustomItemCommand(this));
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
		
		Bukkit.getServer().getPluginManager().registerEvents(new MMSpawn(this), this);
		
	//	Bukkit.getServer().getPluginManager().registerEvents(new CustomMobSpawn(this), this);
	}

	
	
	public void checkFiles() {
		try {
			if(!getDataFolder().exists()) {
				getDataFolder().mkdirs();
			}
			File playerData = new File(getDataFolder()+"/playerData");
			fishDropFile = new File(getDataFolder(), "fishingDrops.yml");
	//		mobFile = new File(getDataFolder(), "mobConfig.yml");
			itemFile = new File(getDataFolder(), "itemList.yml");
			if(!playerData.exists()) {
				playerData.mkdirs();
			}if(!fishDropFile.exists()) {
				fishDropFile.createNewFile();
			}/*if(!mobFile.exists()) {
				mobFile.createNewFile();
			}*/if(!itemFile.exists()) {
				itemFile.createNewFile();
			}
		} catch (IOException e) {
			getLogger().warning("[Colere Klan] Dosyalar olusturulurken bir hata olustu!" + e);
		}
		fishConfig = YamlConfiguration.loadConfiguration(fishDropFile);
	//	mobConfig = YamlConfiguration.loadConfiguration(mobFile);
		itemConfig = YamlConfiguration.loadConfiguration(itemFile);
	}

	
	
	
	
/*	public FileConfiguration getMobConfig() {
		return mobConfig;
	}

	public void setMobConfig(FileConfiguration mobConfig) {
		this.mobConfig = mobConfig;
	}*/

	public FileConfiguration getFishConfig() {
		return fishConfig;
	}

	public void setFishConfig(FileConfiguration fishConfig) {
		this.fishConfig = fishConfig;
	}

	public FileConfiguration getItemConfig() {
		return itemConfig;
	}

	public void setItemConfig(FileConfiguration itemConfig) {
		this.itemConfig = itemConfig;
	}


}
