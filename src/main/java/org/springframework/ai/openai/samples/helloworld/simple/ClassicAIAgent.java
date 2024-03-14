package org.springframework.ai.openai.samples.helloworld.simple;

import dev.langchain4j.service.SystemMessage;

public interface ClassicAIAgent {

    @SystemMessage({
            "you are an agent who do some calculations"
    })
    String calc(String msg);
}
