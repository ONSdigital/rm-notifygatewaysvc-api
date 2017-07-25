package uk.gov.ons.ctp.response.notify.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendSmsResponse {
    private UUID id;
    private UUID templateId;

    private String reference;
    private String fromNumber;
}
