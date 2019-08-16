package me.Enderiatus.ColereCore.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.Enderiatus.ColereCore.Main;
import me.Enderiatus.ColereCore.Status.StatuManager;

public class PLeaveEvent implements Listener {
	
	Main plugin;
	public PLeaveEvent(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void playerLeave(PlayerQuitEvent e) {
		StatuManager.savePlayerFile(e.getPlayer());
		StatuManager.PLAYER_STATUS.remove(e.getPlayer());
	}
	
}
