package me.kira.tileentityremover;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.BitSet;
import java.util.List;
import java.util.HashMap;
import java.util.Set;

public class ChunkLoadListener implements Listener{
    private final HashMap<String, BitSet> Tile_world_configurations = new HashMap<>();

    private final TileEntityRemover plugin;
    private boolean isActive;

    public ChunkLoadListener(TileEntityRemover plugin) {
        this.plugin = plugin;
        this.reloadConfig();
    }

    public void reloadConfig() {
        Tile_world_configurations.clear();
        FileConfiguration config = plugin.getConfig();

        ConfigurationSection worldsSection = config.getConfigurationSection("worlds");

        if (worldsSection == null || worldsSection.getKeys(false).isEmpty()) {
            plugin.getLogger().warning("No worlds found in config or no world added to worlds.");
            return;
        }

        Set<String> worlds = worldsSection.getKeys(false);

        for (String world : worlds) {
            List<String> world_entities = config.getStringList("worlds." + world);

            final BitSet Tile_entities = new BitSet();

            for (String entityName : world_entities) {
                try {
                    Material material = Material.valueOf(entityName);
                    int ordinal = material.ordinal();
                    if (!Tile_entities.get(ordinal)) {
                        Tile_entities.set(ordinal);
                    }
                } catch (IllegalArgumentException e) {
                    plugin.getLogger().warning("Invalid tile entity type in config: " + entityName);
                }
            }
            Tile_world_configurations.put(world, Tile_entities);
        }
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        if (!this.isActive) return;

        BitSet current_world_configurations = Tile_world_configurations.get(event.getWorld().getName());

        if (current_world_configurations == null) return;

        BlockState[] tileEntities = event.getChunk().getTileEntities();

        for(BlockState state : tileEntities) {
            Block block = state.getBlock();
            if (current_world_configurations.get(block.getType().ordinal())) {
                block.setType(Material.AIR, false);
            }
        }
    }

    public void setActive(boolean value) {
        isActive = value;
    }
}