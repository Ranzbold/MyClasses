package de.magicced01.myclasses;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ArenaJoinEvent extends Event {
	private final Player player;

	public ArenaJoinEvent(Player player) {
		this.player = player;
	}
    private static final HandlerList HANDLERS = new HandlerList();

	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
        return HANDLERS;
	}
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
    public Player getPlayer() {
    	return this.player;
    }

}
