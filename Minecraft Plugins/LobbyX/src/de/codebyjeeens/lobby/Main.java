package de.codebyjeeens.lobby;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import de.codebyjeeens.lobby.listener.JoinListener;

public class Main extends JavaPlugin implements Listener{

	@Override
	public void onEnable() {
		new JoinListener(this);
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
    @SuppressWarnings("deprecation")
	@EventHandler
    public void handle(PlayerInteractEvent event){
 
        Player player = event.getPlayer();
 
        if(event.getItem().getType() == Material.COMPASS){
 
            Inventory inventory = Bukkit.createInventory(null, 9*6, "§8» §6§lNavigator");
 
            //type-items
 
            player.openInventory(inventory);
 
 
            for(int i = 0; i < 4; i++){
 
                int time = i+1;
 
                int finalI = i;
                Player finalPlayer = player;
                Bukkit.getScheduler().scheduleAsyncDelayedTask(this, new Runnable() {
                    @Override
                    public void run() {
 
                        switch (finalI) {
                            case 0:
                                if(finalPlayer.getOpenInventory() != null && player.getOpenInventory().getTitle().equals("§8» §6§lNavigator")){
                                    finalPlayer.playSound(finalPlayer.getLocation(), Sound.NOTE_PLING,1,1);
                                    inventory.setItem(19, new ItemStack(Material.BED));
                                }else{
                                    return;
                                }
                                break;
                            case 1:
                                if(finalPlayer.getOpenInventory() != null && player.getOpenInventory().getTitle().equals("§8» §6§lNavigator")){
                                    finalPlayer.playSound(finalPlayer.getLocation(), Sound.NOTE_PLING,1,1);
                                    inventory.setItem(12, new ItemStack(Material.BEACON));
                                }else{
                                    return;
                                }
                                break;
                            case 2:
                                if(finalPlayer.getOpenInventory() != null && player.getOpenInventory().getTitle().equals("§8» §6§lNavigator")){
                                    finalPlayer.playSound(finalPlayer.getLocation(), Sound.NOTE_PLING,1,1);
                                    inventory.setItem(14, new ItemStack(Material.WATCH));
                                }else{
                                    return;
                                }
                                break;
                            case 3:
                                if(finalPlayer.getOpenInventory() != null && player.getOpenInventory().getTitle().equals("§8» §6§lNavigator")){
                                    finalPlayer.playSound(finalPlayer.getLocation(), Sound.NOTE_PLING,1,1);
                                    inventory.setItem(25, new ItemStack(Material.WOOD_PICKAXE));
                                }else{
                                    return;
                                }
                                break;
                        }
                    }
                }, i*10);
            }
        }
    }	
	
	
}
