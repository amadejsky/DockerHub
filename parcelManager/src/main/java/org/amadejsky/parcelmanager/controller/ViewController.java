package org.amadejsky.parcelmanager.controller;

import org.amadejsky.parcelmanager.model.Parcel;
import org.amadejsky.parcelmanager.service.ParcelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin-tool")
public class ViewController {

    private final ParcelService parcelService;

    public ViewController(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    @RequestMapping(path = "/list")
    public String report(Model model) {
        List<Parcel> reportList = parcelService.getParcels(null);

        model.addAttribute("parcel", reportList);
        return "adminPanelView";
    }
    @RequestMapping("/delete/{code}")
    public String deleteParcelCode(Model model, @PathVariable String code) {
        parcelService.deleteParcel(code);
        return "redirect:/admin-tool/list";
    }
    @RequestMapping("/update-status/{code}")
    public String setStatus(@PathVariable String code,@RequestParam Parcel.Status status){
        parcelService.setStatus(code, status);
        return "redirect:/admin-tool/list";
    }

}
