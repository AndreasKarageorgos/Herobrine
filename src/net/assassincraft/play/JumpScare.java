package net.assassincraft.play;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import net.assassincraft.play.NPC.Herobrine;
import net.assassincraft.play.NPC.PacketSender;
import net.md_5.bungee.api.ChatColor;

public class JumpScare {
	
	Main plugin;
	
	
	public JumpScare(Main plugin) {
		this.plugin = plugin;
	}
	
	public void scare(Player player) {
		boolean wasWalking;
		float speed;
		
		Herobrine brine = new Herobrine(player);
		brine.spawn();
		if(player.isSprinting()){player.setSprinting(false);};
		if(player.isFlying()) {
			speed = player.getFlySpeed();
			player.setFlySpeed(0);
			wasWalking = false;
		}else {
			speed = player.getWalkSpeed();
			player.setWalkSpeed(0);
			wasWalking = true;
		}
		player.playSound(player.getLocation(),Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 5, 0.5f);
		player.playSound(player.getLocation(),Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 7, 0.8f);
		player.playSound(player.getLocation(),Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 10, 1);
		player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 80, 2));
		PacketSender packets = new PacketSender(player, brine.getNpc());
		packets.send();
		
		new BukkitRunnable() {
			int count = 0;
			public void run() {
				if(count % 10 ==0) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2, 3));
					player.playSound(player.getLocation(),Sound.ENTITY_ENDER_DRAGON_SHOOT, 10, 0.1f);
				}
				if(count>=60) {
					if(wasWalking) {
						player.setWalkSpeed(speed);
					}else {
						player.setFlySpeed(speed);
					}
					player.playSound(player.getLocation(),Sound.ENTITY_WITCH_CELEBRATE, 5, 0.4f);
					player.playSound(player.getLocation(),Sound.ENTITY_WITCH_CELEBRATE, 10, 0.2f);
					player.sendMessage(ChatColor.GOLD + "HEROBRINE: " + ChatColor.RED +ChatColor.BOLD + "HAHAHA!");
					packets.remove();
					cancel();
				}
				packets.update(player.getLocation());
				count++;
			}
			
			
		}.runTaskTimer(plugin, 0, 0);
	}
	
	
	

}
