package rezplugin.kitpvp;

import org.bukkit.plugin.java.JavaPlugin;
import rezplugin.kitpvp.commands.ListSpawnPoint;
import rezplugin.kitpvp.commands.SetSpawnPoint;
import rezplugin.kitpvp.files.SpawnPointConfig;


public final class KitPvp extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        // regular config
        getConfig().options().copyDefaults();
        saveConfig();

        // spawn points config
        SpawnPointConfig.setup();
        SpawnPointConfig.get().options().copyDefaults(true);
        SpawnPointConfig.save();
        getCommand("kpsetspawnpoint").setExecutor(new SetSpawnPoint());
        getCommand("kplistspawnpoints").setExecutor(new ListSpawnPoint());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
