package com.jklmao.plugin.commands;

import com.jklmao.plugin.ClickTpa;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class CommandTpo implements CommandExecutor {

	private ClickTpa clicktpa;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {

			final Player p = (Player) sender;

			if (!p.hasPermission("clicktpa.tpo")) {
				p.sendMessage((colorize(clicktpa.getConfig().getString("Insufficient-permission"))));
				return true;
			}
			if (args.length == 0) {
				p.sendMessage((colorize(clicktpa.getConfig().getString("Tpo-usage"))));
				return true;
			}

			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				p.sendMessage((colorize(clicktpa.getConfig().getString("No-player-found"))));
				return true;
			}
			if (target.equals(p)) {
				p.sendMessage((colorize(clicktpa.getConfig().getString("Player-teleporting-self"))));
				return true;
			}
			p.sendMessage((colorize(clicktpa.getConfig().getString("Player-currently-teleporting"))));
			target.sendMessage((colorize(clicktpa.getConfig().getString("Target-currently-teleporting"))));
			p.teleport(target);

			return true;
		} else {
			sender.sendMessage((colorize(clicktpa.getConfig().getString("Player-only-command"))));

		}
		return true;
	}

	public static String colorize(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public CommandTpo(ClickTpa pl) {
		clicktpa = pl;
	}

}
