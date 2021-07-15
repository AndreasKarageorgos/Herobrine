package net.assassincraft.play;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class MobAttack {
	
	Random rand;
	
	public MobAttack() {
		rand = new Random();
	}
	
	public void spawnRandMobs(Player player) {
		Location loc = player.getLocation();
		loc.setX(loc.getX()+3.0);
		loc.setZ(loc.getZ()+3.0);
		switch(rand.nextInt(3)) {
		case 0:
			player.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
			player.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
			player.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
			player.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
			player.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
			break;
		
		case 1:
			player.getWorld().spawnEntity(loc, EntityType.PILLAGER);
			player.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
			player.getWorld().spawnEntity(loc, EntityType.PILLAGER);
			player.getWorld().spawnEntity(loc, EntityType.WITCH);
			player.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
			break;
		case 2:
			player.getWorld().spawnEntity(loc, EntityType.WITCH);
			player.getWorld().spawnEntity(loc, EntityType.SKELETON);
			player.getWorld().spawnEntity(loc, EntityType.HUSK);
			player.getWorld().spawnEntity(loc, EntityType.VINDICATOR);
			player.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
			break;
		}
	}
	

}
