package com.example.membership.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user_group_mapping")
@IdClass(UserGroupMappingId.class)
public class UserGroupMappingEntity {
    @Id
    private String userGroupId;
    @Id
    private String userId;

    private String userGroupName;
    private String description;
}
