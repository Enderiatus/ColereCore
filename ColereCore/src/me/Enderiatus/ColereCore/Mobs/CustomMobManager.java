package me.Enderiatus.ColereCore.Mobs;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.drops.DropTable;
import io.lumine.xikage.mythicmobs.mobs.MythicMob;
import me.Enderiatus.ColereCore.Main;

public class CustomMobManager {
	
	public static ArrayList<MythicMob> customMobs = new ArrayList<MythicMob>();
	public static HashMap<MythicMob, DropTable> customDrops = new HashMap<MythicMob, DropTable>();
	
	public static void loadAllMobs() {
		FileConfiguration mobConfig = Main.getInstance().getMobConfig();
		for(String mobID : mobConfig.getStringList("Mobs")) {
			customMobs.add(MythicMobs.inst().getAPIHelper().getMythicMob(mobID));
		}
	}
}
