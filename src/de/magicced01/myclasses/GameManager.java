package de.magicced01.myclasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;


public class GameManager {
	public static HashMap<String, String> PlayerClassCache = new HashMap<String, String>();
	public static HashMap<String, ItemStack[]> InventoryCache = new HashMap<String, ItemStack[]>();
	public static HashMap<String, ItemStack[]> ArmorCache = new HashMap<String, ItemStack[]>();
	public static HashMap<String, Location> LocationCache = new HashMap<String, Location>();
	public static List<String> Players = new ArrayList<String>();
	public static List<String> BluePlayers = new ArrayList<String>();
	public static List<String> RedPlayers = new ArrayList<String>();
	static Plugin pl = MyClasses.plugin;

	public static void setSpawn(Player p, Teams team) {
		if (!(p.hasPermission("myclasses.setspawn"))) {
			p.sendMessage(Messages.NOPERM.description);

		} else {
			if (team == Teams.BLUE) {
				String world = p.getWorld().getName();
				double x = p.getLocation().getX();
				double y = p.getLocation().getY();
				double z = p.getLocation().getZ();
				double yaw = p.getLocation().getYaw();
				double pitch = p.getLocation().getPitch();

				FileConfiguration cfg = pl.getConfig();
				cfg.set("worldblue", world);
				cfg.set("posxblue", x);
				cfg.set("posyblue", y);
				cfg.set("poszblue", z);
				cfg.set("posyawblue", yaw);
				cfg.set("pospitchblue", pitch);
				pl.saveConfig();
				p.sendMessage(Messages.SPAWN_SET.description);
			} else if (team == Teams.RED) {
				String world = p.getWorld().getName();
				double x = p.getLocation().getX();
				double y = p.getLocation().getY();
				double z = p.getLocation().getZ();
				double yaw = p.getLocation().getYaw();
				double pitch = p.getLocation().getPitch();

				FileConfiguration cfg = pl.getConfig();
				cfg.set("worldred", world);
				cfg.set("posxred", x);
				cfg.set("posyred", y);
				cfg.set("poszred", z);
				cfg.set("posyawred", yaw);
				cfg.set("pospitchred", pitch);
				pl.saveConfig();
				p.sendMessage(Messages.SPAWN_SET.description);
			}
		}

	}

	public static Location getSpawn(Teams team) {

		if (team == Teams.BLUE) {
			FileConfiguration cfg = pl.getConfig();
			String worldname = cfg.getString("worldblue");
			World world = Bukkit.getWorld(worldname);
			double x = cfg.getDouble("posxblue");
			double y = cfg.getDouble("posyblue");
			double z = cfg.getDouble("poszblue");
			double yaw = cfg.getDouble("posyawblue");
			double pitch = cfg.getDouble("pospitchblue");
			Location loc = new Location(world, x, y, z);
			loc.setPitch((float) pitch);
			loc.setYaw((float) yaw);
			return loc;
		} else {
			FileConfiguration cfg = pl.getConfig();
			String worldname = cfg.getString("worldred");
			World world = Bukkit.getWorld(worldname);
			double x = cfg.getDouble("posxred");
			double y = cfg.getDouble("posyred");
			double z = cfg.getDouble("poszred");
			double yaw = cfg.getDouble("posyawred");
			double pitch = cfg.getDouble("pospitchred");
			Location loc = new Location(world, x, y, z);
			loc.setPitch((float) pitch);
			loc.setYaw((float) yaw);
			return loc;
		}

	}

	public static boolean isInArena(Player p) {
		if (!(GameManager.Players.contains(p.getName()))) {
			return false;
		} else {
			return true;
		}
	}

	public static void joinArena(Player p) {
		if (!p.hasPermission("myclasses.join")) {
			p.sendMessage(Messages.NOPERM.description);
		} else {
			if (!(GameManager.Players.contains(p.getName()))) {
				GameManager.Players.add(p.getName());
				saveData(p);
				Teams team = allocateTeam(p);
				setTeam(p, team);
				p.teleport(getSpawn(team));
				chooseClass(p, Classes.SCOUT);
				p.sendMessage(Messages.ARENAJOIN.description);
				for (Player online : Bukkit.getOnlinePlayers()) {
					if(GameManager.isInArena(p)) {
						ScoreBoardUtils.updateScoreboard(online);

					}
				}
			} else {
				p.sendMessage(Messages.ARENA_JOINED.description);
			}
		}

	}

