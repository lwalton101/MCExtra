package no.nicknotname.mcextra.register;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import no.nicknotname.mcextra.block.RecordPlayerBlock;
import no.nicknotname.mcextra.block.XPBankBlock;
import no.nicknotname.mcextra.block.entity.XPBankBlockEntity;

public class BlockRegister {

    public static final XPBankBlock XP_BANK_BLOCK = new XPBankBlock(FabricBlockSettings.of(Material.METAL, MapColor.EMERALD_GREEN)
            .strength(4.0f)
            .requiresTool()
    );

    public static final RecordPlayerBlock RECORD_PLAYER_BLOCK = new RecordPlayerBlock(FabricBlockSettings.of(Material.WOOD, MapColor.DIRT_BROWN)
            .strength(2.0f)
            .requiresTool()
            .nonOpaque()
    );

    public static final BlockEntityType<XPBankBlockEntity> XP_BANK_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier("mcextra", "xp_bank_block_entity"),
            FabricBlockEntityTypeBuilder.create(XPBankBlockEntity::new, XP_BANK_BLOCK).build());
    public static void RegisterAll(){
        registerBlock("xp_bank", XP_BANK_BLOCK, ItemGroupRegister.MCEXTRA_GROUP);
        registerBlock("record_player", RECORD_PLAYER_BLOCK, ItemGroupRegister.MCEXTRA_GROUP);
    }

    private static void registerBlock(String blockName, Block block, ItemGroup itemGroup){
        Registry.register(Registries.BLOCK, new Identifier("mcextra", blockName), block);
        ItemRegister.registerItem(blockName, new BlockItem(block, new FabricItemSettings()), itemGroup);
    }
}
