package org.amadejsky.parcelmanager.service;

import org.amadejsky.parcelmanager.model.Notification;
import org.amadejsky.parcelmanager.model.Parcel;
import org.amadejsky.parcelmanager.repository.ParcelRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ParcelServiceImpl implements ParcelService{

    private final ParcelRepository parcelRepository;
    private final RabbitTemplate rabbitTemplate;

    public ParcelServiceImpl(ParcelRepository parcelRepository, RabbitTemplate rabbitTemplate) {
        this.parcelRepository = parcelRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public List<Parcel> getParcels(Parcel.Status status) {
        if(status!=null){
            return parcelRepository.findAllByStatus(status);
        }
        return parcelRepository.findAll();
        // .findAllByStatusNotInactive() previous concept method
    }

    @Override
    public Parcel getParcel(String code) {
        return parcelRepository.findById(code)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Parcel addParcel(Parcel parcel) {
        parcelRepository.save(parcel);
        buildAndSendNotification(parcel, Notification.Info.CREATED);
        return parcel;

    }



    @Override
    public void deleteParcel(String code) {
        Parcel parcel = parcelRepository.findById(code)
                .orElseThrow(IllegalArgumentException::new);
        parcel.setStatus(Parcel.Status.INACTIVE);
        parcelRepository.save(parcel);
        buildAndSendNotification(parcel, Notification.Info.DELETED);


    }

    @Override
    public void setStatus(String code, Parcel.Status status) {
        Parcel parcel = parcelRepository.findById(code)
                .orElseThrow(IllegalArgumentException::new);
        parcel.setStatus(status);
        parcelRepository.save(parcel);
        buildAndSendNotification(parcel, Notification.Info.UPDATED);
    }

    private void buildAndSendNotification(Parcel parcel, Notification.Info info) {
        Notification notification = Notification.builder()
                .info(info)
                .code(parcel.getCode())
                .address(parcel.getAddress())
                .status(parcel.getStatus())
                .build();
        rabbitTemplate.convertAndSend("parcel_manager_event",notification);
    }
}