	public static void leaveArena(Player p) {
		if (!p.hasPermission("myclasses.leave")) {
			p.sendMessage(Messages.NOPERM.description);
		} else {
			if (GameManager.Players.contains(p.getName())) {
				GameManager.Players.remove(p.getName());
				PlayerClassCache.remove(p.getName());
				p.sendMessage(Messages.ARENALEAVE.description);
				setTeam(p, Teams.NONE);
				loadData(p);
				for (Player online : Bukkit.getOnlinePlayers()) {
					ScoreBoardUtils.updateScoreboard(online);
				}
			} else {
				p.sendMessage(Messages.NOT_IN_ARENA.description);
			}
		}

	}

	public static void saveData(Player p) {
		ItemStack[] inv = p.getInventory().getContents();
		ItemStack[] armorcontents = p.getInventory().getArmorContents();
		Location loc = p.getLocation();
		if (InventoryCache.containsKey(p.getName())) {
			InventoryCache.remove(p.getName());
		}
		InventoryCache.put(p.getName(), inv);
		if (ArmorCache.containsKey(p.getName())) {
			ArmorCache.remove(p.getName());
		}
		ArmorCache.put(p.getName(), armorcontents);
		if (LocationCache.containsKey(p.getName())) {
			LocationCache.remove(p.getName());
		}
		LocationCache.put(p.getName(), loc);
	}

	public static void loadData(Player p) {
		ItemStack[] inv = InventoryCache.get(p.getName());
		ItemStack[] armorcontents = ArmorCache.get(p.getName());
		Location loc = LocationCache.get(p.getName());
		p.getInventory().clear();
		p.getInventory().setContents(inv);
		p.getInventory().setArmorContents(armorcontents);
		p.teleport(loc);
	}

	public static void chooseClass(Player p, Classes cl) {
		String clname = cl.getClassName();
		allocateClass(p, clname);

	}

	public static Teams getTeam(Player p) {
		if (BluePlayers.contains(p.getName())) {
			return Teams.BLUE;
		} else if (RedPlayers.contains(p.getName())) {
			return Teams.RED;
		} else {
			return Teams.NONE;
		}
	}

	public static void setTeam(Player p, Teams team) {
		if (team == Teams.BLUE) {
			if (BluePlayers.contains(p.getName())) {
				BluePlayers.remove(p.getName());
			}
			BluePlayers.add(p.getName());
			p.sendMessage(Messages.BLUE.description);
			initTeamColor(p);
		} else if (team == Teams.RED) {
			if (RedPlayers.contains(p.getName())) {
				RedPlayers.remove(p.getName());
			}
			RedPlayers.add(p.getName());
			p.sendMessage(Messages.RED.description);
			initTeamColor(p);
		} else {
			removeTeam(p);
		}
	}

	public static void removeTeam(Player p) {
		if (BluePlayers.contains(p.getName())) {
			BluePlayers.remove(p.getName());
		} else if (RedPlayers.contains(p.getName())) {
			RedPlayers.remove(p.getName());
		}
		p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
	}

	public static Teams allocateTeam(Player p) {
		if (BluePlayers.size() > RedPlayers.size()) {
			return Teams.RED;
		} else if (BluePlayers.size() == RedPlayers.size()) {
			return Teams.BLUE;
		} else if (RedPlayers.size() > BluePlayers.size()) {
			return Teams.BLUE;
		} else {
			return Teams.RED;
		}

	}

	public static void allocateClass(Player p, String classname) {
		if (GameManager.PlayerClassCache.containsKey(p.getName())) {
			GameManager.PlayerClassCache.remove(p.getName());
		}
		GameManager.PlayerClassCache.put(p.getName(), classname);

		if (classname == "scout") {
			Equip_Utils.scout(p);
		}
		if (classname == "tank") {
			Equip_Utils.tank(p);
		}

	}

	public static void calculateDeath(Player killer, Player victim) {
		StatsManager.addKills(killer, 1);
		StatsManager.addDeaths(victim, 1);
		ScoreBoardUtils.updateScoreboard(killer);
		ScoreBoardUtils.updateScoreboard(victim);
	}

	public static List<String> getPlayers() {
		return Players;


	}

	public static List<String> getPlayersofTeam(Teams team) {
		if (team == Teams.BLUE) {
			
			return BluePlayers;
		} else if (team == Teams.RED) {
			
			return RedPlayers;
		} else {
			return null;
		}

	}

	public static void initTeamColor(Player p) {
	}

}
