package com.jay.config;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class MyInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] bytes, ClientHttpRequestExecution execution) throws IOException {
        System.out.println("=============  这是自定义拦截器实现");
        System.out.println("               原来的URI：" + request.getURI());
        // 换成新的请求对象（更换URI）
        HttpRequest newRequest = new MyHttpRequest(request);
        System.out.println("               拦截后新的URI：" + newRequest.getURI());
        return execution.execute(newRequest, bytes);
    }
}
