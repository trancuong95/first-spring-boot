package cuong.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.util.StringUtils;

import cuong.domain.Contact;
import cuong.service.ContactService;

@Controller
public class ContactController {

	@Autowired
	private ContactService contactService;

	@GetMapping("/contact")
	public String index(Model model) {
		model.addAttribute("contacts", contactService.findAll());
		return "list";
	}

	@GetMapping("/contact/create")
	public String create(Model model) {
		model.addAttribute("contact", new Contact());
		return "form";
	}

	@PostMapping("/contact/save")
	public String save(@Valid Contact contact, BindingResult result, RedirectAttributes redirect) {
	    if (result.hasErrors()) {
	        return "form";
	    }
	    contactService.save(contact);
	    redirect.addFlashAttribute("successMessage", "Saved contact successfully!");
	    return "redirect:/contact";
	}

	@GetMapping("/contact/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Model model) {
	    model.addAttribute("contact", contactService.findById(id));
	    return "form";
	}

	@GetMapping("/contact/{id}/delete")
	public String delete(@PathVariable int id, RedirectAttributes redirect) {
	    contactService.deleteById(id);
	    redirect.addFlashAttribute("successMessage", "Deleted contact successfully!");
	    return "redirect:/contact";
	}

	@GetMapping("/contact/search")
	public String search(@RequestParam("term") String term, Model model) {
	    if (StringUtils.isEmpty(term)) {
	        return "redirect:/contact";
	    }

	    model.addAttribute("contacts", contactService.search(term));
	    return "list";
	}
	
	@GetMapping("/contact/add")
	public String add(Model model) {
	    model.addAttribute("contact", new Contact());
	    return "form";
	}
}
