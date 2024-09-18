package org.amadejsky.parcelmanager.service;

import org.amadejsky.parcelmanager.model.Parcel;
import org.amadejsky.parcelmanager.repository.ParcelRepository;

import java.util.List;

public interface ParcelService {
    List<Parcel> getParcels(Parcel.Status status);
    Parcel addParcel(Parcel parcel);
    Parcel getParcel(String code);
    void deleteParcel(String code);

    void setStatus(String code, Parcel.Status status);



}
