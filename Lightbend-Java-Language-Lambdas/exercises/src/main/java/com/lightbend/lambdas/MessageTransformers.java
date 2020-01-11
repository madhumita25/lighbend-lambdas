package com.lightbend.lambdas;

import java.time.Instant;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class MessageTransformers {
    /*
    Original:
    static MessageTransformer<String> toString = new MessageTransformer<String>() {
        @Override
        public String apply(Message message) {
            return message.toString();
        }
    };
        static MessageTransformer<Instant> toTimestamp = new MessageTransformer<Instant>() {
        @Override
        public Instant apply(Message message) {
            return message.getTimestamp();
        }
    };
    static MessageTransformer<Message> toUpperCase = new MessageTransformer<Message>() {
        @Override
        public Message apply(Message message) {
            return new Message(message.getId(), message.getTimestamp(), message.getContent().toUpperCase());
        }
    };
    */

    /*
    Lambadas:

    static MessageTransformer<String> toString = m -> m.toString();

    static MessageTransformer<Instant> toTimestamp = m -> m.getTimestamp();

    static MessageTransformer<Message> toUpperCase = m -> new Message(m.getId(), m.getTimestamp(), m.getContent().toUpperCase());
   */

    /*
    Method Reference

    static MessageTransformer<String> toString = Message::toString;

    static MessageTransformer<Instant> toTimestamp = Message::getTimestamp;

    static MessageTransformer<Message> toUpperCase = m -> new Message(m.getId(), m.getTimestamp(), m.getContent().toUpperCase());
    */

    /*
    Use preexisting functional interfaces
     */
    static Function<Message, String> toString = Message::toString;

    static Function<Message, Instant> toTimestamp = Message::getTimestamp;

    static Function<Message, Message> toUpperCase = m -> new Message(m.getId(), m.getTimestamp(), m.getContent().toUpperCase());

/*
Original
    static <T> List<T> mapMessages(List<Message> messages, Function<Message,T> transformer) {
        return messages
                .stream()
                .map(transformFunction(transformer))
                .collect(Collectors.toList());
    }
    private static <T> Function<Message, T> transformFunction(Function<Message,T> transformer) {
        return new Function<Message, T>() {
            @Override
            public T apply(Message message) {
                return transformer.apply(message);
            }
        };
    }
 */
/*
Using Lambdas

    static <T> List<T> mapMessages(List<Message> messages, Function<Message, T> transformer) {
        return messages
                .stream()
                .map(message -> transformer.apply(message))
                .collect(Collectors.toList());
    }
     */

    /* Using method reference */
    static <T> List<T> mapMessages(List<Message> messages, Function<Message,T> transformer) {
        return messages
            .stream()
            .map(transformer)
            .collect(Collectors.toList());
    }
}
/* From Deleted file
MessageTransformer.java
package com.lightbend.lambdas;

@FunctionalInterface
interface MessageTransformer<T> {
    T apply(Message message);
}
 */
