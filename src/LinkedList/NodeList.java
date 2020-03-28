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
        //index在0到链表长度之间
        if(index < 0||index > length){
            System.out.println("插入点位置不合法！");
            return false;
        }
        //遍历节点
        //因为可能插入到头部之前，故从head开始遍历
        //注意head不是第一个节点，只是一个指向第一个节点的指针
        Node temp = head;
        //遍历节点，index=0，则插入进链表最前面
        //目的：找出插入位置的前一个节点
        while (temp.next!=null&&index!=0){
            temp = temp.next;
            index-- ;
        }
        //待插入节点next和插入位置前一个节点的next一样
            node.next = temp.next;
        //插入位置前一个节点的next赋值为待插入节点
            temp.next = node;
            //插入后长度加一
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
    public NodeList reverse(){
        //排除为空链表和只有一个节点的情况
        if(head.next == null){
            //空链表
            return this;
        }else {
            //只有一个节点
            if(head.next.next == null){
                return this;
            }
        }
       //节点，用来遍历
        Node temp = head.next.next;
        //节点，用来插入
         Node insert = head.next.next;
         //拷贝头节点
        Node newEnd  = null;
         try{
             newEnd = (Node)head.next.clone();
         }catch (CloneNotSupportedException e){
             System.out.println(e.getMessage());
         }
         //将新的头节点插入头部,形成只有一个节点的链表
        head.next = newEnd;
         //头部的该节点反转后应在尾部，故next指针为null
         newEnd.next = null;
         //遍历链表并把每个节点均插入头部
         while (temp!=null){
             //赋值给暂存节点，用于插入
             insert = temp;
             //遍历节点赋值后任务完成，遍历下一个节点
             temp = temp.next;
             //暂存节点插入头部
             insert.next = null;
             insertNode(0,insert);
         }
        return this;
    }

    public static void main(String[] args) {
        NodeList test = new NodeList();
        Node aNode = new Node(1);
        test.addNode(aNode);
        test.addNode(new Node(2));
        test.insertNode(2,new Node(3));

        test.traverse();
        System.out.println("遍历前后分割线----");
        test.reverse().traverse();


    }
}
