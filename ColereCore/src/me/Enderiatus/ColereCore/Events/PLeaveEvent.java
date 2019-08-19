package me.Enderiatus.ColereCore.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.Enderiatus.ColereCore.Main;
import me.Enderiatus.ColereCore.Status.StatusManager;

public class PLeaveEvent implements Listener {
	
	Main plugin;
	public PLeaveEvent(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void playerLeave(PlayerQuitEvent e) {
		StatusManager.savePlayerFile(e.getPlayer());
		StatusManager.PLAYER_STATUS.remove(e.getPlayer());
	}
	
}
