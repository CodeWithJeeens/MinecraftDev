package com.jklmao.plugin.commands;

import com.jklmao.plugin.ClickTpa;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class CommandTpToggle implements CommandExecutor {

	private ClickTpa clicktpa;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {

			final Player p = (Player) sender;
			if (!p.hasPermission("clicktpa.tptoggle")) {
				p.sendMessage((colorize(clicktpa.getConfig().getString("Insufficient-permission"))));
				return true;
			}

			if (p.hasPermission("clicktpa.tptoggle")) {
				if (!clicktpa.getTpToggled().contains(p)) {
					clicktpa.getTpToggled().add(p);
					p.sendMessage((colorize(clicktpa.getConfig().getString("Player-TpToggle-On"))));
					return true;
				} else if (clicktpa.getTpToggled().contains(p)) {
					clicktpa.getTpToggled().remove(p);
					p.sendMessage((colorize(clicktpa.getConfig().getString("Player-TpToggle-Off"))));
					return true;
				}

				return true;
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

	public CommandTpToggle(ClickTpa pl) {
		clicktpa = pl;
	}

}
