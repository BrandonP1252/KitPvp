package rezplugin.kitpvp.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import rezplugin.kitpvp.files.SpawnPointConfig;

import java.util.List;

public class ListSpawnPoint implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("kitpvp.kplistspawnpoints")) {
                SpawnPointConfig.get().options().copyDefaults(true);
                List<Location> locationList = (List<Location>) SpawnPointConfig.get().getList("spawn-point");
                for (Location location : locationList) {
                    int x = location.getBlockX();
                    int y = location.getBlockY();
                    int z = location.getBlockZ();
                    player.sendMessage("Spawn point: " + x + " / " + y + " / " + z);
                }


            }
        }

        return false;
    }
}
