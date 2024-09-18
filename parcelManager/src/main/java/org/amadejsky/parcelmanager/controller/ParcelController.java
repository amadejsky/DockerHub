package org.amadejsky.parcelmanager.controller;

import org.amadejsky.parcelmanager.model.Parcel;
import org.amadejsky.parcelmanager.repository.ParcelRepository;
import org.amadejsky.parcelmanager.service.ParcelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parcels")
public class ParcelController {
    private final ParcelService parcelService;

    public ParcelController(ParcelService parcelService) {
        this.parcelService = parcelService;
    }
    @GetMapping()
    public List<Parcel> getAll(@RequestParam(required = false) Parcel.Status status){
        return parcelService.getParcels(status);
    }
    @PostMapping()
    public void post(@RequestBody Parcel parcel){
        parcelService.addParcel(parcel);
    }
    @PostMapping("/json")
    public void postViaPostman(@RequestBody Parcel parcel){
        parcelService.addParcel(parcel);
    }
    @GetMapping("/{code}")
    public Parcel getParcelById(@PathVariable String code){
        return parcelService.getParcel(code);
    }
//    @DeleteMapping("/{code}")
//    public void deleteParcel(@PathVariable String code){
//        parcelService.deleteParcel(code);
//    }
    @GetMapping("/{code}/change-status")
    public void setStatus(@PathVariable String code,@RequestParam Parcel.Status status){
        parcelService.setStatus(code, status);
    }
    @DeleteMapping("/delete/{code}")
    public void deleteParcelCode(@PathVariable String code) {
        parcelService.deleteParcel(code);
    }



}
// These methods have been commented out because the application's design has changed.
// As a result, they are no longer needed for the current stage of development.
// They may be removed or restored in the future if necessary.
// They are left here as a reference for future testing or changes.


//@GetMapping("/secure-operation")
//    public ResponseEntity<String> secureOperation(@RequestHeader(value = "Feign-Client", required = false) String feignClient) {
//        if (!"true".equals(feignClient)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
//        }
//        return ResponseEntity.ok("This operation is only accessible via Feign client.");
//    }
//    @DeleteMapping("/delete/{code}")
//    public ResponseEntity<String> deleteParcel(@RequestHeader(value = "Feign-Client", required = false) String feignClient,
//                                               @PathVariable String code) {
//        if (!"true".equals(feignClient)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied!");
//        }
//        parcelService.deleteParcel(code);
//        return ResponseEntity.ok("Succesfuly performed delete operation on parcel: "+code);
//    }