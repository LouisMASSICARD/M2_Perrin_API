package org.miage.m2.boundary.queue;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CoursChannel {
    @Input
    SubscribableChannel input();
}
