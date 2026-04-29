package com.p2p.service;

import com.p2p.domain.Borrower;
import com.p2p.domain.Loan;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoanServiceTest {

    private static final Logger log = LogManager.getLogger(LoanServiceTest.class);

    // TC-01: Reject saat Borrower tidak verifikasi (KYC)
    @Test
    void shouldRejectLoanWhenBorrowerNotVerified() {
        log.info("Executing TC-01: shouldRejectLoanWhenBorrowerNotVerified");

        Borrower borrower = new Borrower(false, 700);
        LoanService loanService = new LoanService();
        BigDecimal amount = BigDecimal.valueOf(1000);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            loanService.createLoan(borrower, amount);
        });

        log.info("Exception thrown successfully: " + exception.getMessage());
    }

    // TC-02: Reject saat Amount <= 0
    @Test
    void shouldRejectLoanWhenAmountIsZeroOrNegative() {
        log.info("Executing TC-02: shouldRejectLoanWhenAmountIsZeroOrNegative");

        Borrower borrower = new Borrower(true, 700);
        LoanService loanService = new LoanService();
        BigDecimal invalidAmount = BigDecimal.ZERO;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            loanService.createLoan(borrower, invalidAmount);
        });

        log.info("Exception thrown successfully: " + exception.getMessage());
    }

    // TC-03: Approve jika Credit Score tinggi (>= threshold)
    @Test
    void shouldApproveLoanWhenCreditScoreHigh() {
        log.info("Executing TC-03: shouldApproveLoanWhenCreditScoreHigh");

        Borrower borrower = new Borrower(true, 700); // 700 >= 600
        LoanService loanService = new LoanService();
        BigDecimal amount = BigDecimal.valueOf(5000);

        Loan loan = loanService.createLoan(borrower, amount);

        assertEquals(Loan.Status.APPROVED, loan.getStatus());
        log.info("Assertion passed: Loan status is APPROVED");
    }

    // TC-04: Reject jika Credit Score rendah (< threshold)
    @Test
    void shouldRejectLoanWhenCreditScoreLow() {
        log.info("Executing TC-04: shouldRejectLoanWhenCreditScoreLow");

        Borrower borrower = new Borrower(true, 500); // 500 < 600
        LoanService loanService = new LoanService();
        BigDecimal amount = BigDecimal.valueOf(5000);

        Loan loan = loanService.createLoan(borrower, amount);

        assertEquals(Loan.Status.REJECTED, loan.getStatus());
        log.info("Assertion passed: Loan status is REJECTED");
    }
}