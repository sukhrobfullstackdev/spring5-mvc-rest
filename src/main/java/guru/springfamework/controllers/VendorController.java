package guru.springfamework.controllers;

import guru.springfamework.payload.VendorDTO;
import guru.springfamework.payload.VendorListDTO;
import guru.springfamework.services.interfaces.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vendors")
public class VendorController {
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public ResponseEntity<VendorListDTO> getVendors() {
        return ResponseEntity.ok(new VendorListDTO(vendorService.getVendors()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorDTO> getVendor(@PathVariable Long id) {
        return ResponseEntity.ok(vendorService.getVendor(id));
    }

    @PostMapping("/create")
    public ResponseEntity<VendorDTO> createVendor(@RequestBody VendorDTO vendorDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vendorService.createVendor(vendorDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VendorDTO> updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(vendorService.updateVendor(id, vendorDTO));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
    }
}
