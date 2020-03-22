package LinkedList;

/**
 * @description 链表节点类
 */
public class NodeList {

    public int length;
    //链表头，仅用于标示位置，不是实际节点
    private Node head;
    public NodeList(){
        length = 0;
        head = new Node();
    }
    /**
     * @description 返回链表长度
     */
    public int getLength(){
        return length;

    }
    /**
     * @description 新增一个链表节点,在末尾
     */
    public void addNode(Node node){
        Node temp = head;
        this.length++;
        //一直沿着链表遍历，直到链表末尾
        while (temp.next!=null){
            temp = temp.next;
        }
        temp.next = node;
    }
    /**
     * @description 指定位置新增一个链表节点
     */
    public boolean insertNode(int index,Node node){
        if(index < 0||index > length){
            System.out.println("插入点位置不合法！");
            return false;
        }
        Node temp = head;
        //遍历节点，index=0，则插入进链表最前面
        while (temp.next!=null&&index!=0){
            temp = temp.next;
            index-- ;
        }
            node.next = temp.next;
            temp.next = node;
            length++;
            return true;
    }
    /**
     * @description 指定位置删除一个节点
     */
    public boolean deleteNode(int index){
        if(index < 1||index > length){
            System.out.println("删除位置不合法");
            return false;
        }
        Node temp = head;
        //因为要删除一个节点，需要记录前一个节点的信息，故index--
        index--;
        while (temp.next!=null&&index!=0){
            temp = temp.next;
            index--;
        }
        temp.next = temp.next.next;
        return true;
    }

    /**
     * @description 遍历并打印整个链表
     */
    public void traverse(){
        //临时节点
        Node temp = head;
        while (temp.next!=null){
            temp = temp.next;
            System.out.println(temp.num);
        }
    }

    /**
     * @description  链表反转
     */
//    public NodeList reverse(){
//        Node p = null;
//        Node q = null;
//        Node r = null;
//        NodeList res = new NodeList();
//        if(length < 2){
//            p = head.next;
//            res.head.next = p;
//            return res;
//        }
//        p = head.next;
//        q = p.next;
//        while (length!=1){
//            length--;
//            //两个链表节点反转，记录后一个节点
//            r = q.next;
//            //链表节点反转
//            q.next = p;
//            //p移位
//            p = q;
//            //q移位
//            q = r;
//            //r移位
//            if(r!=null) {
//                r = r.next;
//            }
//        }
//        res.head.next = q;
//        res.length = this.length;
//        return res;
//    }

    public static void main(String[] args) {
        NodeList test = new NodeList();
        Node aNode = new Node(1);
        test.addNode(aNode);
        test.addNode(new Node(2));
        test.insertNode(2,new Node(3));
        //System.out.println(test.reverse().length);
       // test.reverse();

    }
}
