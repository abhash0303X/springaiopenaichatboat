package org.springframework.ai.openai.samples.helloworld.simple;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;


@Component
public class CalcualationTools {


    @Tool("Calculates the length of a string")
    int stringLength(String s) {
        return s.length();
    }

    @Tool("Calculates the sum of two numbers")
    int add(int a, int b) {
        return a + b;
    }

    @Tool("Calculates the square root of a number")
    double sqrt(int x) {
        return Math.sqrt(x);
    }

    @Tool("Establish connection with SQL db and extract schema details")
    String schema(String connection){

        return connection;
    }
}
