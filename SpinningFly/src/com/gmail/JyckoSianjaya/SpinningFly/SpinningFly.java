package com.gmail.JyckoSianjaya.SpinningFly;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.minecraft.server.v1_13_R2.Entity;
import net.minecraft.server.v1_13_R2.PacketPlayOutEntityHeadRotation;

public final class SpinningFly extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getServer().getConsoleSender().sendMessage("ILLUMINATI CONFIRMED!");
	}
	@Override
	public void onDisable() {
	}
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (!p.isFlying()) return;
		if (!p.hasPermission("illuminati.confirmed")) return;
		Location loc = p.getLocation().clone();
		loc.setYaw(loc.getYaw() + 180.0F);
		CraftPlayer craft = (CraftPlayer) p;
		PacketPlayOutEntityHeadRotation packet = new PacketPlayOutEntityHeadRotation(craft.getHandle(), (byte) loc.getYaw());
		for (Player pl : Bukkit.getOnlinePlayers()) {
			CraftPlayer cp = (CraftPlayer) pl;
			cp.getHandle().playerConnection.sendPacket(packet);
		}
	}
}
