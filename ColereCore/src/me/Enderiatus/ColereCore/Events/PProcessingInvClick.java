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
		if(!e.getClickedInventory().getName().equalsIgnoreCase("�9��leme Men�s�"))
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
			if(!e.getClickedInventory().getItem(10).getItemMeta().getDisplayName().contains("�7��lenmemi�")) {
				p.sendMessage("�9��leme �8� �cBu e�ya i�lenemez.");
				p.getInventory().addItem(e.getClickedInventory().getItem(10));
				e.getClickedInventory().setItem(10, new ItemStack(Material.AIR));
				p.updateInventory();
				return;
			}if(e.getClickedInventory().getItem(16) != null) {
				p.sendMessage("�9��leme �8� �c��lenmi� maden slotu dolu.");
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
			p.sendMessage("                    �9��leme Durumu:");
			p.sendMessage("     �8� �a��lenen cevher: "+willRefine.getItemMeta().getDisplayName());
			p.sendMessage("     �8� �a��lenen cevher miktar�: �e"+willRefine.getAmount());
			p.sendMessage("     �8� �aBa�ar�yla i�lenen cevher: �e"+refineItem.getAmount());
			p.sendMessage("     �8� �aMevcut i�leme seviyeniz: �e"+ StatuManager.PLAYER_STATUS.get(p).getProcessingRate()+"%");
			p.sendMessage("     �8� �aOrtalama ba�ar� �ans�: �e~" +succesRate+"%");
			p.sendMessage("");
			p.updateInventory();
			
		}
	}
}
