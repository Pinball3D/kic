package andysthings.kic;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;

public class papi_ext extends PlaceholderExpansion {
    private final Plugin plugin;

    public papi_ext(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getAuthor() {
        return "WillowCraft29";
    }

    @Override
    public String getIdentifier() {
        return "kic";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("kic")){
            return plugin.getConfig().get("placeholdervalue").toString().replace("{value}", plugin.getConfig().get(player.getUniqueId().toString()).toString());
        }

        return null; // Placeholder is unknown by the Expansion
    }
}
