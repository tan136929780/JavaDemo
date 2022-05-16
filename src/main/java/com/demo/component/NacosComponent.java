package com.demo.component;


import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@NacosPropertySource(dataId = "java", autoRefreshed = true)
@Getter
public class NacosComponent {
    @NacosValue(value = "${env:dev}", autoRefreshed = true)
    private String env;

    @NacosValue(value = "${service.ip:}", autoRefreshed = true)
    private String serviceIp;

    @NacosValue(value = "${service.port:}", autoRefreshed = true)
    private String servicePort;
}