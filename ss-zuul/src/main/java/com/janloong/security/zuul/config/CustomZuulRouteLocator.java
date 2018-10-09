/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: CustomZuulRouteLocator.java
 : Author: janloongdoo@gmail.com
 : Date: 18-9-27 上午9:14
 : LastModify: 18-9-27 上午9:14
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.security.zuul.config;


import com.janloong.security.zuul.entity.CusZuulRoute;
import com.janloong.security.zuul.utils.BaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-09-27 9:14
 */
public class CustomZuulRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {
    private final static Logger log = LoggerFactory.getLogger(CustomZuulRouteLocator.class);
    private JdbcTemplate jdbcTemplate;
    private ZuulProperties properties;


    public CustomZuulRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
        this.properties = properties;
    }

    @Override
    public void refresh() {
        doRefresh();
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    protected LinkedHashMap<String, ZuulProperties.ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<>();
        //读取properties配置、eureka默认配置
        routesMap.putAll(super.locateRoutes());
        routesMap.putAll(locateRoutesFromDb());
        LinkedHashMap<String, ZuulProperties.ZuulRoute> values = new LinkedHashMap<>();
        for (Map.Entry<String, ZuulProperties.ZuulRoute> entry : routesMap.entrySet()) {
            String path = entry.getKey();
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (!StringUtils.isEmpty(this.properties.getPrefix())) {
                path = this.properties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            values.put(path, entry.getValue());
        }
        return values;
    }

    private Map<String, ZuulProperties.ZuulRoute> locateRoutesFromDb() {
        Map<String, ZuulProperties.ZuulRoute> routes = new LinkedHashMap<>();

        List<CusZuulRoute> results = new ArrayList<>();
        try {
            results = jdbcTemplate.query("select * from cus_zuul_route where enabled = true",
                    new BeanPropertyRowMapper<>(CusZuulRoute.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("空数据源");
        }
        //}
        log.debug("打印" + results.toString());
        for (CusZuulRoute result : results) {
            if (StringUtils.isEmpty(result.getPath()) && StringUtils.isEmpty(result.getUrl())) {
                continue;
            }

            ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
            try {
                zuulRoute.setId(result.getServiceId());
                zuulRoute.setPath(result.getPath());
                zuulRoute.setServiceId(result.getServiceId());
                zuulRoute.setRetryable(result.getRetryable());
                zuulRoute.setStripPrefix(result.getStripPrefix());
                zuulRoute.setUrl(result.getUrl());
                String s = result.getSensitiveheadersList();
                List<String> sensitiveHeadersList = BaseUtil.str2List(s);
                if (sensitiveHeadersList != null) {
                    Set<String> sensitiveHeaderSet = new HashSet<>(sensitiveHeadersList);
                    zuulRoute.setSensitiveHeaders(sensitiveHeaderSet);
                    zuulRoute.setCustomSensitiveHeaders(true);
                }
            } catch (Exception e) {
                log.error("从数据库加载路由配置异常", e);
            }
            log.debug("添加数据库自定义的路由配置,path：{}，serviceId:{}", zuulRoute.getPath(), zuulRoute.getServiceId());
            routes.put(zuulRoute.getPath(), zuulRoute);
        }
        return routes;
    }
}
