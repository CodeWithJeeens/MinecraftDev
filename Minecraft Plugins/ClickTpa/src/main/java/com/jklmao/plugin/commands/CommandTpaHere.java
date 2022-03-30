package com.jklmao.plugin.commands;

import java.util.List;

import com.jklmao.plugin.ClickTpa;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class CommandTpaHere implements CommandExecutor {

	private ClickTpa clicktpa;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender instanceof Player) {

			final Player p = (Player) sender;
			if (!p.hasPermission("clicktpa.tpahere")) {
				p.sendMessage((colorize(clicktpa.getConfig().getString("Insufficient-permission"))));
				return true;
			}
			if (args.length == 0) {
				p.sendMessage((colorize(clicktpa.getConfig().getString("Tpahere-usage"))));
				return true;
			}

			Player target = Bukkit.getPlayer(args[0]);

			if (target == null) {
				if (clicktpa.getTpToggled().contains(p)) {
					p.sendMessage(colorize(clicktpa.getConfig().getString("Player-Is-TpToggled"))); 
					return true;
				}else {
				p.sendMessage((colorize(clicktpa.getConfig().getString("No-player-found"))));
				return true;
				}
			}
				
			if (target.equals(p)) {
				if (clicktpa.getTpToggled().contains(p)) {
					p.sendMessage(colorize(clicktpa.getConfig().getString("Player-Is-TpToggled"))); 
					return true;
				}else {
				p.sendMessage((colorize(clicktpa.getConfig().getString("Player-teleporting-self"))));
				return true;
				}
			}

			if (clicktpa.getTpToggled().contains(p)) {
				p.sendMessage(colorize(clicktpa.getConfig().getString("Player-Is-TpToggled")));
				return true;
			}

			if (clicktpa.getTpaHere().containsKey(p)) {
				p.sendMessage((colorize(clicktpa.getConfig().getString("Player-already-requested"))));
				return true;
			}

			if (clicktpa.getTpToggled().contains(target)) {
				p.sendMessage(colorize(clicktpa.getConfig().getString("Target-Is-TpToggled")));
				return true;
			}

			if (!clicktpa.getTpToggled().contains(target)) {
				clicktpa.getTpaHere().put(p, p);
				clicktpa.getTpaHere().put(target, target);
				clicktpa.getTeleportStatus().add(p);
				addThemToList(target, p);

				TextComponent accept = new TextComponent(
						colorize(clicktpa.getConfig().getString("Click-to-accept")));
				TextComponent deny = new TextComponent(colorize(clicktpa.getConfig().getString("Click-to-deny")));


				TextComponent eemptyspace = new TextComponent("    ");
				TextComponent bemptyspace = new TextComponent("       ");

				BaseComponent[] accepthover = new ComponentBuilder(
						colorize(clicktpa.getConfig().getString("Hover-message-on-accept"))).create();
				HoverEvent onhoveraccept = new HoverEvent(HoverEvent.Action.SHOW_TEXT, accepthover);
				accept.setHoverEvent(onhoveraccept);

				BaseComponent[] denyhover = new ComponentBuilder(
						colorize(clicktpa.getConfig().getString("Hover-message-on-deny"))).create();
				HoverEvent onhoverdeny = new HoverEvent(HoverEvent.Action.SHOW_TEXT, denyhover);
				deny.setHoverEvent(onhoverdeny);

				TextComponent requestsent = new TextComponent(colorize(clicktpa.getConfig()
						.getString("Player-sent-request").replaceAll("%target%", target.getName())));


				List<String> tpa = clicktpa.getConfig().getStringList("Tpahere-message");
				for (final String m : tpa) {

						target.sendMessage(colorize(m).replaceAll("%player%", p.getName())
								.replaceAll("%accept%", accept.getText()).replaceAll("%deny%", deny.getText()));

				}

				accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept " + p.getName()));
				deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpadeny " + p.getName()));

				if (clicktpa.getConfig().getBoolean("Added-accept-deny-space")) {
					target.spigot().sendMessage(bemptyspace, accept, eemptyspace, deny);
					p.spigot().sendMessage(requestsent);
				}else {
				target.spigot().sendMessage(accept, deny);
				p.spigot().sendMessage(requestsent);
				}
			}


			new BukkitRunnable() {
					public void run() {
						if (clicktpa.getTpaHere().containsKey(p) && clicktpa.getTpaHere().containsKey(target)) {
							clicktpa.getTpaHere().remove(p);
							clicktpa.getTpaHere().remove(target);
							p.sendMessage(
									(colorize(clicktpa.getConfig().getString("Player-teleportation-request-expire"))));
							target.sendMessage(
									(colorize(clicktpa.getConfig().getString("Target-teleportation-request-expire"))));
						}
					}
				}.runTaskLaterAsynchronously(ClickTpa.instance, 2400L);

			}else {
				sender.sendMessage((colorize(clicktpa.getConfig().getString("Player-only-command"))));
				return true;
			}
			return true;
		} 

	

	public void addThemToList(Player sender, Player recipient) {
		String senderName = sender.getName();
		String recipientName = recipient.getName();
		clicktpa.getTpaCancel().put(recipientName, senderName);
	}

	public static String colorize(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public CommandTpaHere(ClickTpa pl) {
		clicktpa = pl;
	}
}
