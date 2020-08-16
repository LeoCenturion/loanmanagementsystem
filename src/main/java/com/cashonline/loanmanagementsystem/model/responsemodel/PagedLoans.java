package com.cashonline.loanmanagementsystem.model.responsemodel;

import com.cashonline.loanmanagementsystem.model.entities.Loan;
import java.util.List;

public record PagedLoans(List<Loan> page, Integer totalPages) {}
