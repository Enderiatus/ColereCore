package me.Enderiatus.ColereCore.Jobs.Events;

import java.util.Optional;
import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import io.lumine.xikage.mythicmobs.drops.DropMetadata;
import io.lumine.xikage.mythicmobs.drops.DropTable;
import io.lumine.xikage.mythicmobs.drops.LootBag;
import io.lumine.xikage.mythicmobs.mobs.GenericCaster;
import me.Enderiatus.ColereCore.Main;
import me.Enderiatus.ColereCore.Jobs.Jobs;
import me.Enderiatus.ColereCore.Status.StatusManager;

public class MMDeath implements Listener {
	
	Main plugin;
	public MMDeath(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void mmDeathEvent(MythicMobDeathEvent e) {
		Player p = (Player) e.getKiller();
		if(!(StatusManager.PLAYER_STATUS.get(p).getPlayerJob() == Jobs.HUNTER)) 
			return;
		Jobs.addJobLevelXP(p, 5);
		if(e.getMobType().getDrops().size() < 1) 
			return;
		int randomChance = new Random().nextInt(1000)+1;
		if(randomChance <= StatusManager.PLAYER_STATUS.get(e.getKiller()).getJobLevel()) {
			Optional<DropTable> maybeDrops = MythicMobs.inst().getDropManager().getDropTable(e.getMobType().getDrops()
					.get(new Random().nextInt(e.getMobType().getDrops().size())+1));
			if(maybeDrops.isPresent()) {
				DropTable dt = maybeDrops.get();
				AbstractPlayer target = BukkitAdapter.adapt(p);
				DropMetadata meta = new DropMetadata(new GenericCaster(target), target);
				if (dt.hasDrops()) {
					LootBag loot = dt.generate(meta);
					loot.drop(target.getLocation());
					target.sendMessage("§cMeslek ödülü olarak ekstra eþya kazandýnýz..");
				}
			}
		}
		
	}
	
	
	@EventHandler
	public void mobDeathEvent(EntityDeathEvent e) {
		if(!(e.getEntity().getKiller() instanceof Player)) 
			return;
		Player p = e.getEntity().getKiller();
		if(!(StatusManager.PLAYER_STATUS.get(p).getPlayerJob() == Jobs.HUNTER)) 
			return;
		Jobs.addJobLevelXP(p, Jobs.getJobMobMultiplier(e.getEntityType()));
	}
}
