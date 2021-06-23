package net.assassincraft.play;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Commands implements CommandExecutor{
	
	private Hunt hunt;
	
	public Commands(Main main) {
		hunt = new Hunt(main);
		hunt.start();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("herobrine")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Only players can execute this command");
				return true;
			}
			
			Player player = (Player) sender;
			if(!(player.hasPermission("herobrine.use") || player.isOp())) {
				player.sendMessage(ChatColor.RED + "You don't have permission");
				return true;
			}
			if(hunt.getPlayers().contains(player)) {
				hunt.getPlayers().remove(player);
				player.sendMessage(ChatColor.BLUE + "Herobrine is no longer hunting you !");
			}else {
				hunt.add(player);
				player.sendMessage(ChatColor.RED + "Herobrine is hunting you !");
				player.playSound(player.getLocation(), Sound.ENTITY_WITCH_CELEBRATE, 10, 0.3f);
			}
		}	
		return false;
	}
	
}
