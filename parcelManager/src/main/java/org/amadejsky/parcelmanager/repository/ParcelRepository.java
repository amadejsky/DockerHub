package org.amadejsky.parcelmanager.repository;

import org.amadejsky.parcelmanager.model.Parcel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcelRepository extends MongoRepository<Parcel,String> {
    @Query("{ 'status': { $ne: 'INACTIVE' } }")
    List<Parcel> findAllByStatusNotInactive();

    List<Parcel> findAllByStatus(Parcel.Status status);
    
}
