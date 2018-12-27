package cgnchat.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import cgnchat.Main;

public class Mute implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("mute")) {
			if (args.length != 1) {
				sender.sendMessage("§6CGNChat§8:§c Correct usage: /mute <nickname>");
			} else {
				if (sender.hasPermission("cgnchat.mute")) {
					FileConfiguration config = Main.getInstance().getConfig();
					Main plugin = Main.getInstance();
					if (config.get("players.muted") == null){
						List<String> empty = new ArrayList<String>();
						config.set("players.muted", empty);
					} else {
						List<String> muted = config.getStringList("players.muted");
						Player target = Bukkit.getPlayer(args[0]);
						if (target == null) {
							sender.sendMessage("§6CGNChat§8:§c This player is currently not online.");
							return false;
						}
						if (muted.contains(target.getDisplayName())) {
							muted.remove(target.getDisplayName());
							config.set("players.muted", muted);
							plugin.saveConfig();
							sender.sendMessage("§6CGNChat§8: §aYou have unmuted "+target.getDisplayName()+".");
							target.sendMessage("§6CGNChat§8: §a You have been unmuted by "+sender.getName()+". Make sure you follow all the /rules in the future.");
							return true;
						} else {
							muted.add(target.getDisplayName());
							config.set("players.muted", muted);
							plugin.saveConfig();
							sender.sendMessage("§6CGNChat§8: §aYou have muted "+target.getDisplayName()+".");
							target.sendMessage("§6CGNChat§8: §cYou have been muted by "+sender.getName()+". ");
							return true;
						}
					}
				} else {
					sender.sendMessage("§6CGNChat§8:§c Sorry, your permission level is too low.");
				}
			}
		}
		return false;
	}
	
}
