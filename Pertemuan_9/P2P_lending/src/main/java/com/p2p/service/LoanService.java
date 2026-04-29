package com.p2p.service;

import com.p2p.domain.*;
import java.math.BigDecimal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoanService {

    private static final Logger log = LogManager.getLogger(LoanService.class);
    private static final int CREDIT_SCORE_THRESHOLD = 600;

    public Loan createLoan(Borrower borrower, BigDecimal amount) {
        log.info("Processing loan creation for amount: " + amount);

        // Melakukan validasi awal
        validateBorrower(borrower);
        validateAmount(amount);

        // Membuat objek loan
        Loan loan = new Loan();

        // Business Logic: Credit Scoring
        if (borrower.getCreditScore() >= CREDIT_SCORE_THRESHOLD) {
            loan.approve();
            log.info("Loan APPROVED. Borrower credit score (" + borrower.getCreditScore() + ") meets the threshold.");
        } else {
            loan.reject();
            log.info("Loan REJECTED. Borrower credit score (" + borrower.getCreditScore() + ") is below the threshold.");
        }

        return loan;
    }

    // Metode Validasi Privat
    private void validateBorrower(Borrower borrower) {
        if (!borrower.canApplyLoan()) {
            log.error("Validation failed: Borrower is not verified (KYC failed)");
            throw new IllegalArgumentException("Borrower not verified");
        }
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            log.error("Validation failed: Loan amount must be greater than zero. Provided: " + amount);
            throw new IllegalArgumentException("Loan amount must be greater than 0");
        }
    }
}