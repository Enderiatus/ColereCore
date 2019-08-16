package me.Enderiatus.ColereCore.Utils;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemCreator {
	
	public static ItemStack createItem(Material material, byte miktar, String name, String lore) {
		ItemStack item = new ItemStack(material, miktar);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		ArrayList<String> Lore = new ArrayList<String>();
		String[] listing = lore.split("&&");
		for(int deger = 0; deger<listing.length ;deger++) {
			Lore.add(ChatColor.translateAlternateColorCodes('&',listing[deger]));
		}
		meta.setLore(Lore);
		item.setItemMeta(meta);
		return item;
	}

}
