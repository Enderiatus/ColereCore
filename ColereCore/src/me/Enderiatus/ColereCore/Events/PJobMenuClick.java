package me.Enderiatus.ColereCore.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;

import me.Enderiatus.ColereCore.Main;
import me.Enderiatus.ColereCore.Jobs.Jobs;
import me.Enderiatus.ColereCore.Utils.ItemCreator;

public class PJobMenuClick implements Listener{
	
	Main plugin;
	public PJobMenuClick(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public static void jobInvClick(InventoryClickEvent e) {
		if(e.getSlotType() == SlotType.OUTSIDE) 
			return;
		if(!e.getClickedInventory().getName().equalsIgnoreCase("§3Meslek Menüsü"))
			return;
		e.setCancelled(true);
		Material clickedItem = e.getCurrentItem().getType();
		if(!(clickedItem == Material.BOOK) || !(clickedItem == Material.STICK)
				|| !(clickedItem == Material.SIGN) || !(clickedItem == Material.TOTEM_OF_UNDYING)) {
			if(!(e.getClickedInventory().getItem(1).getType() == Material.BOOK)) {
				for(Jobs j : Jobs.values()) {
					if(Jobs.getJobDisplayItem(j) == clickedItem) {
						Jobs.selectJob((Player) e.getWhoClicked(), j);
						return;
					}
				}
			}
		}
		switch (e.getRawSlot()) {
		case 1:
			if(clickedItem == Material.BOOK) {
				int i = 0;
				for(Jobs j : Jobs.values()) {
					if(j != Jobs.JOBLESS) {
						e.getClickedInventory().setItem(i, ItemCreator.createItem(Jobs.getJobDisplayItem(j), (byte)1, "&6Meslek: "+Jobs.getJobDisplayName(j), 
								Jobs.getJobAbout(j)));
						i++;
					}
				}
				break;
			}
		case 4:
			e.getWhoClicked().closeInventory();
		default:
			break;
		}
	}
}
