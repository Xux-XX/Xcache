package api;

import java.util.Map;

/**
 * 缓存上下文
 * @author xux
 * @since 0.0.1
 * @since 2024/7/19 下午2:06
 */
public interface ICacheContext<K, V> {
    /**
     * map 信息
     * @return map
     */
    Map<K, V> map();

    /**
     * 大小限制
     * @return 大小限制
     */
    int sizeLimit();

    /**
     * 驱除策略
     * @return 策略
     */
    ICacheEvict<K,V> cacheEvict();
}
