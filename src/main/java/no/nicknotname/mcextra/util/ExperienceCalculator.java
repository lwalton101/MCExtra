package no.nicknotname.mcextra.util;

public class ExperienceCalculator {
    public static double LevelToPoints(int level){
        if(level <= 16){
            return Under16LevelToPoints(level);
        } else if (level <= 31) {
            return Level17To31LevelToPoints(level);
        } else{
            return Above32LevelToPoints(level);
        }

    }

    private static double Under16LevelToPoints(int level){
        return (level * level) + (6 * level);
    }

    private static double Level17To31LevelToPoints(int level){
        return 2.5 * (level * level) - (40.5 * level) + 360;
    }

    private static double Above32LevelToPoints(int level){
        return 4.5 * (level * level) - (162.5 * level) + 2220;
    }
}
