package net.assassincraft.play.NPC;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.MinecraftServer;
import net.minecraft.server.v1_16_R3.PlayerInteractManager;
import net.minecraft.server.v1_16_R3.WorldServer;

public class Herobrine {
	
	private final Player player;
	private World world;
	private EntityPlayer npc;
	
	public Herobrine(Player player) {
		this.player = player;
		this.setWorld(player.getWorld());
		generateNPC();
	}
	
	public void spawn() {
		Location loc = player.getLocation();
		double distance = 2.5;
		if(player.isSprinting()) {
			distance = 3.5;
		}
		
		if(player.getFacing() == BlockFace.NORTH) {
			loc.setZ(loc.getZ() - distance);
		}else if(player.getFacing() == BlockFace.SOUTH) {
			loc.setZ(loc.getZ() + distance);
		}else if(player.getFacing() == BlockFace.WEST) {
			loc.setX(loc.getX() - distance);
		}else if(player.getFacing() == BlockFace.EAST){
			loc.setX(loc.getX() + distance);
		}
		loc.setDirection(loc.getDirection().multiply(-1));
		getNpc().setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
	}
	
	private GameProfile getSkin() {
		// Skin #2022841354 generated on May 29, 2021 7:10:43 PM via MineSkin.org - https://minesk.in/2022841354
		GameProfile skin2022841354 = new GameProfile(UUID.fromString("4f6fbd1e-1c77-418c-9e78-7c94e37ed710"),"");
		skin2022841354.getProperties().put("textures", new Property("textures", "ewogICJ0aW1lc3RhbXAiIDogMTYyMjMwNDY0MjU3MSwKICAicHJvZmlsZUlkIiA6ICI0NWY3YTJlNjE3ODE0YjJjODAwODM5MmRmN2IzNWY0ZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJfSnVzdERvSXQiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTUyM2UxZjA2ZDhlMTM5OWMwNjhhYTU3NjhmMDUzMDI1N2FhYjIxYTM0ZTk1NTY5MzNlNjM0NTUxODY0MjVhNiIKICAgIH0KICB9Cn0=", "PAL4UKYIgCe3RwmxfT6p05DDqc0kW2HTvlNNRuP3npXyuL68Hifjtiuu6OiJ/IQ/YGsjKf3Ni219teFFBJqEUJNEskC7CWnJxHWRq/WkSNDJXVKvBW6JjOYN607HSlBhifn4ZAxasol0bqVRURVG+0BwDUHXIGy4LRSu10/SLhsAmvwKhwYJiQ1xgnerqfzeWmPQSDZOo30Fxo6jNRnJ7v0VBGR8awImOfVsKE3SuEeys47U5AqXrHIp88s36aGOsIa0Bs6NHutW/L6Wbg9BHGUs93NGnjP9jxXizhGITF45KBXNl7gXPPjMnK7/CGOTuxuABc+MVAptB4cHCksnfaSMexryluQuwzWzOJTX1NCMGlYFREDq4c3GgVWJ7Yo2xWOwcy+XZxJXNchQ5/vzA3dv+XdU4zcD93OOhuv9PDoUTlmQgLFQLFbxM6Bq6cSQg3Wybi7wO6jQzBIVpZaswrOo/wgHuXbDkA9rv5uONjQQbKHJQjVjgIrbbUpX3+Kh4MkgBEUztxNvbgbt5RK0knwtYhscKDyrP5AAwp9CX6JnmbKXIRVWtx6b3JNPYC9I6FtBCoGxo6OfHl3f/okncszIh+40Sif3IdMrW+H5g3MxMkn5eHP4nOHRL24izlLUM52gS/TUgpG0dJrG6XMOR3w6KqTxcT/4vyxwlmJ+iDg="));
		return skin2022841354;
	}
	
	private void generateNPC() {
		MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer world = ((CraftWorld)getWorld()).getHandle(); // Change "world" to the world the NPC should be spawned in.
        GameProfile profile = getSkin();
        EntityPlayer npc = new EntityPlayer(server, world, profile, new PlayerInteractManager(world)); // This will be the EntityPlayer (NPC) we send with the sendNPCPacket method.
        
        npc.setCustomNameVisible(false);
        setNpc(npc);
	}

	public Player getPlayer() {
		return player;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public EntityPlayer getNpc() {
		return npc;
	}

	public void setNpc(EntityPlayer npc) {
		this.npc = npc;
	}
	

}
