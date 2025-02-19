public class ListNodeImpl extends ListNode {
    private final int key;
    private int val;
    private ListNode next;
    private ListNode prev;

    public ListNodeImpl(int key, int val) {
        this.key = key;
        this.val = val;
        this.next = null;
        this.prev = null;
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public int getVal() {
        return val;
    }

    @Override
    public void setVal(int val) {
        this.val = val;
    }

    @Override
    public void setNext(ListNode listNode) {
        this.next = listNode;
    }

    @Override
    public ListNode getNext() {
        return this.next;
    }

    @Override
    public void setPrev(ListNode listNode) {
        this.prev = listNode;
    }

    @Override
    public ListNode getPrev() {
        return this.prev;
    }
}
