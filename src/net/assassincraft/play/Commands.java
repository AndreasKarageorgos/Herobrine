package net.assassincraft.play;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Commands implements CommandExecutor{
	
	private Hunt hunt;
	private JumpScare jumpScare;
	private Main plugin;
	
	public Commands(Main main) {
		this.plugin = main;
		jumpScare = new JumpScare(main);
		hunt = new Hunt(main);
		hunt.start();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equals("herobrine")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Only players can execute this command");
				return true;
			}
			
			Player player = (Player) sender;
			
			if(label.equals("herobrine") && args.length >= 1 && (player.hasPermission("herobrine.others") || player.isOp())) {
				for(Player target : Bukkit.getOnlinePlayers()) {
					if(target.getName().equals(args[0])) {
						player.sendMessage(ChatColor.BLUE + "Target got attacked !");
						jumpScare.scare(target);
						return true;
					}
				}
				player.sendMessage(ChatColor.RED + "Target did not found !");
				return true;
			}else if(args.length >=1){
				player.sendMessage(ChatColor.RED + "You don't have permission");
			}
			
			if(!(player.hasPermission("herobrine.use") || player.isOp())) {
				player.sendMessage(ChatColor.RED + "You don't have permission");
				return true;
			}
			if(hunt.getPlayers().contains(player) && !(plugin.getConfig().getBoolean("scare-everyone"))) {
				hunt.getPlayers().remove(player);
				player.sendMessage(ChatColor.BLUE + "Herobrine is no longer hunting you !");
			}else if(!(plugin.getConfig().getBoolean("scare-everyone"))){
				player.sendMessage(ChatColor.RED + "Herobrine is hunting you !");
				player.playSound(player.getLocation(), Sound.ENTITY_WITCH_CELEBRATE, 5, 0.5f);
				player.playSound(player.getLocation(), Sound.ENTITY_WITCH_CELEBRATE, 10, 0.3f);
				hunt.add(player);
			}else {
				player.sendMessage(ChatColor.RED + "Herobrine is already hunting this wolrd !");
			}
		}	
		return false;
	}
	
}
