package me.Enderiatus.ColereCore.Jobs.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import me.Enderiatus.ColereCore.Main;

public class FisherFishEvent implements Listener {
	
	Main plugin;
	public FisherFishEvent(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void catchFish(PlayerFishEvent e) {

	}

}
