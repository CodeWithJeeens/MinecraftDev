package com.jklmao.plugin.commands;

import com.jklmao.plugin.ClickTpa;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTpaDeny implements CommandExecutor {

	private ClickTpa clicktpa;
	String targetsname = new String();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {

			Player p = (Player) sender;
			if (!p.hasPermission("clicktpa.tpadeny")) {
				p.sendMessage((colorize(clicktpa.getConfig().getString("Insufficient-permission"))));
				return true;
			}
			if (args.length == 0) {
				p.sendMessage((colorize(clicktpa.getConfig().getString("Tpdeny-usage"))));
				return true;
			}

			Player target = Bukkit.getPlayer(args[0]);

			if (target != null) {
				if (target.isOnline()) {
					if (clicktpa.getHash().containsKey(target) || clicktpa.getTpaHere().containsKey(target)) {
						clicktpa.getHash().clear();
						clicktpa.getTpaHere().clear();
						clicktpa.getTeleportStatus().clear();
						p.sendMessage((colorize(clicktpa.getConfig().getString("Player-deny-tpa-message"))));
						target.sendMessage((colorize(clicktpa.getConfig().getString("Target-deny-tpa-message"))));
						return true;
					} else if (!clicktpa.getHash().containsKey(p) || (!(clicktpa.getTpaHere().containsKey(p)))) {
						p.sendMessage((colorize(clicktpa.getConfig().getString("Player-no-pendingtpa-message"))));
						return true;
					}
				}  else {

					p.sendMessage(colorize(clicktpa.getConfig().getString("Target-is-offline")));
					clicktpa.getTeleportStatus().clear();
					clicktpa.getHash().clear();
					clicktpa.getTpaHere().clear();
					return true;
				}
			} else {
				if (!clicktpa.getHash().containsKey(target) || (!(clicktpa.getTpaHere().containsKey(target)))) {
					clicktpa.getHash().clear();
					clicktpa.getTpaHere().clear();
					clicktpa.getTeleportStatus().clear();
					p.sendMessage(colorize(clicktpa.getConfig().getString("Target-is-offline")));
					return true;
				} else if (clicktpa.getHash().containsKey(target) || clicktpa.getTpaHere().containsKey(target)){
					clicktpa.getHash().clear();
					clicktpa.getTpaHere().clear();
					clicktpa.getTeleportStatus().clear();
					p.sendMessage((colorize(clicktpa.getConfig().getString("Player-no-pendingtpa-message"))));
					return true;
				}

			}
			return true;
		} else {
			sender.sendMessage((colorize(clicktpa.getConfig().getString("Player-only-command"))));
		}
		return true;
	}

	public static String colorize(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public CommandTpaDeny(ClickTpa pl) {
		clicktpa = pl;
	}
}
