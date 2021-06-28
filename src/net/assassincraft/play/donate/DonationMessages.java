package net.assassincraft.play.donate;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.assassincraft.play.Main;
import net.md_5.bungee.api.ChatColor;

public class DonationMessages {
	
	Main plugin;
	
	public DonationMessages(Main plugin) {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()) {
					if(player.isOp() && player.isOnline()) {
						player.sendMessage(ChatColor.GOLD + "[Herobrine] : " + ChatColor.RED +"If you like this plugin please consider to donate even a small amount of 5€. " + ChatColor.BLUE + "https://bit.ly/3qGYZ21");
					}
				}
				
			}
		}.runTaskTimer(plugin, 60, (120*20));
	}

}
