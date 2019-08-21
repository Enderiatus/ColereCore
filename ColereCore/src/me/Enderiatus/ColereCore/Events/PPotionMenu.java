package me.Enderiatus.ColereCore.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;

import me.Enderiatus.ColereCore.Main;
import me.Enderiatus.ColereCore.Items.CustomPotionManager;

public class PPotionMenu implements Listener{
	
	Main plugin;
	public PPotionMenu(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void processingClickInventory(InventoryClickEvent e) {
		if(e.getSlotType() == SlotType.OUTSIDE) 
			return;
		if(!e.getClickedInventory().getName().equalsIgnoreCase("§9Ýksir Menüsü"))
			return;
		e.setCancelled(true);
		if(e.getClick() == ClickType.DOUBLE_CLICK) 
			return;
		if(e.getCurrentItem().getType() == Material.AIR) 
			return;
		CustomPotionManager.sellPotion((Player) e.getWhoClicked(), e.getCurrentItem());
	}

}
