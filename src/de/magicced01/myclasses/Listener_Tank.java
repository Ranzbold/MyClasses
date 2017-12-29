package de.magicced01.myclasses;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class Listener_Tank implements Listener {
	int cooldown = 10;
	public static HashMap<String, Long> cooldownmap = new HashMap<String, Long>();

	@EventHandler
	public void onPickRightClick(PlayerInteractEvent event) {
		if (event.hasItem()) {
			if ((event.getItem().getType().equals(Material.GOLD_PICKAXE))) {
				if (GameManager.PlayerClassCache.get(event.getPlayer().getName()) == "tank") {
					long time = System.currentTimeMillis();
					Player p = event.getPlayer();
					if(!(cooldownmap.containsKey(p.getName()))) {
						p.setVelocity(p.getEyeLocation().getDirection().multiply(new Vector(3, 0, 3)));
						p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 100, 2), true);
						p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 75, 2), true);

						cooldownmap.put(p.getName(), time);
					}
					long difference = (time - cooldownmap.get(p.getName()));
					if(difference > 15000) {
						p.setVelocity(p.getEyeLocation().getDirection().multiply(new Vector(3, 0, 3)));
						cooldownmap.put(p.getName(), time);
						p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 100, 2), true);
						p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 75, 2), true);
					}
					else {
						int seconds = (int) ((15000-difference) / 1000);
						p.sendMessage(Messages.PREFIX.description + "Du hast noch Cooldown: " + seconds + " Sekunden");
					} 


				}
			}
		}

	}

}
