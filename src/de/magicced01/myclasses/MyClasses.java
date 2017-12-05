package de.magicced01.myclasses;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MyClasses extends JavaPlugin {

	public static MyClasses plugin;

	public void onEnable()
	{
		plugin = this;
		System.out.println("MyClasses gestartet");
		getCommand("classes").setExecutor(new Command_Classes());
		getCommand("classarena").setExecutor(new Command_Arena());
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Listener_Game(), this);
		pm.registerEvents(new Listener_Scout(), this);

	}
	public void onDisable()
	{
		System.out.println("MyClasses gestoppt");
	}
	

	

}
