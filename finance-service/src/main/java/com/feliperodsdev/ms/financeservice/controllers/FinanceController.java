package com.feliperodsdev.ms.financeservice.controllers;

import com.feliperodsdev.ms.financeservice.dtos.CreatePaymentDto;
import com.feliperodsdev.ms.financeservice.dtos.HttpResponseDto;
import com.feliperodsdev.ms.financeservice.dtos.MarkAsPaidDto;
import com.feliperodsdev.ms.financeservice.enums.FinancialTransactionType;
import com.feliperodsdev.ms.financeservice.enums.PaymentStatus;
import com.feliperodsdev.ms.financeservice.services.FinanceService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Field;

@RestController
@RequestMapping("/payment")
public class FinanceController {

    @Autowired
    FinanceService financeService;

    @GetMapping("/all-payments")
    public ResponseEntity<Object> getAllPayments(){
        HttpResponseDto response = new HttpResponseDto();
        return response.ok(financeService.getAllPayments());
    }

    @GetMapping("/{:id}")
    public ResponseEntity<Object> findByIdPayment(@PathVariable("id") Long id){
        HttpResponseDto response = new HttpResponseDto();
        return response.found(financeService.findByIdPayment(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createPayment(@RequestBody CreatePaymentDto createPaymentDto){
        HttpResponseDto response = new HttpResponseDto();

        String[] requiredFields = {"value", "type", "referenceId"};

        for (String field : requiredFields) {
            try {
                Field declaredField = createPaymentDto.getClass().getDeclaredField(field);
                declaredField.setAccessible(true);
                if (declaredField.get(createPaymentDto) == null) {
                    return response.badRequest("Missing Param: " + field);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                return response.serverError(e.getMessage());
            }
        }

        financeService.createPayment(createPaymentDto);

        return response.created("created");
    }

    @PutMapping("/pay/{id}")
    public ResponseEntity<Object> markAsPaid(@PathVariable("id") Long id,
                                             @RequestBody MarkAsPaidDto markAsPaidDto){
        HttpResponseDto response = new HttpResponseDto();

        String[] requiredFields = {"paymentMethod"};

        for (String field : requiredFields) {
            try {
                Field declaredField = markAsPaidDto.getClass().getDeclaredField(field);
                declaredField.setAccessible(true);
                if (declaredField.get(markAsPaidDto) == null) {
                    return response.badRequest("Missing Param: " + field);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                return response.serverError(e.getMessage());
            }
        }

        financeService.markAsPaid(id, markAsPaidDto);
        return response.ok("Paid");
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Object> markAsCanceled(@PathVariable("id") Long id){
        HttpResponseDto response = new HttpResponseDto();
        financeService.cancelPayment(id);
        return response.ok("Canceled");
    }

    @PutMapping("/refund/{id}")
    public ResponseEntity<Object> markAsRefunded(@PathVariable("id") Long id){
        HttpResponseDto response = new HttpResponseDto();
        financeService.markAsRefund(id);
        return response.ok("Refunded");
    }

    @GetMapping("/report-quantity/{type}")
    public ResponseEntity<Object> getReportQuantity(@PathVariable("type") int type){
        HttpResponseDto response = new HttpResponseDto();
        return response.ok(financeService.getReportQuantity(FinancialTransactionType.valueOf(type)));
    }

    @GetMapping("/report-finance/{type}/{status}")
    public ResponseEntity<Object> getReportQuantity(@PathVariable("type") int type,
                                                    @PathVariable("status") int status){
        HttpResponseDto response = new HttpResponseDto();
        return response.ok(financeService.getReportFinance(FinancialTransactionType.valueOf(type), PaymentStatus.valueOf(status)));
    }

    @GetMapping("/report-finance/pdf/{type}/{status}")
    public ResponseEntity<Object> getReportQuantityPDF(@PathVariable("type") int type,
                                                    @PathVariable("status") int status) throws IOException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=finance.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(financeService.getReportFinancePDF(FinancialTransactionType.valueOf(type),
                            PaymentStatus.valueOf(status))));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
