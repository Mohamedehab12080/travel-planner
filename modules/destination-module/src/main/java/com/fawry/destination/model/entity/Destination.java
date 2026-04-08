package com.fawry.destination.model.entity;

import com.fawry.user.model.entity.User;
import com.fawry.destination.model.enums.DestinationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tp_destination")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Basic
    @Column(name = "country_code")
    private String countryCode;

    @Basic
    @Column(name = "country_name")
    private String countryName;

    @Basic
    @Column(name="capital")
    private String capital;

    @Basic
    @Column(name="region")
    private String region;

    @Basic
    @Column(name="sub_region")
    private String subRegion;

    @Basic
    @Column(name="population")
    private Long population;

    @Basic
    @Column(name = "currency_name")
    private String currencyName;

    @Basic
    @Column(name = "currency_code")
    private String currencyCode;

    @Basic
    @Column(name = "flag_url")
    private String flagUrl;

    @Basic
    @Column(name="languages")
    private String languages;

    @Basic
    @Column(name="time_zones")
    private String timezones;

    @Basic
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private DestinationStatus status;

    @Basic
    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "last_modified_by_id")
    private User lastModifiedBy;

    @Basic
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Basic
    @Column(name = "last_modified_on")
    private LocalDateTime lastModifiedOn;

}