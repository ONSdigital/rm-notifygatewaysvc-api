package uk.gov.ons.ctp.response.notify.service;

import uk.gov.service.notify.Notification;
import uk.gov.service.notify.NotificationClient;
import uk.gov.service.notify.NotificationClientException;
import uk.gov.service.notify.NotificationResponse;

import java.util.HashMap;

/**
 * To test manually Notify
 */
public class ManualTest {
  private static final String FIRST_NAME = "firstName";
  private static final String IAC = "iac";
  private static final String TEMPLATE_ID = "966731dc-ef2e-41ad-a828-8cdd95c81ebc";

  public static void main(String[] args) throws NotificationClientException {
    NotificationClient notificationClient = new NotificationClient("ctpnotifygateway-0e515090-7962-40e8-a8c7-acbacf079c21-ac979394-a989-4f79-b0e0-6abcca564393");
    HashMap<String, String> personalisation = new HashMap<>();
    personalisation.put(FIRST_NAME, "John");
    personalisation.put(IAC, "ABCD EFGH IJKL");
    NotificationResponse response = notificationClient.sendSms(TEMPLATE_ID, "07985675158", personalisation);
    String notificationId = response.getNotificationId();
    Notification notification = notificationClient.getNotificationById(notificationId);
    String status = notification.getStatus();
    System.out.println("status = {}" + status);
  }
}