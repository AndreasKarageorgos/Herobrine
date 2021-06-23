package net.assassincraft.play;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import net.assassincraft.play.NPC.Herobrine;
import net.assassincraft.play.NPC.PacketSender;

public class Hunting{
	
	private Random rand;
	private final Main plugin;
	
	public Hunting(Main plugin) {
		this.plugin = plugin;
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
	
	private void jumpScare(Player player) {
		Herobrine brine = new Herobrine(player);
		brine.spawn();
		player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 1));
		player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 3));
		player.playSound(player.getLocation(),Sound.ENTITY_GHAST_HURT, 10, 0.2f);
		PacketSender packets = new PacketSender(player, brine.getNpc());
		packets.send();
		
		new BukkitRunnable() {
			int count = 0;
			public void run() {
				if(count % 10 ==0) {
					player.playSound(player.getLocation(),Sound.ENTITY_ENDER_DRAGON_SHOOT, 10, 0.3f);
				}
				if(count>=60) {
					packets.remove();
					cancel();
				}
				packets.update(player.getLocation());
				count++;
			}
			
			
		}.runTaskTimer(plugin, 0, 0);
		
	}
	
	public void hunt(ArrayList<Player> players) {
		int magicnumber = rand.nextInt(5); //increase this to make scares more rare.
		ArrayList<Player> failed = new ArrayList<Player>();
		
		for(Player player : players) {
			try {
				if(player.isOnline()) {
					Location location = player.getLocation();
					
					if(rand.nextInt(magicnumber+1)==magicnumber) {
						if(rand.nextInt(10)==5) {
							jumpScare(player);
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
