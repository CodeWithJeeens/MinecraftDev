package com.jklmao.plugin.utils;

import net.md_5.bungee.api.ChatColor;

public class Commons {

    // For the &1, &2, &3 color coding method

    private static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);

    }
/*
    public static void registerCommand(Command command) {
        try {
            final Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);

            final CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
            commandMap.register(command.getLabel(), command);


        }catch (final Exception e) {
            e.printStackTrace();
        }

 */
    }
