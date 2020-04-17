package TreeSet;

import java.util.*;

/**
 * 通过存储类型TreeSet<Map.Entry<Key,Value>>的一个数据成员编写实现TreeMap类的程序
 * @param <K>键
 * @param <V>值
 */
public class MyTreeMap<K,V>{
    /**
     * 节点类
     */
    private class MyTreeNode<K,V> implements Comparable<K>{
        K key;
        V value;
        Comparable<? super K> comparator;
        public MyTreeNode(K key,V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(K o) {
            Comparable<? super K> oCom = (Comparable<? super K>)o;
            return oCom.compareTo(key);
        }
    }

    /**
     * 构造方法
     */
    public MyTreeMap(){
        set = new TreeSet<MyTreeNode<K,V>>();
    }
    //维护一个TreeSet
    private TreeSet<MyTreeNode<K,V>> set;

    /**
     * 添加元素
     * @param key
     * @param value
     * @return
     */
    private boolean add(K key,V value){
        MyTreeNode<K,V>toAdd = new MyTreeNode<K,V>(key,value);
       boolean f = set.add(toAdd);
       return f;
    }
    /**
     * 删除元素
     */
    public V remove(K key){
        int len = set.size();
        V returnV = null;
        Comparable<K> kCom = (Comparable<K>)key;
        synchronized (set){
            //先找到再记录，最后删除
            MyTreeNode<K,V>[] res = new MyTreeNode[len];
            set.toArray(res);
            for (int i = 0;i < len;i++){
                if(kCom.compareTo(res[i].key) == 0){
                    //记录旧的值
                    returnV = res[i].value;
                }
            }
        }
        return returnV;
    }
    /**
     * 查找元素
     */
    public boolean contains(K key){
        return set.contains(new MyTreeNode<K, V>(key,null));
    }
    /**
     * 返回值
     */
    public V getValue(K key){
        int len = set.size();
        Comparable<K> kCom = (Comparable<K>)key;
        MyTreeNode<K,V>[] res = new MyTreeNode[len];
        set.toArray(res);
        for (int i = 0;i < len;i++){
            if(kCom.compareTo(res[i].key) == 0){
                //记录旧的值
                return res[i].value;
            }
        }
        return null;
    }
    /**
     * 迭代器
     */
    private class MyIterator<K,V> implements Iterator<K>{
        Iterator it;
        MyTreeNode<K,V>a;

        public MyIterator() {
          it = set.iterator();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public K next() {
            MyTreeNode<K,V> res = (MyTreeNode<K,V>)it.next();
            return res.key;
        }

        @Override
        public void remove() {
            it.remove();
        }
    }

    Iterator<K> iterator(){
        return new MyIterator<K,V>();
    }

    /**
     * test
     */
    public static void main(String[] args) {
        MyTreeMap<JayChouSongs,String> test = new MyTreeMap<JayChouSongs,String>();
        test.add(new JayChouSongs(6,"回到过去"),"firstSong");
        test.add(new JayChouSongs(3,"给我一首歌的时间"),"secondSong");
        test.add(new JayChouSongs(8,"给我一首歌的时间"),"3Song");
        test.add(new JayChouSongs(7,"给我一首歌的时间"),"4Song");
        System.out.println(test.remove(new JayChouSongs(7,"给我一首歌的时间")).toString());
       Iterator a =  test.iterator();
       test.contains(new JayChouSongs(3,"jjj"));
       System.out.println(test.getValue(new JayChouSongs(6,"kkk")));
       while (a.hasNext()){
           System.out.println(a.next());
       }
    }
}
