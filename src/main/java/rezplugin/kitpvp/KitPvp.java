package rezplugin.kitpvp;

import org.bukkit.plugin.java.JavaPlugin;
import rezplugin.kitpvp.commands.DeleteSpawnPoint;
import rezplugin.kitpvp.commands.KitPvpStart;
import rezplugin.kitpvp.commands.ListSpawnPoint;
import rezplugin.kitpvp.commands.SetSpawnPoint;
import rezplugin.kitpvp.files.SpawnPointConfig;
import rezplugin.kitpvp.listeners.PlayerDeathEve;


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

        // commands
        getCommand("kpsetspawnpoint").setExecutor(new SetSpawnPoint());
        getCommand("kplistspawnpoints").setExecutor(new ListSpawnPoint());
        getCommand("kpdelspawnpoint").setExecutor(new DeleteSpawnPoint());
        getCommand("kitpvp").setExecutor(new KitPvpStart());

        // listeners
        getServer().getPluginManager().registerEvents(new PlayerDeathEve(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
