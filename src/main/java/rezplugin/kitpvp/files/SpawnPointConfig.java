package rezplugin.kitpvp.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import rezplugin.kitpvp.KitPvp;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;

public class SpawnPointConfig {
    private static File file;
    private static FileConfiguration spawnPointFile;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("KitPvp").getDataFolder(), "spawnpoint.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("File could not be created.");
            }
        }
        spawnPointFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return spawnPointFile;
    }

    public static void save() {
        try {
            spawnPointFile.save(file);
        } catch (IOException e) {
            System.out.println("Could not save file.");
        }
    }
    public static void reload() {
        spawnPointFile = YamlConfiguration.loadConfiguration(file);
    }
}
