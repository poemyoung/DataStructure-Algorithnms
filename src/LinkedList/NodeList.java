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
     * 单链表交换两个节点
     * 数据结构与算法习题
     */
    public NodeList switchNode(int front,int after){
        //临时节点，用于遍历
        Node temp = head;
        //前交换节点
        Node frontNode = null;
        Node afterNode = null;
        //判断输入是否符合要求
        if(front < 1||front > after||after > length){
            System.out.println("数组下标不对，无法进行交换");
        }
        //相邻两个节点的交换算法
        if(after - front == 1){
            //找到待交换节点的前一个节点
            front = front - 1;
            while (temp.next!= null&&front > 0){
                temp = temp.next;
                //找到该节点
               front--;
            }
            frontNode = temp.next;
            afterNode = temp.next.next;
            //链表节点交换算法
            temp.next = afterNode;
            frontNode.next = afterNode.next;
            afterNode.next = frontNode;
            return this;
        }
        //非相邻两个节点进行交换
        else {
            //找前一个节点
            front--;
            after--;
            //前面交换节点的前面节点
            Node frontFront = null;
            //后面交换节点的前面节点
            Node afterFront = null;
            while (temp.next!=null&&(front > 0||after > 0)){
                //找到了前一个节点
                if(front == 0){
                    frontFront = temp;
                    frontNode = temp.next;
                }
                //节点移动
                temp = temp.next;
                front--;
                after--;
            }
            afterFront = temp;
            afterNode = temp.next;
            Node afterAfter = afterNode.next;
            System.out.println(frontFront.num+""+frontNode.num+afterFront.num+afterNode.num);
            //交换
            //先交换两个节点本身
            afterNode.next = frontNode.next;
            frontNode.next = afterAfter;
            //再交换节点前后的节点
            frontFront.next = afterNode;
            afterFront.next = frontNode;
            return this;
        }
    }

    /**
     * @description  链表反转
     * 美团点评视频面试
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
        test.addNode(new Node(4)) ;
        test.switchNode(1,3).traverse();
        System.out.println("遍历前后分割线----");
        test.reverse().traverse();


    }
}
