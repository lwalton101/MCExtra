package no.nicknotname.mcextra;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.random.ChunkRandom;
import no.nicknotname.mcextra.register.ItemRegister;

public class MCExtraClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelPredicateProviderRegistry.register(ItemRegister.slimeChunkDetector, new Identifier("found_slime_chunk"), ((stack, world, entity, seed) -> {
            if(entity == null){
                return 0f;
            }
            ChunkPos chunkPos = new ChunkPos(entity.getBlockPos());
            float slimeChunk = ChunkRandom.getSlimeRandom(chunkPos.x, chunkPos.z, seed, 987234911L).nextInt(10);
            MCExtra.LOGGER.info(String.valueOf(slimeChunk));

            return slimeChunk == 0 ? 1.0f: 0.0f;

        }));
    }
}
