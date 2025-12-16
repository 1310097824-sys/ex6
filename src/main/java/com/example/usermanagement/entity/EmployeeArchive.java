package com.example.usermanagement.entity;

import java.util.Date;

public class EmployeeArchive {
    private Integer id;
    private String archiveNo;
    private String fullName;
    private Integer orgLevel1Id;
    private Integer orgLevel2Id;
    private Integer orgLevel3Id;
    private Integer positionId;
    private String status;
    private String photoUrl;
    private String notes;
    private Date createdAt;
    private Date updatedAt;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getArchiveNo() { return archiveNo; }
    public void setArchiveNo(String archiveNo) { this.archiveNo = archiveNo; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public Integer getOrgLevel1Id() { return orgLevel1Id; }
    public void setOrgLevel1Id(Integer orgLevel1Id) { this.orgLevel1Id = orgLevel1Id; }

    public Integer getOrgLevel2Id() { return orgLevel2Id; }
    public void setOrgLevel2Id(Integer orgLevel2Id) { this.orgLevel2Id = orgLevel2Id; }

    public Integer getOrgLevel3Id() { return orgLevel3Id; }
    public void setOrgLevel3Id(Integer orgLevel3Id) { this.orgLevel3Id = orgLevel3Id; }

    public Integer getPositionId() { return positionId; }
    public void setPositionId(Integer positionId) { this.positionId = positionId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
}
