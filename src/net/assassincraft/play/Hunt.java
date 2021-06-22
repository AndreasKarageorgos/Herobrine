package net.assassincraft.play;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.entity.Player;

public class Hunt extends Thread{
	
	private ArrayList<Player> players;
	private Hunting hunting;
	private Random rand;
	
	
	public Hunt() {
		players = new ArrayList<Player>();
		hunting = new Hunting();
		rand = new Random();
	}
	
	public void add(Player player) {
		players.add(player);
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(300);
				if(!(players.size()==0)) {
					Thread.sleep(rand.nextInt(20000)+10000); //Scares players every 20-60 seconds.
					hunting.hunt(players);
				}
			} catch (InterruptedException e) {
				continue;
			}
		}
	}
	
	public ArrayList getPlayers() {
		return players;
	}
	
	
	

}
