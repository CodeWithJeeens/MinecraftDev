package net.homecastle.oneblock;


import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.homecastle.oneblock.listener.BrakeBlock;
import net.homecastle.oneblock.listener.Join;
import net.homecastle.oneblock.listener.Quit;


public class Main extends JavaPlugin {
	
	
	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage("�a[OneBlock] wurde Erfolgrich Gestartet!");
		
		new Join(this);
		new Quit(this);
		new BrakeBlock(this);
		
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("�cOneBlock wurde Erfolgrich Beendet!");
	
	}
}
