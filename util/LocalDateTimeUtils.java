package wk.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * java8新的日期时间API已具备足够的方法能够满足日常使用， 但因其有对不同国家地区时区的考量，故转换起来比较麻烦。
 *     此工具类是围绕LocalDateTime编写的简易转换方法，隐藏时区设置细节，并提供最常用的字符串转换。
 * 
 * @author wk
 */
public class LocalDateTimeUtils {

    /**
     * 中国时区
     */
    public static final ZoneOffset LOCAL_ZONE_OFFSET = ZoneOffset.of("+8");
    /**
     * 默认时间日期格式
     */
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认时间时期格式器
     */
    public static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_PATTERN);

    /**
     * LocalDateTime转毫秒
     */
    public static long toMillis(LocalDateTime localDateTime) {
        return localDateTime.toInstant(LOCAL_ZONE_OFFSET).toEpochMilli();
    }

    /**
     * 毫秒转LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(long millis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转Date
     */
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(LOCAL_ZONE_OFFSET));
    }

    /**
     * Date转LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转默认格式的String
     */
    public static String toString(LocalDateTime localDateTime) {
        return localDateTime.format(DEFAULT_FORMATTER);
    }

    /**
     * LocalDateTime转指定格式的String
     */
    public static String toString(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 默认格式的字符串转LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DEFAULT_FORMATTER);
    }

    /**
     * 指定格式的字符串转LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(String dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateTime, formatter);
    }
}
