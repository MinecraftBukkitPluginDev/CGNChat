package cgnchat;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import cgnchat.commands.Mute;
import cgnchat.events.ChatEvent;

public class Main extends JavaPlugin {
	
	private static Main instance;
	
	@Override
	public void onEnable() {
		instance = this;
		boolean mistake = 
				!getServer().getPluginManager().isPluginEnabled("PermissionsEx");
		if (mistake) {
			Bukkit.broadcastMessage("§6CGNChat§8:§c The plugin will be disabled, because there's no PermissionsEx plugin enabled.");
			System.err.println("The plugin CGNChat (Series CGN) requires the PermissionsEx plugin. The plugin will be disabled. Please make sure PEX is installed before launching the plugin again;");
			getServer().getPluginManager().disablePlugin(this);
		} else {
			getServer().getPluginManager().registerEvents(new ChatEvent(), this);
			getCommand("mute").setExecutor(new Mute());
		}
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static Main getInstance() {
		return instance;
	}
}
