package de.magicced01.myclasses;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MyClasses extends JavaPlugin {

	public static MyClasses plugin;

	public void onEnable()
	{
		plugin = this;
		getCommand("classes").setExecutor(new Command_Classes());
		getCommand("classarena").setExecutor(new Command_Arena());
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Listener_Game(), this);
		pm.registerEvents(new Listener_Scout(), this);
		pm.registerEvents(new Listener_Tank(), this);

	}
	public void onDisable()
	{
		for(String s: GameManager.getPlayers()) {
			Player p = Bukkit.getPlayer(s);
			GameManager.leaveArena(p);
			saveConfig();
		}
		
	}
	

	

}
