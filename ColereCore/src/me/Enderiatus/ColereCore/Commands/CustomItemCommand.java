package me.Enderiatus.ColereCore.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Enderiatus.ColereCore.Main;
import me.Enderiatus.ColereCore.Items.CustomItemManager;

public class CustomItemCommand implements CommandExecutor{
	
	Main plugin;
	public CustomItemCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("ci"))
			return true;
		if(!sender.isOp())
			return true;
		Player p = (Player) sender;
		if(args.length < 1) {
			p.sendMessage("�6Kullan�m bi�imleri:");
			p.sendMessage("     �7- �eliste");
			p.sendMessage("     �7- �ever <liste ad�>");
			p.sendMessage("     �7- �ebilgi <liste ad�>");
			return true;
		}
		if(args[0].equalsIgnoreCase("liste")) {
			p.sendMessage("�9�lMevcut �zel e�yalar�n listesi:");
			for(String ciKey : CustomItemManager.customItems.keySet()) {
				p.sendMessage("E�ya: "+ciKey);
			}
			return true;
		}else if(args[0].equalsIgnoreCase("ver")) {
			p.getInventory().addItem(CustomItemManager.customItems.get(args[1]).getItem());
		}
		return false;
	}

}
