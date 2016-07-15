package me.BellewaerdeCraft;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	File statistieken;
	FileConfiguration stats;
	File config;
	FileConfiguration con;
	
	public void onEnable(){

		statistieken = new File(getDataFolder(), "stats.yml");
		stats = YamlConfiguration.loadConfiguration(statistieken);
		config = new File(getDataFolder(), "config.yml");
		con = YamlConfiguration.loadConfiguration(config);
		
	}
	
	public void onDisable(){

	}
	
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		if(cmd.getName().equalsIgnoreCase("statistieken")) {
			
			if(args.length >= 1) {
				
				if(args[0].toLowerCase().equalsIgnoreCase("parkour")) {
					
					Player p = (Player) sender;
					
					sender.sendMessage(ChatColor.AQUA + "Jou Statistieken voor Parkour");
					if(stats.getInt(p.getName() + ".IceLandWins") >= 1) {
						sender.sendMessage(ChatColor.GOLD + "Wins in Ice Land: " + ChatColor.AQUA + stats.getInt(p.getName() + ".IceLandWins"));
					} else {
						sender.sendMessage(ChatColor.GOLD + "Wins in Ice Land: " + ChatColor.AQUA + "0");	
					}
					if(stats.getInt(p.getName() + ".IceLandFails") >= 1) {
						sender.sendMessage(ChatColor.GOLD + "Fails in Ice Land: " + ChatColor.AQUA + stats.getInt(p.getName() + ".IceLandFails"));
					} else {
						sender.sendMessage(ChatColor.GOLD + "Fails in Ice Land: " + ChatColor.AQUA + "0");	
					}
					if(stats.getInt(p.getName() + ".AztecWins") >= 1) {
						sender.sendMessage(ChatColor.GOLD + "Wins in Aztec: " + ChatColor.AQUA + stats.getInt(p.getName() + ".AztecWins"));
					} else {
						sender.sendMessage(ChatColor.GOLD + "Wins in Aztec: " + ChatColor.AQUA + "0");	
					}
					if(stats.getInt(p.getName() + ".AztecFails") >= 1) {
						sender.sendMessage(ChatColor.GOLD + "Fails in Aztec: " + ChatColor.AQUA + stats.getInt(p.getName() + ".AztecFails"));
					} else {
						sender.sendMessage(ChatColor.GOLD + "Fails in Aztec: " + ChatColor.AQUA + "0");	
					}
					
				}
				
			} else {
				
				
				sender.sendMessage(ChatColor.RED + "Bedoel je /statistieken parkour ?");
				
			}
			
		} else if (cmd.getName().equalsIgnoreCase("addwin")) {
			
			if(sender.hasPermission("Statistieken.add")) {
			
				if(args.length >= 2) {
					
					if(args[1].toLowerCase().equalsIgnoreCase("IceLand")) {
					
					
					stats.set(args[0] + ".IceLandWins", stats.getInt(args[0] + ".IceLandWins") + 1);
					try {
						stats.save(statistieken);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					} else if(args[1].toLowerCase().equalsIgnoreCase("Aztec")) {
						
						
						stats.set(args[0] + ".AztecWins", stats.getInt(args[0] + ".AztecWins") + 1);
						try {
							stats.save(statistieken);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
					
				} else {
					
					sender.sendMessage(ChatColor.RED + "Bedoel je /addwin [PLAYER] [MAP] ?");
					
				}
			
			}
			
		} else if (cmd.getName().equalsIgnoreCase("addlose")) {
			
			if(sender.hasPermission("Statistieken.add")) {
			
				if(args.length >= 2) {
					
					
					if(args[1].toLowerCase().equalsIgnoreCase("IceLand")) {
						
						
						stats.set(args[0] + ".IceLandFails", stats.getInt(args[0] + ".IceLandFails") + 1);
						try {
							stats.save(statistieken);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						} else if(args[1].toLowerCase().equalsIgnoreCase("Aztec")) {
							
							
							stats.set(args[0] + ".AztecFails", stats.getInt(args[0] + ".AztecFails") + 1);
							try {
								stats.save(statistieken);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					
					
				} else {
					
					sender.sendMessage(ChatColor.RED + "Bedoel je /addlose [PLAYER] [MAPS] ?");
					
				}
			
			}
			
		} else if (cmd.getName().equalsIgnoreCase("parkour")) {
			
			if(args.length >= 1) {
			
				if(args[0].toLowerCase().equalsIgnoreCase("setleave")) {
					
					Player p = (Player) sender;
					
					if(p.hasPermission("Parkour.Setup")) {
						
						
						con.set("leave.x", p.getLocation().getBlockX());
						con.set("leave.y", p.getLocation().getBlockY());
						con.set("leave.z", p.getLocation().getBlockZ());
						p.sendMessage(ChatColor.GOLD + "Je hebt de " + ChatColor.AQUA + "Leave" + ChatColor.GOLD + "spawn gezet!");
						
						try {
							con.save(config);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
				
			}
					
		} else if(cmd.getName().equalsIgnoreCase("leave")) {
					
			Player p = (Player) sender;
					
			if(p.getWorld().getName().equals("game")) {
						
				int x = con.getInt("leave.x");
				int y = con.getInt("leave.y");
				int z = con.getInt("leave.z");
						
				Location leaveloc = new Location(p.getWorld(), x, y, z);
						
				p.teleport(leaveloc);
						
			}
					
				
			
		} else if(cmd.getName().equalsIgnoreCase("showstats")) {
			
			if(args.length >= 2) {
				
				if(sender.hasPermission("Statistieken.showstats")) {
					
					Player target = Bukkit.getPlayer(args[0]);
						
					target.sendMessage(ChatColor.AQUA + "Jou statistieken op de map " + args[1]);
					if(stats.getInt(target.getName() + "." + args[1] + "Wins") >= 1) {
						target.sendMessage(ChatColor.GOLD + "Wins: " + ChatColor.AQUA + stats.getInt(target.getName() + "." + args[1] + "Wins"));
					} else {
						target.sendMessage(ChatColor.GOLD + "Wins: " + ChatColor.AQUA + "0");	
					}
					if(stats.getInt(target.getName() + "." + args[1] + "Fails") >= 1) {
						target.sendMessage(ChatColor.GOLD + "Fails: " + ChatColor.AQUA + stats.getInt(target.getName() + "." + args[1] + "Fails"));
					} else {
						target.sendMessage(ChatColor.GOLD + "Fails: " + ChatColor.AQUA + "0");	
					}
				
				}
				
			}
			
		}
		
		return false;
		
	}
	
}
