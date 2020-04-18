package TreeSet;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * 编写TreeSet类实现程序，其中相关迭代器使用二叉查找树
 * 在每个节点上添加通向下一个最小节点和下一个最大节点的链
 * 添加头节点和尾节点，不属于二叉树的一部分，但有助于使得程序的链表部分更简单
 * @param <E>
 */
public class MyTreeSetTwo<E extends Comparable<? super E>>implements MyTreeSet<E>{
    /**
     * 节点类
     */
    private class Node<E extends Comparable<? super E>>{
        E value;
        Node<E> left;
        Node<E> right;
        //下一个最小节点
        Node<E> nextMin;
        Node<E> nextMax;

        public Node(E value, Node<E> left, Node<E> right, Node<E> nextMin, Node<E> nextMax) {
            this.value = value;
            this.left = left;
            this.right = right;
            this.nextMin = nextMin;
            this.nextMax = nextMax;
        }
    }
    /**
     * 迭代器类
     */
    private class MyIterator<E> implements Iterator<E>{
        private int expectedModCount = modCount;
        boolean isEnd ;
        private Node current;

        public MyIterator() {
            if(length > 0){
                isEnd = false;
                head.nextMin = findMin(root);
                head.nextMax = findMax(root);
                findMin(root).nextMin = end;
                findMax(root).nextMax =end;
                current = head.nextMin;
            }else {
                isEnd = true;
            }
        }

        @Override
        public boolean hasNext() {
            if(expectedModCount != modCount){
                throw new ConcurrentModificationException();
            }
            return !isEnd;
        }

        @Override
        public E next() {
            Node res = current;
            current = current.nextMax;
            if(current == null||current == end){
                isEnd = true;
            }
            return (E)res.value;
        }

        @Override
        public void remove() {

        }
    }

    //头尾节点
    Node<E> head;
    Node<E> end;
    //根节点
    Node<E> root;
    private int length;
    private int modCount;

    /**
     * 构造方法，无参数
     */
    public MyTreeSetTwo() {
        modCount = 0;
        length = 0;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(E o) {
        if(o == null||root == null){
            return false;
        }else {
            return contains(o,root)!=null;
        }
    }
    private Node<E> contains(E o,Node<E> top){
        //基准条件
        if(top == null){
            return null;
        }
        //递归条件
        //往右走
        if(o.compareTo(top.value) > 0){
            return contains(o,top.right);
        }else if(o.compareTo(top.value) == 0){
            return top;
        }else {
            return contains(o,top.left);
        }
    }
    @Override
    public Iterator<E> iterator() {
        return new MyIterator<E>();
    }

    @Override
    public boolean insert(E e) {
        //空树
        if(root == null){
            root = new Node<E>(e,null,null,head,end);
            head = new Node<E>(null,null,root,root,null);
            end = new Node<E>(null,null,null,root,root);
            head.nextMax = root;
            head.nextMin = root;
            return true;
        }
        root = insert(e,root,head,false);
        return false;
    }
    public Node<E> insert(E e,Node<E> top,Node<E> topF,boolean right){
        //基准条件
        if(top == null){
            Node<E> newNode;
           //插入右子树
            if(right){
                newNode =  new Node<>(e,null,null,topF,topF.nextMax);
                if(topF.nextMax!=null){
                    topF.nextMax.nextMin = newNode;
                }
                topF.nextMax = newNode;
            }else {
                newNode = new Node<>(e,null,null,topF.nextMin,topF);
                if(topF.nextMin!=null){
                    topF.nextMin.nextMax = newNode;
                }
                topF.nextMin = newNode;
            }
            length++;
             modCount++;
            return newNode;
        }
        //递归条件
        if(top.value.compareTo(e) > 0){
            top.left = insert(e,top.left,top,false);
        }else if(top.value.compareTo(e) == 0){
            return top;
        }else{
            top.right = insert(e,top.right,top,true);
        }
        return top;
    }
    @Override
    public boolean remove(E o) {
        remove(o,root,true);
        return false;
    }
    private Node<E> remove(E o,Node<E> top,boolean f){
        //基准条件
        if(top == null){
            return null;
        }
        //递归条件

        if(o.compareTo(top.value) > 0){
            top.right = remove(o,top.right,f);
        }else if(o.compareTo(top.value) < 0){
            top.left = remove(o,top.left,f);
        }else {
            //相等
            //双向链表节点交换
            if(f) {
                if (top.nextMin != null) {
                    top.nextMin.nextMax = top.nextMax;
                }
                if (top.nextMax != null) {
                    top.nextMax.nextMin = top.nextMin;
                }
            }
            //删除
            Node<E> rightMin;
            if(top.left == null&&top.right == null){
                return null;
            }else if(top.left!=null&&top.right!=null){
                //左右都不为空,进行双向链表节点交换
                Node<E> temp = findMin(top.right);
                rightMin = new Node<E>(temp.value,top.left,top.right,temp.nextMin,temp.nextMax);
                rightMin.right = remove(temp.value,top.right,false);
                return rightMin;
            }else if(top.left!=null){
                return top.left;
            }else {
                return top.right;
            }
        }
        return top;

    }

    @Override
    public void printTree() {
        printTree(root,0);
    }
    public void printTree(Node<E> top,int height){
        //基准条件
        if(top.right!=null) {
            //右边有就往右走
            printTree(top.right, height + 1);
        }
        //格式化输出
        for (int i = 0;i < height;i++){
            System.out.print("      ");
        }
        if(top.nextMin!=null) {
            System.out.print("("+top.nextMin.value+")");
        }else {
            System.out.print("(null)");
        }
        System.out.print(top.value.toString());
        if(top.nextMax!=null){
            System.out.println("("+top.nextMax.value+")");
        }else {
            System.out.println("(null)");
        }
        //往左走
        if(top.left!=null) {
            printTree(top.left, height + 1);
        }
    }

    @Override
    public E findMin() {
        if(root == null){
            return null;
        }
        return findMin(root).value;
    }
    private Node<E> findMin(Node<E> top){
        //基准条件
        if(top.left == null){
            return top;
        }
        return findMin(top.left);
    }

    @Override
    public E findMax() {
        if(root == null){
            return null;
        }
        return findMax(root).value;
    }
    private Node<E> findMax(Node<E> top){
        //基准条件
        if(top.right == null) {
            return top;
        }
        return findMax(top.right);
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        MyTreeSetTwo<JayChouSongs> test = new MyTreeSetTwo<>();
        test.insert(new JayChouSongs(10,"回到过去"));
        test.insert(new JayChouSongs(5,"回到过去"));
        test.insert(new JayChouSongs(15,"回到过去"));
        test.insert(new JayChouSongs(13,"回到过去"));
        test.insert(new JayChouSongs(8,"回到过去"));
        test.insert(new JayChouSongs(2,"回到过去"));
        test.insert(new JayChouSongs(18,"回到过去"));
        test.insert(new JayChouSongs(16,"回到过去"));
        test.insert(new JayChouSongs(20,"回到过去"));
        test.insert(new JayChouSongs(21,"回到过去"));
        test.insert(new JayChouSongs(19,"回到过去"));
        test.printTree();
        System.out.println(test.contains(new JayChouSongs(3,"回到过去")));
        test.remove(new JayChouSongs(20,"回到过去"));
        test.printTree();
        Iterator<JayChouSongs> it = test.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}
