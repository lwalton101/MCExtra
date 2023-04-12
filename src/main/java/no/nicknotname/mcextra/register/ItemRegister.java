package no.nicknotname.mcextra.register;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import no.nicknotname.mcextra.item.AncientEyeItem;
import no.nicknotname.mcextra.item.SlimeChunkDetectorItem;

public class ItemRegister {
    public static final AncientEyeItem ancientEyeItem = new AncientEyeItem(new FabricItemSettings()
            .maxCount(64));

    public static final SlimeChunkDetectorItem slimeChunkDetector = new SlimeChunkDetectorItem(new FabricItemSettings().maxCount(1));

    public static void RegisterAll(){
        registerItem("ancient_eye", ancientEyeItem);
        registerItem("slime_chunk_detector", slimeChunkDetector);
    }

    private static void registerItem(String itemName, Item item){
        Registry.register(Registries.ITEM, new Identifier("mcextra", itemName), item);
    }

}
