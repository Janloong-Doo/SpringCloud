package com.janloong.eurekaclient2.config;


import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

/**
 * 自定义ribbon rule
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-04-12 11:18
 */

public class CustomRule implements IRule {

    private final static Logger logger = LoggerFactory.getLogger(CustomRule.class);

    private ILoadBalancer iLoadBalancer;

    @Override
    public Server choose(Object o) {
        logger.info("自定义负载均衡规则");

        Random r = new Random();

        int randomNum = r.nextInt(10);
        List<Server> servers = iLoadBalancer.getAllServers();
        if (randomNum > 7) {
            return getServerByPort(servers, 8763);
        }
        return getServerByPort(servers, 8764);
    }

    private Server getServerByPort(List<Server> servers, int port) {
        for (Server s : servers) {
            logger.info("\n - : " + "\n"
                    + "host:" + ":" + s.getHostPort() + "\n"
            );
            if (s.getPort() == port) {
                return s;
            }
        }
        return null;
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        this.iLoadBalancer = iLoadBalancer;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return iLoadBalancer;
    }
}
