package net.assassincraft.play;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Hunting {
	
	private Random rand;
	private int temp;
	private int temp2;
	
	
	public Hunting() {
		rand = new Random();
	}
	
	private Sound sounds() {
		switch(rand.nextInt(6)) {
		case 0:
			return Sound.ENTITY_GHAST_HURT;
		case 1:
			return Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR;
		case 2:
			return Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR;
		case 3:
			return Sound.ENTITY_CREEPER_PRIMED;
		case 4:
			return Sound.ENTITY_ENDERMAN_STARE;
		case 5:
			return Sound.ENTITY_GENERIC_EXPLODE;
		}
		return null;
		
	}
	
	
	public void hunt(ArrayList<Player> players) {
		int magicnumber = rand.nextInt(5); //increase this to make scares more rare.
		ArrayList<Player> failed = new ArrayList<Player>();
		
		for(Player player : players) {
			try {
				if(player.isOnline()) {
					Location location = player.getLocation();
					
					if(rand.nextInt(magicnumber+1)==magicnumber) {
						player.playSound(location, sounds(), 10, 1);
						System.out.println("[Herobrine]: Player: " + player.getName() + " got scared !");
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
