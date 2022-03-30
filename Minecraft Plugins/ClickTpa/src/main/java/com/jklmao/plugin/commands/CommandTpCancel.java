package com.jklmao.plugin.commands;

import com.jklmao.plugin.ClickTpa;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CommandTpCancel implements CommandExecutor {

	private ClickTpa clicktpa;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		Player p = null;

		if (sender instanceof Player) {
			p = (Player) sender;
			if (!(p == null)) {
				if (!(p.hasPermission("clicktpa.tpacancel"))) {
					p.sendMessage((colorize(clicktpa.getConfig().getString("Insufficient-permission"))));
					return true;
				}
				
				if (clicktpa.getTpaCancel().containsKey(p.getName())) {
					Player poorRejectedGuy = Bukkit.getServer().getPlayer(clicktpa.getTpaCancel().get(p.getName()));

					clicktpa.getTpaCancel().remove(p.getName());
					clicktpa.getHash().remove(p);
					clicktpa.getHash().remove(poorRejectedGuy);
					clicktpa.getTpaHere().remove(p);
					clicktpa.getTpaHere().remove(poorRejectedGuy);

					if (!(poorRejectedGuy == null)) {
						p.sendMessage(colorize(clicktpa.getConfig().getString("Tpacancel-message")));
						poorRejectedGuy.sendMessage(colorize(clicktpa.getConfig().getString("Tpacancel-message")));
						return true;
					} else {
						p.sendMessage(colorize(clicktpa.getConfig().getString("Player-no-pendingtpa-message")));
					}

				} else if (!clicktpa.getTpaCancel().containsKey(p.getName())) {
					p.sendMessage(colorize(clicktpa.getConfig().getString("Player-no-pendingtpa-message")));
					return true;
				}
			}

			return true;
		} else {
			sender.sendMessage((colorize(clicktpa.getConfig().getString("Player-only-command"))));

		}

		return true;
	}

	public String returnStatementPlayer(String configMessages, Player player) {
		return clicktpa.getConfig().getString(configMessages).replaceAll("%player%", player.getName());
	}

	public String returnStatementTarget(String configMessages, Player target) {
		return clicktpa.getConfig().getString(configMessages).replaceAll("%target%", target.getName());
	}

	public static String colorize(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public CommandTpCancel(ClickTpa pl) {
		clicktpa = pl;
	}
}

