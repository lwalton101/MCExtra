package no.nicknotname.mcextra;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.random.ChunkRandom;
import no.nicknotname.mcextra.register.ItemRegister;
import org.lwjgl.glfw.GLFW;

public class MCExtraClient implements ClientModInitializer {

    private static KeyBinding keyBinding;
    @Override
    public void onInitializeClient() {
        ModelPredicateProviderRegistry.register(ItemRegister.slimeChunkDetector, new Identifier("found_slime_chunk"), ((stack, world, entity, seed) -> {
            if(entity == null){
                return 0f;
            }
            ChunkPos chunkPos = new ChunkPos(entity.getBlockPos());
            float slimeChunk = ChunkRandom.getSlimeRandom(chunkPos.x, chunkPos.z, seed, 987234911L).nextInt(10);

            return slimeChunk == 0 ? 1.0f: 0.0f;

        }));

        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.mcextra.spook", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_R, // The keycode of the key
                "category.mcextra.test" // The translation key of the keybinding's category.
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {

            }
        });


    }
}
