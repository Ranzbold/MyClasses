package de.magicced01.myclasses;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class ScoreBoardUtils {

	public static ScoreboardManager manager = Bukkit.getScoreboardManager();
	public static Scoreboard board = manager.getNewScoreboard();
	public static HashMap<String, Scoreboard> boards = new HashMap<>();


	public static void removeScoreboard(Player p) {
		boards.remove(p.getName());
		for(Player online: Bukkit.getOnlinePlayers()) {
			ScoreBoardUtils.updateScoreboard(online);
		}
		
		p.setScoreboard(manager.getNewScoreboard());
	}

	public static void updateScoreboard(Player p) {
		// TODO : Implement Hashmap to create a new Objective for each Player(Bookmark
		// in Chrome)

		// team init

		if (GameManager.isInArena(p)) {
			if (!(boards.containsKey(p.getName()))) {
				// if player is in arena and doesnt yet have a scoreboard assigned

				Scoreboard boardlocal = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
				boardlocal.registerNewObjective("statistik", "dummy");
				Team teamred = boardlocal.registerNewTeam("red");
				Team teamblue = boardlocal.registerNewTeam("blue");
				teamblue.setPrefix("§9");
				teamblue.setAllowFriendlyFire(false);
				teamblue.setCanSeeFriendlyInvisibles(true);
				teamred.setPrefix("§c");
				teamred.setAllowFriendlyFire(false);
				teamred.setCanSeeFriendlyInvisibles(true);
				boards.put(p.getName(), boardlocal);
			}
			

			Scoreboard boardplayer = boards.get(p.getName());
			Team red = boardplayer.getTeam("red");
			Team blue = boardplayer.getTeam("blue");
			for(String playername: blue.getEntries()) {
				Player player = Bukkit.getPlayer(playername);
				if(!(GameManager.getTeam(player) == Teams.BLUE)) {
					blue.removeEntry(playername);
				}
			}
			for(String playername: red.getEntries()) {
				Player player = Bukkit.getPlayer(playername);
				if(!(GameManager.getTeam(player) == Teams.RED)) {
					red.removeEntry(playername);
				}
			}
			
			for(String playername : GameManager.getPlayersofTeam(Teams.RED)) {
				if(!(red.hasEntry(playername))) {
					red.addEntry(playername);
				}
			}
			for(String playername : GameManager.getPlayersofTeam(Teams.BLUE)) {
				if(!(blue.hasEntry(playername))) {
					blue.addEntry(playername);
				}
			}
			Objective objectiveplayer = boardplayer.getObjective("statistik");
			objectiveplayer.setDisplaySlot(DisplaySlot.SIDEBAR);
			objectiveplayer.setDisplayName("§3 Statistik " + p.getName());
			int kills = StatsManager.getKills(p);
			int deaths = StatsManager.getDeaths(p);
			int killstreak = StatsManager.getKillstreak(p);
			Score killsdisplay = objectiveplayer.getScore("Kills: ");
			killsdisplay.setScore(kills);
			Score deathsdisplay = objectiveplayer.getScore("Tode: ");
			deathsdisplay.setScore(deaths);
			Score killstreakdisplay = objectiveplayer.getScore("Killstreak: ");
			killstreakdisplay.setScore(killstreak);
			
			boards.remove(p.getName());
			boards.put(p.getName(), boardplayer);

			p.setScoreboard(boardplayer);
		}
	}

}
