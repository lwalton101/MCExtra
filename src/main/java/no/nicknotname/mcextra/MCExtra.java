package no.nicknotname.mcextra;

import net.fabricmc.api.ModInitializer;
import no.nicknotname.mcextra.register.BlockRegister;
import no.nicknotname.mcextra.register.ItemRegister;
import no.nicknotname.mcextra.util.ExperienceCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MCExtra implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("mcextra");

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("Hello Fabric world!");
        LOGGER.info(String.valueOf(ExperienceCalculator.LevelToPoints(5)));
        LOGGER.info(String.valueOf(ExperienceCalculator.LevelToPoints(30)));
        LOGGER.info(String.valueOf(ExperienceCalculator.LevelToPoints(50)));
        ItemRegister.RegisterAll();
        BlockRegister.RegisterAll();
    }
}
