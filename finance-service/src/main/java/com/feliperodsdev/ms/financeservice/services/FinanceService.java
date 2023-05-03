package com.feliperodsdev.ms.financeservice.services;

import com.feliperodsdev.ms.financeservice.dtos.*;
import com.feliperodsdev.ms.financeservice.enums.FinancialTransactionType;
import com.feliperodsdev.ms.financeservice.enums.PaymentMethod;
import com.feliperodsdev.ms.financeservice.enums.PaymentStatus;
import com.feliperodsdev.ms.financeservice.model.Payment;
import com.feliperodsdev.ms.financeservice.repositories.IFinanceRepository;
import com.feliperodsdev.ms.financeservice.services.exceptions.ResourceNotFound;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinanceService {

    private IFinanceRepository repository;

    public FinanceService(@Qualifier("FinanceRepository") IFinanceRepository repository){
        this.repository = repository;
    }

    public void createPayment(CreatePaymentDto createPaymentDto){
        Payment payment = Payment.create(createPaymentDto.getValue(),
                createPaymentDto.getReferenceId(),
                createPaymentDto.getType());

        repository.save(payment);
    }

    public List<Payment> getAllPayments(){
        return repository.getAllPayments();
    }

    public Payment findByIdPayment(Long id){
        return repository.findByIdPayment(id).orElseThrow(() -> new ResourceNotFound());
    }

    public void cancelPayment(Long id){
        Payment payment = findByIdPayment(id);
        payment.markAsCanceled();
        repository.save(payment);
    }

    public void markAsPaid(Long id, MarkAsPaidDto markAsPaidDto){
        Payment payment = findByIdPayment(id);
        payment.markAsPaid(markAsPaidDto.getPaymentMethod());
        repository.save(payment);
    }

    public void markAsRefund(Long id){
        Payment payment = findByIdPayment(id);
        payment.markAsRefund();
        repository.save(payment);
    }

    public ReportQuantityDto getReportQuantity(FinancialTransactionType type){
        List<Payment> paymentList = repository.getAllPayments().stream().filter(
                payment -> payment.getType().equals(type)
        ).collect(Collectors.toList());

        List<Payment> paidPaymentList = getPaymentsByStatus(paymentList, PaymentStatus.PAID);

        List<Payment> waitingPaymentList = getPaymentsByStatus(paymentList, PaymentStatus.WAITING_PAYMENT);

        List<Payment> refundPaymentList = getPaymentsByStatus(paymentList, PaymentStatus.REFUND);

        List<Payment> canceledPaymentList = getPaymentsByStatus(paymentList, PaymentStatus.CANCELED);

        Double totalValuePayment = paymentList.stream()
                .mapToDouble(Payment::getValue)
                .sum();

        return new ReportQuantityDto(totalValuePayment, paymentList.size(),
                paidPaymentList.size(), waitingPaymentList.size(),
                refundPaymentList.size(), canceledPaymentList.size());

    }

    public ReportFinanceDto getReportFinance(FinancialTransactionType type, PaymentStatus status){
        List<Payment> paymentList = repository.getAllPayments().stream().filter(
                payment -> payment.getType().equals(type)
        ).collect(Collectors.toList());

        List<Payment> list = getPaymentsByStatus(paymentList, status);

        Double totalValue = list.stream()
                .mapToDouble(Payment::getValue)
                .sum();

        List<Payment> paymentMoneyList = getPaymentsByMethod(list, PaymentMethod.MONEY);
        Double valueMoney = paymentMoneyList.stream()
                .mapToDouble(Payment::getValue)
                .sum();

        List<Payment> paymentCreditList = getPaymentsByMethod(list, PaymentMethod.CREDIT_CARD);
        Double valueCredit = paymentCreditList.stream()
                .mapToDouble(Payment::getValue)
                .sum();

        List<Payment> paymentDebitList = getPaymentsByMethod(list, PaymentMethod.DEBIT_CARD);
        Double debitCredit = paymentDebitList.stream()
                .mapToDouble(Payment::getValue)
                .sum();

        return new ReportFinanceDto(new ReportFinanceQuantityDto(list.size(),
                paymentMoneyList.size(), paymentCreditList.size(),
                paymentDebitList.size()), new ReportFinanceValueDto(totalValue, valueMoney,
                valueCredit, debitCredit));

    }

    public ByteArrayInputStream getReportFinancePDF(FinancialTransactionType type, PaymentStatus status) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(baos));
        pdfDocument.setDefaultPageSize(PageSize.A4.rotate());

        Document document = new Document(pdfDocument);

        Paragraph title = new Paragraph(type == FinancialTransactionType.REVENUE ?
                "Report Finance - Revenues"
                : "Report Finance - Expenses")
                .setFontSize(28)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD))
                .setTextAlignment(TextAlignment.CENTER);

        document.add(title);
        document.add(new Paragraph("\n"));

        Table table = new Table(8)
                .useAllAvailableWidth()
                .setTextAlignment(TextAlignment.CENTER);

        table.addHeaderCell("Total Payments");
        table.addHeaderCell("Total value Payments");
        table.addHeaderCell("Money Payments");
        table.addHeaderCell("Money value Payments");
        table.addHeaderCell("Credit Payments");
        table.addHeaderCell("Credit value Payments");
        table.addHeaderCell("Debit Payments");
        table.addHeaderCell("Debit value Payments");

        ReportFinanceDto reportFinanceDto = getReportFinance(type, status);

        table.addCell(reportFinanceDto.getQuantitySession().getQuantityPayments().toString());
        table.addCell(reportFinanceDto.getValueSession().getTotalValue().toString());
        table.addCell(reportFinanceDto.getQuantitySession().getQuantityPaymentMoney().toString());
        table.addCell(reportFinanceDto.getValueSession().getValueMoney().toString());
        table.addCell(reportFinanceDto.getQuantitySession().getQuantityPaymentCredit().toString());
        table.addCell(reportFinanceDto.getValueSession().getValueCredit().toString());
        table.addCell(reportFinanceDto.getQuantitySession().getQuantityPaymentDebit().toString());
        table.addCell(reportFinanceDto.getValueSession().getValueDebit().toString());

        document.add(table);
        document.close();

        return new ByteArrayInputStream(baos.toByteArray());
    }

    public List<Payment> getPaymentsByStatus(List<Payment> paymentList, PaymentStatus status){
        return paymentList.stream().filter(
                        payment -> payment.getStatusPayment().equals(status))
                .collect(Collectors.toList());
    }

    public List<Payment> getPaymentsByMethod(List<Payment> paymentList, PaymentMethod method) {
        if(method == null) {
            return Collections.emptyList();
        }
        return paymentList.stream().filter(
                        payment -> payment.getPaymentMethod() == method)
                .collect(Collectors.toList());
    }

}
