package andysthings.kic;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Kic extends JavaPlugin implements Listener {
    FileConfiguration file = getConfig();
    String permission;
    @Override
    public void onEnable() {
        int pluginId = 13605;
        Metrics metrics = new Metrics(this, pluginId);
        file.addDefault("usePermission", "false");
        file.options().header("placeholdervalue is the value the placeholder %kic% will return. use {value} to get the value of the player");
        file.addDefault("placeholdervalue", "Keep Inventory: {value}");
        file.options().copyDefaults(true);
        saveConfig();
        getCommand("keepinv").setExecutor(new command());
        getCommand("kic").setExecutor(new admincomands());
        getCommand("kic").setTabCompleter(new admintab());
        getServer().getPluginManager().registerEvents(this, this);
        permission = file.get("usePermission").toString();
        //if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
        //new papi_ext(this).register();
        //}
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
