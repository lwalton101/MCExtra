package no.nicknotname.mcextra.register;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
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
        registerItem("ancient_eye", ancientEyeItem, ItemGroupRegister.MCEXTRA_GROUP);
        registerItem("slime_chunk_detector", slimeChunkDetector, ItemGroupRegister.MCEXTRA_GROUP);
    }

    public static void registerItem(String itemName, Item item, ItemGroup itemGroup){
        Registry.register(Registries.ITEM, new Identifier("mcextra", itemName), item);
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> entries.add(item));
    }

}
