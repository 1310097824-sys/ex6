package com.example.usermanagement.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SalaryGrant {
    private Integer id;
    private String grantNo;
    private Integer employeeId;
    private Integer standardId;
    private BigDecimal reward;
    private BigDecimal deduction;
    private BigDecimal finalAmount;
    private String status;
    private String reviewComment;
    private Date createdAt;
    private Date updatedAt;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getGrantNo() { return grantNo; }
    public void setGrantNo(String grantNo) { this.grantNo = grantNo; }

    public Integer getEmployeeId() { return employeeId; }
    public void setEmployeeId(Integer employeeId) { this.employeeId = employeeId; }

    public Integer getStandardId() { return standardId; }
    public void setStandardId(Integer standardId) { this.standardId = standardId; }

    public BigDecimal getReward() { return reward; }
    public void setReward(BigDecimal reward) { this.reward = reward; }

    public BigDecimal getDeduction() { return deduction; }
    public void setDeduction(BigDecimal deduction) { this.deduction = deduction; }

    public BigDecimal getFinalAmount() { return finalAmount; }
    public void setFinalAmount(BigDecimal finalAmount) { this.finalAmount = finalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getReviewComment() { return reviewComment; }
    public void setReviewComment(String reviewComment) { this.reviewComment = reviewComment; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
}
