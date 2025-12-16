package com.example.usermanagement.controller;

import com.example.usermanagement.entity.*;
import com.example.usermanagement.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class HrController {

    @Autowired
    private HrService hrService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("topLevelOrgs", hrService.findOrganizationsByLevel(1));
        model.addAttribute("archiveCount", hrService.findArchives(null, null).size());
        model.addAttribute("standardCount", hrService.findSalaryStandards().size());
        model.addAttribute("grantCount", hrService.findSalaryGrants().size());
        return "dashboard";
    }

    @GetMapping("/system")
    public String system(Model model) {
        List<Organization> level1 = hrService.findOrganizationsByLevel(1);
        List<Organization> level2 = hrService.findOrganizationsByLevel(2);
        List<Organization> level3 = hrService.findOrganizationsByLevel(3);
        Map<Integer, List<Organization>> level2ByParent = level2.stream()
                .collect(Collectors.groupingBy(Organization::getParentId));
        Map<Integer, List<Organization>> level3ByParent = level3.stream()
                .collect(Collectors.groupingBy(Organization::getParentId));

        model.addAttribute("level1List", level1);
        model.addAttribute("level2List", level2);
        model.addAttribute("level3List", level3);
        model.addAttribute("level2ByParent", level2ByParent);
        model.addAttribute("level3ByParent", level3ByParent);
        model.addAttribute("positions", hrService.findPositions());
        model.addAttribute("salaryItems", hrService.findSalaryItems());
        return "system-settings";
    }

    @PostMapping("/system/org")
    public String createOrg(@RequestParam String name,
                            @RequestParam String code,
                            @RequestParam int level,
                            @RequestParam(required = false) Integer parentId) {
        hrService.createOrganization(name, code, level, parentId);
        return "redirect:/system";
    }

    @PostMapping("/system/position")
    public String createPosition(@RequestParam String name,
                                 @RequestParam Integer orgId) {
        hrService.createPosition(name, orgId);
        return "redirect:/system";
    }

    @PostMapping("/system/salary-item")
    public String createSalaryItem(@RequestParam String name,
                                   @RequestParam(required = false) String description) {
        hrService.createSalaryItem(name, description);
        return "redirect:/system";
    }

    @GetMapping("/archives")
    public String archives(@RequestParam(required = false) String keyword,
                           @RequestParam(required = false) String status,
                           Model model) {
        List<EmployeeArchive> archives = hrService.findArchives(keyword, status);
        model.addAttribute("archives", archives);
        model.addAttribute("level1List", hrService.findOrganizationsByLevel(1));
        model.addAttribute("level2List", hrService.findOrganizationsByLevel(2));
        model.addAttribute("level3List", hrService.findOrganizationsByLevel(3));
        model.addAttribute("positions", hrService.findPositions());
        return "archives";
    }

    @PostMapping("/archives")
    public String createArchive(@RequestParam String fullName,
                                @RequestParam Integer level1Id,
                                @RequestParam Integer level2Id,
                                @RequestParam Integer level3Id,
                                @RequestParam Integer positionId,
                                @RequestParam(required = false) String photoUrl,
                                @RequestParam(required = false) String notes) {
        Organization l1 = hrService.findOrganizationsByLevel(1).stream().filter(o -> o.getId().equals(level1Id)).findFirst().orElse(null);
        Organization l2 = hrService.findOrganizationsByLevel(2).stream().filter(o -> o.getId().equals(level2Id)).findFirst().orElse(null);
        Organization l3 = hrService.findOrganizationsByLevel(3).stream().filter(o -> o.getId().equals(level3Id)).findFirst().orElse(null);
        if (l1 == null || l2 == null || l3 == null) {
            return "redirect:/archives";
        }
        EmployeeArchive archive = new EmployeeArchive();
        archive.setArchiveNo(hrService.generateArchiveNo(l1, l2, l3));
        archive.setFullName(fullName);
        archive.setOrgLevel1Id(level1Id);
        archive.setOrgLevel2Id(level2Id);
        archive.setOrgLevel3Id(level3Id);
        archive.setPositionId(positionId);
        archive.setStatus("PENDING_REVIEW");
        archive.setPhotoUrl(photoUrl);
        archive.setNotes(notes);
        hrService.saveArchive(archive);
        return "redirect:/archives";
    }

    @PostMapping("/archives/{id}/review")
    public String reviewArchive(@PathVariable Integer id,
                                @RequestParam String action) {
        String status = "NORMAL";
        if ("reject".equals(action)) {
            status = "PENDING_REVIEW";
        }
        hrService.updateArchiveStatus(id, status);
        return "redirect:/archives";
    }

    @PostMapping("/archives/{id}/delete")
    public String deleteArchive(@PathVariable Integer id) {
        hrService.updateArchiveStatus(id, "DELETED");
        return "redirect:/archives";
    }

    @PostMapping("/archives/{id}/restore")
    public String restoreArchive(@PathVariable Integer id) {
        hrService.updateArchiveStatus(id, "NORMAL");
        return "redirect:/archives";
    }

    @PostMapping("/archives/{id}/update")
    public String updateArchive(@PathVariable Integer id,
                                @RequestParam String fullName,
                                @RequestParam Integer level1Id,
                                @RequestParam Integer level2Id,
                                @RequestParam Integer level3Id,
                                @RequestParam Integer positionId,
                                @RequestParam(required = false) String photoUrl,
                                @RequestParam(required = false) String notes) {
        EmployeeArchive archive = hrService.findArchiveById(id);
        if (archive == null) {
            return "redirect:/archives";
        }
        archive.setFullName(fullName);
        archive.setOrgLevel1Id(level1Id);
        archive.setOrgLevel2Id(level2Id);
        archive.setOrgLevel3Id(level3Id);
        archive.setPositionId(positionId);
        archive.setPhotoUrl(photoUrl);
        archive.setNotes(notes);
        archive.setStatus("PENDING_REVIEW");
        hrService.updateArchive(archive);
        return "redirect:/archives";
    }

    @GetMapping("/salary-standards")
    public String salaryStandards(Model model) {
        model.addAttribute("archives", hrService.findArchives(null, "NORMAL"));
        model.addAttribute("standards", hrService.findSalaryStandards());
        return "salary-standards";
    }

    @PostMapping("/salary-standards")
    public String createSalaryStandard(@RequestParam Integer employeeId,
                                       @RequestParam BigDecimal baseSalary,
                                       @RequestParam(required = false) String comment) {
        SalaryStandard standard = new SalaryStandard();
        standard.setEmployeeId(employeeId);
        standard.setBaseSalary(baseSalary);
        standard.setPension(hrService.calculatePension(baseSalary));
        standard.setMedical(hrService.calculateMedical(baseSalary));
        standard.setUnemployment(hrService.calculateUnemployment(baseSalary));
        standard.setHousing(hrService.calculateHousing(baseSalary));
        standard.setTotalPayable(hrService.calculateTotal(baseSalary));
        standard.setStandardNo(hrService.generateStandardNo(employeeId));
        standard.setStatus("PENDING_REVIEW");
        standard.setReviewComment(comment);
        hrService.createSalaryStandard(standard);
        return "redirect:/salary-standards";
    }

    @PostMapping("/salary-standards/{id}/review")
    public String reviewSalaryStandard(@PathVariable Integer id,
                                       @RequestParam String status,
                                       @RequestParam(required = false) String comment) {
        hrService.reviewSalaryStandard(id, status, comment);
        return "redirect:/salary-standards";
    }

    @GetMapping("/salary-grants")
    public String salaryGrants(Model model) {
        model.addAttribute("standards", hrService.findSalaryStandards());
        model.addAttribute("grants", hrService.findSalaryGrants());
        return "salary-grants";
    }

    @PostMapping("/salary-grants")
    public String createSalaryGrant(@RequestParam Integer standardId,
                                    @RequestParam BigDecimal reward,
                                    @RequestParam BigDecimal deduction,
                                    @RequestParam(required = false) String comment) {
        SalaryStandard standard = hrService.findStandard(standardId);
        if (standard == null) {
            return "redirect:/salary-grants";
        }
        SalaryGrant grant = new SalaryGrant();
        grant.setStandardId(standardId);
        grant.setEmployeeId(standard.getEmployeeId());
        grant.setReward(reward);
        grant.setDeduction(deduction);
        grant.setFinalAmount(standard.getTotalPayable().add(reward).subtract(deduction));
        grant.setGrantNo(hrService.generateGrantNo(standard.getEmployeeId()));
        grant.setStatus("PENDING_REVIEW");
        grant.setReviewComment(comment);
        hrService.createSalaryGrant(grant);
        return "redirect:/salary-grants";
    }

    @PostMapping("/salary-grants/{id}/review")
    public String reviewSalaryGrant(@PathVariable Integer id,
                                    @RequestParam BigDecimal reward,
                                    @RequestParam BigDecimal deduction,
                                    @RequestParam String status,
                                    @RequestParam(required = false) String comment) {
        hrService.reviewSalaryGrant(id, reward, deduction, status, comment);
        return "redirect:/salary-grants";
    }
}
