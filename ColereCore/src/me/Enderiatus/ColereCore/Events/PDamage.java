package me.Enderiatus.ColereCore.Events;

import java.util.Random;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.Enderiatus.ColereCore.Main;
import me.Enderiatus.ColereCore.Status.StatuManager;

public class PDamage implements Listener{
	
	Main plugin;
	public PDamage(Main plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void entityDamage(EntityDamageByEntityEvent e) {
		if(!(e.getEntity() instanceof Player || e.getDamager() instanceof Player)) 
			return;
		LivingEntity entity = (LivingEntity) e.getEntity();
		e.getDamager().sendMessage("Max health: "+ entity.getMaxHealth()+" "+entity.getHealth());
		final Random generator = new Random();
		if(e.getEntity() instanceof Player) {
			Player victim = (Player) e.getEntity();
			if(e.getDamager() instanceof Player) {
				Player attacker = (Player) e.getDamager();
				final int blockRandomChance = generator.nextInt(100) + 1;
				if(blockRandomChance <= StatuManager.PLAYER_STATUS.get(victim).getBlockChance()) {
		        	e.setDamage(0);
		        	victim.sendMessage("§cSavaþ Bilgisi §7» §eHasarý blokladýnýz!");
		        	return;
		        }
				final int criticRandomChance = generator.nextInt(100) + 1;
				if(criticRandomChance <= StatuManager.PLAYER_STATUS.get(attacker).getCriticChance()) {
					attacker.sendMessage("§cSavaþ Bilgisi §7» §eKritik hasar!");
					e.setDamage(e.getDamage()*2);
					return;
				}
			}else if(e.getDamager() instanceof Arrow) {
				final int evasionRandomChance = generator.nextInt(100)+1;
		        if(evasionRandomChance <= StatuManager.PLAYER_STATUS.get(victim).getEvasionChance()) {
		        	victim.sendMessage("§cSavaþ Bilgisi §7» §eOktan kaçýndýnýz!");
		        	e.setDamage(0);
		        }
			}
		}
	}

}
