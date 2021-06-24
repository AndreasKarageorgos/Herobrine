package net.assassincraft.play;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Hunt{
	
	private ArrayList<Player> players;
	private Hunting hunting;
	private Random rand;
	private final Main plugin;
	
	
	public Hunt(Main plugin) {
		this.plugin = plugin;
		this.plugin.saveDefaultConfig();
		players = new ArrayList<Player>();
		hunting = new Hunting(plugin);
		rand = new Random();
		
	}
	
	public void add(Player player) {
		players.add(player);
	}
	
	public void start() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
			public void run() {
				if(players.size()>0) {
					hunting.hunt(players);
				}
				
			}
			
		}, 40, ((rand.nextInt(plugin.getConfig().getInt("max")-plugin.getConfig().getInt("mix"))+plugin.getConfig().getInt("min")) * 4));
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	
	

}
