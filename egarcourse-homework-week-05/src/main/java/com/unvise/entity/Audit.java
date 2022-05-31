package com.unvise.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Embeddable
@Data
public class Audit {

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @PrePersist
    public void create() {
        createAt = LocalDateTime.now();
    }

    @PreUpdate
    public void update() {
        updateAt = LocalDateTime.now();
    }
}
