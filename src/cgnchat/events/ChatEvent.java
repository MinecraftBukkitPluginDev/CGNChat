package cgnchat.events;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import cgnchat.Main;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ChatEvent implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		FileConfiguration config = Main.getInstance().getConfig();
		Main plugin = Main.getInstance();
		
		if (event.getMessage().startsWith("!") && event.getPlayer().hasPermission("cgnchat.sendstaff")) {
			String message = event.getMessage().replaceFirst("\\!", "");
			Bukkit.broadcast("§f[Staff] "+PermissionsEx.getUser(event.getPlayer()).getPrefix().replaceAll("\\&", "§")+"§f "+event.getPlayer().getDisplayName()+" §7"+message, "cgnchat.receivestaff");
			event.setCancelled(true);
		}
		
		if (config.get("players.muted") == null) {
			List<String> empty = new ArrayList<String> ();
			config.set("players.muted", empty);
		}
		
		List<String> muted = config.getStringList("players.muted");
		if (muted.contains(event.getPlayer().getDisplayName())) {
			event.getPlayer().sendMessage("§6CGNChat§8:§c Sorry, but you are muted and so you can't chat. Please contact the administration for an appeal.");
			event.setCancelled(true);
		}
		String pre_time = Time.valueOf(LocalTime.now()).getHours()+":"+Time.valueOf(LocalTime.now()).getMinutes();
		event.setFormat("§7"+pre_time+"§f "+PermissionsEx.getUser(event.getPlayer()).getPrefix().replaceAll("\\&", "§")+" §f"+event.getPlayer().getDisplayName()+"§7 "+event.getMessage().replaceAll("\\&", "§"));
	}
}
