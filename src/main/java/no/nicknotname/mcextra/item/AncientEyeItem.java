package no.nicknotname.mcextra.item;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.EyeOfEnderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureKeys;

public class AncientEyeItem extends Item {
    public AncientEyeItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        user.setCurrentHand(hand);
        if (world instanceof ServerWorld serverWorld) {

            Registry<Structure> structureRegistry = serverWorld.getRegistryManager().get(RegistryKeys.STRUCTURE);
            if (structureRegistry.getEntry(StructureKeys.ANCIENT_CITY).isPresent()) {
                Pair<BlockPos, RegistryEntry<Structure>> pair = serverWorld.getChunkManager().getChunkGenerator().locateStructure(serverWorld, RegistryEntryList.of(structureRegistry.getEntry(StructureKeys.ANCIENT_CITY).get()), user.getBlockPos(), 100, false);
                if (pair != null) {
                    EyeOfEnderEntity eyeOfEnderEntity = new EyeOfEnderEntity(world, user.getX(), user.getBodyY(0.5), user.getZ());
                    eyeOfEnderEntity.setItem(itemStack);
                    eyeOfEnderEntity.initTargetPos(pair.getFirst());
                    world.emitGameEvent(GameEvent.PROJECTILE_SHOOT, eyeOfEnderEntity.getPos(), GameEvent.Emitter.of(user));
                    world.spawnEntity(eyeOfEnderEntity);

                    world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
                    world.syncWorldEvent(null, 1003, user.getBlockPos(), 0);
                    if (!user.getAbilities().creativeMode) {
                        itemStack.decrement(1);
                    }

                    user.incrementStat(Stats.USED.getOrCreateStat(this));
                    user.swingHand(hand, true);
                    return TypedActionResult.success(itemStack);
                }
            }
        }

        return super.use(world, user, hand);
    }
}
