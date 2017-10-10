package org.egov.property.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.egov.models.NoticeRequest;
import org.egov.property.services.NoticeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@EnableKafka
public class NoticeConsumer {

    private String createNoticeTopic;

    private ObjectMapper objectMapper;

    private NoticeService noticeService;

    public NoticeConsumer(@Value("${egov.propertytax.property.notice.create}") String createNoticeTopic,
                          ObjectMapper objectMapper, NoticeService noticeService) {
        this.createNoticeTopic = createNoticeTopic;
        this.objectMapper = objectMapper;
        this.noticeService = noticeService;
    }

    @KafkaListener(topics = {"${egov.propertytax.property.notice.create}"})
    public void listen(Map<String, Object> consumerRecord, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic)
            throws Exception {
        if (topic.equalsIgnoreCase(createNoticeTopic))
            noticeService.create(objectMapper.convertValue(consumerRecord, NoticeRequest.class));
    }
}