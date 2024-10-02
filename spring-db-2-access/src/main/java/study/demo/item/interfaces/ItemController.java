package study.demo.item.interfaces;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import study.demo.item.domain.dto.ItemSearchCond;
import study.demo.item.domain.dto.ItemUpdateDto;
import study.demo.item.application.service.ItemService;
import study.demo.item.domain.entity.Item;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/items")
@Controller
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public String items(@ModelAttribute("itemSearch") ItemSearchCond cond, Model model) {
        List<Item> items = itemService.findItems(cond);
        model.addAttribute("items", items);
        return "items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemService.findItem(itemId).get();
        model.addAttribute("item", item);
        return "item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "addForm";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemService.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemService.findItem(itemId).get();
        model.addAttribute("item", item);
        return "editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String update(@PathVariable Long itemId, @ModelAttribute ItemUpdateDto updateParam) {
        itemService.update(itemId, updateParam);
        return "redirect:/items/{itemId}";
    }

    @DeleteMapping("/{itemId}/delete")
    public String delete(@PathVariable Long itemId) {
        itemService.delete(itemId);
        return "redirect:/items";
    }
}
