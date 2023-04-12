package no.nicknotname.mcextra.register;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import no.nicknotname.mcextra.item.AncientEyeItem;

public class ItemRegister {
    public static final AncientEyeItem ancientEyeItem = new AncientEyeItem(new FabricItemSettings()
            .maxCount(64));

    public static void RegisterAll(){
        Registry.register(Registries.ITEM, new Identifier("mcextra", "ancient_eye"), ancientEyeItem);
    }

}
