package me.Enderiatus.ColereCore.Minigame.TempleEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent;
import me.Enderiatus.ColereCore.Main;
import me.Enderiatus.ColereCore.Mobs.CustomMobManager;

public class MMSpawn implements Listener{
	
	Main plugin;
	public MMSpawn(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void mmMobLoot(MythicMobSpawnEvent e) {
		if(CustomMobManager.customMobs.contains(e.getMobType())) {
			if(TempleEvent.templeStatus == TempleStatus.STOP) 
				e.getEntity().remove();
		}
	}

}
