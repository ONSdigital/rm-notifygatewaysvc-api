package uk.gov.ons.ctp.response.notify.endpoint;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uk.gov.ons.ctp.common.endpoint.CTPEndpoint;
import uk.gov.ons.ctp.common.error.CTPException;
import uk.gov.ons.ctp.common.error.InvalidRequestException;
import uk.gov.ons.ctp.response.notify.message.NotifyRequestPublisher;
import uk.gov.ons.ctp.response.notify.message.notify.NotifyRequest;
import uk.gov.ons.ctp.response.notify.representation.SendSmsResponseDTO;
import uk.gov.ons.ctp.response.notify.representation.NotifyRequestDTO;
import uk.gov.service.notify.SendSmsResponse;

import javax.validation.Valid;
import java.net.URI;

/**
 * The REST endpoint controller for Text Messages
 */
@RestController
@RequestMapping(value = "/texts", produces = "application/json")
@Slf4j
public class TextEndpoint implements CTPEndpoint {

    @Qualifier("notifySvcBeanMapper")
    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private NotifyRequestPublisher notifyRequestPublisher;

    /**
     * To send a text message using template id
     *
     * @param templateId the GOV.UK Notify text message template id
     * @param notifyRequestDTO the NotifyRequestDTO containing phoneNumber and personalisation
     * @param bindingResult the bindingResult used to validate requests
     * @return the created SendSmsResponseDTO
     * @throws CTPException on failure to create CaseEvent
     * @throws InvalidRequestException if binding errors
     */
    @RequestMapping(value = "/{templateId}", method = RequestMethod.POST)
    public ResponseEntity<SendSmsResponseDTO> sendTextMessage(@PathVariable("templateId") final String templateId,
                                                              @RequestBody @Valid final NotifyRequestDTO notifyRequestDTO,
                                                              BindingResult bindingResult) throws CTPException, InvalidRequestException {
        log.info("Entering sendTextMessage with templateId {} and requestObject {}", templateId, notifyRequestDTO);

        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Binding errors for case event creation: ", bindingResult);
        }

        NotifyRequest notifyRequest = mapperFacade.map(notifyRequestDTO, NotifyRequest.class);
        notifyRequest.setTemplateId(templateId);
        notifyRequestPublisher.send(notifyRequest);

        // TODO Store to DB?

        // TODO Define URI
        return ResponseEntity.created(URI.create("TODO")).body(mapperFacade.map(new SendSmsResponse("TODO"),
                SendSmsResponseDTO.class));
    }
}
