package com.example.membership.model;

import com.example.membership.status.BulkPassStatus;
import com.example.membership.status.PassStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bulk_pass")
public class BulkPassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bulkPassSeq;
    private long packageSeq;
    private String userGroupId;

    @Enumerated(EnumType.STRING)
    private BulkPassStatus status;
    private Integer count;

    private LocalDateTime regDate;
    private LocalDateTime expDate;

    public PassEntity convert(String userId){
        return PassEntity.builder()
                .packageSeq(this.packageSeq)
                .userId(userId)
                .status(PassStatus.READY)
                .remainingCount(count)
                .startedAt(regDate)
                .expiredAt(expDate)
                .build();
    }
}
