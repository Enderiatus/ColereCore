package me.Enderiatus.ColereCore.Status;

import org.bukkit.Material;

public class Status {

	
	private String yamlName;
	private String fullName;
	private String statuInfo;
	private int defaultValue;
	private int needXPPerLevel;
	private int maxLevel;
	private Material invItem;
	
	public Status(String yamlName, String fullName, int defaultValue, int needXPPerLevel, int maxLevel, String statuInfo, Material invItem) {
		this.yamlName = yamlName;
		this.fullName = fullName;
		this.statuInfo = statuInfo;
		this.defaultValue = defaultValue;
		this.needXPPerLevel = needXPPerLevel;
		this.maxLevel = maxLevel;
		this.invItem = invItem;
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(int defaultValue) {
		this.defaultValue = defaultValue;
	}
	public int getNeedXPPerLevel() {
		return needXPPerLevel;
	}
	public void setNeedXPPerLevel(int needXPPerLevel) {
		this.needXPPerLevel = needXPPerLevel;
	}
	public int getMaxLevel() {
		return maxLevel;
	}
	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}

	public String getYamlName() {
		return yamlName;
	}

	public void setYamlName(String yamlName) {
		this.yamlName = yamlName;
	}

	public String getStatuInfo() {
		return statuInfo;
	}

	public void setStatuInfo(String statuInfo) {
		this.statuInfo = statuInfo;
	}

	public Material getInvItem() {
		return invItem;
	}

	public void setInvItem(Material invItem) {
		this.invItem = invItem;
	} 

} 
