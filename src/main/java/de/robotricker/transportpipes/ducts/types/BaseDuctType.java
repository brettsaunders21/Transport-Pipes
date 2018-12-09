package de.robotricker.transportpipes.ducts.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ch.jalu.injector.Injector;
import de.robotricker.transportpipes.DuctFactory;
import de.robotricker.transportpipes.DuctManager;
import de.robotricker.transportpipes.ItemManager;
import de.robotricker.transportpipes.ducts.Duct;
import de.robotricker.transportpipes.rendersystems.RenderSystem;

public final class BaseDuctType<T extends Duct> {

    private String name;
    private DuctManager<T> ductManager;
    private DuctFactory<T> ductFactory;
    private ItemManager<T> itemManager;
    private Set<RenderSystem> renderSystems;

    private List<DuctType> ductTypes;

    public BaseDuctType(String name, DuctManager<T> ductManager, DuctFactory<T> ductFactory, ItemManager<T> itemManager, Set<RenderSystem> renderSystems) {
        this.name = name;
        this.ductManager = ductManager;
        this.ductFactory = ductFactory;
        this.itemManager = itemManager;
        this.renderSystems = renderSystems;
        this.ductTypes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public DuctManager<T> getDuctManager() {
        return ductManager;
    }

    public DuctFactory<T> getDuctFactory() {
        return ductFactory;
    }

    public ItemManager<T> getItemManager() {
        return itemManager;
    }

    public Set<RenderSystem> getRenderSystems() {
        return renderSystems;
    }

    public boolean is(String name) {
        return this.name.equalsIgnoreCase(name);
    }

    // ****************************************************
    // DUCT TYPE
    // ****************************************************

    public List<DuctType> ductTypes() {
        return ductTypes;
    }

    public <S extends DuctType> S ductTypeOf(String displayName) {
        for (DuctType dt : ductTypes) {
            if (dt.getName().equalsIgnoreCase(displayName)) {
                return (S) dt;
            }
        }
        return null;
    }

    public void registerDuctType(DuctType ductType) {
        if (ductTypes.stream().anyMatch(dt -> dt.getName().equalsIgnoreCase(ductType.getName()))) {
            throw new IllegalArgumentException("DuctType '" + ductType.getName() + "' already exists");
        }
        ductTypes.add(ductType);
    }

}
