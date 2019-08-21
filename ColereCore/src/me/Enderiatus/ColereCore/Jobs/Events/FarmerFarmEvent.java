package me.Enderiatus.ColereCore.Jobs.Events;

import java.util.Random;

import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.Enderiatus.ColereCore.Main;
import me.Enderiatus.ColereCore.Items.CustomItemManager;
import me.Enderiatus.ColereCore.Jobs.Jobs;
import me.Enderiatus.ColereCore.Status.StatusManager;

public class FarmerFarmEvent implements Listener{
	
	Main plugin;
	public FarmerFarmEvent(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void cropBreak(BlockBreakEvent e) {
		if(e.getBlock().getBlockData() instanceof Ageable && StatusManager.PLAYER_STATUS.get(e.getPlayer()).getPlayerJob() == Jobs.FARMER) {
			Ageable age = (Ageable) e.getBlock().getBlockData();
			if(age.getAge() != age.getMaximumAge()) 
				return;
			Jobs.addJobLevelXP(e.getPlayer(), 8);
			int chanceOfItem = new Random().nextInt(1000)+1;
			if(chanceOfItem > StatusManager.PLAYER_STATUS.get(e.getPlayer()).getJobLevel()) 
				return;
			CustomItemManager.dropCustomItem("Farmer", e.getPlayer());
		}
	}

}
