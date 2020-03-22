package LinkedList;

public class Node {
    //链表节点的值
    public int num;
    //链表节点指针，指向下一个节点
    public Node next;

    //链表构造函数，给节点赋值
    public Node(int aNum) {
        this.num = aNum;
        this.next = null;
    }
    //空构造函数，用于在链表节点变化时使用
    public Node(){
        this.num = 0;
        next = null;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Node a = new Node(this.num);
        a.next = this.next;
        return a;
    }
}