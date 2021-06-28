package net.assassincraft.play;

import org.bukkit.plugin.java.JavaPlugin;

import net.assassincraft.play.donate.DonationMessages;

public class Main extends JavaPlugin{

	
	@Override
	public void onEnable() {
		this.getCommand("Herobrine").setExecutor(new Commands(this));
		@SuppressWarnings("unused")
		DonationMessages dms = new DonationMessages(this);
	}
	
	@Override
	public void onDisable() {
		System.out.println("[Herobrine] Disabled !");	
	}

	
}
