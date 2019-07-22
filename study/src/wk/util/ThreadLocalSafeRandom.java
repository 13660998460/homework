package wk.util;

import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class ThreadLocalSafeRandom {

    private SecureRandom random = new SecureRandom();

    private ThreadLocalSafeRandom() {}

    private static final ThreadLocal<ThreadLocalSafeRandom> POOL = new ThreadLocal<>();

    public static ThreadLocalSafeRandom safeRandom() {
        if (POOL.get() == null) {
            POOL.set(new ThreadLocalSafeRandom());
        }
        return POOL.get();
    }

    public int next() {
        return random.nextInt();
    }

    public int next(int maxValue) {
        return random.nextInt(maxValue);
    }

    public float nextFloat() {
        return random.nextFloat();
    }

    /**
     * 
     * @param minValue
     *            最小值（包括）
     * @param maxValue
     *            最大值（不包括）
     */
    public int next(int minValue, int maxValue) {
        if (minValue < maxValue) {
            return random.nextInt(maxValue - minValue) + minValue;
        }
        return minValue;
    }

    public float next(float minValue, float maxValue) {
        if (minValue < maxValue) {
            return random.nextFloat() * (maxValue - minValue) + minValue;
        }
        return minValue;
    }

    public <K, V> Entry<K, V> randomMapEntry(Map<K, V> map) {
        if (map == null)
            return null;

        int index = random.nextInt(map.size());
        int i = 0;
        for (Entry<K, V> entry : map.entrySet()) {
            if (i == index) {
                return entry;
            }
            i++;
        }
        return null;
    }

    public <E> E randomListElement(List<E> list) {
        if (list == null || list.size() < 1)
            return null;
        return list.get(random.nextInt(list.size()));
    }

    public <E> E randomArrayElement(E[] array) {
        if (array == null || array.length < 1)
            return null;
        return array[random.nextInt(array.length)];
    }

    public <E> E randomSetElement(Set<E> set) {
        if (set == null)
            return null;
        int index = random.nextInt(set.size());
        int i = 0;
        for (E e : set) {
            if (index == i)
                return e;
            i++;
        }
        return null;
    }

}
