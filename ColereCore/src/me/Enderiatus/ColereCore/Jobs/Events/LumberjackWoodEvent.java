package me.Enderiatus.ColereCore.Jobs.Events;


import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import me.Enderiatus.ColereCore.Main;
import me.Enderiatus.ColereCore.Items.CustomItemManager;
import me.Enderiatus.ColereCore.Jobs.Jobs;
import me.Enderiatus.ColereCore.Status.PlayerStatus;
import me.Enderiatus.ColereCore.Status.StatusManager;

public class LumberjackWoodEvent implements Listener{

	Main plugin;
	public LumberjackWoodEvent(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void woodBreak(BlockBreakEvent e) {
		if(e.isCancelled()) 
			return;
		if(!(StatusManager.PLAYER_STATUS.get(e.getPlayer()).getPlayerJob() == Jobs.LUMBERJACK)) 
			return;
		if(!e.getBlock().getType().toString().contains("_LOG")) 
			return;
		Player p = e.getPlayer();
		PlayerStatus pS = StatusManager.PLAYER_STATUS.get(p);
		pS.setJobXP(pS.getJobXP()+(3*pS.getJobLevel()));
		Jobs.checkLevelUP(p);
	}
	
	@EventHandler
	public void woodPlace(BlockPlaceEvent e) {
		if(e.isCancelled()) 
			return;
		if(!(StatusManager.PLAYER_STATUS.get(e.getPlayer()).getPlayerJob() == Jobs.LUMBERJACK)) 
			return;
		if(!e.getBlock().getType().toString().contains("_LOG")) 
			return;
		Player p = e.getPlayer();
		PlayerStatus pS = StatusManager.PLAYER_STATUS.get(p);
		if(pS.getJobXP()+(3*pS.getJobLevel()) < 1) 
			return;
		pS.setJobXP(pS.getJobXP()-(3*pS.getJobLevel()));
	}
	
	@EventHandler
	public void leafBreak(BlockBreakEvent e) {
		if(!e.getBlock().getType().toString().contains("_LEAVES")) 
			return;
		if(!(StatusManager.PLAYER_STATUS.get(e.getPlayer()).getPlayerJob() == Jobs.LUMBERJACK)) 
			return;
		int chanceOfItem = new Random().nextInt(1000)+1;
		if(chanceOfItem > StatusManager.PLAYER_STATUS.get(e.getPlayer()).getJobLevel()) 
			return;
		CustomItemManager.dropCustomItem("Lumberjack", e.getPlayer());
	}
	
}
