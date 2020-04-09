/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: ZookeeperConfig.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/4/9 下午4:42
 * LastModify: 2020/4/9 下午4:42
 */

package com.janloong.springsecurity.zookeeper.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * cur
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @version V1.0
 * @date 2020-04-09 16:42
 **/
public class CuratorConfig {

    /**
     * 初始化curator客户端
     * <p>
     * connectionString	服务器列表，格式host1:port1,host2:port2,…
     * retryPolicy	重试策略,内建有四种重试策略,也可以自行实现RetryPolicy接口
     * sessionTimeoutMs	会话超时时间，单位毫秒，默认60000ms
     * connectionTimeoutMs	连接创建超时时间，单位毫秒，默认60000ms
     * namespace 包含命名空间的隔离
     * <p>
     * zookeeper的节点创建模式:
     * PERSISTENT：持久化
     * PERSISTENT_SEQUENTIAL：持久化并且带序列号
     * EPHEMERAL：临时
     * EPHEMERAL_SEQUENTIAL：临时并且带序列号
     *
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/4/9 17:10
     * @see <a heref="http://www.throwable.club/2018/12/16/zookeeper-curator-usage/#Zookeeper%E5%AE%A2%E6%88%B7%E7%AB%AFCurator%E4%BD%BF%E7%94%A8%E8%AF%A6%E8%A7%A3"></a>
     **/
    public static CuratorFramework getClient() {
        String connectionInfo = "192.168.1.1:22002";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        //return CuratorFrameworkFactory.newClient(
        //                connectionInfo,
        //                5000,
        //                3000,
        //                retryPolicy);
        return CuratorFrameworkFactory.builder()
                .connectString(connectionInfo)
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace("doo")
                .build();
    }

    public static void main(String[] args) throws Exception {
        //启动客户端
        CuratorFramework client = getClient();
        client.start();
        //创建一个节点，初始内容为空
        client.create().forPath("path");
        //创建一个节点，附带初始化内容
        client.create().forPath("path", "init".getBytes());
        //创建一个节点，指定创建模式（临时节点），内容为空
        client.create().withMode(CreateMode.EPHEMERAL).forPath("path");
        //创建一个节点，指定创建模式（临时节点），附带初始化内容
        client.create().withMode(CreateMode.EPHEMERAL).forPath("path", "init".getBytes());
        //创建一个节点，指定创建模式（临时节点），附带初始化内容，并且自动递归创建父节点
        client.create()
                .creatingParentContainersIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath("path", "init".getBytes());

        //删除一个节点
        client.delete().forPath("path");
        //注意，此方法只能删除叶子节点，否则会抛出异常。
        //删除一个节点，并且递归删除其所有的子节点
        client.delete().deletingChildrenIfNeeded().forPath("path");
        //删除一个节点，强制指定版本进行删除
        client.delete().withVersion(10086).forPath("path");
        //删除一个节点，强制保证删除
        client.delete().guaranteed().forPath("path");
        //guaranteed()接口是一个保障措施，只要客户端会话有效，那么Curator会在后台持续进行删除操作，直到删除节点成功。
        //注意：上面的多个流式接口是可以自由组合的，例如：
        client.delete().guaranteed().deletingChildrenIfNeeded().withVersion(10086).forPath("path");

        //读取一个节点的数据内容
        client.getData().forPath("path");
        //注意，此方法返的返回值是byte[ ];
        //读取一个节点的数据内容，同时获取到该节点的stat
        Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath("path");

        //更新一个节点的数据内容
        client.setData().forPath("path", "data".getBytes());
        //注意：该接口会返回一个Stat实例
        //更新一个节点的数据内容，强制指定版本进行更新
        client.setData().withVersion(10086).forPath("path", "data".getBytes());
        //检查节点是否存在
        client.checkExists().forPath("path");
        //注意：该方法返回一个Stat实例，用于检查ZNode是否存在的操作. 可以调用额外的方法(监控或者后台处理)并在最后调用forPath()指定要操作的ZNode
        //获取某个节点的所有子节点路径
        client.getChildren().forPath("path");
        //注意：该方法的返回值为List,获得ZNode的子节点Path列表。 可以调用额外的方法(监控、后台处理或者获取状态watch, background or get stat) 并在最后调用forPath()指定要操作的父ZNode


        //CuratorFramework的实例包含inTransaction( )接口方法，调用此方法开启一个ZooKeeper事务. 可以复合create, setData, check, and/or delete 等操作然后调用commit()作为一个原子操作提交。一个例子如下：

        client.inTransaction().check().forPath("path")
                .and()
                .create().withMode(CreateMode.EPHEMERAL).forPath("path","data".getBytes())
                .and()
                .setData().withVersion(10086).forPath("path","data2".getBytes())
                .and()
                .commit();
    }
}
