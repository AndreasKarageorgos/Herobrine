package net.assassincraft.play.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.assassincraft.play.Hunt;
import net.assassincraft.play.Main;

public class JoinListener implements Listener{
	
	private Hunt hunt;
	
	public JoinListener(Main plugin) {
		this.hunt = new Hunt(plugin);
		this.hunt.start();
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = (Player) event.getPlayer();
		hunt.add(player);
	}

}
