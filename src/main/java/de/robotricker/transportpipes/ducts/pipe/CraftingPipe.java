package de.robotricker.transportpipes.ducts.pipe;

import org.bukkit.Chunk;
import org.bukkit.World;

import de.robotricker.transportpipes.ducts.types.BaseDuctType;
import de.robotricker.transportpipes.location.BlockLocation;

public class CraftingPipe extends Pipe {

    public CraftingPipe(DuctService ductService, BlockLocation blockLoc, World world, Chunk chunk) {
        super(ductService, blockLoc, world, chunk, BaseDuctType.valueOf("Pipe").ductTypeValueOf("Crafting"));
    }
}