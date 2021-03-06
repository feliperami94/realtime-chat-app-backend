package com.chat.testchat.controller;

import com.chat.testchat.Dto.MessageDto;
import com.chat.testchat.usecases.MessageUseCase;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controller for messages - Rest
 * @author dannielf - feliperami94
 * @version 0.0.1
 * @since 0.0.1
 */
@RestControllerAdvice
@AllArgsConstructor
@RequestMapping(path = "/messages")
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
    private final MessageUseCase messageUseCase;

    @GetMapping(path = "/channel/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<MessageDto> findByIdReceiver(@PathVariable("id") String id) {
        return messageUseCase.findAllByIdReceiver(id);
    }

    @GetMapping(path = "/private/{idSender}/{idReceiver}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<MessageDto> findByIdSenderAndIdReceiver(@PathVariable("idSender") String idSender,
                                                        @PathVariable("idReceiver") String idReceiver) {
        return messageUseCase.findAllByIdSenderAndIdReceiver(idSender, idReceiver);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteMessageById(@PathVariable("id") String id) {
        return messageUseCase.deleteMessageById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Mono<MessageDto>  updateMessage(@RequestBody MessageDto messageDto){
        return messageUseCase.updateMessage(messageDto);
    }
}
