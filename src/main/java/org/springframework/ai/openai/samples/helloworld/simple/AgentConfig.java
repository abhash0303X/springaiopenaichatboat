package org.springframework.ai.openai.samples.helloworld.simple;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgentConfig {

    @Bean
    ClassicAIAgent getClassicAgent(ChatLanguageModel chatLanguageModel, CalcualationTools calcualationTools){
        return AiServices.builder(ClassicAIAgent.class).chatLanguageModel(chatLanguageModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(20))
                .tools(calcualationTools)
                .build();
    }
}
