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
		if(!e.getClickedInventory().getName().equalsIgnoreCase("�6Stat� Geli�tirme Men�s�"))
			return;
		e.setCancelled(true);
		if(!StatuManager.STATUS.containsKey(e.getSlot())) 
			return;
		if(e.getClick() == ClickType.DOUBLE_CLICK) 
			return;
		Player p = (Player) e.getWhoClicked();
		int sID = e.getSlot();
		if(StatuManager.PLAYER_STATUS.get(p).getLeftPoint() < StatuManager.STATUS.get(sID).getNeedXPPerLevel()) {
			p.sendMessage("�5Stat� �8� �e"+StatuManager.STATUS.get(sID).getFullName()+" �7stat�s�n� geli�tirmek i�in �6"+(StatuManager.STATUS.get(sID).getNeedXPPerLevel()-StatuManager.PLAYER_STATUS.get(p).getLeftPoint())+" �7puana daha ihtiyac�n var.");
			return;
		}if( StatuManager.getStatuLevelWithID(p, sID) >= StatuManager.STATUS.get(sID).getMaxLevel()) {
			p.sendMessage("�5Stat� �8� �7Bu stat�n�z zaten son seviyede.");
			return;
		}
		StatuManager.addPlayerStatuLevel(p, sID);
		ItemMeta itemMeta = e.getClickedInventory().getItem(4).getItemMeta();
		itemMeta.setDisplayName("�7Kalan Stat� Puan�: �c"+StatuManager.PLAYER_STATUS.get(p).getLeftPoint());
		e.getClickedInventory().getItem(4).setItemMeta(itemMeta);
		//e.getClickedInventory().getItem(4).getItemMeta().setDisplayName("�7Kalan Stat� Puan�: �c"+StatuManager.PLAYER_STATUS.get(p).getLeftPoint());
		p.getOpenInventory().setItem(sID, ItemCreator.createItem(StatuManager.STATUS.get(sID).getInvItem(), (byte)1, 
				"&7Stat�: &c"+StatuManager.STATUS.get(sID).getFullName(),
				StatuManager.STATUS.get(sID).getStatuInfo()+"&7Stat�n�n mevcut seviyesi: &a"
				+StatuManager.getStatuLevelWithID(p, sID)+"&7/&c"+StatuManager.STATUS.get(sID).getMaxLevel()
				+"&&&7Seviye atlatmak i�in gerekli puan: &e"+StatuManager.STATUS.get(sID).getNeedXPPerLevel()));
		p.updateInventory();
	}
}
