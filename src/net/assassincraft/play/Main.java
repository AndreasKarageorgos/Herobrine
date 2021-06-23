package net.assassincraft.play;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	
	@Override
	public void onEnable() {
		this.getCommand("Herobrine").setExecutor(new Commands(this));
	}
	
	@Override
	public void onDisable() {
		System.out.println("[Herobrine] Disabled !");	
	}

	
}
