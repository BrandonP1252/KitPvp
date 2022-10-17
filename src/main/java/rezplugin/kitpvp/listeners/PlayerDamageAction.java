package rezplugin.kitpvp.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import rezplugin.kitpvp.KitPvp;
import rezplugin.kitpvp.files.SpawnPointConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerDamageAction implements Listener {

    @EventHandler
    public void onPlayerDamageEvent(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntity();

        if (player.getHealth() - event.getFinalDamage() <= 0 && KitPvp.plugin.playerList.contains(player)) {
            event.setCancelled(true);
            ArrayList<Location> spawnPoints = new ArrayList<Location>();
            spawnPoints.addAll((List<Location>) SpawnPointConfig.get().getList("spawn-point"));
            Random random = new Random();
            int spawnPointIndex = random.nextInt(spawnPoints.size());
            player.teleport(spawnPoints.get(spawnPointIndex));
            player.sendMessage("you died bruh");
            player.setHealth(20);
        }
    }

}
