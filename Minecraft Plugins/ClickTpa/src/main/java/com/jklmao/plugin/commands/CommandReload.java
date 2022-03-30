package com.jklmao.plugin.commands;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

import com.jklmao.plugin.ClickTpa;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CommandReload implements CommandExecutor{

	private ClickTpa clicktpa;
	public FileConfiguration config;
	public File configFile;
	
	
	public void reloadConfig() {
        if (this.configFile == null) {
            this.configFile = new File(this.clicktpa.getDataFolder(), "config.yml");
        }

        this.config = YamlConfiguration.loadConfiguration(this.configFile);

        final InputStream defaultStream = this.clicktpa.getResource("config.yml");
        if (defaultStream != null) {
            final YamlConfiguration defaultConfig = YamlConfiguration
                    .loadConfiguration(new InputStreamReader(defaultStream));
            this.config.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig() {
        if (this.config == null) {
            reloadConfig();
        }
        return this.config;
    }

    public void saveConfig() {
        if (this.config == null || this.configFile == null) {
            return;
        }

        try {
            this.getConfig().save(this.configFile);
        } catch (final IOException e) {
            this.clicktpa.getLogger().log(Level.SEVERE, "Could not save config to " + this.configFile, e);
        }
    }

    public void saveDefaultConfig() {
        if (this.configFile == null) {
            this.configFile = new File(this.clicktpa.getDataFolder(), "config.yml");

        }
        if (!this.configFile.exists()) {
            this.clicktpa.saveResource("config.yml", false);
        }
    }

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		String prefix = "&4&l[Click&b&lTpa]";
		
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (!p.hasPermission("clicktpa.reload")) {
				p.sendMessage((colorize(clicktpa.getConfig().getString("Insufficient-permission"))));
			}else {
				saveDefaultConfig();
				reloadConfig();
				
				sender.sendMessage(colorize(prefix + " &aThe plugin has been reloaded!"));
			}
		}else {
			saveDefaultConfig();
			reloadConfig();

			sender.sendMessage(colorize(prefix + " &aThe plugin has been reloaded!"));
		}
		
		
		return true;
	}
	
	public static String colorize(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
	
	public CommandReload(ClickTpa pl) {
		clicktpa = pl;
		saveDefaultConfig();
	}
}
