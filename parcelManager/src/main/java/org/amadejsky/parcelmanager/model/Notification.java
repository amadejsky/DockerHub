package org.amadejsky.parcelmanager.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Notification {
    private Info info;
    private String code;
    private String address;
    private Parcel.Status status;

    public enum Info{
        CREATED,
        UPDATED,
        DELETED
    }
}
