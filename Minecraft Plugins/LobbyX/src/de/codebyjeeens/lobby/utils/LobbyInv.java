package de.codebyjeeens.lobby.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class LobbyInv {

	Player p;
	
	public LobbyInv(Player p) {
		this.p = p;
	}
	
	public void setInv() {
		p.getInventory().setItem(0, new ItemApi("�b�lTeleporter", Material.COMPASS, (byte) 0, 1).build());
		p.getInventory().setItem(2, new ItemApi("�b�lSpieler Verstecken", Material.BLAZE_ROD, (byte) 0, 1).build());
		p.getInventory().setItem(4, new ItemApi("�b�lLobbys", Material.WATCH, (byte) 0, 1).build());
		p.updateInventory();
	}
	
}
