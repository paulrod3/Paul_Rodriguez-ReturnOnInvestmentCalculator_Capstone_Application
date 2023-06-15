package ROICalculatorCapstone.models.controllers;
import ROICalculatorCapstone.models.Property;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ROICalculatorCapstone.services.PropertyService;

import java.util.List;

@Controller
@RequestMapping("/properties")
public class PropertyController {
    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
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
        return "property_form"; // Return the property-form.html template to display the property form
    }

    @PostMapping
    public String addProperty(@ModelAttribute("property") Property property) {
        propertyService.saveProperty(property);
        return "redirect:/properties"; // Redirect to the /properties endpoint to display the updated list
    }

    @GetMapping("/{address}/edit")
    public String showUpdateForm(@PathVariable String address, Model model) {
        Property property = propertyService.getPropertyByAddress(address);
        if (property != null) {
            model.addAttribute("property", property);
            return "property-form"; // Return the property-form.html template to display the property update form
        }
        return "redirect:/properties"; // Redirect to the /properties endpoint if property not found
    }

    @PostMapping("/{address}")
    public String updateProperty(@PathVariable String address, @ModelAttribute("property") Property updatedProperty) {
        Property property = propertyService.getPropertyByAddress(address);
        if (property != null) {
            // Update the necessary fields of the property with the updatedProperty values
            property.setPropertyType(updatedProperty.getPropertyType());
            property.setSqft(updatedProperty.getSqft());
            property.setNumberOfBedrooms(updatedProperty.getNumberOfBedrooms());
            property.setNumberOfBathrooms(updatedProperty.getNumberOfBathrooms());

            propertyService.saveProperty(property);
        }
        return "redirect:/properties"; // Redirect to the /properties endpoint to display the updated list
    }

    @GetMapping("/{address}/delete")
    public String deleteProperty(@PathVariable String address) {
        propertyService.deleteProperty(address);
        return "redirect:/properties"; // Redirect to the /properties endpoint to display the updated list
    }
}