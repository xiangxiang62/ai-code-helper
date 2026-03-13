package com.kd.szhjf.aicodehelper.factory;

import com.kd.szhjf.aicodehelper.service.AiCodeHelperService;
import com.kd.szhjf.aicodehelper.tools.InterviewQuestionTool;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // 和 AiService 只能存在一个
public class AiCodeHelperServiceFactory {

    @Resource
    private ChatModel qwenChatModel;

    @Resource
    private ContentRetriever contentRetriever;

    @Resource
    private StreamingChatModel qwenStreamingChatModel;

//    @Bean
//    public AiCodeHelperService aiCodeHelperService() {
//        return AiServices.create(AiCodeHelperService.class, qwenChatModel);
//    }

//    @Bean
//    public AiCodeHelperService aiCodeHelperService() {
//        // 会话记忆
//        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
//        AiCodeHelperService aiCodeHelperService = AiServices.builder(AiCodeHelperService.class)
//                .chatModel(qwenChatModel)
//                .chatMemory(chatMemory)
//                .build();
//        return aiCodeHelperService;
//    }


//    @Bean
//    public AiCodeHelperService aiCodeHelperService() {
//        // 会话记忆
//        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
//        // 构造 AI Service
//        AiCodeHelperService aiCodeHelperService = AiServices.builder(AiCodeHelperService.class)
//                .chatModel(qwenChatModel)
//                .chatMemory(chatMemory)
//                .contentRetriever(contentRetriever) // RAG 检索增强生成
//                .build();
//        return aiCodeHelperService;
//    }


//    @Bean
//    public AiCodeHelperService aiCodeHelperService() {
//        // 会话记忆
//        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
//        // 构造 AI Service
//        AiCodeHelperService aiCodeHelperService = AiServices.builder(AiCodeHelperService.class)
//                .chatModel(qwenChatModel)
//                .chatMemory(chatMemory)
//                .contentRetriever(contentRetriever) // RAG 检索增强生成
//                .tools(new InterviewQuestionTool()) // 工具调用
//                .build();
//        return aiCodeHelperService;
//
//    }


    @Bean
    public AiCodeHelperService aiCodeHelperService() {
        // 会话记忆
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

        AiCodeHelperService aiCodeHelperService = AiServices.builder(AiCodeHelperService.class)
                .chatModel(qwenChatModel)
                .streamingChatModel(qwenStreamingChatModel)
                .chatMemory(chatMemory)
                .chatMemoryProvider(memoryId ->
                        MessageWindowChatMemory.withMaxMessages(10)) // 每个会话独立存储
                .contentRetriever(contentRetriever) // RAG 检索增强生成
                .tools(new InterviewQuestionTool()) // 工具调用
                .build();
        return aiCodeHelperService;

    }

}