package com.sr.om.dal.mysql.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.joda.time.DateTime;

@MappedSuperclass
public abstract class BaseEntity extends AppendBaseEntity {

    @Column(name = "updated_at_epoch", nullable = false)
    private long updatedAtEpoch;

    public long getUpdatedAtEpoch() {
        return updatedAtEpoch;
    }

    public void setUpdatedAtEpoch(long updatedAtEpoch) {
        this.updatedAtEpoch = updatedAtEpoch;
    }

    @PrePersist
    public void onPrePersist() {
        // DateTime time = new DateTime();
        // this.setCreatedAtEpoch(time.getMillis());
        // this.updatedAtEpoch = time.getMillis();
        this.setCreatedAtEpoch(System.currentTimeMillis());
        this.updatedAtEpoch = System.currentTimeMillis();

    }

    @PreUpdate
    public void onPreUpdate() {
        // DateTime time = new DateTime();
        // this.updatedAtEpoch = time.getMillis();
        this.updatedAtEpoch = System.currentTimeMillis();

    }

}
