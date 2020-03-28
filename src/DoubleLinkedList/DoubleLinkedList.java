package DoubleLinkedList;

/**
 * 双向链表
 */
public class DoubleLinkedList {
    //双向链表，有头有尾
    Node head;
    Node end;
    int length;

    //构造函数，首位相连
    public DoubleLinkedList(){
        head = new Node(new Object());
        end = new Node(new Object());
        head.next = end;
        end.prev = head;
        head.prev = null;
        end.next = null;
        length = 0;
    }

    /**
     * 双向链表节点类
     */
    private class Node{
        Object value;
        //前向指针和后向指针
        Node next;
        Node prev;
        //构造函数传递节点值
        public Node(Object obj){
           this.value = obj;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    /**
     * 插入算法
     */
    public DoubleLinkedList addNode(int index,Object aValue){
        Node newNode = new Node(aValue);
        //0为插头后面，lenth为插尾后面
        if(index < 0||index > length){
            System.out.println("插入位置不合法！");
            return this;
        }
        //插入位置前和插入位置后的节点
        Node tempF = head;
        Node tempA = null;
        //遍历
        while (--index >= 0){
            tempF = tempF.next;
        }
        //遍历完毕，确定tempF,再确定tempA
        tempA = tempF.next;
        //前后节点确定，进行插入
        newNode.next = tempA;
        newNode.prev = tempF;
        tempF.next = newNode;
        tempA.prev = newNode;
        //插入完毕长度加一
        length++;
        return this;
    }

    /**
     * 遍历算法
     */
    public void traverse(){
        Node temp = null;
        temp = head.next;
        while (temp!=end){
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }

    /**
     * 双向链表中的两个节点交换
     * 蚂蚁金服面试
     */
    public DoubleLinkedList switchNodes(int front,int after){
        //判断前后位置是否一致
        if(front >= after||front < 1||after > length){
            System.out.println("交换节点位置有误");
            return this;
        }
        //遍历找到前节点和后节点
        Node frontNode = head.next;
        Node afterNode = head.next;
        //相邻节点交换算法
        if(after - front == 1){
            //相邻节点交换算法
            while (--front > 0){
                frontNode = frontNode.next;
            }
            afterNode = frontNode.next;
            //Step1更改两个非交换节点
            frontNode.prev.next = afterNode;
            afterNode.next.prev = frontNode;
            //Step2更改两个交换节点
            //记录两个交换节点前面一个节点
            Node c = frontNode.prev;
            frontNode.prev = afterNode;
            frontNode.next = afterNode.next;
            afterNode.prev = c;
            afterNode.next = frontNode;
            return this;
        }
        //head.next为索引1的节点
        while (--front > 0){
            frontNode = frontNode.next;
        }
        while (--after > 0){
            afterNode = afterNode.next;
        }
        //节点交换算法

        //记录前面节点的前后节点
        Node frontNodeF = frontNode.prev;
        Node frontNodeA = frontNode.next;
        //Step1更改frontNode的左右节点
        frontNodeF.next = afterNode;
        frontNodeA.prev = afterNode;
        //Step2更改afterNode的左右节点
        afterNode.next.prev = frontNode;
        afterNode.prev.next = frontNode;
        //Step3更改frontNode的指针
        frontNode.next = afterNode.next;
        frontNode.prev = afterNode.prev;
        //Step4更改afterNode指针
        afterNode.next = frontNodeA;
        afterNode.prev = frontNodeF;
        return this;
    }

    public static void main(String[] args) {
        DoubleLinkedList test = new DoubleLinkedList();
        Phone a = new Phone();
        a.setName("联想");
        a.setPeriod("初中");
        Phone b = new Phone();
        b.setName("诺基亚");
        b.setPeriod("小学");
        Phone c = new Phone();
        c.setPeriod("高中");
        c.setName("小米");
        Phone d = new Phone();
        d.setName("oppor11");
        d.setPeriod("大一大二大三");
        test.addNode(0,a);
        test.addNode(0,b);
        test.addNode(2,c);
        test.addNode(3,d).switchNodes(1,2).traverse();
    }
}
