package com.client.grpc;

import com.alibaba.fastjson.JSONObject;
import com.client.vnos.newvcms.InstanceServiceGrpc;
import com.client.vnos.newvcms.Newvcms;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class TestGrpcController {
    @Test
    public void hello() throws InterruptedException {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("10.1.68.60", 9090)
                .usePlaintext().build();
        InstanceServiceGrpc.InstanceServiceBlockingStub instanceServiceBlockingStub = InstanceServiceGrpc.newBlockingStub(managedChannel);
        Newvcms.InstanceCreateRequest.Builder builder = Newvcms.InstanceCreateRequest.newBuilder();
        Map<String,String> map = new HashMap();
        List<Map> list = new ArrayList<>();//模型属性
        Map<String,String> map1 = new HashMap();//属性数据
        map1.put("instance.identifier","id");
        String[] strings = {"property"};
        map1.put("instance.isa", JSONObject.toJSONString(strings));
        map1.put("property.identifier","id");
        map1.put("property.type","string");
        map1.put("property.status","1");
        list.add(map1);

        Map<String,String> map2 = new HashMap();
        map2.put("instance.identifier","唯一标识");
        map2.put("instance.isa",JSONObject.toJSONString(strings));
        map2.put("property.identifier","ldapcode");
        map2.put("property.type","string");
        map2.put("property.status","1");
        list.add(map2);

        Map<String,String> map3 = new HashMap();
        map3.put("instance.identifier","链接地址");
        map3.put("instance.isa",JSONObject.toJSONString(strings));
        map3.put("property.identifier","url");
        map3.put("property.type","string");
        map3.put("property.status","1");
        list.add(map3);

        Map<String,String> map4 = new HashMap();
        map4.put("instance.identifier","端口");
        map4.put("instance.isa",JSONObject.toJSONString(strings));
        map4.put("property.identifier","port");
        map4.put("property.type","string");
        map4.put("property.status","1");
        list.add(map4);


        Map<String,String> map5 = new HashMap();
        map5.put("instance.identifier","组织base");
        map5.put("instance.isa",JSONObject.toJSONString(strings));
        map5.put("property.identifier","base");
        map5.put("property.type","string");
        map5.put("property.status","1");
        list.add(map5);

        Map<String,String> map6 = new HashMap();
        map6.put("instance.identifier","组织Dn");
        map6.put("instance.isa",JSONObject.toJSONString(strings));
        map6.put("property.identifier","dn");
        map6.put("property.type","string");
        map6.put("property.status","1");
        list.add(map6);


        Map<String,String> map7 = new HashMap();
        map7.put("instance.identifier","Ldap密码");
        map7.put("instance.isa",JSONObject.toJSONString(strings));
        map7.put("property.identifier","ldapPassword");
        map7.put("property.type","string");
        map7.put("property.status","1");
        list.add(map7);

        Map<String,String> map8 = new HashMap();
        map8.put("instance.identifier","用户加密方式");
        map8.put("instance.isa",JSONObject.toJSONString(strings));
        map8.put("property.identifier","userEncrypt");
        map8.put("property.type","string");
        map8.put("property.status","1");
        list.add(map8);

        Map<String,String> map9 = new HashMap();
        map9.put("instance.identifier","默认组织");
        map9.put("instance.isa",JSONObject.toJSONString(strings));
        map9.put("property.identifier","defaultOu");
        map9.put("property.type","string");
        map9.put("property.status","1");
        list.add(map9);

        Map<String,String> map10 = new HashMap();
        map10.put("instance.identifier","默认用户名");
        map10.put("instance.isa",JSONObject.toJSONString(strings));
        map10.put("property.identifier","defaultCn");
        map10.put("property.type","string");
        map10.put("property.status","1");
        list.add(map10);

        Map<String,String> map11 = new HashMap();
        map11.put("instance.identifier","默认连接");
        map11.put("instance.isa",JSONObject.toJSONString(strings));
        map11.put("property.identifier","defaultConnect");
        map11.put("property.type","int");
        map11.put("property.status","1");
        list.add(map11);

        Map<String,String> map12 = new HashMap();
        map12.put("instance.identifier","创建时间");
        map12.put("instance.isa",JSONObject.toJSONString(strings));
        map12.put("property.identifier","createTime");
        map12.put("property.type","date");
        map12.put("property.status","1");
        list.add(map12);
        map.put("instance.identifier","认证源模型");
        String [] a = {"type"};
        map.put("instance.isa",JSONObject.toJSONString(a));
        map.put("type.identifier","ldaptype");
        map.put("type.property",JSONObject.toJSONString(list)); //添加模型属性
        map.put("type.status","1");
        Newvcms.InstanceCreateRequest createRequest = builder.setIdentifier("type").putAllData(map).build();
        try {
            Newvcms.CreateResult createResult = instanceServiceBlockingStub.createInstance(createRequest);
            System.out.println("返回结果："+createResult);
        }finally {
            managedChannel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}
