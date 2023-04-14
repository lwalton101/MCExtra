package no.nicknotname.mcextra.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import no.nicknotname.mcextra.block.entity.XPBankBlockEntity;
import no.nicknotname.mcextra.register.BlockRegister;
import org.jetbrains.annotations.Nullable;

public class XPBankBlock extends BlockWithEntity {

    private int xpBackup = 0;
    public XPBankBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {

    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        XPBankBlockEntity blockEntity = (XPBankBlockEntity) world.getBlockEntity(pos);

        if(blockEntity == null || world.isClient || hand.equals(Hand.MAIN_HAND)){
            return super.onUse(state, world, pos, player, hand, hit);
        }

        blockEntity.handleClick(player);
        world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
        xpBackup = blockEntity.getXp();
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        if(world instanceof ServerWorld){
            ExperienceOrbEntity experienceOrbEntity = new ExperienceOrbEntity((ServerWorld)world, pos.getX(), pos.getY(), pos.getZ(), xpBackup);
            world.spawnEntity(experienceOrbEntity);
        }

        super.onBroken(world, pos, state);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new XPBankBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, BlockRegister.XP_BANK_BLOCK_ENTITY, (XPBankBlockEntity::tick));
    }
}
