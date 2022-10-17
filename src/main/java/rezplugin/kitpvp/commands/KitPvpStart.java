package rezplugin.kitpvp.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import rezplugin.kitpvp.KitPvp;
import rezplugin.kitpvp.files.SpawnPointConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KitPvpStart implements CommandExecutor {
    Boolean kitPvpActive = false;

    public Boolean getKitPvpActive() {
        return kitPvpActive;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // command will teleport user to a random spawn point and give them the kits
        if (sender instanceof Player) {
            Player player = (Player) sender;
            kitPvpActive = true;

            // teleport player to random spawn point location
            ArrayList<Location> spawnPoints = new ArrayList<Location>();
            spawnPoints.addAll((List<Location>) SpawnPointConfig.get().getList("spawn-point"));
            Random random = new Random();
            int spawnPointIndex = random.nextInt(spawnPoints.size());
            player.teleport(spawnPoints.get(spawnPointIndex));
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1f, 1f);

            // give player kit
            ItemStack ironSword = new ItemStack(Material.IRON_SWORD);
            ItemStack bow = new ItemStack(Material.BOW);
            ItemStack arrow = new ItemStack(Material.ARROW);
            arrow.setAmount(64);
            ItemStack helmet = new ItemStack(Material.IRON_HELMET);
            ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
            ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
            ItemStack boots = new ItemStack(Material.IRON_BOOTS);
            ItemStack steak = new ItemStack(Material.COOKED_BEEF);
            steak.setAmount(64);

            player.getInventory().setItem(0, ironSword);
            player.getInventory().setItem(1, bow);
            player.getInventory().setItem(2, arrow);
            player.getInventory().setItem(8, steak);
            player.getInventory().setHelmet(helmet);
            player.getInventory().setChestplate(chestplate);
            player.getInventory().setLeggings(leggings);
            player.getInventory().setBoots(boots);

            // add player to arraylist
            KitPvp.plugin.playerList.add(player);

        }

        return false;
    }
}
