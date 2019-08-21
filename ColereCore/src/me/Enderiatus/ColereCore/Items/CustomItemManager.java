package me.Enderiatus.ColereCore.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.lumine.xikage.mythicmobs.MythicMobs;
import me.Enderiatus.ColereCore.Main;

public class CustomItemManager {

	public static HashMap<String, ArrayList<ItemStack>> customItems = new HashMap<String, ArrayList<ItemStack>>();
	
	
	public static void loadAllItems() {
		FileConfiguration itemConfig = Main.getInstance().getItemConfig();
		for(String jobs : itemConfig.getConfigurationSection("Items").getKeys(false)) {
			ArrayList<ItemStack> items = new ArrayList<ItemStack>();
			for(String itemID : itemConfig.getStringList("Items."+jobs)) {
				ItemStack item = MythicMobs.inst().getItemManager().getItemStack(itemID);
				items.add(item);
			}
			customItems.put(jobs, items);
		}
	}

	public static void dropCustomItem(String jobs, Player player) {
		int random = new Random().nextInt(CustomItemManager.customItems.get(jobs).size());
		ItemStack customItem = CustomItemManager.customItems.get(jobs).get(random);
		player.getLocation().getWorld().dropItem(player.getLocation(), customItem);
		player.sendMessage("§cMeslek §7» §eMeslek ödülü olarak ekstra eþya kazandýnýz.. §7("+customItem.getItemMeta().getDisplayName()+"§7)");
		
	}
	

	
	
/*	public static void loadCustomItems() {
		FileConfiguration itemConfig = Main.getInstance().getItemConfig();
		if(!itemConfig.isConfigurationSection("Items")) {
			return;
		}
		for(String itemID : itemConfig.getConfigurationSection("Items").getKeys(false)) {
			customItems.put(itemID, new CustomItem(
					itemConfig.getString("Items."+itemID+".Name"),
					itemConfig.getString("Items."+itemID+".Type"),
					itemConfig.getStringList("Items."+itemID+".Lore"),
					itemConfig.getStringList("Items."+itemID+".Enchant"),
					itemConfig.getStringList("Items."+itemID+".Others")));
		}
	}
	
	
	public static Color hexToRGB(int hex) {
		int r = (hex & 0xFF0000) >> 16;
		int g = (hex & 0xFF00) >> 8;
		int b = (hex & 0xFF);
		return Color.fromBGR(r, g, b);
	} */

}
