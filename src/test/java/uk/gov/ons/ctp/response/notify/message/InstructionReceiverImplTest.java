package uk.gov.ons.ctp.response.notify.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import uk.gov.ons.ctp.common.error.CTPException;
import uk.gov.ons.ctp.response.action.message.instruction.ActionInstruction;
import uk.gov.ons.ctp.response.notify.message.impl.InstructionReceiverImpl;
import uk.gov.ons.ctp.response.notify.service.NotifyService;
import uk.gov.ons.ctp.response.notify.utility.ObjectBuilder;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static uk.gov.ons.ctp.response.notify.utility.ObjectBuilder.buildTestData;

/**
 * To unit test InstructionReceiverImpl
 */
@RunWith(MockitoJUnitRunner.class)
public class InstructionReceiverImplTest {

  @InjectMocks
  InstructionReceiverImpl instructionReceiver;

  @Mock
  private NotifyService notifyService;

  @Mock
  private Tracer tracer;

  @Test
  public void testProcessInstruction() throws CTPException {
    instructionReceiver.processInstruction(ObjectBuilder.buildActionInstruction(buildTestData()));
    verify(tracer, times(1)).createSpan(any(String.class));
    verify(notifyService, times(1)).process(any(ActionInstruction.class));
    verify(tracer, times(1)).close(any(Span.class));
  }
}
