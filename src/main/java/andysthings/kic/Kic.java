package andysthings.kic;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Kic extends JavaPlugin implements Listener {
    FileConfiguration file = getConfig();

    @Override
    public void onEnable() {
        int pluginId = 13605;
        Metrics metrics = new Metrics(this, pluginId);
        file.options().copyDefaults(true);
        saveConfig();
        getCommand("keepinv").setExecutor(new command());
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!event.getPlayer().hasPlayedBefore()) {
            file.set(event.getPlayer().getUniqueId().toString(), "yes");
            saveConfig();
        }
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (getConfig().get(event.getEntity().getUniqueId().toString()) == "yes") {
            event.setKeepInventory(true);
            event.setKeepLevel(true);
            event.getDrops().clear();
            event.setDroppedExp(0);
            saveConfig();
        }
    }
}
