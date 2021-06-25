package net.assassincraft.play;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Hunting{
	
	private Random rand;
	private final Main plugin;
	private JumpScare jumpScare;
	
	public Hunting(Main plugin) {
		this.plugin = plugin;
		rand = new Random();
		plugin.saveDefaultConfig();
		jumpScare = new JumpScare(plugin);
	}
	
	
	private Sound sounds() {
		switch(rand.nextInt(4)) {
		case 0:
			return Sound.ENTITY_GHAST_HURT;
		case 1:
			return Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR;
		case 2:
			return Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR;
		case 3:
			return Sound.ENTITY_CREEPER_PRIMED;
		}
		return null;
		
	}
	
	private void attack(Player player) {
		player.damage(rand.nextInt(4)+1.0);
	}
	
	public void hunt(ArrayList<Player> players) {
		int magicnumber = rand.nextInt(plugin.getConfig().getInt("scare"));
		ArrayList<Player> failed = new ArrayList<Player>();
		
		for(Player player : players) {
			try {
				if(player.isOnline()) {
					Location location = player.getLocation();
					
					if(rand.nextInt(magicnumber+1)==magicnumber) {
						if(rand.nextInt(plugin.getConfig().getInt("jumpscare"))==0) {
							jumpScare.scare(player);
							attack(player);
							System.out.println("[Herobrine]: Player: " + player.getName() + " got JumpScare !");
						}else {
							player.playSound(location, sounds(), 10, 1);
							System.out.println("[Herobrine]: Player: " + player.getName() + " got scared !");
						}
					}
				}else {
					failed.add(player);
				}
			}catch(Exception e) {
				if(!(players.contains(player))) {failed.add(player);}
			}
		}
		
		for(Player player : failed) {players.remove(player);}
		
	}
	

}
