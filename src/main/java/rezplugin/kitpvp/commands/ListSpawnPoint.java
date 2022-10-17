package rezplugin.kitpvp.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
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
                if (SpawnPointConfig.get().getKeys(true).size() == 0 || locationList.isEmpty()) {
                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD +"There are currently no spawn points.");
                } else {
                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "List of current spawn points");
                    int counter = 1;
                    for (Location location : locationList) {
                        int x = location.getBlockX();
                        int y = location.getBlockY();
                        int z = location.getBlockZ();
                        TextComponent component = new TextComponent(ChatColor.YELLOW + "Spawn point #"+ counter +": " + x + " / " + y + " / " + z);
                        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click to teleport to spawn point #" + counter)));
                        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/teleport " + x +" " + y + " " + z));
                        player.spigot().sendMessage(component);
                        counter++;
                    }

                }

            }
        }

        return false;
    }
}
