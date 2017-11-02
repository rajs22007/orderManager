package com.sr.om.dal.mysql.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Version;

import org.joda.time.DateTime;

@MappedSuperclass
public class AppendBaseEntity {

    @Column(name = "created_at_epoch", updatable = false, nullable = false)
    private long createdAtEpoch;

    @Version
    @Column(name = "version")
    private int version;

    public long getCreatedAtEpoch() {
        return createdAtEpoch;
    }

    public void setCreatedAtEpoch(long createdAtEpoch) {
        this.createdAtEpoch = createdAtEpoch;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "AppendBaseEntity [createdAtEpoch=" + createdAtEpoch + ", version=" + version + "]";
    }

    @PrePersist
    public void onPrePersist() {
        // this.createdAtEpoch = new DateTime().getMillis();
        this.createdAtEpoch = System.currentTimeMillis();

    }

}
