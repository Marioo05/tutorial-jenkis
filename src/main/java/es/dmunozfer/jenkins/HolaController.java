package es.dmunozfer.jenkins;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HolaController {

    @RequestMapping("/")
    public String raiz() {
	return "redirect:/hola";
    }
    
    @RequestMapping("/hola")
    public String hola(@RequestParam(value = "nombre", required = false, defaultValue = "Mundo") String nombre,
	    Model model) {
	model.addAttribute("nombre", nombre);
	return "hola";
    }
}
