package me.Enderiatus.ColereCore.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Enderiatus.ColereCore.Main;
import me.Enderiatus.ColereCore.Jobs.Jobs;

public class JobsCommand implements CommandExecutor{
	
	Main plugin;
	public JobsCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("meslek"))
			return true;
		Player p = (Player) sender;
		Jobs.openJobsMenu(p);
		return false;
	}

}
