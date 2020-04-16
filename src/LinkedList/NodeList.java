package LinkedList;

import javax.print.attribute.standard.NumberUp;
import java.util.Map;
import java.util.TreeSet;

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
            return this;
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

    /**
     * 单链表反转再次实现：使用递归方式
     */
    private Node rerRecursion(Node nowNode){
        //基准条件
        if(nowNode.next == null){
            //该节点作为第一个节点
            head.next = nowNode;
            return nowNode;
        }
        //递归
        //寻找下一个节点
        Node temp = rerRecursion(nowNode.next);
        //nowNode的下一个节点已经找到，不再需要nowNode.next
        nowNode.next = null;
        //使它的下一个节点指向当前节点
        temp.next = nowNode;
        //返回当前节点给上一个node进行处理
        return nowNode;
    }
    /**
     * 单链表反转启动方法
     */
    public NodeList reverseStart(){
        rerRecursion(head.next);
        return this;
    }


    /**
     * 找出链表倒数第k个节点
     * @param k
     */
    public Node findReverseSpec(int k){
        if(k > length){
            System.out.println("长度不足！");
            return null;
        }
        //两个节点指针进行移动
        Node front = head.next;
        Node after = head.next;
        //拉出k - 1 个距离
        for (int i = 1;i < k;i++){
            after = after.next;
        }
        //一起循环
        while (after.next!=null){
            after = after.next;
            front = front.next;
        }
        return front;
    }

    /**
     * 找出链表中间元素
     */
    public Node findMiddle(){
        //使用一个节点走一位，另一个节点走两位的方式找到中间节点
        Node front = head.next;
        Node after = head.next;
        while (after!= null&&after.next!=null){
            after = after.next.next;
            front = front.next;
        }
        return front;
    }
    /**
     * 链表排序（冒泡排序）
     */
    public NodeList sort(){
        Node temp = head.next;
        //冒泡排序的终点（终点及以后是已经排好的元素）
        Node end = null;
        //空链表和元素为1的链表链表不排序
        if(length == 0||length == 1){
            return this;
        }
        //排序
        //外循环改终点
        //记录位置
        int position;
      while (end!=head.next){
          //外层每次temp都从第一个节点开始
          temp = head.next;
          //内层循环做交换
          position = 1;
          while (temp.next!=end){
              //如果需要做交换
              if (temp.num > temp.next.num){
                  switchNode(position,position+1);
              }else {
                  //不需要做交换
                  temp = temp.next;
              }
              position++;
          }
          end = temp;
      }
        return this;
    }
    public static void main(String[] args) {
        NodeList test = new NodeList();
       test.addNode(new Node(10));
       test.addNode(new Node(2));
       test.addNode(new Node(5));
       test.addNode(new Node(9));
       test.addNode(new Node(4));
        test.addNode(new Node(13));
        test.addNode(new Node(1));
      // test.reverseStart().traverse();
        test = test.sort();
        test.traverse();
        System.out.println("------------");
        System.out.println(test.findMiddle().num);

    }
}
