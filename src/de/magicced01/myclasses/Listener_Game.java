package de.magicced01.myclasses;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import net.minecraft.server.v1_12_R1.PacketPlayInClientCommand;
import net.minecraft.server.v1_12_R1.PacketPlayInClientCommand.EnumClientCommand;

public class Listener_Game implements Listener {

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		Player killed = e.getEntity();
		e.setKeepInventory(true);
		if (GameManager.isInArena(e.getEntity())) {
			if (e.getEntity().getKiller() instanceof Player) {
				Player killer = (Player) e.getEntity().getKiller();
				GameManager.calculateDeath(killer, killed);

			} else {
				StatsManager.addDeaths(killed, 1);
				ScoreBoardUtils.updateScoreboard(killed);

			}
			if (e.getEntity().isDead()) {
				((CraftPlayer) e.getEntity()).getHandle().playerConnection
						.a(new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN));
			}
		}

	}

	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e) {
		if (GameManager.isInArena((Player) e.getEntity())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e) {
		if (GameManager.isInArena(e.getPlayer())) {
			e.setCancelled(true);
		}

	}

	@EventHandler
	public void onItemPickUp(EntityPickupItemEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (GameManager.isInArena(p)) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (GameManager.isInArena(e.getPlayer())) {
			e.getPlayer().sendMessage(Messages.NO_BREAK.description);
			e.setCancelled(true);
		}

	}

	public void onBlockPlace(BlockPlaceEvent e) {
		if (GameManager.isInArena(e.getPlayer())) {
			e.getPlayer().sendMessage(Messages.NO_PLACE.description);
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();

		if ((GameManager.isInArena(p)) && (!(GameManager.PlayerClassCache.get(p.getName()) == null))) {
			String pclass = GameManager.PlayerClassCache.get(p.getName());
			GameManager.allocateClass(p, pclass);
			Location spawn = GameManager.getSpawn(GameManager.getTeam(e.getPlayer()));
			e.setRespawnLocation(spawn);
		}
	}

}
