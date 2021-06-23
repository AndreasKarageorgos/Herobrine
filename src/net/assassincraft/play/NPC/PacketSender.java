package net.assassincraft.play.NPC;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntity;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_16_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_16_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_16_R3.PlayerConnection;

public class PacketSender {
	private final Player player;
	private final EntityPlayer npc;
	
	public PacketSender(Player player, EntityPlayer npc) {
		this.player = player;
		this.npc = npc;
	}

	public Player getPlayer() {
		return player;
	}

	public EntityPlayer getNpc() {
		return npc;
	}
	
	public void send() {
		PlayerConnection connection = ((CraftPlayer)getPlayer()).getHandle().playerConnection;
		connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, getNpc())); // "Adds the player data for the client to use when spawning a player" - https://wiki.vg/Protocol#Spawn_Player
		connection.sendPacket(new PacketPlayOutNamedEntitySpawn(getNpc())); // Spawns the NPC for the player client.
		connection.sendPacket(new PacketPlayOutEntityHeadRotation(getNpc(), (byte) (getNpc().yaw * 256 / 360))); // Correct head rotation when spawned in player look direction.
	}
	
	public void remove() {
		PlayerConnection connection = ((CraftPlayer)getPlayer()).getHandle().playerConnection;
		connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, getNpc())); // "Adds the player data for the client to use when spawning a player" - https://wiki.vg/Protocol#Spawn_Player
		connection.sendPacket(new PacketPlayOutEntityDestroy(getNpc().getId()));
	
	}
	
	public void update(Location loc) {
		PlayerConnection connection = ((CraftPlayer)getPlayer()).getHandle().playerConnection;
		loc.setDirection(loc.getDirection().multiply(-1));
		connection.sendPacket(new PacketPlayOutEntityHeadRotation(getNpc(),
				(byte) (loc.getYaw() * 256/360)));
	
	connection.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(getNpc().getId(),
			(byte)(loc.getYaw()*256/360),(byte)(loc.getPitch()*256/360),true));
	}
	
	

}
