package com.fawry.destination.model.vto;

import com.fawry.user.model.vto.LightUserVTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminDestinationVTO {
    private Long id;
    private String countryCode;
    private String countryName;
    private String capital;
    private String region;
    private String status;
    private String rejectionReason;
    private LightUserVTO createdBy;
    private String note;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime createdOn;
    private LightUserVTO lastModifiedBy;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime lastModifiedOn;
}
