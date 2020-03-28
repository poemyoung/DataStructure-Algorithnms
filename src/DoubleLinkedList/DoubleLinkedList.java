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
    public void addNode(int index,Object aValue){
        Node newNode = new Node(aValue);
        //0为插头后面，lenth为插尾后面
        if(index < 0||index > length){
            System.out.println("插入位置不合法！");
            return;
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
    public void switchNodes(int front,int after){
        //判断前后位置是否一致
        if(front >= after||front < 1||after > length){
            System.out.println("交换节点位置有误");
            return;
        }
        //遍历找到前节点和后节点
        Node frontNode = head;
        Node afterNode = head;
        //head.next为索引1的节点
        while (--front > 0){
            frontNode = frontNode.next;
        }
        while (--after > 0){
            afterNode = afterNode.next;
        }
        //节点交换算法

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
        test.addNode(0,a);
        test.addNode(0,b);
        test.addNode(2,c);
        test.traverse();
    }
}
