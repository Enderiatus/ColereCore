package me.Enderiatus.ColereCore.Mobs;
/*
import java.util.Random;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import me.Enderiatus.ColereCore.Main;
*/
public class CustomMobSpawn {
}/*
	Main plugin;
	public CustomMobSpawn(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void customMobSpawnEvent(EntitySpawnEvent e) {
	//	if(TempleEvent.templeStatus == TempleStatus.STOP) 
	//		return;
		Random randomChance = new Random();
		int mobChance = randomChance.nextInt(100)+1;
		for(CustomMob cMob : CustomMobManager.customMobs) {
			if(cMob.getEntityType().equalsIgnoreCase(e.getEntityType().toString())) {
				if(mobChance <= cMob.getChance()) {
					e.getEntity().remove();
					CustomMobManager.spawnCustomEntity(e.getLocation(), cMob);
				}
			}
		}
	}
}*/
