package ROICalculatorCapstone.controllers;
import ROICalculatorCapstone.models.Property;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ROICalculatorCapstone.services.PropertyService;

import java.util.List;

//The PropertyController is a controller that handles HTTP requests related to properties.
// It communicates with the PropertyService to perform CRUD operations and return appropriate
// view templates or redirects. It provides endpoints for displaying property details, creating
// new properties, updating existing properties, and deleting properties.
@Controller
@RequestMapping("/properties")
public class PropertyController {
    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {

        this.propertyService = propertyService;
    }


    @GetMapping("/{address}")
    public String showProperty(@PathVariable String address, Model model) {
        Property property = propertyService.getPropertyByAddress(address);
        if (property != null) {
            model.addAttribute("property", property);
            return "property_detail";
        } else {
            // Handle property not found case
            return "redirect:/properties";
        }
    }
    @GetMapping
    public String getAllProperties (Model model){
    List<Property> properties = propertyService.getAllProperties();
    model.addAttribute("properties", properties);
        return "property";
    }

    @GetMapping("/new")
    public String showPropertyForm(Model model) {
        model.addAttribute("property", new Property());
        model.addAttribute("address", ""); // Add an empty address attribute to the model
        return "property_form"; // Return the property_form.html template to display the property form
    }

    @PostMapping
    public String addProperty(@ModelAttribute("property") Property property) {
        String trimmedAddress = property.getAddress().trim().toLowerCase(); // Trim and convert to lowercase
        property.setAddress(trimmedAddress);

        propertyService.saveProperty(property);
        return "redirect:/properties"; // Redirect to the /properties endpoint to display the updated list
    }

    @GetMapping("/{address}/edit")
    public String showUpdateForm(@PathVariable String address, Model model) {
        Property property = propertyService.getPropertyByAddress(address);
        if (property != null) {
            model.addAttribute("property", property);
            return "property_update"; // Return the property-form.html template to display the property update form
        }
        return "redirect:/properties"; // Redirect to the /properties endpoint if property not found
    }


    @PostMapping("/{address}")
    public String updateProperty(@PathVariable String address, @ModelAttribute("property") Property updatedProperty) {
        Property property = propertyService.getPropertyByAddress(address);
        if (property != null) {
            property.setPropertyType(updatedProperty.getPropertyType());
            property.setSqft(updatedProperty.getSqft());
            property.setNumberOfBedrooms(updatedProperty.getNumberOfBedrooms());
            property.setNumberOfBathrooms(updatedProperty.getNumberOfBathrooms());
            propertyService.saveProperty(property);
        }
        return "redirect:/properties";
    }

    @PostMapping("/{address}/delete")
    public String deleteProperty(@PathVariable String address) {
        propertyService.deleteProperty(address);
        return "redirect:/properties";
    }
}