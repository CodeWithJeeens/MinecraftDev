package com.jklmao.plugin.events;

import com.jklmao.plugin.ClickTpa;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerEvents implements Listener {

    private ClickTpa clicktpa;

    public PlayerEvents(ClickTpa pl) {
        clicktpa = pl;
    }

    @EventHandler
    public void onWalk(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (clicktpa.getTeleportStatus().contains(p)) {
            if (e.getFrom().getX() != e.getTo().getX() || e.getFrom().getZ() != e.getTo().getZ()) { //block allways it's full
                clicktpa.getTeleportStatus().clear();
            }
        } else
            return;

    }

    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
