package me.Enderiatus.ColereCore.Jobs.Events;

import java.util.Random;

import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import me.Enderiatus.ColereCore.Main;
import me.Enderiatus.ColereCore.Items.CustomItemManager;
import me.Enderiatus.ColereCore.Jobs.Jobs;
import me.Enderiatus.ColereCore.Status.StatusManager;

public class FisherFishEvent implements Listener {
	
	Main plugin;
	public FisherFishEvent(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void catchFish(PlayerFishEvent e) {
		if(!(StatusManager.PLAYER_STATUS.get(e.getPlayer()).getPlayerJob() == Jobs.FISHER)) 
			return;
		if(!(e.getCaught() instanceof Item)) 
			return;
		int randomChance = new Random().nextInt(1000)+1;
		if(randomChance <= StatusManager.PLAYER_STATUS.get(e.getPlayer()).getJobLevel()) {
			CustomItemManager.dropCustomItem("Fisher", e.getPlayer());
		}
	}

}
