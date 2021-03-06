package TreeSet;
import java.util.*;

/**
 * 简单的二叉查找树，不是AVL
 * 迭代器使用二叉查找树，添加父节点的链
 */
public class MyTreeSetOne<E extends Comparable<? super E>>{
    //节点数
    int count;
    //修改次数
    int modCount;
    //根节点
    TreeNode<E> root;

    /**
     * 节点类，左、右、父指针
     */
    private class TreeNode<E>{
        TreeNode<E>leftChild;
        TreeNode <E>rightChild;
        TreeNode <E>father;
        E value;
        //附加指示域，重复插入时相当于插入值一样的链表中
       public LinkedList<E> spec;
        public TreeNode(TreeNode leftChild, TreeNode rightChild, TreeNode father, E value) {
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.father = father;
            this.value = value;
            spec = new LinkedList<E>();
        }
        public TreeNode() {
            spec = new LinkedList<E>();
        }
    }
    /**
     * 相关迭代器
     */
    public class MyIterator implements Iterator<E>{

        //标志，判断迭代是否结束
        private boolean isEnd;
        private TreeNode<E>current;
        private int expectedModCount;
        private TreeNode<E>lastAccess;

        public MyIterator() {
            expectedModCount = modCount;
            current = findMin(root);
            isEnd = false;
        }

        @Override
        public boolean hasNext() {
            return !isEnd;
        }

        /**
         *不能用递归
         *思路：当节点往下走时（根为上），一定是去寻找右子树最小节点
         * 若节点往上走，分两种情况
         * 1，节点从右子树往上走，表示走到的节点不再需要遍历了,右子树也不需要遍历
         * 2，节点从左子树往上走，表示走到的节点仍需要遍历，右子树也需要
         * 取一个节点记录节点的前一个节点即可判断是哪种情况
         */
        @Override
        public E next() {
            TreeNode<E> returnNode = current;
            //中序遍历输出结果
            if(modCount!=expectedModCount){
                throw new ConcurrentModificationException();
            }
            //树为空
            if(root == null){
                return null;
            }
            //树不为空
            //当节点往上走且走到根的father时结束
            if(current == null){
                isEnd = true;
            }
            //往右走
            if(current.rightChild!=null && current.rightChild!=lastAccess){
                current = findMin(current.rightChild);
            }else {
                //往上走,从右子树一直往上走
                while (current.rightChild == null || current.rightChild == lastAccess) {
                    //走一步赋一次值，直到上次的节点不是它的右子树
                    lastAccess = current;
                    current = current.father;
                    //已经成为了根的father，即为null,已经结束了
                    if(current == null){
                        isEnd = true;
                        break;
                    }
                    //从左子树上的来就遍历该节点
                    if (current.leftChild == lastAccess) {
                        break;
                    }

                }
            }
            return returnNode.value;
        }

    }
    /**
     * 构造方法：带参数
     */
    public MyTreeSetOne(Collection<E> collection) {
        Iterator<E> it = collection.iterator();
        count = 0;
        modCount = 0;
        while (it.hasNext()){
            insert(it.next());
        }
    }
    /**
     * 构造方法，无参数
     */
    public MyTreeSetOne() {
        count = 0;
        modCount = 0;
    }
    /**
     * 判断空 isEmpty
     */
    public boolean isEmpty(){
        return count == 0;
    }

    /**
     * 置为空 makeEmpty
     */
    public void makeEmpty(){
        root = null;
        modCount++;
    }

    /**
     * 查找是否包含 contains
     */
    public boolean contains(E e){
        return contains(e,root)!=null;
    }
    public TreeNode<E> contains(E e,TreeNode<E> rootNode){
        if(rootNode == null){
            return null;
        }
        //非递归方式：使用栈
        Stack<TreeNode<E>> temp = new Stack<>();
        temp.push(rootNode);
        //非空就表示未结束
        while (!temp.isEmpty()){
            //取出一个比较
            TreeNode<E> aNode = temp.pop();
            if(aNode.value.compareTo(e) == 0){
                return aNode;
            }
            //放进它的左右节点（非空）
            if (aNode.leftChild!=null){
                temp.push(aNode.leftChild);
            }
            if(aNode.rightChild!=null){
                temp.push(aNode.rightChild);
            }
        }
        return null;
    }
    /**
     * 找最小值 findMIn
     */
    public E findMin(){
        if(isEmpty()){
            return null;
        }
        return findMin(root).value;
    }
    private TreeNode<E> findMin(TreeNode<E> rootNode){
        //基准条件
        if(rootNode.leftChild == null){
            return rootNode;
        }
        //递归条件
        return findMin(rootNode.leftChild);
    }

