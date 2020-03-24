/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: SentintelConfig.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/1/1 下午8:21
 * LastModify: 2020/1/1 下午8:21
 */

package com.janloong.consumer.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 限流配置
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2020/1/1 20:21
 **/

public class SentintelConfig {

    public void limiting() {
        List<FlowRule> rules = new ArrayList<FlowRule>();
        FlowRule rule = new FlowRule();
        rule.setResource("doo-sentinel");
        // set limit qps to 10
        rule.setCount(10);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setLimitApp("default");
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
