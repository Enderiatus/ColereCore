package me.Enderiatus.ColereCore.Utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CheckInventory {
	
	public static boolean isInvEmpty(Player player, ItemStack item) {
		int stackCounter=0;
		int totalItem = 0;
		for(ItemStack loopItem : player.getInventory().getStorageContents()) {
			if(loopItem == null) {
				return true;
			}else if(loopItem.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName()) 
					&& loopItem.getType().equals(item.getType())) {
				if((loopItem.getAmount()+item.getAmount()) <= loopItem.getMaxStackSize()) {
					return true;
				}else {
					stackCounter++;
					totalItem += loopItem.getAmount();
					if(totalItem < (loopItem.getMaxStackSize()*stackCounter))
						return true; 
				}
			}	
		}
		return false;
	}
	
	public static boolean hasItem(Player p, ItemStack item) {
		int totalAmount = 0;
		for(ItemStack loopItem : p.getInventory().getStorageContents()) {
			if(loopItem != null) {
				if(loopItem.getItemMeta().equals(item.getItemMeta())) {
					totalAmount+=loopItem.getAmount();
				}
			}	
		}
		if(totalAmount >= item.getAmount()) {
			return true;
		}
		return false;
	}
	
	public static void removeItem(Player p, ItemStack item) {
		int remainingAmount = item.getAmount();
		for(ItemStack loopItem : p.getInventory().getStorageContents()) {
			if(loopItem != null) {
				if(loopItem.getItemMeta().equals(item.getItemMeta())) {
					if(loopItem.getAmount() <= remainingAmount) {
						remainingAmount-=loopItem.getAmount();
						loopItem.setAmount(0);
					}else if(loopItem.getAmount() > remainingAmount){
						loopItem.setAmount(loopItem.getAmount()-remainingAmount);
						return;
					}
				}
			}	
		}
	}

}
