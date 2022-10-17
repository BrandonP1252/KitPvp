package rezplugin.kitpvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import rezplugin.kitpvp.KitPvp;
import rezplugin.kitpvp.files.SpawnPointConfig;

import java.util.ArrayList;
import java.util.List;

public class SetSpawnPoint implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("kitpvp.kpsetspawnpoint")) {
                ArrayList<Location> spawnPoints = new ArrayList<>();
                Location location = player.getLocation();
                if (SpawnPointConfig.get().getKeys(true).size() == 0) {
                    spawnPoints.add(location);
                }
                else {
                    spawnPoints.addAll((List<Location>) SpawnPointConfig.get().getList("spawn-point"));
                    spawnPoints.add(location);
                }
                SpawnPointConfig.get().set("spawn-point", spawnPoints);
                SpawnPointConfig.save();
                SpawnPointConfig.reload();
                player.sendMessage(ChatColor.GREEN + "Spawn point set.");
            }
            else {
                player.sendMessage("You do not have permission for kitpvp.kpsetspawnpoint");
            }
        }

        return false;
    }
}
