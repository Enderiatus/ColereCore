package me.Enderiatus.ColereCore.Mobs;
/*
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.Enderiatus.ColereCore.Items.CustomItemManager;
import net.md_5.bungee.api.ChatColor;
*/
public class CustomMob {
	
}
/*
	private String displayName;
	private String entityType;
	private double health;
	private int damage;
	private int chance;
	private List<String> dropItems = new ArrayList<String>();
	private List<String> effects;
	private ItemStack armorHelmet;
	private ItemStack armorChestplate;
	private ItemStack armorLeggings;
	private ItemStack armorBoots;
	
	
	public CustomMob(String displayName, String entityType, int chance, double health, int damage, List<String> effects, List<String> dropItems,
			String helmet, String chestplate, String leggings, String boots) {
		this.displayName = ChatColor.translateAlternateColorCodes('&', displayName);
		this.setEntityType(entityType);
		this.chance = chance;
		this.health = health;
		this.effects = effects;
		this.dropItems = dropItems;
		if(helmet.split(":")[0].equalsIgnoreCase("CUSTOM")) {
			armorHelmet = CustomItemManager.customItems.get(helmet.split(":")[1]).getItem();
		}else {
			armorHelmet = new ItemStack(Material.getMaterial(helmet));
		}
		if(chestplate.split(":")[0].equalsIgnoreCase("CUSTOM")) {
			armorChestplate = CustomItemManager.customItems.get(chestplate.split(":")[1]).getItem();
		}else {
			armorChestplate = new ItemStack(Material.getMaterial(chestplate));
		}
		if(leggings.split(":")[0].equalsIgnoreCase("CUSTOM")) {
			armorLeggings = CustomItemManager.customItems.get(leggings.split(":")[1]).getItem();
		}else {
			armorLeggings = new ItemStack(Material.getMaterial(leggings));
		}
		if(boots.split(":")[0].equalsIgnoreCase("CUSTOM")) {
			armorBoots = CustomItemManager.customItems.get(boots.split(":")[1]).getItem();
		}else {
			armorBoots = new ItemStack(Material.getMaterial(boots));
		}
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public List<String> getDropItems() {
		return dropItems;
	}

	public void setDropItems(List<String> dropItems) {
		this.dropItems = dropItems;
	}

	public List<String> getEffects() {
		return effects;
	}

	public void setEffects(List<String> effects) {
		this.effects = effects;
	}


	public int getChance() {
		return chance;
	}


	public void setChance(int chance) {
		this.chance = chance;
	}


	public String getEntityType() {
		return entityType;
	}


	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public ItemStack getArmorHelmet() {
		return armorHelmet;
	}

	public ItemStack getArmorChestplate() {
		return armorChestplate;
	}

	public ItemStack getArmorLeggings() {
		return armorLeggings;
	}

	public ItemStack getArmorBoots() {
		return armorBoots;
	}
}*/
