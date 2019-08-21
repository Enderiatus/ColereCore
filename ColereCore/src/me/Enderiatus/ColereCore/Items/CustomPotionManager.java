package me.Enderiatus.ColereCore.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.lumine.xikage.mythicmobs.MythicMobs;
import me.Enderiatus.ColereCore.Main;
import me.Enderiatus.ColereCore.Utils.CheckInventory;

public class CustomPotionManager {
	
	public static HashMap<ItemStack, ArrayList<ItemStack>> customPotionRecipes = new HashMap<ItemStack, ArrayList<ItemStack>>();
	
	
	public static void loadAllPotions() {
		FileConfiguration potionConfig = Main.getInstance().getPotionConfig();
		for(String producedItem : potionConfig.getConfigurationSection("Potions").getKeys(false)) {
			Bukkit.getLogger().info("Keys: "+ producedItem);
			ArrayList<ItemStack> recipeItems = new ArrayList<ItemStack>();
			for(String itemID : potionConfig.getStringList("Potions."+producedItem)) {
				String[] parsed = itemID.split(":");
				ItemStack item = MythicMobs.inst().getItemManager().getItemStack(parsed[0]);
				item.setAmount(Integer.parseInt(parsed[1]));
				recipeItems.add(item);
			}
			ItemStack keyItem = MythicMobs.inst().getItemManager().getItemStack(producedItem);
			customPotionRecipes.put(keyItem, recipeItems);
		}
	}
	
	public static void openPotionMenu(Player p) {
		Inventory potionInv = Bukkit.createInventory(null, 54, "§9Ýksir Menüsü");
		int i = 0;
		for(ItemStack iS : customPotionRecipes.keySet()) {
			ItemStack clone = iS.clone();
			ItemMeta cloneMeta = clone.getItemMeta();
			List<String> lore = cloneMeta.getLore();
			lore.add("");
			lore.add("§7Gerekli Malzemeler:");
			for(ItemStack need :customPotionRecipes.get(iS)) {
				String newName = ChatColor.stripColor(need.getItemMeta().getDisplayName());
				if(CheckInventory.hasItem(p, need)) {
					lore.add("  §7+ §a"+newName+" §7(§ex"+need.getAmount()+"§7)");
				}else {
					lore.add("  §7+ §c"+newName+" §7(§ex"+need.getAmount()+"§7)");
				}
				
			}
			cloneMeta.setLore(lore);
			clone.setItemMeta(cloneMeta);
			potionInv.setItem(i, clone);
			i++;
		}
		p.openInventory(potionInv);
	}
	
	public static void sellPotion(Player p, ItemStack item) {
		for(ItemStack iS : customPotionRecipes.keySet()) {
			if(iS.getItemMeta().getDisplayName().contains(item.getItemMeta().getDisplayName())) {
				for(ItemStack need :customPotionRecipes.get(iS)) {
					if(!CheckInventory.hasItem(p, need)) {
						p.sendMessage("§5Iksir Dükkaný §7» §cBir eþya eksik gibi duruyor? §7("+need.getItemMeta().getDisplayName()+"§7)");
						return;
					}	
				}if(!CheckInventory.isInvEmpty(p, item)) {
					p.sendMessage("§5Iksir Dükkaný §7» §cEnvanterinizde yeterli boþluk yok.");
					return;
				}for(ItemStack need :customPotionRecipes.get(iS)) {
					CheckInventory.removeItem(p, need);
				}
				p.getInventory().addItem(iS);
				p.sendMessage("§5Iksir Dükkaný §7» §aIksir satýn alýndý. §7("+item.getItemMeta().getDisplayName()+"§7)");
				openPotionMenu(p);
				return;
			}
		}
	}

}
