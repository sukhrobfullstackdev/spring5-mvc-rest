package guru.springfamework.services.implementations;

import guru.springfamework.domain.Vendor;
import guru.springfamework.exception.ResourceNotFoundException;
import guru.springfamework.payload.VendorDTO;
import guru.springfamework.repositories.VendorRepository;
import guru.springfamework.services.interfaces.VendorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;
    private final static String VENDOR_BASE_URL = "/api/v1/vendors";

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<VendorDTO> getVendors() {
        List<Vendor> vendors = vendorRepository.findAll();
        List<VendorDTO> vendorDTOS = new ArrayList<>();
        for (Vendor vendor : vendors) {
            vendorDTOS.add(new VendorDTO(vendor.getName(), VENDOR_BASE_URL + "/" + vendor.getId()));
        }
        return vendorDTOS;
    }

    @Override
    public VendorDTO getVendor(Long id) {
        Optional<Vendor> optionalVendor = vendorRepository.findById(id);
        if (optionalVendor.isPresent()) {
            Vendor vendor = optionalVendor.get();
            return new VendorDTO(vendor.getName(), VENDOR_BASE_URL + "/" + vendor.getId());
        } else {
            throw new ResourceNotFoundException("The vendor has not been found by id: " + id);
        }
    }

    @Override
    public VendorDTO createVendor(VendorDTO vendorDTO) {
        Vendor savedVendor = vendorRepository.save(new Vendor(vendorDTO.getName()));
        return new VendorDTO(savedVendor.getName(), VENDOR_BASE_URL + "/" + savedVendor.getId());
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
        Optional<Vendor> optionalVendor = vendorRepository.findById(id);
        if (optionalVendor.isPresent()) {
            Vendor vendor = optionalVendor.get();
            vendor.setName(vendorDTO.getName());
            Vendor savedVendor = vendorRepository.save(vendor);
            return new VendorDTO(savedVendor.getName(), VENDOR_BASE_URL + "/" + savedVendor.getId());
        } else {
            throw new ResourceNotFoundException("The vendor has not been found to edit by id: " + id);
        }
    }

    @Override
    public void deleteVendor(Long id) {
        Optional<Vendor> optionalVendor = vendorRepository.findById(id);
        if (optionalVendor.isPresent()) {
            vendorRepository.delete(optionalVendor.get());
        } else {
            throw new ResourceNotFoundException("The vendor has not been found to delete by id: " + id);
        }
    }
}
