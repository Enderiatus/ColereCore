package me.Enderiatus.ColereCore.Jobs;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

public class FishingExtras {
	
	public static ArrayList<FishingExtras> fishExtra = new ArrayList<FishingExtras>();

	private ItemStack extraItem;
	private int maxRange;
	private int chance;
	
	public FishingExtras() {
		
	}

	public ItemStack getExtraItem() {
		return extraItem;
	}

	public void setExtraItem(ItemStack extraItem) {
		this.extraItem = extraItem;
	}

	public int getMaxRange() {
		return maxRange;
	}

	public void setMaxRange(int maxRange) {
		this.maxRange = maxRange;
	}

	public int getChance() {
		return chance;
	}

	public void setChance(int chance) {
		this.chance = chance;
	}
}
