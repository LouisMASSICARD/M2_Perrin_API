package org.miage.m2.boundary;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author louis
 * @version 1.0.0
 * Janvier 2021
 */
public interface CoursChannel {
    @Input
    SubscribableChannel input();
}
