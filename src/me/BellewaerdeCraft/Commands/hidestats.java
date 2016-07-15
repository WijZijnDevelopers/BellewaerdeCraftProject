package me.BellewaerdeCraft.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class hidestats implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(args.length >= 1) {
			
			Player player = Bukkit.getPlayer(args[0]);
			player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
		
		}
		
		return true;
		
	}
	
}
