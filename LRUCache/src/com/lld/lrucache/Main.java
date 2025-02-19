/**
 * LRUCache % javac -d ./build/ ./src/com/lld/lrucache/*.java
 * LRUCache % cd build
 * build % java Main
 */
public class Main {
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCacheImpl(3);
        lRUCache.add(1, 1);
        lRUCache.add(2, 2);
        System.out.println(lRUCache.get(1));
        lRUCache.add(3, 3);
        lRUCache.add(4, 4);
        System.out.println(lRUCache.get(4));
        System.out.println(lRUCache.get(2));
        System.out.println(lRUCache.get(3));
        lRUCache.add(5, 5);
        System.out.println(lRUCache.get(5));
    }
}
