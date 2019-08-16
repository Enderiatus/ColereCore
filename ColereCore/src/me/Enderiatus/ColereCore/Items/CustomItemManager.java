package me.Enderiatus.ColereCore.Items;

import java.util.HashMap;

import org.bukkit.Color;
import org.bukkit.configuration.file.FileConfiguration;

import me.Enderiatus.ColereCore.Main;

public class CustomItemManager {

	public static HashMap<String, CustomItem> customItems = new HashMap<String, CustomItem>();
	
	public static void loadCustomItems() {
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
	}

}
