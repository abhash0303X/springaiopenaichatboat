package org.springframework.ai.openai.samples.helloworld.simple;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.StreamingChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.beans.Mergeable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class SimpleAiController {

	private final ChatClient chatClient;

	@Autowired
	private ClassicAIAgent agent;

	@Autowired
	private EmbeddingClient embeddingClient;

	@Autowired
	public SimpleAiController(ChatClient chatClient) {
		this.chatClient = chatClient;
	}

	List<Message> qa = refreshList();
	Queue<Message> msgQue = new ArrayDeque<>();
	String answer ="";

	@GetMapping("/ai/simple")
	public String completion(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {

		if(qa.size() > 20){
			qa = refreshList();
		}
		if(answer.contains("?")){
			qa.add(new KiddoMsg("Thia's Question: " + answer +
					", user's response: " + message + "?", MessageType.ASSISTANT));
		}else{
			qa.add(new KiddoMsg("Question: " + message + "? answer: ?"));
		}
		Prompt prompt = new Prompt(qa);

		ChatResponse res = chatClient.call(prompt);

		answer = res.getResult().getOutput().getContent();
		qa.get(qa.size()-1).getContent().replace("? answer: ?", "? answer: " + answer);
		return answer;
	}

	private List<Message> refreshList() {
		List<Message> s = new ArrayList<>();
		s.add(new KiddoMsg("Instruction: Consider that you are Thia and you are talking to a 5 to 8 year old child." +
				" Please answer the questions in a kid language and use very simple words and fewer words." +
				" Also if do not generate user's response, user will provide the response and based on that you should generate next question or your response."));
		if(qa != null && qa.size() > 0){
			s.addAll(qa.subList(2, qa.size()));
		}

		return s;
	}

	@GetMapping("/ai/reset")
	public String reset() {
		qa = null;
		qa = refreshList();
		return "reset success!";
	}

	@GetMapping("/ai/calc")
	public String calc(@RequestParam String msg) {
		return agent.calc(msg);
	}
}
