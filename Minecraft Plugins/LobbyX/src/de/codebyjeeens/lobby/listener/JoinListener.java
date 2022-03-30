package de.codebyjeeens.lobby.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.codebyjeeens.lobby.Main;
import de.codebyjeeens.lobby.utils.LobbyInv;

public class JoinListener implements Listener {

	public Main main;
	
	public JoinListener(Main main) {
		this.main = main;
		Bukkit.getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		new LobbyInv(e.getPlayer()).setInv();
		
	}
		
}
