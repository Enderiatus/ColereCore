package me.Enderiatus.ColereCore.Jobs.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import me.Enderiatus.ColereCore.Main;
import me.Enderiatus.ColereCore.Jobs.Jobs;
import me.Enderiatus.ColereCore.Status.PlayerStatus;
import me.Enderiatus.ColereCore.Status.StatuManager;

public class LumberjackWoodEvent implements Listener{

	Main plugin;
	public LumberjackWoodEvent(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void woodBreak(BlockBreakEvent e) {
		if(e.isCancelled()) 
			return;
		if(!(StatuManager.PLAYER_STATUS.get(e.getPlayer()).getPlayerJob() == Jobs.LUMBERJACK)) 
			return;
		if(!e.getBlock().getType().toString().contains("_LOG")) 
			return;
		Player p = e.getPlayer();
		PlayerStatus pS = StatuManager.PLAYER_STATUS.get(p);
		pS.setJobsXP(pS.getJobsXP()+(3*pS.getJobsLevel()));
		Jobs.checkLevelUP(p);
	}
	
	@EventHandler
	public void woodPlace(BlockPlaceEvent e) {
		if(e.isCancelled()) 
			return;
		if(!(StatuManager.PLAYER_STATUS.get(e.getPlayer()).getPlayerJob() == Jobs.LUMBERJACK)) 
			return;
		if(!e.getBlock().getType().toString().contains("_LOG")) 
			return;
		Player p = e.getPlayer();
		PlayerStatus pS = StatuManager.PLAYER_STATUS.get(p);
		if(pS.getJobsXP()+(3*pS.getJobsLevel()) < 1) 
			return;
		pS.setJobsXP(pS.getJobsXP()-(3*pS.getJobsLevel()));
	}
	
}
