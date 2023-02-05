package com.ltp.globalsuperstore;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class StoreController {
  List<Product> inventory = new ArrayList<>();

  @GetMapping("/inventory")
  public String getInventory(Model model) {
    model.addAttribute("inventory", inventory);
    return "inventory";
  }

  @GetMapping("/")
  public String getForm(Model model, @RequestParam(required = false) String id) {
    int index = getIndexFromInventory(id);
    model.addAttribute("product", index == Constants.NOT_FOUND ? new Product() : inventory.get(index));
    model.addAttribute("categories", Constants.CATEGORIES);
    return "form";
  }

  @PostMapping("/submitHandler")
  public String submitProduct(Product product, RedirectAttributes redirectAttributes) {
    int index = getIndexFromInventory(product.getId());
    if (index == Constants.NOT_FOUND) {
      inventory.add(product);
      redirectAttributes.addFlashAttribute("message", new String("added"));
    } else {
      inventory.set(index, product);
      redirectAttributes.addFlashAttribute("message", new String("updated"));
    }
    redirectAttributes.addFlashAttribute("productAdded", product);

    return "redirect:/inventory";
  }

  public Integer getIndexFromInventory(String id) {
    for (int i = 0; i < inventory.size(); i++) {
      if (inventory.get(i).getId().equals(id)) {
        return i;
      }
    }

    return Constants.NOT_FOUND;
  }
}
