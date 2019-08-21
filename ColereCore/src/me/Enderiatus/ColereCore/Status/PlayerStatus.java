package me.Enderiatus.ColereCore.Status;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.Enderiatus.ColereCore.Main;
import me.Enderiatus.ColereCore.Jobs.Jobs;

enum StatuType {
	evasionChance, criticChance, blockChance, extraSpeed, extraHealth, enchantLuck, extraEnchant, miningLuck, refinedOre, processingRate;
}

public class PlayerStatus {
	
	private String playerName;
	private Jobs playerJob;
	
	// FIGHT
	private int evasionChance;
	private int criticChance;
	private int blockChance;
	
	// COMMON
	
	private int extraSpeed;
	private int extraHealth;
	
	// ENCHANTING
	
	private int enchantLuck;
	private int extraEnchant;
	
	// MINING
	
	private int miningLuck;
	private int refinedOre;
	private int processingRate;
	
	// 
	
	private int leftPoint;
	private int pointXP;
	
	private int jobsLevel;
	private int jobsXP;
	
	
	private File userFile;
	private FileConfiguration userConfigration;
	
	
	public PlayerStatus(String playerName, int evasionChance, int criticChance, int blockChance, int extraSpeed, int extraHealth, int enchantLuck,
			int extraEnchant, int miningLuck, int refinedOre, int processingRate, int leftPoint, int pointXP, String playerJob, int jobsLevel, int jobsXP) {
		this.playerName = playerName;
		this.playerJob = Jobs.valueOf(playerJob);
		this.evasionChance = evasionChance;
		this.criticChance = criticChance;
		this.blockChance = blockChance;
		this.extraSpeed = extraSpeed;
		this.extraHealth = extraHealth;
		this.enchantLuck = enchantLuck;
		this.extraEnchant = extraEnchant;
		this.miningLuck = miningLuck;
		this.refinedOre = refinedOre;
		this.processingRate = processingRate;
		this.leftPoint = leftPoint;
		this.pointXP = pointXP;
		this.jobsLevel = jobsLevel;
		this.jobsXP = jobsXP;
		userFile = new File(Main.getInstance().getDataFolder(), "/playerData/"+playerName+".yml");
		userConfigration = YamlConfiguration.loadConfiguration(userFile);
	}
	
	public int getStatuValue(StatuType statuToGet) {
        switch (statuToGet) {
        case evasionChance:
            return this.evasionChance;
        case blockChance:
          	return this.blockChance;
        case criticChance:
           	return this.criticChance;
        case enchantLuck:
           	return this.enchantLuck;
        case extraEnchant:
           	return this.extraEnchant;
        case extraHealth:
           	return this.extraHealth;
        case extraSpeed:
           	return this.extraSpeed;
        case miningLuck:
          	return this.miningLuck;
        case processingRate:
          	return this.processingRate;
        case refinedOre:
           	return this.refinedOre;
        default:
           	return 0;
        }
    }
	
	public void setStatuValue(StatuType statuToGet, int value) {
		switch (statuToGet) {
		case evasionChance:
			this.evasionChance = value;
		case blockChance:
			this.blockChance = value;;
		case criticChance:
			this.criticChance = value;;
		case enchantLuck:
			this.enchantLuck = value;;
		case extraEnchant:
			this.extraEnchant = value;;
		case extraHealth:
			this.extraHealth = value;;
		case extraSpeed:
			this.extraSpeed = value;;
		case miningLuck:
			this.miningLuck = value;;
		case processingRate:
			this.processingRate = value;;
		case refinedOre:
			this.refinedOre = value;;
		default:
			break;
		}
	}
	
	public int getEvasionChance() {
		return evasionChance;
	}
	public void setEvasion(int evasionChance) {
		this.evasionChance = evasionChance;
	}
	public int getCriticChance() {
		return criticChance;
	}
	public void setCriticChance(int criticChance) {
		this.criticChance = criticChance;
	}
	public int getBlockChance() {
		return blockChance;
	}
	public void setBlockChance(int blockChance) {
		this.blockChance = blockChance;
	}
	public int getExtraSpeed() {
		return extraSpeed;
	}
	public void setExtraSpeed(int extraSpeed) {
		this.extraSpeed = extraSpeed;
	}
	public int getExtraHealth() {
		return extraHealth;
	}
	public void setExtraHealth(int extraHealth) {
		this.extraHealth = extraHealth;
	}
	public int getEnchantLuck() {
		return enchantLuck;
	}
	public void setEnchantLuck(int enchantLuck) {
		this.enchantLuck = enchantLuck;
	}
	public int getExtraEnchant() {
		return extraEnchant;
	}
	public void setExtraEnchant(int extraEnchant) {
		this.extraEnchant = extraEnchant;
	}
	public int getMiningLuck() {
		return miningLuck;
	}
	public void setMiningLuck(int miningLuck) {
		this.miningLuck = miningLuck;
	}
	public int getRefinedOre() {
		return refinedOre;
	}
	public void setRefinedOre(int refinedOre) {
		this.refinedOre = refinedOre;
	}
	public int getProcessingRate() {
		return processingRate;
	}
	public void setProcessingRate(int processingRate) {
		this.processingRate = processingRate;
	}


	public int getLeftPoint() {
		return leftPoint;
	}


	public void setLeftPoint(int leftPoint) {
		this.leftPoint = leftPoint;
	}


	public File getUserFile() {
		return userFile;
	}


	public void setUserFile(File userFile) {
		this.userFile = userFile;
	}


	public FileConfiguration getUserConfigration() {
		return userConfigration;
	}


	public void setUserConfigration(FileConfiguration userConfigration) {
		this.userConfigration = userConfigration;
	}


	public String getPlayerName() {
		return playerName;
	}


	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


	public int getPointXP() {
		return pointXP;
	}


	public void setPointXP(int pointXP) {
		this.pointXP = pointXP;
	}


	public int getJobLevel() {
		return jobsLevel;
	}


	public void setJobLevel(int jobsLevel) {
		this.jobsLevel = jobsLevel;
	}


	public int getJobXP() {
		return jobsXP;
	}


	public void setJobXP(int jobsXP) {
		this.jobsXP = jobsXP;
	}


	public Jobs getPlayerJob() {
		return playerJob;
	}
	
	public void setPlayerJob(Jobs playerJob) {
		this.playerJob = playerJob;
	}

}
