package net.homecastle.oneblock.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.homecastle.oneblock.Main;

public class Join implements Listener{
	
	public Main main;
	
	public Join(Main main) {
		this.main = main;
		Bukkit.getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void OnJoin( PlayerJoinEvent e) {
				
		Player p = e.getPlayer();
		World world = Bukkit.getServer().getWorld("world");
		
		Location Spawn = new Location(world, 0.5, 66, 0.5, 92, 0);
		Location BlockSpawn = new Location(world, 0, 65, 0);
		
		if(!p.hasPlayedBefore()) {
			e.setJoinMessage("?7?l[?a?l+?7?l]");
			BlockSpawn.getBlock().setType(Material.DIRT);
			p.teleport(Spawn);
		}
		else {
			e.setJoinMessage("?7?l[?a?l+?7?l]");
			p.teleport(Spawn);
		}
	}
	
}
