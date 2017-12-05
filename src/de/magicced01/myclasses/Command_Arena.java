package de.magicced01.myclasses;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Command_Arena implements CommandExecutor {
	static Plugin pl = MyClasses.plugin;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Nur Spieler können diesen Befehl ausführen!");
			return true;
		}
		Player p = (Player) sender;
		if ((args.length < 1) || (args.length > 2)) {
			return false;
		}
		if (args[0].equalsIgnoreCase("setspawn")) {
			if (args.length == 2) {
				Teams team;
				if (args[1].equalsIgnoreCase("blue")) {
					team = Teams.BLUE;
				}
				else if(args[1].equalsIgnoreCase("red")) {
					team = Teams.RED;
				}
				else {
					return false;
				}
				GameManager.setSpawn(p,team);
				return true;
			}
		} else if (args[0].equalsIgnoreCase("join")) {
			GameManager.joinArena(p);
			return true;
		} else if (args[0].equalsIgnoreCase("leave")) {
			GameManager.leaveArena(p);
			return true;
		}
		return false;

	}

}
