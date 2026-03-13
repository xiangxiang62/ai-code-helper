package com.kd.szhjf.aicodehelper.service;

import dev.langchain4j.rag.content.Content;
import dev.langchain4j.service.Result;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AiCodeHelperServiceTest {

    @Resource
    private AiCodeHelperService aiCodeHelperService;


     @Test
    void chat() {
        String result = aiCodeHelperService.chat("你好，我是程序员锟斤拷");
        System.out.println(result);
    }

    @Test
    void chatWithMemory() {
        String result = aiCodeHelperService.chat("你好，我是程序员锟斤拷");
        System.out.println(result);
        result = aiCodeHelperService.chat("你好，我是谁来着？");
        System.out.println(result);
    }

    @Test
    void chatForReport() {
        String userMessage = "你好，我是程序员锟斤拷，学编程两年半，请帮我制定学习报告";
        AiCodeHelperService.Report report = aiCodeHelperService.chatForReport(userMessage);
        System.out.println(report);
    }


    @Test
    void chatWithRag() {
        Result<String> result = aiCodeHelperService.chatWithRag("讲讲高并发负载均衡");
        String content = result.content();
        List<Content> sources = result.sources();
        System.out.println(content);
        System.out.println(sources);
    }




}
