package me.BellewaerdeCraft.Commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class statsshow implements CommandExecutor {
	
	public Scoreboard board;
	public File statistieken;
	public FileConfiguration stats;
	
	public statsshow(Scoreboard board, File statistieken, FileConfiguration stats) {
		
		this.board = board;
		this.statistieken = statistieken;
		this.stats = stats;
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(args.length >= 1) {
			
			Player player = Bukkit.getPlayer(args[0]);
			board = Bukkit.getScoreboardManager().getNewScoreboard();
			
			Objective objective = board.registerNewObjective("Test", player.getName());
			objective.setDisplayName(ChatColor.UNDERLINE.BOLD.AQUA + "Parkour Stats");
			objective.setDisplaySlot(DisplaySlot.SIDEBAR);

			Score score = objective.getScore(ChatColor.GOLD + "Iceland Wins");
			score.setScore(stats.getInt(player + ".IceLandWins"));

			Score score2 = objective.getScore(ChatColor.GOLD + "Iceland Fails");
			score2.setScore(stats.getInt(player + ".IceLandFails"));

			Score score3 = objective.getScore(ChatColor.GOLD + "Aztec Wins");
			score3.setScore(stats.getInt(player + ".AztecWins"));

			Score score4 = objective.getScore(ChatColor.GOLD + "Aztec Fails");
			score4.setScore(stats.getInt(player + ".AztecFails"));
			player.setScoreboard(board);
			
		}
		
		return true;
		
	}
	
}
