package me.Enderiatus.ColereCore.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.meta.ItemMeta;

import me.Enderiatus.ColereCore.Main;
import me.Enderiatus.ColereCore.Status.StatuManager;
import me.Enderiatus.ColereCore.Utils.ItemCreator;

public class PStatuInvClick implements Listener {

	Main plugin;
	public PStatuInvClick(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void statuClickInventory(InventoryClickEvent e) {
		if(e.getSlotType() == SlotType.OUTSIDE) 
			return;
		if(!e.getClickedInventory().getName().equalsIgnoreCase("§6Statü Geliþtirme Menüsü"))
			return;
		e.setCancelled(true);
		if(!StatuManager.STATUS.containsKey(e.getSlot())) 
			return;
		if(e.getClick() == ClickType.DOUBLE_CLICK) 
			return;
		Player p = (Player) e.getWhoClicked();
		int sID = e.getSlot();
		if(StatuManager.PLAYER_STATUS.get(p).getLeftPoint() < StatuManager.STATUS.get(sID).getNeedXPPerLevel()) {
			p.sendMessage("§5Statü §8» §e"+StatuManager.STATUS.get(sID).getFullName()+" §7statüsünü geliþtirmek için §6"+(StatuManager.STATUS.get(sID).getNeedXPPerLevel()-StatuManager.PLAYER_STATUS.get(p).getLeftPoint())+" §7puana daha ihtiyacýn var.");
			return;
		}if( StatuManager.getStatuLevelWithID(p, sID) >= StatuManager.STATUS.get(sID).getMaxLevel()) {
			p.sendMessage("§5Statü §8» §7Bu statünüz zaten son seviyede.");
			return;
		}
		StatuManager.addPlayerStatuLevel(p, sID);
		ItemMeta itemMeta = e.getClickedInventory().getItem(4).getItemMeta();
		itemMeta.setDisplayName("§7Kalan Statü Puaný: §c"+StatuManager.PLAYER_STATUS.get(p).getLeftPoint());
		e.getClickedInventory().getItem(4).setItemMeta(itemMeta);
		//e.getClickedInventory().getItem(4).getItemMeta().setDisplayName("§7Kalan Statü Puaný: §c"+StatuManager.PLAYER_STATUS.get(p).getLeftPoint());
		p.getOpenInventory().setItem(sID, ItemCreator.createItem(StatuManager.STATUS.get(sID).getInvItem(), (byte)1, 
				"&7Statü: &c"+StatuManager.STATUS.get(sID).getFullName(),
				StatuManager.STATUS.get(sID).getStatuInfo()+"&7Statünün mevcut seviyesi: &a"
				+StatuManager.getStatuLevelWithID(p, sID)+"&7/&c"+StatuManager.STATUS.get(sID).getMaxLevel()
				+"&&&7Seviye atlatmak için gerekli puan: &e"+StatuManager.STATUS.get(sID).getNeedXPPerLevel()));
		p.updateInventory();
	}
}
