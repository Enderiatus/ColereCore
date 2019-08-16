package me.Enderiatus.ColereCore.Jobs.Events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent;
import me.Enderiatus.ColereCore.Main;

public class MMSpawn implements Listener{
	
	Main plugin;
	public MMSpawn(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void mmMobLoot(MythicMobSpawnEvent e) {
		Bukkit.getServer().broadcastMessage("spawnlanan mob tipi: "+e.getMobType().getEntityType().toString());
	}

}
