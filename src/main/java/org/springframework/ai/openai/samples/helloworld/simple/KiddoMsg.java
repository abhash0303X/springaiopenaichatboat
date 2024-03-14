package org.springframework.ai.openai.samples.helloworld.simple;

import dev.langchain4j.agent.tool.ToolExecutionRequest;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;
import java.util.Map;

public class KiddoMsg implements Message {



    private String msg;


    private MessageType type = MessageType.USER;

    public KiddoMsg(String msg) {
        this.msg = msg;
    }

    public KiddoMsg(String msg, MessageType type) {
        this.msg = msg;
        this.type = type;
    }


    @Override
    public String getContent() {
        return msg;
    }

    @Override
    public Map<String, Object> getProperties() {
        return null;
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.USER;
    }
}
