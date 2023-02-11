package com.example.demoproject.controller;

import com.example.demoproject.domain.Item;
import com.example.demoproject.domain.enums.ItemStatus;
import com.example.demoproject.model.ItemForm;
import com.example.demoproject.service.ItemService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class ItemController {

    private static final Logger log = LoggerFactory.getLogger(ItemController.class);

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/item/")
    public String viewItemsPage(Model model) {
        model.addAttribute("item_list", itemService.findAll());
        return "items";
    }

    @GetMapping("/item/add/")
    public String viewItemsAddPageGet(Model model) {
        model.addAttribute("statuses", ItemStatus.values());
        model.addAttribute("is_edit", false);
        model.addAttribute("form", new ItemForm());
        return "item_update";
    }

    @PostMapping(path = "/item/add/", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String viewItemsAddPagePost(@Valid @ModelAttribute("form") ItemForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", ItemStatus.values());
            model.addAttribute("is_edit", false);
            model.addAttribute("form", form);
            return "item_update";
        }
        itemService.add(form);
        return "redirect:/item/";
    }

    @GetMapping("/item/update/{itemId}/")
    public String viewItemUpdatePage(@PathVariable("itemId") Integer itemId, Model model) {
        Optional<Item> itemOpt = itemService.findById(itemId);
        if(itemOpt.isEmpty()) {
            return "redirect:/";
        }
        Item item = itemOpt.get();

        ItemForm form = new ItemForm();
        form.setId(itemId);
        form.setStatus(item.getStatus());
        form.setName(item.getName());
        form.setOrderId(item.getOrderId());
        form.setProductId(item.getProductId());
        form.setContent(item.getContent());
        model.addAttribute("statuses", ItemStatus.values());
        model.addAttribute("is_edit", true);
        model.addAttribute("form", form);
        return "item_update";
    }

    @PostMapping(path = "/item/update/", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String viewItemUpdatePagePost(@Valid @ModelAttribute("form") ItemForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", ItemStatus.values());
            model.addAttribute("is_edit", true);
            model.addAttribute("form", form);
            return "item_update";
        }
        itemService.update(form);
        return "redirect:/item/";
    }

}
