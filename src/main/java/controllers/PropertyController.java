package controllers;
import models.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.PropertyService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/properties")
public class PropertyController {
    private PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @GetMapping("/{address}")
    public Property getPropertyByAddress(@PathVariable String address) {
        return propertyService.getPropertyByAddress(address);
    }

    @PostMapping
    public Property addProperty(@RequestBody Property property) {
        return propertyService.saveProperty(property);
    }

    @PutMapping("/{address}")
    public Property updateProperty(@PathVariable String address, @RequestBody Property updatedProperty) {
        Property property = propertyService.getPropertyByAddress(address);
        if (property != null) {
            // Update the necessary fields of the property with the updatedProperty values
            property.setPropertyType(updatedProperty.getPropertyType());
            property.setSqft(updatedProperty.getSqft());
            property.setNumberOfBedrooms(updatedProperty.getNumberOfBedrooms());
            property.setNumberOfBathrooms(updatedProperty.getNumberOfBathrooms());

            return propertyService.saveProperty(property);
        }
        return null; // Handle error if property not found
    }

    @DeleteMapping("/{address}")
    public void deleteProperty(@PathVariable String address) {
        propertyService.deleteProperty(address);
    }
}