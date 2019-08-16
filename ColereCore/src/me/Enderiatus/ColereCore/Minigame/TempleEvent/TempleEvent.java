package me.Enderiatus.ColereCore.Minigame.TempleEvent;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import me.Enderiatus.ColereCore.Main;

public class TempleEvent {
	
	public static TempleStatus templeStatus = TempleStatus.STOP;
	public static HashMap<Player, BossBar> barList = new HashMap<Player, BossBar>();
	private static int sc;
	protected static double bbP = 1;

	public static void startEvent() {
		if(templeStatus == TempleStatus.START) 
			return;
		templeStatus = TempleStatus.START;
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			createTempleBossBar(p);
		}
		sc = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
			@Override
			public void run() {
				for(Player p : barList.keySet()) {
					barList.get(p).setProgress(bbP);
				}
				bbP = bbP-(1.11/100);
				if(bbP < 0) {
					Bukkit.getScheduler().cancelTask(sc);
					stopEvent();
				}
			}
		}, 120L, 120L);
		
	}
	
	public static void stopEvent() {
		templeStatus = TempleStatus.STOP;
		for(Player p : barList.keySet()) {
			barList.get(p).removePlayer(p);
			p.getPlayer().sendTitle("§a§lTapýnak kapýsý onarýldý!", "§7§oDýþarýda kalan yaratýklar toplanýyor.", 10, 80, 10);
		}
		barList.clear();
		bbP = 1;
	}
	
	public static void createTempleBossBar(Player p) {
		BossBar bb = Bukkit.createBossBar("§6TAPINAK DURUMU", BarColor.GREEN, BarStyle.SOLID);
		bb.addPlayer(p);
		bb.setProgress(1);
		if(!barList.containsKey(p)) {
			barList.put(p, bb);
		}
		p.getPlayer().sendTitle("§c§lTapýnak kapýsý yýkýldý!", "§6§oEtrafta tapýnaktan kaçan yaratýklar dolanýyor..", 10, 80, 10);
		p.getPlayer().playSound(p.getLocation(), Sound.AMBIENT_CAVE, 2, 1);
	}
	
	public static void startTempleTimer() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
			@Override
			public void run() {
				World world = Bukkit.getServer().getWorld("world");
				if(world.getTime() == 13300) {
					int chance = new Random().nextInt(100)+1;
					if(chance < 80) {
						startEvent();
					}
				}
			}
		}, 1, 1);
	}
}
