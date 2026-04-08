package com.fawry.wishlist.model.entity;

import com.fawry.destination.model.entity.Destination;
import com.fawry.user.model.entity.User;
import com.fawry.wishlist.model.enums.WishlistStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tp_wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;

    @Basic
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private WishlistStatus status;

    @Basic
    @Column(name = "priority")
    private Integer priority;

    @Basic
    @Column(name = "notes")
    private String notes;

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

    @Basic
    @Column(name = "visited_date")
    private LocalDate visitedDate;

    @Basic
    @Column(name = "rating")
    private Integer rating;

    @Basic
    @Column(name = "review")
    private String review;
}
