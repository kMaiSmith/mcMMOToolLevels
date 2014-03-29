package com.thatonetechserver.mcmmotoollevels;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

import static com.gmail.nossr50.api.ExperienceAPI.getLevel;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by drew on 3/29/14.
 */
public class PlayerEventHandler implements Listener {

    FileConfiguration config;

    public PlayerEventHandler(FileConfiguration config) {
        this.config = config;
    }

    @EventHandler
    void onPlayerItemHeldEvent(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        Material heldItem;
        try {
            heldItem = player.getInventory().getItem(event.getNewSlot()).getType();
        } catch (NullPointerException e){
            return;
        }

        boolean canUse = canUseItem(player, heldItem);
        if(!canUse) {
            player.sendMessage("You are too inexperienced to use this tool.");
        }
        event.setCancelled(!canUse);
    }

    //TODO: Factor numbers/tools into config
    private boolean canUseItem(Player player, Material heldItem) {

        if(player.hasPermission("mcmmotoollevels.override")) {
            return true;
        }

        try {
            return getLevel(player, (String)config.get("tools." + heldItem.name() + ".skill")) >= (Integer)config.get("tools." + heldItem.name() + ".level");
        } catch (NullPointerException e) {
            return true;
        }
    }
}
