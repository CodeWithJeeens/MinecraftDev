package net.homecastle.oneblock.listener;





import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import net.homecastle.oneblock.Main;


public class BrakeBlock implements Listener{
	
	public Main main;
	
	public BrakeBlock (Main main) {
		this.main = main;
		Bukkit.getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
    public void onBrakeBlock (BlockBreakEvent e) {
		
		//Drop Location
		Block block = e.getBlock();
		Location loc = e.getBlock().getLocation().add(0.5, 0, 0.5);
		//Block Location
		World world = Bukkit.getServer().getWorld("world");
		Location BlockSpawn = new Location(world, 0, 65, 0);
		
		// If is a ONEBLOCK Block
		if (e.getBlock().getLocation().equals(BlockSpawn)) {
			
			// GoldOre -> GoldIngot
		    if (block.getType() == Material.GOLD_ORE){
		        e.setCancelled(true);
		        loc.getWorld().dropItem(loc,new ItemStack(Material.GOLD_INGOT, 2));
		    }
			
			
		}
		
	}

}
