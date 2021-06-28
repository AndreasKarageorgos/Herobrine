package net.assassincraft.play;

import java.util.Random;

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
	Random rand;
	
	
	public JumpScare(Main plugin) {
		this.plugin = plugin;
		rand = new Random();
	}
	
	public void scare(Player player) {
		Herobrine brine = new Herobrine(player);
		brine.spawn();
		if(player.isSprinting()){player.setSprinting(false);};
		player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 65, 20));
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
					player.playSound(player.getLocation(),Sound.ENTITY_ENDER_DRAGON_SHOOT, 10, 0.1f);
				}
				if(count>=60) {
					player.playSound(player.getLocation(),Sound.ENTITY_WITCH_CELEBRATE, 5, 0.4f);
					player.playSound(player.getLocation(),Sound.ENTITY_WITCH_CELEBRATE, 10, 0.2f);
					player.sendMessage(ChatColor.GOLD + "HEROBRINE: " + ChatColor.RED +ChatColor.BOLD + "HAHAHA!");
					packets.remove();
					if(rand.nextInt(2)==0) {
						MobAttack mb = new MobAttack();
						mb.spawnRandMobs(player);
					}
					cancel();
				}
				packets.update(player.getLocation());
				count++;
			}
			
			
		}.runTaskTimer(plugin, 0, 0);
	}
	
	
	

}
