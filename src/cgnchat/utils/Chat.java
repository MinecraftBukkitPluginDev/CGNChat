package cgnchat.utils;

import org.bukkit.Bukkit;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Chat {

	public static void sendStaff(String message) {
		Bukkit.broadcast("§f[Staff] §6Abstract §fSystem  §7"+message, "cgnchat.receivestaff");
	}
	
}



