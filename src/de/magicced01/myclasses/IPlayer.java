package de.magicced01.myclasses;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class IPlayer {
	
	Player player;
	ItemStack[] inv;
	ItemStack[] armorcontents;
	Location loc;
	int xpLevel;
	float xpvalue; 
	int foodlevel; 
	int fireticks;
	Collection<PotionEffect> effects;
	GameMode gamemode;
	
	public IPlayer(Player p) {
		this.inv = p.getInventory().getContents();
		this.armorcontents = p.getInventory().getArmorContents();
		this.loc = p.getLocation();
		this.xpLevel = p.getLevel();
		this.xpvalue = p.getExp();
		this.foodlevel = p.getFoodLevel();
		this.fireticks = p.getFireTicks();
		this.effects = p.getActivePotionEffects();
		this.gamemode = p.getGameMode();
		this.player = p;
	}
	
	public void restoreData() {
		Player p = player;
		p.getInventory().clear();
		p.getInventory().setContents(inv);
		p.getInventory().setArmorContents(armorcontents);
		p.setFoodLevel(foodlevel);
		p.setLevel(xpLevel);
		p.setExp(xpvalue);
		p.setFireTicks(fireticks);
		p.addPotionEffects(effects);
		p.setGameMode(gamemode);
		p.teleport(loc);
		
	}

	

}