    /**
     * 找最大值 findMax
     */
    public E findMax(){
        if(isEmpty()){
            return null;
        }
        return findMax(root).value;
    }
    public TreeNode<E>findMax(TreeNode<E> rootNode){
        if(rootNode.rightChild == null){
            return rootNode;
        }
        return findMax(rootNode.rightChild);
    }

    /**
     * 插入Insert
     * 返回树根
     */
    public TreeNode<E> insert(E elem){
        //先判断奇怪元素
        if(elem == null){
            return null;
        }
        //先找有没有
        TreeNode<E> x = contains(elem,root);
        if(x!=null){
            x.spec.add(elem);
            return x;
        }else {
            count++;
        }
        modCount++;
        //插入的递归方法
        root = insert(elem,root,null);
        return root;

    }
    private TreeNode<E> insert(E elem,TreeNode<E> st,TreeNode<E> fa){
        //基准条件
        if(st == null){
            return new TreeNode<E>(null,null,fa,elem);
        }
        //递归条件
        int jud = elem.compareTo(st.value);
        if(jud > 0){
            st.rightChild = insert(elem,st.rightChild,st);
        }else if(jud < 0) {
            st.leftChild = insert(elem, st.leftChild, st);
        }
        return st;
    }

    /**
     * 打印出整个tree  printTree
     */
    public void printTree(){
        printTree(root,0);
    }
    private void printTree(TreeNode<E> rootNode,int height){
        //基准条件
        if (rootNode.rightChild!=null){
            printTree(rootNode.rightChild,height+1);
        }
        for (int i = 0;i < height;i++){
            System.out.print("   ");
        }
        if(rootNode!=root)
        System.out.println(rootNode.value.toString()+"("+rootNode.father.value.toString()+")");
        else
            System.out.println(rootNode.value.toString());
        if(rootNode.leftChild!=null){
            printTree(rootNode.leftChild,height+1);
        }
    }

    /**
     * 移除一个节点 remove
     */
    public E remove(E elem){
        //寻找有没有
        TreeNode<E> temp = contains(elem,root);
        if(temp == null){
            return null;
        }
        modCount--;
        count--;
//        if(temp.father == null){
//            //移除顶根
//            remove(elem,temp);
//        }
//        remove(elem,temp.father);
        removeAgain(temp);
        return elem;
    }
    private TreeNode<E> remove(E e,TreeNode<E> rootNode){
        if(rootNode == null){
            //空节点直接返回
            return null;
        }
        //比较
        int res = rootNode.value.compareTo(e);
        if(res > 0){
            //往左走
            rootNode.leftChild = remove(e,rootNode.leftChild);
        }else if(res < 0){
            //往右走
            rootNode.rightChild = remove(e,rootNode.rightChild);
        }else if(rootNode.rightChild!=null&&rootNode.leftChild!=null){
            //相等情况,左右均不为空
            TreeNode<E> min = findMin(rootNode.rightChild);
            rootNode.value = min.value;
            //通知右子节点他们的父节点被移除了
            if(min.rightChild!=null){
                min.rightChild.father = min.father;
            }
            remove(min.value,rootNode.rightChild);
        }else {
            //左右有一个为空或者两个都为空
            //通知father被移除了
            if(rootNode.leftChild !=null){
                rootNode.leftChild.father = rootNode.father;
            }else if(rootNode.rightChild!=null){
                rootNode.rightChild.father = rootNode.father;
            }else {

            }
            return (rootNode.leftChild == null)? rootNode.rightChild : rootNode.leftChild;
        }
        return rootNode;
    }
    /**
     * remove的另外一种实现方法，使用fatherNode
     */
    public void removeAgain(TreeNode<E>node){
        E elem = node.value;
        if(node.rightChild!=null&&node.leftChild!=null){
            //左子树和右子树均不为空
            //找到右子树最小值
            TreeNode<E> rMin = findMin(node.rightChild);
            node.value = rMin.value;
            removeAgain(rMin);
        }else if(node.rightChild!=null){
            //左子树为空
            //判断父节点是否为空
            if(node.father == null){
                root = node.rightChild;
                return;
            }
            //判断是父节点左节点还是右节点
            if(elem.compareTo(node.father.value) > 0){
                //右子树
                node.father.rightChild = node.rightChild;
                node.rightChild.father = node.father;
            }else {
                //左子树
                node.father.leftChild = node.rightChild;
                node.rightChild.father = node.father;
            }
            node.father = null;
            node.rightChild = null;
            node.value = null;//Help GC
        }else if(node.leftChild!=null){
            //右子树为空
            if(node.father == null){
                root = node.leftChild;
                return;
            }
            //判断是父节点左节点还是右节点
            if(elem.compareTo(node.father.value) > 0){
                //右子树
                node.father.rightChild = node.leftChild;
                node.leftChild.father = node.father;
            }else {
                //左子树
                node.father.leftChild = node.leftChild;
                node.leftChild.father = node.father;
            }
            node.father = null;
            node.rightChild = null;
            node.value = null;//Help GC
        }else {
            if(elem.compareTo(node.father.value) > 0){
                //右子树
               node.father.rightChild = null;
            }else {
                //左子树
                node.father.leftChild = null;
            }
            node.father = null;
            node.rightChild = null;
            node.value = null;//Help GC
        }
        }
    /**
     * 返回迭代器
     */
    public Iterator<E> iterator(){
        return new MyIterator();
    }

