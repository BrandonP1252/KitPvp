package rezplugin.kitpvp;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import rezplugin.kitpvp.commands.DeleteSpawnPoint;
import rezplugin.kitpvp.commands.KitPvpStart;
import rezplugin.kitpvp.commands.ListSpawnPoint;
import rezplugin.kitpvp.commands.SetSpawnPoint;
import rezplugin.kitpvp.files.SpawnPointConfig;
import rezplugin.kitpvp.listeners.PlayerDamageAction;

import java.util.ArrayList;
import java.util.List;


public final class KitPvp extends JavaPlugin {

    public static KitPvp plugin;
    public List<Player> playerList;

    @Override
    public void onEnable() {
        // Plugin startup logic

        playerList = new ArrayList<>();
        plugin = this;

        // regular config
        getConfig().options().copyDefaults();
        saveConfig();

        // spawn points config
        setupSpawnPoints();

        // commands
        registerCommands();

        // listeners
        registerListeners();
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerDamageAction(), this);
    }

    private void registerCommands() {
        getCommand("kpsetspawnpoint").setExecutor(new SetSpawnPoint());
        getCommand("kplistspawnpoints").setExecutor(new ListSpawnPoint());
        getCommand("kpdelspawnpoint").setExecutor(new DeleteSpawnPoint());
        getCommand("kitpvp").setExecutor(new KitPvpStart());
    }

    private void setupSpawnPoints() {
        SpawnPointConfig.setup();
        SpawnPointConfig.get().options().copyDefaults(true);
        SpawnPointConfig.save();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
