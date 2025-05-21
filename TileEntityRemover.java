package me.kira.tileentityremover;

import org.bukkit.plugin.java.JavaPlugin;

public final class TileEntityRemover extends JavaPlugin {

    private ChunkLoadListener listener;

    @Override
    public void onEnable() {
        getLogger().info("TileEntityRemover has been enabled!");
        listener = new ChunkLoadListener(this);
        listener.setActive(true);
        getServer().getPluginManager().registerEvents(listener, this);
        getLogger().info("ChunkLoadListener has been registered successfully!");

        getCommand("reload_tile-entity-remover").setExecutor((sender, command, label, args) -> {
            // Check if the sender has the required permission
            if (!sender.hasPermission("TileEntityRemover.reload")) {
                sender.sendMessage("§cYou do not have permission to use this command.");
                return true;
            }

            // Reload configuration
            reloadConfig();
            listener.reloadConfig();
            sender.sendMessage("§aTileEntityRemover configuration reloaded!");
            return true;
        });

        getCommand("disable_tile-entity-remover").setExecutor((sender, command, label, args) -> {
            // Check if the sender has the required permission
            if (!sender.hasPermission("TileEntityRemover.disable")) {
                sender.sendMessage("§cYou do not have permission to use this command.");
                return true;
            }

            // Disables the plugin
            listener.setActive(false);
            sender.sendMessage("§aTileEntityRemover has been disabled");
            return true;
        });

        getCommand("enable_tile-entity-remover").setExecutor((sender, command, label, args) -> {
            // Check if the sender has the required permission
            if (!sender.hasPermission("TileEntityRemover.enable")) {
                sender.sendMessage("§cYou do not have permission to use this command.");
                return true;
            }

            // Disables the plugin
            listener.setActive(true);
            sender.sendMessage("§aTileEntityRemover has been enabled");
            return true;
        });

        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        listener.setActive(false);
        getLogger().info("TileEntityRemover plugin has been disabled");
    }
}
