package andysthings.kic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class admincomands implements CommandExecutor {
    FileConfiguration config = Kic.getPlugin(Kic.class).getConfig();
    Plugin plugin = Kic.getPlugin(Kic.class);
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("kic.admin")) {
                if (args[0].equalsIgnoreCase("get")) {
                    if (config.get(Bukkit.getOfflinePlayer(args[1]).getUniqueId().toString()) == "no") {
                        player.sendMessage(ChatColor.RED+args[1]+"'s keep inventory is set to: "+config.get(Bukkit.getOfflinePlayer(args[1]).getUniqueId().toString()));
                        return true;
                    } else {
                        player.sendMessage(ChatColor.GREEN+args[1]+"'s keep inventory is set to: "+config.get(Bukkit.getOfflinePlayer(args[1]).getUniqueId().toString()));
                        return true;
                    }
                }
                if (args[0].equalsIgnoreCase("set")) {
                    if (config.get(Bukkit.getOfflinePlayer(args[1]).getUniqueId().toString()) == "yes") {
                        config.set(Bukkit.getOfflinePlayer(args[1]).getUniqueId().toString(), "no");
                        plugin.saveConfig();
                        player.sendMessage(ChatColor.RED+args[1]+"'s keep inventory is now turned off");
                        return true;
                    } else {
                        config.set(Bukkit.getOfflinePlayer(args[1]).getUniqueId().toString(), "yes");
                        plugin.saveConfig();
                        player.sendMessage(ChatColor.GREEN+args[1]+"'s keep inventory is now turned on");
                        return true;
                    }
                }
            }
        }
        return true;
    }
}
