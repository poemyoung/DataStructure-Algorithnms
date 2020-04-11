package Deque;

import java.util.NoSuchElementException;

/**
 * 使用链表的方式实现双端队列
 * 双向链表，提高速度
 */
public class Deque<T>{
    /**
     * 维护一个内部节点类
     */
    private static class Node<T>{
        T obj;
        Node<T>next;
        Node<T>prev;
        public Node(){

        }
        public Node(T obj){
            this.obj = obj;
        }
        public T getObj(){
            return obj;
        }
    }
   //头节点和尾节点均包含数据
    Node<T>head;
    Node<T>tail;
    int size;
    /**
     * 构造函数进行初始化但保持空值
     */
    public Deque(){
        head = new Node<T>();
        tail = head;
        size = 0;
    }
    /**
     * 初始化第一个值
     */
    public Deque(T obj){
        head  = new Node<T>(obj);
        tail = head;
        size = 1;
    }
    /**
     * 判断是否为空方法
     */
    public boolean isEmpty(){
       return size <= 0;
    }
    /**
     * 插入前端
     */
    public void push(T x){
        Node<T>newNode = new Node<>(x);
        //判断是否是第一次插入
        if (!isEmpty()) {
            //非第一次插入
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }else {
            //是第一次插入
            head = newNode;
            //和tail连接在一起
            tail = head;
        }
        size++;
    }
    /**
     * 删除前端并将其返回
     */
    public T pop(){
        final T value;
        //为空
        if(isEmpty()){
            throw new NoSuchElementException("队列为空");
        }else if (head == tail){
            //只有一个元素
            size--;
            value =  head.obj;
            head.obj = null; //help GC
        }else {
            //多个元素
            size--;
            value = head.obj;
           head.obj = null;
           //清除节点指针
           Node<T> next = head.next;
           next.prev = null;
           head = next;
        }
        return value;
    }
    /**
     * 插入后端
     */
    public void inject(T x){
        if(isEmpty()){
            //是空
            tail = new Node<T>(x);
            head = tail;
        }else {
            //非空
            Node<T> newNode = new <T>Node(x);
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    /**
     * 删除后端
     */
    public T eject(){
        final T value;
        if(isEmpty()){
            throw new NoSuchElementException("队列为空");
        }else if(head == tail){
           value = tail.obj;
            tail.obj = null;
            tail = null;
        }else {
            value = tail.obj;
            tail.obj = null;
            Node<T> prev  = tail.prev;
            prev.next = null;
            tail = prev;
        }
        size--;
        return value;
    }
    /**
     * 遍历方法
     */
    public void traverse(){
        if(isEmpty()){
            System.out.println("队列为空");
        }else {
            for (Node<T>i = head;i!=tail.next;i = i.next){
                System.out.println(i.obj.toString());
            }
        }
    }
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        Deque<String> test = new Deque<String>();
        test.push("测试1");
        test.inject("测试2");
        test.push("测试3");
        test.inject("测试4");
        test.push("测试5");
        test.inject("测试6");
        test.inject("测试7");
        System.out.println(test.eject()+"-------");
        System.out.println(test.pop()+"--------");
        System.out.println(test.pop()+"--------");
        System.out.println(test.pop()+"--------");
        System.out.println(test.pop()+"--------");
        System.out.println(test.eject()+"-------");
        System.out.println(test.pop()+"--------");
        test.inject("测试8");
        test.traverse();
    }
}
