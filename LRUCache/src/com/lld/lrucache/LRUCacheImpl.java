import java.util.HashMap;

public class LRUCacheImpl extends LRUCache {
    private final int capacity;
    private final HashMap<Integer, ListNode> cache;
    private ListNode head = null;
    private ListNode tail = null;

    public LRUCacheImpl(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
    }

    @Override
    public void add(int key, int val) {
        if (cache.containsKey(key)) {
            cache.get(key).setVal(val);
            return;
        }

        if (cache.size() == capacity) {
            ListNode temp = this.head;
            this.head = this.head.getNext();
            temp.setNext(null);
            cache.remove(temp.getKey());
        }

        ListNode newNode = new ListNodeImpl(key, val);
        cache.put(key, newNode);
        if (head == null) {
            head = newNode;
            tail = head;
            return;
        }

        tail.setNext(newNode);
        newNode.setPrev(this.tail);
        tail = newNode;
    }

    @Override
    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        ListNode temp = cache.get(key);
        if (temp == tail) return temp.getVal();
        if (temp.getPrev() != null) {
            temp.getPrev().setNext(temp.getNext());
        }

        if (temp.getNext() != null) {
            temp.getNext().setPrev(temp.getPrev());
        }

        if (head == temp) {
            head = temp.getNext();
        }

        temp.setNext(null);
        temp.setPrev(null);
        this.tail.setNext(temp);
        temp.setPrev(this.tail);
        this.tail = temp;
        return this.tail.getVal();
    }

    @Override
    public void printString() {
        if (head == null) {
            System.out.println("Empty");
            return;
        }

        ListNode ptr = head;
        while (ptr.getNext() != null) {
            System.out.print(ptr.getKey() + ":" + ptr.getVal() + ", ");
            ptr = ptr.getNext();
        }

        System.out.println(ptr.getKey() + ":" + ptr.getVal());
    }
}
