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
import java.time.LocalDateTime;

@RestController
@RequestMapping("/payment")
public class FinanceController {

    @Autowired
    FinanceService financeService;

    @GetMapping("/all-payments")
    public ResponseEntity<Object> getAllPayments(){
        HttpResponseDto response = new HttpResponseDto();
        HttpHeaders headers = new HttpHeaders();
        headers.add("date-time", LocalDateTime.now().toString());
        return response.ok(financeService.getAllPayments(), headers, MediaType.APPLICATION_JSON);
    }

    @GetMapping("/{:id}")
    public ResponseEntity<Object> findByIdPayment(@PathVariable("id") Long id){
        HttpResponseDto response = new HttpResponseDto();
        HttpHeaders headers = new HttpHeaders();
        headers.add("date-time", LocalDateTime.now().toString());
        return response.found(financeService.findByIdPayment(id), headers, MediaType.APPLICATION_JSON);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createPayment(@RequestBody CreatePaymentDto createPaymentDto){
        HttpResponseDto response = new HttpResponseDto();

        String[] requiredFields = {"value", "type", "referenceId"};

        HttpHeaders headers = new HttpHeaders();
        headers.add("date-time", LocalDateTime.now().toString());

        for (String field : requiredFields) {
            try {
                Field declaredField = createPaymentDto.getClass().getDeclaredField(field);
                declaredField.setAccessible(true);
                if (declaredField.get(createPaymentDto) == null) {
                    return response.badRequest("Missing Param: " + field, headers, MediaType.APPLICATION_JSON);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                return response.serverError(e.getMessage());
            }
        }

        financeService.createPayment(createPaymentDto);

        return response.created("created", headers, MediaType.APPLICATION_JSON);
    }

    @PutMapping("/pay/{id}")
    public ResponseEntity<Object> markAsPaid(@PathVariable("id") Long id,
                                             @RequestBody MarkAsPaidDto markAsPaidDto){
        HttpResponseDto response = new HttpResponseDto();

        String[] requiredFields = {"paymentMethod"};

        HttpHeaders headers = new HttpHeaders();
        headers.add("date-time", LocalDateTime.now().toString());

        for (String field : requiredFields) {
            try {
                Field declaredField = markAsPaidDto.getClass().getDeclaredField(field);
                declaredField.setAccessible(true);
                if (declaredField.get(markAsPaidDto) == null) {
                    return response.badRequest("Missing Param: " + field, headers, MediaType.APPLICATION_JSON);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                return response.serverError(e.getMessage());
            }
        }

        financeService.markAsPaid(id, markAsPaidDto);
        return response.ok("Paid", headers, MediaType.APPLICATION_JSON);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Object> markAsCanceled(@PathVariable("id") Long id){
        HttpResponseDto response = new HttpResponseDto();
        HttpHeaders headers = new HttpHeaders();
        headers.add("date-time", LocalDateTime.now().toString());
        financeService.cancelPayment(id);
        return response.ok("Canceled", headers, MediaType.APPLICATION_JSON);
    }

    @PutMapping("/refund/{id}")
    public ResponseEntity<Object> markAsRefunded(@PathVariable("id") Long id){
        HttpResponseDto response = new HttpResponseDto();
        HttpHeaders headers = new HttpHeaders();
        headers.add("date-time", LocalDateTime.now().toString());
        financeService.markAsRefund(id);
        return response.ok("Refunded", headers, MediaType.APPLICATION_JSON);
    }

    @GetMapping("/report-quantity/{type}")
    public ResponseEntity<Object> getReportQuantity(@PathVariable("type") int type){
        HttpResponseDto response = new HttpResponseDto();
        HttpHeaders headers = new HttpHeaders();
        headers.add("date-time", LocalDateTime.now().toString());
        return response.ok(financeService.getReportQuantity(FinancialTransactionType.valueOf(type)),
                headers,
                MediaType.APPLICATION_JSON);
    }

    @GetMapping("/report-finance/{type}/{status}")
    public ResponseEntity<Object> getReportQuantity(@PathVariable("type") int type,
                                                    @PathVariable("status") int status){
        HttpResponseDto response = new HttpResponseDto();
        HttpHeaders headers = new HttpHeaders();
        headers.add("date-time", LocalDateTime.now().toString());
        return response.ok(financeService.getReportFinance(FinancialTransactionType.valueOf(type),
                PaymentStatus.valueOf(status)),
                headers,
                MediaType.APPLICATION_JSON);
    }

    @GetMapping("/report-finance/pdf/{type}/{status}")
    public ResponseEntity<Object> getReportQuantityPDF(@PathVariable("type") int type,
                                                    @PathVariable("status") int status) throws IOException {

        HttpResponseDto response = new HttpResponseDto();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=finance.pdf");
            headers.add("date-time", LocalDateTime.now().toString());
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(financeService.getReportFinancePDF(
                            FinancialTransactionType.valueOf(type),
                            PaymentStatus.valueOf(status)));
        } catch (Exception e) {
            return response.serverError("Server Error");
        }

    }

}
