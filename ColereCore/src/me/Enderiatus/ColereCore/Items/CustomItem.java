package me.Enderiatus.ColereCore.Items;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class CustomItem {
	
	private ItemStack itemStack;

	public CustomItem(String itemName, String itemType, List<String> itemLore, List<String> itemEnchants, List<String> itemOthers) {
		itemStack = new ItemStack(Material.getMaterial(itemType));
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemName));
		int index = 0;
		for(String Lore : itemLore) {
			itemLore.set(index, ChatColor.translateAlternateColorCodes('&', Lore));
			index++;
		}
		itemMeta.setLore(itemLore);
		itemStack.setItemMeta(itemMeta);
		if(itemEnchants.size() >= 1) {
			for(String enchant : itemEnchants) {
				Enchantment enchantment = EnchantmentWrapper.getByKey(NamespacedKey.minecraft(enchant.split(":")[0].toLowerCase()));
				int level = Integer.parseInt(enchant.split(":")[1]);
				itemStack.addUnsafeEnchantment(enchantment, level);
			}
		}
		if(itemOthers.size() >= 1) {
			for(String other : itemOthers) {
				if(other.split(":")[0].toLowerCase().equalsIgnoreCase("color")) {
					LeatherArmorMeta laMeta = (LeatherArmorMeta) itemStack.getItemMeta();
					int red = Integer.parseInt(other.split(":")[1]);
					int blue = Integer.parseInt(other.split(":")[2]);
					int green = Integer.parseInt(other.split(":")[3]);
					laMeta.setColor(Color.fromRGB(red, blue, green));
					itemStack.setItemMeta(laMeta);
				}else if(other.split(":")[0].toLowerCase().equalsIgnoreCase("amount")) {
					itemStack.setAmount(Integer.parseInt(other.split(":")[1]));
				}else if(other.split(":")[0].toLowerCase().equalsIgnoreCase("durability")) {
					final Damageable itemMetaDamage = (Damageable) itemStack.getItemMeta();
					itemMetaDamage.setDamage(Integer.parseInt(other.split(":")[1]));
					itemStack.setItemMeta((ItemMeta) itemMetaDamage);
				}
			}
		}
	}
	
	public ItemStack getItem() {
		return itemStack;
	}
}
