package wk.util;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 
 * @author wk
 * 
 *         常用的可静态导入方法
 * 
 *         eclipse设置路径：Preferences->Java->Editor->Content Assist->Favorites
 * 
 */
public class StaticImport {
    private StaticImport() {}

    // ----------- System ---------
    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static void sysout(Object s) {
        System.out.println(s);
    }

    public static void syserr(Object s) {
        System.err.println(s);
    }

    public static String lineSeparator() {
        return System.getProperties().getProperty("line.separator");
    }

    // ----------- Thread ---------
    public static Thread currentThread() {
        return Thread.currentThread();
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ----------- Comparator ---------
    public static <T, U extends Comparable<? super U>> Comparator<T>
        comparing(Function<? super T, ? extends U> keyExtractor) {
        return Comparator.comparing(keyExtractor);
    }

    public static <T, U> Comparator<T> comparing(Function<? super T, ? extends U> keyExtractor,
        Comparator<? super U> keyComparator) {
        return Comparator.comparing(keyExtractor, keyComparator);
    }

    public static <T> Comparator<T> reverseOrder() {
        return Collections.reverseOrder();
    }

    // ---------- Collectors ----------
    public static Collector<CharSequence, ?, String> joining() {
        return Collectors.joining();
    }

    public static Collector<CharSequence, ?, String> joining(CharSequence delimiter) {
        return Collectors.joining(delimiter);
    }

    public static <T, K> Collector<T, ?, Map<K, List<T>>> groupingBy(Function<? super T, ? extends K> classifier) {
        return Collectors.groupingBy(classifier);
    }

    public static <T> Collector<T, ?, List<T>> toList() {
        return Collectors.toList();
    }

    public static <T> Collector<T, ?, Set<T>> toSet() {
        return Collectors.toSet();
    }

    public static <T, K, U> Collector<T, ?, Map<K, U>> toMap(Function<? super T, ? extends K> keyMapper,
        Function<? super T, ? extends U> valueMapper) {
        return Collectors.toMap(keyMapper, valueMapper);
    }

    // ---------- Collections ----------
    public static <T extends Object & Comparable<? super T>> T min(Collection<? extends T> coll) {
        return Collections.min(coll);
    }

    public static <T> T min(Collection<? extends T> coll, Comparator<? super T> comp) {
        return Collections.min(coll, comp);
    }

    public static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll) {
        return Collections.max(coll);
    }

    public static <T> T max(Collection<? extends T> coll, Comparator<? super T> comp) {
        return Collections.max(coll, comp);
    }

    public static <T extends Comparable<? super T>> List<T> sort(List<T> list) {
        Collections.sort(list);
        return list;
    }

    public static <T> List<T> sort(List<T> list, Comparator<? super T> c) {
        Collections.sort(list, c);
        return list;
    }

    public static <T> List<T> shuffle(List<T> list) {
        Collections.shuffle(list);
        return list;
    }

    public static <T> Collection<T> unmodifiableCollection(Collection<? extends T> c) {
        return Collections.unmodifiableCollection(c);
    }

    // ---------- ThreadLocalRandom ----------
    public static ThreadLocalRandom random() {
        return ThreadLocalRandom.current();
    }

    public static ThreadLocalSafeRandom safeRandom() {
        // 性能差
        return ThreadLocalSafeRandom.safeRandom();
    }

    // ---------- Integer ----------
    public static String toBinaryString(int val) {
        return Integer.toBinaryString(val);
    }

    public static <T> T self(T t) {
        return t;
    }

    // ---------- Null ----------
    public static <T> T requireNonNull(T obj) {
        return Objects.requireNonNull(obj);
    }

}
