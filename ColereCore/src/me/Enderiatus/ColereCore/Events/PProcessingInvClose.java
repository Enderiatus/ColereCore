package me.Enderiatus.ColereCore.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import me.Enderiatus.ColereCore.Main;
import me.Enderiatus.ColereCore.Utils.CheckInventory;

public class PProcessingInvClose implements Listener {

	Main plugin;
	public PProcessingInvClose(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void invClose(InventoryCloseEvent e) {
		if(!e.getInventory().getName().equals("§9Ýþleme Menüsü")) 
			return;
		Inventory closedInventory = e.getInventory();
		Player p = (Player) e.getPlayer();
		if(!(closedInventory.getItem(10) == null)) {
			if(CheckInventory.isInvEmpty(p, closedInventory.getItem(10))) {
				p.getInventory().addItem(e.getInventory().getItem(10));
			}else {
				p.getLocation().getBlock().getWorld().dropItem(p.getLocation(), closedInventory.getItem(10));
			}
		}
		if(!(closedInventory.getItem(16) == null)) {
			if(CheckInventory.isInvEmpty(p, closedInventory.getItem(16))) {
				p.getInventory().addItem(e.getInventory().getItem(16));
			}else {
				p.getLocation().getBlock().getWorld().dropItem(p.getLocation(), closedInventory.getItem(16));
			}
		}
	}
}
