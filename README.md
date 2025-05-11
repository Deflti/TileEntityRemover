# TileEntityRemover

**A Fast and Efficient Plugin for Removing Tile Entities in Minecraft**

## Description

TileEntityRemover is a high-performance Minecraft plugin that automatically removes specified tile entities (e.g., spawners) from chunks as they load. This helps improve server performance by eliminating unnecessary or resource-intensive blocks. The plugin is lightweight, easy to configure, and operates seamlessly with administrator permissions.

## Features

- ✅ Automatically removes configured tile entities when chunks load  
- ✅ Supports per-world configuration  
- ✅ Simple command interface: reload, enable, and disable  
- ✅ Admin-only access via permissions  
- ✅ Optimized for performance  

## Installation & Configuration

1. Download the plugin and place the `.jar` file in your server’s `plugins/` directory.  
2. Restart your server.  
3. A default `config.yml` will be generated in the `TileEntityRemover` plugin folder.  
4. Configure tile entities to remove under the `worlds:` section. Example:
   ```yaml
   worlds:
     world:
       - SPAWNER
     world_nether:
       - SPAWNER
5. Save the file and reload the configuration using the `/reload_tile-entity-remover` command.

## Commands

`/reload_tile-entity-remover`
`/disable_tile-entity-remover`
`/enable_tile-entity-remover`

## Permissions

To execute commands, a player must have the corresponding permissions. By default, all permissions are granted to `OPs`.
