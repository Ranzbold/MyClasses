package de.magicced01.myclasses;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Classes implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (!(cs instanceof Player)) {
			cs.sendMessage("Du musst ein Spieler sein!");
			return true;
		}

		Player p = (Player) cs;
		if (args.length == 0) {
			p.sendMessage("Gib /classes list für die Liste aller Klassen ein");
			return true;
		}
		if (!(GameManager.isInArena(p))) {
			p.sendMessage(Messages.NOT_IN_ARENA.description);
			return true;
		}

		if ((args.length == 1)) {
			if (args[0].equals("list")) {
				p.sendMessage(Messages.PREFIX.description + "Aktuell verfügbare Klassen:");
				p.sendMessage("§9Scout");
				p.sendMessage("§9Tank");
				return true;
			}
			String classstring = args[0];
			if (classstring.equals("scout")) {
				p.sendMessage(Messages.CLASS.description + "§5Scout");
				GameManager.chooseClass(p, Classes.SCOUT);
				return true;

			}
			else if (classstring.equals("tank")) {
				p.sendMessage(Messages.CLASS.description + "§5Tank");
				GameManager.chooseClass(p, Classes.TANK);
				return true;

			}


		}

		return false;
	}

}
