### 数据结构之栈的实现

实现方式，使用数组模拟，int模拟整型数据，见Stack类，可扩容。

### 存在的问题：

##### P1：数组中除了栈之外的内存无法被回收（也无法被使用）

解决办法，改用链表实现

### P2：int写死，不能存储其它的类

解决办法：将int变为object，或者使用泛型

###### 改进，见StackTwo，使用链表和Object实现