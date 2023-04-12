package no.nicknotname.mcextra.register;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ItemGroupRegister {
    public static final ItemGroup MCEXTRA_GROUP = FabricItemGroup.builder(new Identifier("mcextra","mcextra_group"))
            .displayName(Text.translatable("itemgroup.mcextra.mcextragroup"))
            .icon(() -> new ItemStack(ItemRegister.ancientEyeItem))
            .build();
}
