package de.robotricker.transportpipes.rendersystems.pipe.vanilla.model;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

import de.robotricker.transportpipes.protocol.ArmorStandData;
import de.robotricker.transportpipes.rendersystems.pipe.vanilla.model.data.VanillaIronPipeModelData;
import de.robotricker.transportpipes.rendersystems.pipe.vanilla.model.data.VanillaPipeModelData;
import de.robotricker.transportpipes.utils.RelLoc;
import de.robotricker.transportpipes.utils.TPDirection;
import de.robotricker.transportpipes.utils.hitbox.AxisAlignedBB;

public class VanillaPipeModelMID extends VanillaPipeModel {

    public VanillaPipeModelMID() {
        super();
        aabb = new AxisAlignedBB(0.25d, 0.25d, 0.25d, 0.75d, 0.75d, 0.75d);
    }

    @Override
    public List<ArmorStandData> createASD(VanillaPipeModelData data) {
        if (data.getPipeType().is("Iron")) {
            TPDirection outputDir = ((VanillaIronPipeModelData) data).getOutputDir();
            return createComplexBlockASD(modelBlocks.get(data.getPipeType()),
                    outputDir == TPDirection.EAST ? ITEM_CARPET_YELLOW : ITEM_CARPET_WHITE,
                    outputDir == TPDirection.WEST ? ITEM_CARPET_YELLOW : ITEM_CARPET_WHITE,
                    outputDir == TPDirection.NORTH ? ITEM_CARPET_YELLOW : ITEM_CARPET_WHITE,
                    outputDir == TPDirection.SOUTH ? ITEM_CARPET_YELLOW : ITEM_CARPET_WHITE,
                    outputDir == TPDirection.UP ? ITEM_CARPET_YELLOW : ITEM_CARPET_WHITE,
                    outputDir == TPDirection.DOWN ? ITEM_CARPET_YELLOW : ITEM_CARPET_WHITE
            );
        } else if (data.getPipeType().is("Golden")) {
            return createComplexBlockASD(modelBlocks.get(data.getPipeType()),
                    ITEM_CARPET_BLUE,
                    ITEM_CARPET_YELLOW,
                    ITEM_CARPET_RED,
                    ITEM_CARPET_WHITE,
                    ITEM_CARPET_GREEN,
                    ITEM_CARPET_BLACK
            );
        } else {
            return createSimpleBlockASD(modelBlocks.get(data.getPipeType()));
        }
    }

    private List<ArmorStandData> createSimpleBlockASD(ItemStack block) {
        List<ArmorStandData> asd = new ArrayList<>();
        asd.add(new ArmorStandData(new RelLoc(0.5f, -0.43f, 0.5f), true, new Vector(1, 0, 0), new Vector(0, 0, 0), new Vector(0, 0, 0), block, null));
        return asd;
    }

    private List<ArmorStandData> createComplexBlockASD(ItemStack centerBlock, ItemStack eastCarpet, ItemStack westCarpet, ItemStack northCarpet, ItemStack southCarpet, ItemStack topCarpet, ItemStack bottomCarpet) {
        List<ArmorStandData> asd = new ArrayList<>();
        asd.add(new ArmorStandData(new RelLoc(0.5f, -0.43f, 0.5f), true, new Vector(1, 0, 0), new Vector(0f, 0f, 0f), new Vector(0f, 0f, 0f), centerBlock, null));
        asd.add(new ArmorStandData(new RelLoc(0.5f + 0.26f, -0.255f, 0.5f), true, new Vector(1, 0, 0), new Vector(90f, 0f, 0f), new Vector(0f, 0f, 0f), eastCarpet, null));
        asd.add(new ArmorStandData(new RelLoc(0.5f - 0.26f, -0.255f, 0.5f), true, new Vector(-1, 0, 0), new Vector(90f, 0f, 0f), new Vector(0f, 0f, 0f), westCarpet, null));
        asd.add(new ArmorStandData(new RelLoc(0.5f, -0.255f, 0.5f + 0.26f), true, new Vector(0, 0, 1), new Vector(90f, 0f, 0f), new Vector(0f, 0f, 0f), southCarpet, null));
        asd.add(new ArmorStandData(new RelLoc(0.5f, -0.255f, 0.5f - 0.26f), true, new Vector(0, 0, -1), new Vector(90f, 0f, 0f), new Vector(0f, 0f, 0f), northCarpet, null));
        asd.add(new ArmorStandData(new RelLoc(0.5f, -0.255f + 0.26f, 0.5f), true, new Vector(1, 0, 0), new Vector(0f, 0f, 0f), new Vector(0f, 0f, 0f), topCarpet, null));
        asd.add(new ArmorStandData(new RelLoc(0.5f, -0.255f - 0.26f, 0.5f), true, new Vector(1, 0, 0), new Vector(180f, 0f, 0f), new Vector(0f, 0f, 0f), bottomCarpet, null));
        return asd;
    }

}
