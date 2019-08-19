package me.Enderiatus.ColereCore.Events;

import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import me.Enderiatus.ColereCore.Main;
import me.Enderiatus.ColereCore.Jobs.Jobs;
import me.Enderiatus.ColereCore.Processing.RefineBlockType;
import me.Enderiatus.ColereCore.Status.PlayerStatus;
import me.Enderiatus.ColereCore.Status.StatusManager;

public class PBlockBreak implements Listener {
	
	
	Main plugin;
	public PBlockBreak(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void blockBreak(BlockBreakEvent e) {
		if(e.isCancelled()) 
			return;
		Player p = e.getPlayer();
		if(!(p.getGameMode() == GameMode.SURVIVAL)) 
			return;
		PlayerStatus pS = StatusManager.PLAYER_STATUS.get(p);
		if(pS.getPlayerJob() == Jobs.MINER) {
			Jobs.addJobLevelXP(p, Jobs.getJobBlockMultiplier(e.getBlock().getType()));
		}
		if(!RefineBlockType.contains(e.getBlock().getType().toString()))
			return; 
		e.setDropItems(false);
		Random random = new Random();
		int dropItemAmount = pS.getMiningLuck()+1;
		if(p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
			dropItemAmount += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS)-1;
		}if(pS.getPlayerJob() == Jobs.MINER) {
			int chanceOfMinerLuck = random.nextInt(100)+1;
			if(chanceOfMinerLuck <= pS.getJobsLevel()) {
				dropItemAmount += random.nextInt(5)+1;
			}
		}
		dropItemAmount = random.nextInt(dropItemAmount)+1;
		int ICIRate = random.nextInt(100)+1;
		if(ICIRate <= pS.getRefinedOre()) {
			p.sendMessage("§bMaden Bilgisi §7» Madeni iþlenmiþ olarak çýkardýnýz.");
			ItemStack dropItem = RefineBlockType.getRefineBlockRefinedItem(RefineBlockType.valueOf(e.getBlock().getType().toString()));
			dropItem.setAmount(dropItemAmount);
			e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), dropItem);
			return;
		} 
		ItemStack dropItem = RefineBlockType.getRefineBlockunRefinedItem(RefineBlockType.valueOf(e.getBlock().getType().toString()));
		dropItem.setAmount(dropItemAmount);
		e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), dropItem);
//	ProcessingMenu.openProcessingMenu(p);
	}
	

}
