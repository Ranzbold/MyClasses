package de.magicced01.myclasses;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class StatsManager {

	static String CP = "playerstats";
	static Plugin PL = MyClasses.plugin;
	private static HashMap<String,Integer>Killstreaks = new HashMap<String,Integer>();
	

	//Add, Set ,Get Kills
	public static void addKills(Player p, int count){
		initializePlayerConfig(p);
		String playerid = p.getUniqueId().toString();
		int kills = PL.getConfig().getInt(CP+"."+playerid+".kills");
		kills+=count;
		PL.getConfig().set(CP+"."+playerid+".kills",kills);
		PL.saveConfig();
		raiseKillstreak(p,1);
	}
	
	public static void setKills(Player p, int count){
		initializePlayerConfig(p);
		String playerid = p.getUniqueId().toString();
		PL.getConfig().set(CP+"."+playerid+".kills",count);
		PL.saveConfig();
	}

	public static int getKills(Player p){
		initializePlayerConfig(p);
		String playerid = p.getUniqueId().toString();
		return PL.getConfig().getInt(CP+"."+playerid+".kills");
	}
	
	
	//Add, Set, Get Deaths
	public static void addDeaths(Player p, int count){
		initializePlayerConfig(p);
		String playerid = p.getUniqueId().toString();
		int deaths = PL.getConfig().getInt(CP+"."+playerid+".deaths");
		deaths+=count;
		PL.getConfig().set(CP+"."+playerid+".deaths",deaths);
		PL.saveConfig();
		resetKillstreak(p);
	}
	
	public static void setDeaths(Player p, int count){
		String playerid = p.getUniqueId().toString();
		initializePlayerConfig(p);
		PL.getConfig().set(CP+"."+playerid+".deaths",count);
		PL.saveConfig();
	}

	public static int getDeaths(Player p){
		initializePlayerConfig(p);
		String playerid = p.getUniqueId().toString();
		return PL.getConfig().getInt(CP+"."+playerid+".deaths");
	}
	
	
	static //Initialization of Player's config section and killstreak entry
	void initializePlayerConfig(Player p){
		String playerid = p.getUniqueId().toString();
		if (!PL.getConfig().contains(CP+"."+playerid)){
			PL.getConfig().set(CP+"."+playerid+".kills",0);
			PL.getConfig().set(CP+"."+playerid+".deaths",0);
		}
	}
	
	public static void initializePlayerKillstreaks(Player p){
		String playerid = p.getUniqueId().toString();
		if (!Killstreaks.containsKey(playerid)){
			Killstreaks.put(playerid, 0);
		}
	}
	
	//Raise,Reset,Get Killstreaks
	public static void raiseKillstreak(Player p, int count){
		String playerid = p.getUniqueId().toString();
		initializePlayerKillstreaks(p);
		int killstreak = Killstreaks.get(playerid);
		killstreak+=count;
		Killstreaks.put(playerid, killstreak);
	}
	
	public static void resetKillstreak(Player p){
		initializePlayerKillstreaks(p);
		String playerid = p.getUniqueId().toString();
		Killstreaks.put(playerid, 0);
	}
	
	public static int getKillstreak(Player p){
		String playerid = p.getUniqueId().toString();
		initializePlayerKillstreaks(p);
		return Killstreaks.get(playerid);
	}
}