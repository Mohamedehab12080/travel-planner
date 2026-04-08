package com.fawry.destination.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BulkDestinationDTO {

    @NotEmpty(message = "Destinations list cannot be empty")
    @Valid
    private List<DestinationDTO> destinations;

    @NotNull(message = "Auto approve flag is required")
    private Boolean autoApprove;
}