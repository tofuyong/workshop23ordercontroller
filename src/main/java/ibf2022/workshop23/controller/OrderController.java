package ibf2022.workshop23.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2022.workshop23.model.Order;
import ibf2022.workshop23.payload.SearchRequest;
import ibf2022.workshop23.service.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {
    
    @Autowired
    OrderService orderSvc;

    @GetMapping
    public String searchOrder(Model model) {
        SearchRequest sr = new SearchRequest();
        model.addAttribute("searchObject", sr);
        return "searchorder"; 
    }

    @PostMapping("/search")
    // why add model? to inject in the search order, so that order is injected into orderlist
    public String processSearch(@ModelAttribute("searchObject") SearchRequest searchObj, Model model, BindingResult result) {
        List<Order> orders = orderSvc.findOrderbyId(searchObj.getOrderId());
        model.addAttribute("orders", orders);
        return "orderlist";
    }

}
