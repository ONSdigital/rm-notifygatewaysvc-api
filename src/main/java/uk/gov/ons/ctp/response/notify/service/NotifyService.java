package uk.gov.ons.ctp.response.notify.service;

import uk.gov.ons.ctp.common.error.CTPException;
import uk.gov.ons.ctp.response.action.message.feedback.ActionFeedback;
import uk.gov.ons.ctp.response.action.message.instruction.ActionRequest;

/**
 * Service to interact with GOV.UK Notify
 */
public interface NotifyService {
  /**
   * To process an ActionRequest. It sends an SMS using GOV.UK Notify.
   *
   * @param actionRequest to be processed
   * @return the associated ActionFeedback
   * @throws CTPException if GOV.UK Notify gives an issue
   */
  ActionFeedback process(ActionRequest actionRequest) throws CTPException;
}