    /**
     * 快手算法面试题：找一棵树距离根节点最深的叶子节点的集合
     * from NowCoder
     */
    //一个栈记录深度
    Stack<Integer>depth = new Stack<>();
    //一个栈记录节点集合
    Stack<E>nodes = new Stack<>();
    public Set<E> findDeepest(){
        if(root == null){
            return null;
        }
        findDeepest(root,0);
        Set<E> aSet = new HashSet<>(nodes);
        return aSet;
    }
    private void findDeepest(TreeNode<E> node,int deep){
        //基准条件
        if(node.rightChild == null&&node.leftChild == null){
            //表示叶节点
            if(depth.isEmpty()){
                //空栈直接放进元素
                nodes.push(node.value);
                depth.push(deep);
            }else if(depth.peek() > deep){
                //深度非最大深度
            }else if(depth.peek() < deep){
                //栈顶深度不是最大深度
                while (!depth.isEmpty() && depth.peek() < deep){
                    depth.pop();
                    nodes.pop();
                }
                if(depth.isEmpty()||depth.peek() == deep){
                    depth.push(deep);
                    nodes.push(node.value);
                }
            }else {
                depth.push(deep);
                nodes.push(node.value);
            }
        }
        else {
            //递归条件
            if(node.leftChild !=null)
            findDeepest(node.leftChild, deep + 1);
            if(node.rightChild!=null)
            findDeepest(node.rightChild, deep + 1);
        }
    }
    /**
     * 测试
     */
    public static void main(String[] args) {
        MyTreeSetOne<JayChouSongs> test = new MyTreeSetOne<JayChouSongs>();
        test.insert(new JayChouSongs(5,"晴天"));
        test.insert(new JayChouSongs(3,"说好不哭"));
        test.insert(new JayChouSongs(10,"等你下课"));
        test.insert(new JayChouSongs(20,"夜曲"));
        test.insert(new JayChouSongs(15,"说好的幸福呢"));
        test.insert(new JayChouSongs(8,"算什么男人"));
        test.insert(new JayChouSongs(8,"算什么男人"));
        test.insert(new JayChouSongs(18,"算什么男人"));
        test.insert(new JayChouSongs(4,"回到过去"));
        test.printTree();
        Set<JayChouSongs> a =test.findDeepest();
        Iterator<JayChouSongs> b = a.iterator();
        while (b.hasNext()){
            System.out.println(b.next());
        }

    }
}
