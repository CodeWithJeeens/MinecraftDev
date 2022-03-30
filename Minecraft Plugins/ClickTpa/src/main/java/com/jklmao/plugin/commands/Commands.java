package com.jklmao.plugin.commands;

import com.jklmao.plugin.ClickTpa;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class Commands implements CommandExecutor
{
    private int seconds;
    private int countdown;
    private ClickTpa clicktpa;
    ConsoleCommandSender console;
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        seconds = clicktpa.getConfig().getInt("Seconds-until-tpa");
        if (!(sender instanceof Player)) {
            sender.sendMessage(colorize(clicktpa.getConfig().getString("Player-only-command")));
            return true;
        }
        final Player p = (Player)sender;
        if (!p.hasPermission("clicktpa.tpaccept")) {
            p.sendMessage(colorize(clicktpa.getConfig().getString("Insufficient-permission")));
            return true;
        }
        if (args.length == 0) {
            p.sendMessage(colorize(clicktpa.getConfig().getString("Tpaccept-usage")));
            return true;
        }
        final Player target = Bukkit.getPlayer(args[0]);
        if (args.length >= 1) {
            if (target != null) {
                if (!target.isOnline()) {
                    p.sendMessage(colorize(clicktpa.getConfig().getString("Target-is-offline")));
                    clicktpa.getHash().clear();
                    clicktpa.getTpaHere().clear();
                    return true;
                }
                if (clicktpa.getHash().containsKey(target) || clicktpa.getTpaHere().containsKey(target)) {
                    p.sendMessage(colorize(clicktpa.getConfig().getString("Player-got-accepted-tpa")));
                    target.sendMessage(colorize(clicktpa.getConfig().getString("Target-accepted-tpa-request")));
                    target.sendMessage(colorize(clicktpa.getConfig().getString("Countdown-until-tpa")));
                    clicktpa.getHash().clear();
                    clicktpa.getTpaHere().clear();
                   
                    countdown = clicktpa.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin)clicktpa, () -> {
                        if (seconds != -1) {
                            if (seconds != 0) {
                                if (clicktpa.getTeleportStatus().contains(p)) {
                                    --seconds;
                                }
                                else {
                                    clicktpa.getServer().getScheduler().cancelTask(countdown);
                                    target.sendMessage(colorize(clicktpa.getConfig().getString("Player-moved-before-tp").replaceAll("%player%", p.getName())));
                                    p.sendMessage(colorize(clicktpa.getConfig().getString("Player-moved-before-tp").replaceAll("%player%", p.getName())));
                                }
                            }
                            else {
                                clicktpa.getHash().clear();
                                clicktpa.getTpaHere().clear();
                                p.teleport(target);
                                p.sendMessage(colorize(clicktpa.getConfig().getString("Target-currently-teleporting")));
                                target.sendMessage(colorize(clicktpa.getConfig().getString("Player-currently-teleporting")));
                                clicktpa.getTeleportStatus().clear();
                                clicktpa.getServer().getScheduler().cancelTask(countdown);
                            }
                        }
                        return;
                    }, 0L, 20L);
                    return true;
                }
                if (!clicktpa.getTpaHere().containsKey(p) || !clicktpa.getHash().containsKey(p)) {
                    p.sendMessage(colorize(clicktpa.getConfig().getString("Player-no-pendingtpa-message")));
                    return true;
                }
                return true;
            }
            else if (target == null) {
                if (!clicktpa.getHash().containsKey(target) || clicktpa.getTpaHere().containsKey(target)) {
                    clicktpa.getHash().clear();
                    clicktpa.getTpaHere().clear();
                    p.sendMessage(colorize(clicktpa.getConfig().getString("Target-is-offline")));
                    return true;
                }
                if (clicktpa.getHash().containsKey(target) || clicktpa.getTpaHere().containsKey(target)) {
                    clicktpa.getHash().clear();
                    clicktpa.getTpaHere().clear();
                    p.sendMessage(colorize(clicktpa.getConfig().getString("Player-no-pendingtpa-message")));
                    return true;
                }
            }
        }
        return true;
    }
    
    public String colorize(final String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    
    public Commands(final ClickTpa pl) {
        console = Bukkit.getServer().getConsoleSender();
        clicktpa = pl;
    }
}