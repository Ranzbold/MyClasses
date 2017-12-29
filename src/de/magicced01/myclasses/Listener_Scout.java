package de.magicced01.myclasses;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class Listener_Scout implements Listener {

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if ((e.getDamager() instanceof Arrow)) {
			Arrow arrow = (Arrow) e.getDamager();
			if (arrow.getShooter() instanceof Player) {
				Player shooter = (Player) arrow.getShooter();
				if ((e.getEntity() instanceof Player) && (GameManager.PlayerClassCache.get(shooter.getName()) == "scout")) {
					Player victim = (Player)e.getEntity();
					if(shooter.getLocation().distance(victim.getLocation()) >= 25)
					{
						Teams victimteam = GameManager.getTeam(victim);
						Teams shooterteam = GameManager.getTeam(shooter);
						if(!(shooterteam == victimteam)) {
							arrow.setKnockbackStrength(0);
							shooter.playSound(shooter.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
							shooter.sendMessage("Du hast " + victim.getName() + " mit einem Kopfschuss getötet!");
							victim.sendMessage("Du wurdest von " + shooter.getName() + " mit einem Kopfschuss getötet!");
							GameManager.allocateClass(victim, GameManager.PlayerClassCache.get(victim.getName()));
							GameManager.calculateDeath(shooter, victim);
							victim.setHealth(victim.getMaxHealth());
							e.setDamage(0);
							Location spawn = GameManager.getSpawn(GameManager.getTeam(victim));
							victim.setVelocity(new Vector(0,0,0));
							victim.teleport(spawn);
						}
					}
					else
					{
						int duration = 0;

						for(PotionEffect effect : shooter.getActivePotionEffects())
						{
							if(effect.getType().equals(PotionEffectType.SPEED))
							{
								duration = effect.getDuration();
							}
						}
						shooter.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 45 + duration, 1), true);

					}

				}
			}
		}
	}
		
}
