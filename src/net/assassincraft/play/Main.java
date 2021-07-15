package net.assassincraft.play;

import org.bukkit.plugin.java.JavaPlugin;

import net.assassincraft.play.donate.DonationMessages;
import net.assassincraft.play.listeners.JoinListener;

public class Main extends JavaPlugin{

	
	@SuppressWarnings("unused")
	@Override
	public void onEnable() {
		this.getCommand("Herobrine").setExecutor(new Commands(this));
		if(this.getConfig().getBoolean("scare-everyone")) {
			this.getServer().getPluginManager().registerEvents(new JoinListener(this), this);
		}
		
		
		DonationMessages dms = new DonationMessages(this);
	}
	
	@Override
	public void onDisable() {
		System.out.println("[Herobrine] Disabled !");	
	}

	
}
