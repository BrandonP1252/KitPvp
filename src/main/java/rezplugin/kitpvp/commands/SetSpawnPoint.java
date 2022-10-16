package rezplugin.kitpvp.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import rezplugin.kitpvp.KitPvp;
import rezplugin.kitpvp.files.SpawnPointConfig;

import java.util.ArrayList;

public class SetSpawnPoint implements CommandExecutor {

    ArrayList<Location> spawnPoints = new ArrayList<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("kitpvp.kpsetspawnpoint")) {
                Location location = player.getLocation();
                spawnPoints.add(location);
                SpawnPointConfig.get().set("spawn-point", spawnPoints);
                SpawnPointConfig.save();
                SpawnPointConfig.reload();
            }
            else {
                player.sendMessage("bruh");
            }
        }

        return false;
    }
}
