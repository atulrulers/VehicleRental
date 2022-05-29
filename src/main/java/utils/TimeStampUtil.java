package utils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.time.Instant;

@UtilityClass
public class TimeStampUtil {

    public static Long getTimeStamp() {
        Instant instant = Instant.now();
        return instant.getEpochSecond();
    }

    public static Long getTimeInHour(@NonNull final Long startTime, @NonNull final Long endTime) {
        final double timeSpent = (double) endTime - (double) startTime;
        return (long) Math.ceil(timeSpent / 3600);
    }
}
