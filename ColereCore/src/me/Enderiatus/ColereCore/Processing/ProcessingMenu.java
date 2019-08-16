package me.Enderiatus.ColereCore.Processing;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.Enderiatus.ColereCore.Utils.ItemCreator;

public class ProcessingMenu{
	
	private static int[] redGlass = {0,1,2,9,11,18,19,20};
	private static int[] blackGlass = {3,4,5,12,14,21,22,23};
	private static int[] greenGlass = {6,7,8,15,17,24,25,26};
	
	public static void openProcessingMenu(Player p) {
		Inventory processingInv = Bukkit.createInventory(null, 27, "§9Ýþleme Menüsü");
		for(int i : redGlass) {
			processingInv.setItem(i, ItemCreator.createItem(Material.RED_STAINED_GLASS_PANE, (byte)1, "&c&lIÞLENECEK MADEN", "&&&7Ýþlenecek madeni ortaya koyunuz."));
		}for(int i : blackGlass) {
			processingInv.setItem(i, ItemCreator.createItem(Material.BLACK_STAINED_GLASS_PANE, (byte)1, " ", ""));
		}
		processingInv.setItem(13, ItemCreator.createItem(Material.CAULDRON, (byte)1, "&6&lMADENLERI IÞLE!", "&&&7Madenleri iþlemek için &e&ntýklayýnýz."));
		for(int i : greenGlass) {
			processingInv.setItem(i, ItemCreator.createItem(Material.GREEN_STAINED_GLASS_PANE, (byte)1, "&2&lIÞLENMIÞ MADEN", "&&&7Ýþlenmiþ madeni ortadan alýnýz."));
		}
		p.openInventory(processingInv);
	}

}
