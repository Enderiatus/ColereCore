package me.Enderiatus.ColereCore.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Enderiatus.ColereCore.Main;
import me.Enderiatus.ColereCore.Status.StatuManager;

public class PJoinEvent implements Listener{
	
	Main plugin;
	public PJoinEvent(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void playerJoin(PlayerJoinEvent e) {
		StatuManager.checkPlayerFile(e.getPlayer());
	}

}
