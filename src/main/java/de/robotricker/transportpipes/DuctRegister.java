package de.robotricker.transportpipes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import de.robotricker.transportpipes.ducts.Duct;
import de.robotricker.transportpipes.ducts.types.BaseDuctType;
import de.robotricker.transportpipes.rendersystems.RenderSystem;

public class DuctRegister {

    private List<BaseDuctType<? extends Duct>> baseDuctTypes;

    private TransportPipes plugin;

    @Inject
    public DuctRegister(TransportPipes plugin) {
        this.plugin = plugin;
        this.baseDuctTypes = new ArrayList<>();
    }

    public <T extends Duct> void registerBaseDuctType(String name, Class<? extends DuctManager<T>> ductManagerClass, Class<? extends DuctFactory<T>> ductFactoryClass, Class<? extends ItemManager<T>> itemManagerClass, Set<RenderSystem> renderSystems) {
        if (baseDuctTypes.stream().anyMatch(bdt -> bdt.getName().equalsIgnoreCase(name))) {
            throw new IllegalArgumentException("BaseDuctType '" + name + "' already exists");
        }
        BaseDuctType<T> baseDuctType = new BaseDuctType<>(name, plugin.getInjector().newInstance(ductManagerClass), plugin.getInjector().newInstance(ductFactoryClass), plugin.getInjector().newInstance(itemManagerClass), renderSystems);
        this.baseDuctTypes.add(baseDuctType);
        baseDuctType.getDuctManager().registerDuctTypes();
        baseDuctType.getItemManager().registerItems();
    }

    public List<BaseDuctType<? extends Duct>> baseDuctTypes() {
        return baseDuctTypes;
    }

    public <T extends Duct> BaseDuctType<T> baseDuctTypeOf(String displayName) {
        return (BaseDuctType<T>) baseDuctTypes().stream().filter(bdt -> bdt.getName().equalsIgnoreCase(displayName)).findAny().orElse(null);
    }

}
