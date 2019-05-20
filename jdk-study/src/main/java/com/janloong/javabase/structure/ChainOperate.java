package com.janloong.javabase.structure;


/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-03-04 23:12
 */
public class ChainOperate {

    //public static void main(String[] args) {
    //
    //}
    //实现单向链表
    public class SingleLinkImpl {

        //保存链表的头节点
        private Node header;
        //保存链表的尾节点
        private Node tail;
        //保存节点数
        int length = 0;

        //创建一个空的单向链表
        public SingleLinkImpl() {
            header = null;
            tail = null;
        }

        //指定某一个元素创建一个单向链表
        public SingleLinkImpl(Object element) {
            header = new Node(element, null);
            //头和尾都是指向同一个
            tail = header;
            length++;
        }

        //返回链表的长度
        public int getLength() {
            return length;
        }

        //采用尾插入法为链表添加新的节点
        public void add(Object data) {
            //判断此时是否为空链表
            if (header == null) {
                header = new Node(data, null);
                //头和尾指向同一个节点
                tail = header;
            } else {

                //创建新的节点
                Node newNode = new Node(data, null);
                //尾部节点next指向新增的节点
                tail.setNext(newNode);
                //新的节点作为尾部节点
                tail = newNode;
            }
            length++;
        }

        //获取链表中索引为index出的元素
        public Object getData(int index) {
            return getNodeByIndex(index).getData();
        }

        private Node getNodeByIndex(int index) {
            if (index < 0 || index > length - 1) {
                throw new IndexOutOfBoundsException("索引越界！");
            }
            //从头节点开始
            Node current = header;
            for (int i = 0; i < length & current != null; i++, current = current.getNext()) {
                if (i == index) {
                    return current;
                }
            }
            return null;
        }


        //查询指定元素的索引哈
        public int getDataIndex(Object data) {
            Node current = header;
            for (int i = 0; i < length & current != null; i++, current = current.getNext()) {
                if (current.getData().equals(data)) {
                    return i;
                }
            }
            return -1;
        }

        //向单向链表中指定位置插入一个元素
        public void insertDataByIndex(Object data, int index) {
            if (index < 0 || index > length - 1) {
                throw new IndexOutOfBoundsException("插入的位置有错误！");
            }
            //如果链表还是空的话，这个是要考虑的哈
            if (header == null) {
                add(data);
                length++;
            } else {

                //考虑好是否是在链表头插入的哈
                if (index == 0) {
                    header = new Node(data, header);
                    length++;
                } else {

                    //获取前一个节点
                    Node prev = getNodeByIndex(index - 1);
                    //改变指向
                    prev.setNext(new Node(data, prev.getNext()));
                    length++;
                }
            }
        }


        //删除索引中的指定的节点
        public void myDelete(int index) {
            if (index < 0 || index > length - 1) {
                throw new IndexOutOfBoundsException("删除索引超出边界！");
            }
            if (header == null) {
                throw new NullPointerException("链表为空！");
            }

            //判断是不是删除第一个节点
            if (index == 0) {
                //改变头节点 哈！
                header = header.getNext();
                length--;
            } else {
                //获取删除的节点哈！
                Node del = getNodeByIndex(index);
                //获取删除节点的前一个节点
                Node prev = getNodeByIndex(index - 1);

                prev.setNext(del.getNext());
                length--;
            }
        }
    }

    public static void main(String[] args) {

        new ChainOperate().initTest();

    }

    public void initTest() {
        SingleLinkImpl myTest = new SingleLinkImpl();
        myTest.add("123");
        myTest.add("234");
        myTest.add("wang");
        myTest.add(123);
        System.out.println(myTest.getLength());
        myTest.insertDataByIndex("test", 2);
        myTest.myDelete(4);
        System.out.println(myTest.getData(2));
        System.out.println(myTest.getDataIndex("wangpeili"));
    }

}
