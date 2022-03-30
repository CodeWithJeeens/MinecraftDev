package net.homecastle.oneblock.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.homecastle.oneblock.Main;

public class Quit implements Listener{
	
	public Main main;
	
	public Quit(Main main) {
		this.main = main;
		Bukkit.getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void OnQuit( PlayerJoinEvent p) {
		p.setJoinMessage("�7�l[�a�l+�7�l]");
	}

}
