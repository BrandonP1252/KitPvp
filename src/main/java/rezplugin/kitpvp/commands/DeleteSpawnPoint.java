package rezplugin.kitpvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import rezplugin.kitpvp.files.SpawnPointConfig;

import java.util.ArrayList;
import java.util.List;

public class DeleteSpawnPoint implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("kitpvp.kpdelspawnpoint")) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "You need to specify which spawn point you want to remove.");
                }
                else {
                    ArrayList<Location> spawnPoints = new ArrayList<>();
                    if (SpawnPointConfig.get().getKeys(true).size() == 0){
                        player.sendMessage(ChatColor.RED + "There is nothing to delete.");

                    } else {
                        spawnPoints.addAll((List<Location>) SpawnPointConfig.get().getList("spawn-point"));
                        if (spawnPoints.isEmpty()) {
                            player.sendMessage(ChatColor.RED + "There is nothing to delete.");
                        }
                        else {
                            try {
                                int index = Integer.parseInt(args[0]) - 1;
                                if (index >= spawnPoints.size()) {
                                    player.sendMessage(ChatColor.RED + "Your selection does not exist.");
                                }
                                else {
                                    spawnPoints.remove(index);
                                    SpawnPointConfig.get().set("spawn-point", spawnPoints);
                                    SpawnPointConfig.save();
                                    SpawnPointConfig.reload();
                                    player.sendMessage(ChatColor.GREEN + "Spawn point #" + Integer.parseInt(args[0]) + " deleted.");
                                }
                            } catch (IllegalArgumentException e) {
                                player.sendMessage(ChatColor.RED + "Invalid argument: must be a positive integer.");
                            }
                        }

                    }
                }



            }


        }



        return false;
    }
}
