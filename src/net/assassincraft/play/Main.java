package net.assassincraft.play;

import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	@Override
	public void onEnable() {
		System.out.println("[Herobrine] Enabled !");
		this.getCommand("Herobrine").setExecutor(new herobrine());
	}
	
	@Override
	public void onDisable() {
		System.out.println("[Herobrine] Disabled !");
		
	}
	
	
}
