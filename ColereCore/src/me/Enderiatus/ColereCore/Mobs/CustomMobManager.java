package me.Enderiatus.ColereCore.Mobs;
/*
import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Enderiatus.ColereCore.Main;
*/
public class CustomMobManager {
}/*

	public static ArrayList<CustomMob> customMobs = new ArrayList<CustomMob>();
	
	public static void loadAllMobs() {
		FileConfiguration mobConfig = Main.getInstance().getMobConfig();
		if(!mobConfig.isConfigurationSection("Mobs")) {
			return;
		}
		for(String mobID : mobConfig.getConfigurationSection("Mobs").getKeys(false)) {
			customMobs.add(new CustomMob(mobConfig.getString("Mobs."+mobID+".DisplayName"),
					mobConfig.getString("Mobs."+mobID+".EntityType"),
					mobConfig.getInt("Mobs."+mobID+".SpawnChance"), 
					mobConfig.getDouble("Mobs."+mobID+".Health"), 
					mobConfig.getInt("Mobs."+mobID+".Damage"), 
					mobConfig.getStringList("Mobs."+mobID+".Effects"), 
					mobConfig.getStringList("Mobs."+mobID+".DropItems"),
					mobConfig.getString("Mobs."+mobID+".Items.Helmet"),
					mobConfig.getString("Mobs."+mobID+".Items.Chestplate"),
					mobConfig.getString("Mobs."+mobID+".Items.Leggings"),
					mobConfig.getString("Mobs."+mobID+".Items.Boots")));
		}
	}
	
	public static boolean isCustomMob(String displayName) {
		for(CustomMob cMob : customMobs) {
			if(cMob.getDisplayName().equalsIgnoreCase(displayName)) 
				return true;
		} return false;
	}

	public static void spawnCustomEntity(Location location, CustomMob cMob) {
		if(cMob.getEntityType().equalsIgnoreCase("ZOMBIE")) {
			LivingEntity zombie = (Zombie) location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
			zombie.setCustomName(cMob.getDisplayName());
			zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(cMob.getHealth());
			zombie.setHealth(cMob.getHealth());
			zombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(cMob.getDamage());
			zombie.getEquipment().setHelmet(cMob.getArmorHelmet());
			zombie.getEquipment().setChestplate(cMob.getArmorChestplate());
			zombie.getEquipment().setLeggings(cMob.getArmorLeggings());
			zombie.getEquipment().setBoots(cMob.getArmorBoots());
			for(String effect : cMob.getEffects()) {
				zombie.addPotionEffect(new PotionEffect(PotionEffectType.getByName(effect.split(":")[0]), 
						12000, 
						Integer.parseInt(effect.split(":")[1])));
			}
		}
		
	}
	
}*/
