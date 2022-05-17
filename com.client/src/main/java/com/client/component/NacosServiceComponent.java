//package com.client.component;
//
//import com.alibaba.nacos.api.annotation.NacosInjected;
//import com.alibaba.nacos.api.exception.NacosException;
//import com.alibaba.nacos.api.naming.NamingService;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
//@Component
//public class NacosServiceComponent {
//    @NacosInjected
//    private NamingService namingService;
//
//    @Value("${spring.application.name}")
//    private String serviceName;
//
//    @Value("${grpc.server.address}")
//    private String serviceIp;
//
//    @Value("${grpc.server.port}")
//    private int servicePort;
//
//    @PostConstruct
//    public void registerInstance() throws NacosException {
//        namingService.registerInstance(serviceName, serviceIp, servicePort);
//    }
//}
