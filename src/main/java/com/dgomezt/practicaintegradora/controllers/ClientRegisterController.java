package com.dgomezt.practicaintegradora.controllers;

import com.dgomezt.practicaintegradora.entities.Client;
import com.dgomezt.practicaintegradora.entities.User;
import com.dgomezt.practicaintegradora.entities.dtos.clientForm.ContactDataDTO;
import com.dgomezt.practicaintegradora.entities.dtos.clientForm.OtherDataDTO;
import com.dgomezt.practicaintegradora.entities.dtos.clientForm.PersonalDataDTO;
import com.dgomezt.practicaintegradora.entities.helpers.Address;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.services.*;
import com.dgomezt.practicaintegradora.utilities.ConfProperties;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/client")
public class ClientRegisterController {
    private final String PERSONAL_DATA_OBJECT = "personalDataDTO";
    private final String CONTACT_DATA_OBJECT = "contactDataDTO";
    private final String OTHER_DATA_OBJECT = "otherDataDTO";

    @Autowired
    ConfProperties confProperties;
    @Autowired
    DocumentTypeService documentTypeService;
    @Autowired
    GenderService genderService;
    @Autowired
    CountryService countryService;
    @Autowired
    TypeRoadService typeRoadService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ClientService clientService;
    @Autowired
    AddressService addressService;

    @ModelAttribute
    public void addCollections(Model model) {
        model.addAttribute("genderList", genderService.findAll());
        model.addAttribute("documentTypeList", documentTypeService.findAll());
        model.addAttribute("countryList", countryService.findAll());
        model.addAttribute("typeRoadList", typeRoadService.findAll());
        model.addAttribute("categoryList", categoryService.findAll());
    }

    @GetMapping("/register/step1")
    public ModelAndView initRegister(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();

        PersonalDataDTO personalDataDTO = (PersonalDataDTO) httpSession.getAttribute(PERSONAL_DATA_OBJECT);

        if (personalDataDTO == null)
            personalDataDTO = new PersonalDataDTO();

        modelAndView.addObject(personalDataDTO);

        modelAndView.setViewName("main");
        modelAndView.addObject(confProperties.CONTENT_CONTAINER, "client/formClient");
        modelAndView.addObject(confProperties.FRAGMENT_CONTAINER, "personalData");
        return modelAndView;
    }

    @PostMapping("/register/step1")
    public ModelAndView personalDataStep(@Valid PersonalDataDTO personalDataDTO,
                                         BindingResult bindingResult,
                                         HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");

        if (!bindingResult.hasErrors()) {
            httpSession.setAttribute(PERSONAL_DATA_OBJECT, personalDataDTO);
            modelAndView.setViewName("redirect:step2");
            return modelAndView;
        }

        if (bindingResult.hasGlobalErrors()) {
            bindingResult.rejectValue("document", "PatternDniNie", bindingResult.getGlobalError().getDefaultMessage());
        }

        modelAndView.addObject(personalDataDTO);
        modelAndView.addObject(confProperties.CONTENT_CONTAINER, "client/formClient");
        modelAndView.addObject(confProperties.FRAGMENT_CONTAINER, "personalData");
        return modelAndView;
    }

    @GetMapping("/register/step2")
    public ModelAndView contactDataStep(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();

        ContactDataDTO contactDataDTO = (ContactDataDTO) httpSession.getAttribute(CONTACT_DATA_OBJECT);

        if (contactDataDTO == null)
            contactDataDTO = new ContactDataDTO();

        modelAndView.addObject(contactDataDTO);

        modelAndView.setViewName("main");
        modelAndView.addObject(confProperties.CONTENT_CONTAINER, "client/formClient");
        modelAndView.addObject(confProperties.FRAGMENT_CONTAINER, "contactData");
        return modelAndView;
    }

    @PostMapping("/register/step2")
    public ModelAndView contactDataStep(@Valid ContactDataDTO contactDataDTO,
                                        BindingResult bindingResult,
                                        HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");

        if (!bindingResult.hasErrors()) {
            httpSession.setAttribute(CONTACT_DATA_OBJECT, contactDataDTO);
            modelAndView.setViewName("redirect:step3");
            return modelAndView;
        }

        modelAndView.addObject(contactDataDTO);
        modelAndView.addObject(confProperties.CONTENT_CONTAINER, "client/formClient");
        modelAndView.addObject(confProperties.FRAGMENT_CONTAINER, "contactData");
        return modelAndView;
    }

    @GetMapping("/register/step3")
    public ModelAndView otherDataStep(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();

        OtherDataDTO otherDataDTO = (OtherDataDTO) httpSession.getAttribute(OTHER_DATA_OBJECT);

        if (otherDataDTO == null)
            otherDataDTO = new OtherDataDTO();

        modelAndView.addObject(otherDataDTO);

        modelAndView.setViewName("main");
        modelAndView.addObject(confProperties.CONTENT_CONTAINER, "client/formClient");
        modelAndView.addObject(confProperties.FRAGMENT_CONTAINER, "otherData");
        return modelAndView;
    }

    @PostMapping("/register/step3")
    public ModelAndView contactDataStep(@Valid OtherDataDTO otherDataDTO,
                                        BindingResult bindingResult,
                                        HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");

        if (!bindingResult.hasErrors()) {
            httpSession.setAttribute(OTHER_DATA_OBJECT, otherDataDTO);
            modelAndView.setViewName("redirect:/client/register/resume");
            return modelAndView;
        }

        modelAndView.addObject(otherDataDTO);
        modelAndView.addObject(confProperties.CONTENT_CONTAINER, "client/formClient");
        modelAndView.addObject(confProperties.FRAGMENT_CONTAINER, "otherData");
        return modelAndView;
    }

    @GetMapping("/register/resume")
    public ModelAndView resumeRegister(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");

        ContactDataDTO contactDataDTO = (ContactDataDTO) httpSession.getAttribute(CONTACT_DATA_OBJECT);
        PersonalDataDTO personalDataDTO = (PersonalDataDTO) httpSession.getAttribute(PERSONAL_DATA_OBJECT);
        OtherDataDTO otherDataDTO = (OtherDataDTO) httpSession.getAttribute(OTHER_DATA_OBJECT);

        modelAndView.addObject(contactDataDTO);
        modelAndView.addObject(personalDataDTO);
        modelAndView.addObject(otherDataDTO);

        modelAndView.addObject(confProperties.CONTENT_CONTAINER, "client/formClient");
        modelAndView.addObject(confProperties.FRAGMENT_CONTAINER, "resume");
        return modelAndView;
    }

    @PostMapping("/register/resume")
    public ModelAndView register(HttpSession httpSession) throws ElementNotFoundException {
        ModelAndView modelAndView = new ModelAndView();

        ContactDataDTO contactDataDTO = (ContactDataDTO) httpSession.getAttribute(CONTACT_DATA_OBJECT);
        PersonalDataDTO personalDataDTO = (PersonalDataDTO) httpSession.getAttribute(PERSONAL_DATA_OBJECT);
        OtherDataDTO otherDataDTO = (OtherDataDTO) httpSession.getAttribute(OTHER_DATA_OBJECT);

        User user = (User) httpSession.getAttribute(confProperties.SESSION_USER);

        Client registeredClient = clientService.registerDTOSRegisterStep(personalDataDTO, contactDataDTO, otherDataDTO, user);

        modelAndView.setViewName("redirect:http://localhost:5173/");

        return modelAndView;
    }
}
