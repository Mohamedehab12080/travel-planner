package com.fawry.destination.model.vto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DestinationSummaryVTO {
    private Long total;
    private Long pending;
    private Long approved;
    private Long rejected;
}