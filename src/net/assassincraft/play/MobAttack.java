package net.assassincraft.play;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class MobAttack {
	
	public MobAttack() {}
	
	public void spawnRandMobs(Player player) {
		Location loc = player.getLocation();
		loc.setX(loc.getX()+3.0);
		loc.setZ(loc.getZ()+3.0);
		player.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
		player.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
		player.getWorld().spawnEntity(loc, EntityType.PILLAGER);
		player.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
		player.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
	}
	

}
