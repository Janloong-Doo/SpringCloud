package com.janloong.javabase.structure;


/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-03-05 11:59
 */
public class Node {
    //存储数据
    private Object data;
    //下一个节点
    private Node next;

    //定义构造方法
    public Node(Object data, Node next) {
        this.data = data;
        this.next = next;
    }

    public Node() {

    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }


}