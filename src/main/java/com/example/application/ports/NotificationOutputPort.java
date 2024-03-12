package com.example.application.ports;

import com.example.domain.entities.NotificationDetails;
import com.example.domain.exceptions.NotificationException;

/**
 * NotificationOutputPort outlines the interface for sending out notifications such as email verifications,
 * decoupling the notification mechanism from the application service logic.
 * It adheres to the hexagonal architecture principles by using the NotificationDetails object to communicate with other layers.
 */
public interface NotificationOutputPort {

    /**
     * Sends a notification based on the provided notification details.
     * The 'details' parameter must contain the recipient's information, the message content, and the notification type.
     * In case of a failure in sending the notification, a NotificationException is thrown.
     *
     * @param details The notification details
     * @throws NotificationException If there is a failure in sending the notification
     */
    void sendNotification(NotificationDetails details) throws NotificationException;

}
