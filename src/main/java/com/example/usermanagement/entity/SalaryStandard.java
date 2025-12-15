package com.example.usermanagement.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SalaryStandard {
    private Integer id;
    private String standardNo;
    private Integer employeeId;
    private BigDecimal baseSalary;
    private BigDecimal pension;
    private BigDecimal medical;
    private BigDecimal unemployment;
    private BigDecimal housing;
    private BigDecimal totalPayable;
    private String status;
    private String reviewComment;
    private Date createdAt;
    private Date updatedAt;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getStandardNo() { return standardNo; }
    public void setStandardNo(String standardNo) { this.standardNo = standardNo; }

    public Integer getEmployeeId() { return employeeId; }
    public void setEmployeeId(Integer employeeId) { this.employeeId = employeeId; }

    public BigDecimal getBaseSalary() { return baseSalary; }
    public void setBaseSalary(BigDecimal baseSalary) { this.baseSalary = baseSalary; }

    public BigDecimal getPension() { return pension; }
    public void setPension(BigDecimal pension) { this.pension = pension; }

    public BigDecimal getMedical() { return medical; }
    public void setMedical(BigDecimal medical) { this.medical = medical; }

    public BigDecimal getUnemployment() { return unemployment; }
    public void setUnemployment(BigDecimal unemployment) { this.unemployment = unemployment; }

    public BigDecimal getHousing() { return housing; }
    public void setHousing(BigDecimal housing) { this.housing = housing; }

    public BigDecimal getTotalPayable() { return totalPayable; }
    public void setTotalPayable(BigDecimal totalPayable) { this.totalPayable = totalPayable; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getReviewComment() { return reviewComment; }
    public void setReviewComment(String reviewComment) { this.reviewComment = reviewComment; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
}
