package com.wamazon.app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wamazon.app.PaymentForm;

import jakarta.validation.Valid;

@Controller
public class PaymentController {
    @GetMapping("/payment")
    public String showPaymentForm(Model model) {
        model.addAttribute("paymentForm", new PaymentForm());
        return "payment";
    }
    
    @PostMapping("/process-payment")
    public String processPayment(@Valid PaymentForm paymentForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "payment";
        }

        // Here you can simulate the payment process, for now, we'll just return a success message
        model.addAttribute("message", "Payment Successful! Your order will be processed.");
        return "payment-success";
    }
}
