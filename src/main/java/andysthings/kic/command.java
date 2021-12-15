package andysthings.kic;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class command implements CommandExecutor {
    FileConfiguration config = Kic.getPlugin(Kic.class).getConfig();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            if (config.get(player.getUniqueId().toString()) == "yes") {
                config.set(player.getUniqueId().toString(), "no");
                player.sendMessage(ChatColor.RED+"Your keep inventory has been turned off.");
                Kic.getPlugin(Kic.class).saveConfig();
                return true;
            } else {
                config.set(player.getUniqueId().toString(), "yes");
                player.sendMessage(ChatColor.GREEN+"Your keep inventory has been turned on.");
                Kic.getPlugin(Kic.class).saveConfig();
                return true;
            }
        }
        return true;
    }
}
