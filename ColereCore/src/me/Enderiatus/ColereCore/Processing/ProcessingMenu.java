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
		Inventory processingInv = Bukkit.createInventory(null, 27, "�9��leme Men�s�");
		for(int i : redGlass) {
			processingInv.setItem(i, ItemCreator.createItem(Material.RED_STAINED_GLASS_PANE, (byte)1, "&c&lI�LENECEK MADEN", "&&&7��lenecek madeni ortaya koyunuz."));
		}for(int i : blackGlass) {
			processingInv.setItem(i, ItemCreator.createItem(Material.BLACK_STAINED_GLASS_PANE, (byte)1, " ", ""));
		}
		processingInv.setItem(13, ItemCreator.createItem(Material.CAULDRON, (byte)1, "&6&lMADENLERI I�LE!", "&&&7Madenleri i�lemek i�in &e&nt�klay�n�z."));
		for(int i : greenGlass) {
			processingInv.setItem(i, ItemCreator.createItem(Material.GREEN_STAINED_GLASS_PANE, (byte)1, "&2&lI�LENMI� MADEN", "&&&7��lenmi� madeni ortadan al�n�z."));
		}
		p.openInventory(processingInv);
	}

}
