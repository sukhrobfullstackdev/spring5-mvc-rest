package guru.springfamework.services.interfaces;

import guru.springfamework.payload.VendorDTO;

import java.util.List;

public interface VendorService {
    List<VendorDTO> getVendors();

    VendorDTO getVendor(Long id);

    VendorDTO createVendor(VendorDTO vendorDTO);

    VendorDTO updateVendor(Long id, VendorDTO vendorDTO);

    void deleteVendor(Long id);
}
