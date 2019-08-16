package me.Enderiatus.ColereCore.Events;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.ItemStack;

import me.Enderiatus.ColereCore.Main;
import me.Enderiatus.ColereCore.Processing.RefineBlockType;
import me.Enderiatus.ColereCore.Status.StatuManager;

public class PProcessingInvClick implements Listener {

	Main plugin;
	public PProcessingInvClick(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void processingClickInventory(InventoryClickEvent e) {
		if(e.getSlotType() == SlotType.OUTSIDE) 
			return;
		if(!e.getClickedInventory().getName().equalsIgnoreCase("§9Ýþleme Menüsü"))
			return;
		int clickedSlot = e.getSlot();
		if(e.getSlot() == 10 || e.getSlot() == 16) 
			return;
		e.setCancelled(true);
		if(e.getClick() == ClickType.DOUBLE_CLICK) 
			return;
		Player p = (Player) e.getWhoClicked();
		if(clickedSlot == 13) {
			if(e.getClickedInventory().getItem(10).getType().toString().equalsIgnoreCase("AIR")) 
				return;
			if(!e.getClickedInventory().getItem(10).getItemMeta().getDisplayName().contains("§7Ýþlenmemiþ")) {
				p.sendMessage("§9Ýþleme §8» §cBu eþya iþlenemez.");
				p.getInventory().addItem(e.getClickedInventory().getItem(10));
				e.getClickedInventory().setItem(10, new ItemStack(Material.AIR));
				p.updateInventory();
				return;
			}if(e.getClickedInventory().getItem(16) != null) {
				p.sendMessage("§9Ýþleme §8» §cÝþlenmiþ maden slotu dolu.");
				return;
			}
			Random randomChance = new Random();
			ItemStack refineItem = RefineBlockType.refineMaterialType(e.getClickedInventory().getItem(10).getItemMeta().getDisplayName());
			ItemStack willRefine = e.getClickedInventory().getItem(10);
			int playerChance = StatuManager.PLAYER_STATUS.get(p).getProcessingRate();
			for (int i = 0; i < willRefine.getAmount(); i++) {
				int chance = randomChance.nextInt(100)+1;
				if(chance <= playerChance) {
					refineItem.setAmount(refineItem.getAmount()+1);
				}
			}
			e.getClickedInventory().setItem(16, refineItem);
			e.getClickedInventory().setItem(10, new ItemStack(Material.AIR));
			double succesRate = (refineItem.getAmount())*100 / willRefine.getAmount();
			p.sendMessage("");
			p.sendMessage("                    §9Ýþleme Durumu:");
			p.sendMessage("     §8» §aÝþlenen cevher: "+willRefine.getItemMeta().getDisplayName());
			p.sendMessage("     §8» §aÝþlenen cevher miktarý: §e"+willRefine.getAmount());
			p.sendMessage("     §8» §aBaþarýyla iþlenen cevher: §e"+refineItem.getAmount());
			p.sendMessage("     §8» §aMevcut iþleme seviyeniz: §e"+ StatuManager.PLAYER_STATUS.get(p).getProcessingRate()+"%");
			p.sendMessage("     §8» §aOrtalama baþarý þansý: §e~" +succesRate+"%");
			p.sendMessage("");
			p.updateInventory();
			
		}
	}
}
