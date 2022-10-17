package rezplugin.kitpvp.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import rezplugin.kitpvp.commands.KitPvpStart;
import rezplugin.kitpvp.files.SpawnPointConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerDeathEve implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        KitPvpStart kitPvpStart = new KitPvpStart();
        Boolean isActive = kitPvpStart.getKitPvpActive();
        Player player = event.getEntity().getPlayer();

        if (isActive) {
            ArrayList<Location> spawnPoints = new ArrayList<Location>();
            spawnPoints.addAll((List<Location>) SpawnPointConfig.get().getList("spawn-point"));
            Random random = new Random();
            int spawnPointIndex = random.nextInt(spawnPoints.size());
            player.teleport(spawnPoints.get(spawnPointIndex));
            player.sendMessage("you died bruh");
        }
        else {
            player.sendMessage("you aint in kit pvp");
        }


    }

}
