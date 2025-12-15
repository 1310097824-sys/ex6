package com.example.usermanagement.service;

import com.example.usermanagement.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class HrService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Organization> orgMapper = new RowMapper<>() {
        @Override
        public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
            Organization o = new Organization();
            o.setId(rs.getInt("id"));
            o.setName(rs.getString("name"));
            o.setCode(rs.getString("code"));
            o.setLevel(rs.getInt("level"));
            o.setParentId((Integer) rs.getObject("parent_id"));
            o.setCreatedAt(rs.getTimestamp("created_at"));
            o.setUpdatedAt(rs.getTimestamp("updated_at"));
            return o;
        }
    };

    private RowMapper<JobPosition> positionMapper = new RowMapper<>() {
        @Override
        public JobPosition mapRow(ResultSet rs, int rowNum) throws SQLException {
            JobPosition p = new JobPosition();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setOrgId(rs.getInt("org_id"));
            p.setCreatedAt(rs.getTimestamp("created_at"));
            p.setUpdatedAt(rs.getTimestamp("updated_at"));
            return p;
        }
    };

    private RowMapper<SalaryItem> salaryItemMapper = new RowMapper<>() {
        @Override
        public SalaryItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            SalaryItem s = new SalaryItem();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setDescription(rs.getString("description"));
            s.setCreatedAt(rs.getTimestamp("created_at"));
            s.setUpdatedAt(rs.getTimestamp("updated_at"));
            return s;
        }
    };

    private RowMapper<EmployeeArchive> archiveMapper = new RowMapper<>() {
        @Override
        public EmployeeArchive mapRow(ResultSet rs, int rowNum) throws SQLException {
            EmployeeArchive e = new EmployeeArchive();
            e.setId(rs.getInt("id"));
            e.setArchiveNo(rs.getString("archive_no"));
            e.setFullName(rs.getString("full_name"));
            e.setOrgLevel1Id(rs.getInt("org_level1_id"));
            e.setOrgLevel2Id(rs.getInt("org_level2_id"));
            e.setOrgLevel3Id(rs.getInt("org_level3_id"));
            e.setPositionId(rs.getInt("position_id"));
            e.setStatus(rs.getString("status"));
            e.setPhotoUrl(rs.getString("photo_url"));
            e.setNotes(rs.getString("notes"));
            e.setCreatedAt(rs.getTimestamp("created_at"));
            e.setUpdatedAt(rs.getTimestamp("updated_at"));
            return e;
        }
    };

    private RowMapper<SalaryStandard> standardMapper = new RowMapper<>() {
        @Override
        public SalaryStandard mapRow(ResultSet rs, int rowNum) throws SQLException {
            SalaryStandard s = new SalaryStandard();
            s.setId(rs.getInt("id"));
            s.setStandardNo(rs.getString("standard_no"));
            s.setEmployeeId(rs.getInt("employee_id"));
            s.setBaseSalary(rs.getBigDecimal("base_salary"));
            s.setPension(rs.getBigDecimal("pension"));
            s.setMedical(rs.getBigDecimal("medical"));
            s.setUnemployment(rs.getBigDecimal("unemployment"));
            s.setHousing(rs.getBigDecimal("housing"));
            s.setTotalPayable(rs.getBigDecimal("total_payable"));
            s.setStatus(rs.getString("status"));
            s.setReviewComment(rs.getString("review_comment"));
            s.setCreatedAt(rs.getTimestamp("created_at"));
            s.setUpdatedAt(rs.getTimestamp("updated_at"));
            return s;
        }
    };

    private RowMapper<SalaryGrant> grantMapper = new RowMapper<>() {
        @Override
        public SalaryGrant mapRow(ResultSet rs, int rowNum) throws SQLException {
            SalaryGrant g = new SalaryGrant();
            g.setId(rs.getInt("id"));
            g.setGrantNo(rs.getString("grant_no"));
            g.setEmployeeId(rs.getInt("employee_id"));
            g.setStandardId(rs.getInt("standard_id"));
            g.setReward(rs.getBigDecimal("reward"));
            g.setDeduction(rs.getBigDecimal("deduction"));
            g.setFinalAmount(rs.getBigDecimal("final_amount"));
            g.setStatus(rs.getString("status"));
            g.setReviewComment(rs.getString("review_comment"));
            g.setCreatedAt(rs.getTimestamp("created_at"));
            g.setUpdatedAt(rs.getTimestamp("updated_at"));
            return g;
        }
    };

    public List<Organization> findOrganizationsByLevel(int level) {
        return jdbcTemplate.query("SELECT * FROM org_unit WHERE level = ? ORDER BY id", orgMapper, level);
    }

    public List<Organization> findChildren(Integer parentId) {
        return jdbcTemplate.query("SELECT * FROM org_unit WHERE parent_id = ? ORDER BY id", orgMapper, parentId);
    }

    public void createOrganization(String name, String code, int level, Integer parentId) {
        jdbcTemplate.update("INSERT INTO org_unit(name, code, level, parent_id) VALUES (?,?,?,?)", name, code, level, parentId);
    }

    public List<JobPosition> findPositions() {
        return jdbcTemplate.query("SELECT * FROM position ORDER BY id", positionMapper);
    }

    public void createPosition(String name, Integer orgId) {
        jdbcTemplate.update("INSERT INTO position(name, org_id) VALUES (?,?)", name, orgId);
    }

    public List<SalaryItem> findSalaryItems() {
        return jdbcTemplate.query("SELECT * FROM salary_item ORDER BY id", salaryItemMapper);
    }

    public void createSalaryItem(String name, String description) {
        jdbcTemplate.update("INSERT INTO salary_item(name, description) VALUES (?,?)", name, description);
    }

    public List<EmployeeArchive> findArchives(String keyword, String status) {
        StringBuilder sql = new StringBuilder("SELECT * FROM employee_archive WHERE 1=1");
        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND (full_name LIKE '%" + keyword + "%' OR archive_no LIKE '%" + keyword + "%')");
        }
        if (status != null && !status.isEmpty()) {
            sql.append(" AND status = '" + status + "'");
        }
        sql.append(" ORDER BY created_at DESC");
        return jdbcTemplate.query(sql.toString(), archiveMapper);
    }

    public EmployeeArchive findArchiveById(Integer id) {
        List<EmployeeArchive> list = jdbcTemplate.query("SELECT * FROM employee_archive WHERE id=?", archiveMapper, id);
        return list.isEmpty() ? null : list.get(0);
    }

    public void saveArchive(EmployeeArchive archive) {
        jdbcTemplate.update(
                "INSERT INTO employee_archive(archive_no, full_name, org_level1_id, org_level2_id, org_level3_id, position_id, status, photo_url, notes) " +
                        "VALUES (?,?,?,?,?,?,?,?,?)",
                archive.getArchiveNo(), archive.getFullName(), archive.getOrgLevel1Id(), archive.getOrgLevel2Id(),
                archive.getOrgLevel3Id(), archive.getPositionId(), archive.getStatus(), archive.getPhotoUrl(), archive.getNotes());
    }

    public void updateArchive(EmployeeArchive archive) {
        jdbcTemplate.update("UPDATE employee_archive SET full_name=?, org_level1_id=?, org_level2_id=?, org_level3_id=?, position_id=?, status=?, photo_url=?, notes=? WHERE id=?",
                archive.getFullName(), archive.getOrgLevel1Id(), archive.getOrgLevel2Id(), archive.getOrgLevel3Id(), archive.getPositionId(), archive.getStatus(), archive.getPhotoUrl(), archive.getNotes(), archive.getId());
    }

    public void updateArchiveStatus(Integer id, String status) {
        jdbcTemplate.update("UPDATE employee_archive SET status=? WHERE id=?", status, id);
    }

    public String generateArchiveNo(Organization l1, Organization l2, Organization l3) {
        String year = String.valueOf(LocalDate.now().getYear());
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM employee_archive WHERE org_level3_id=?", Integer.class, l3.getId());
        int next = count == null ? 1 : count + 1;
        return year + l1.getCode() + l2.getCode() + l3.getCode() + String.format("%02d", next);
    }

    public List<SalaryStandard> findSalaryStandards() {
        return jdbcTemplate.query("SELECT * FROM salary_standard ORDER BY created_at DESC", standardMapper);
    }

    public SalaryStandard findStandard(Integer id) {
        List<SalaryStandard> list = jdbcTemplate.query("SELECT * FROM salary_standard WHERE id=?", standardMapper, id);
        return list.isEmpty() ? null : list.get(0);
    }

    public void createSalaryStandard(SalaryStandard standard) {
        jdbcTemplate.update("INSERT INTO salary_standard(standard_no, employee_id, base_salary, pension, medical, unemployment, housing, total_payable, status, review_comment) VALUES (?,?,?,?,?,?,?,?,?,?)",
                standard.getStandardNo(), standard.getEmployeeId(), standard.getBaseSalary(), standard.getPension(), standard.getMedical(),
                standard.getUnemployment(), standard.getHousing(), standard.getTotalPayable(), standard.getStatus(), standard.getReviewComment());
    }

    public void reviewSalaryStandard(Integer id, String status, String comment) {
        jdbcTemplate.update("UPDATE salary_standard SET status=?, review_comment=? WHERE id=?", status, comment, id);
    }

    public List<SalaryGrant> findSalaryGrants() {
        return jdbcTemplate.query("SELECT * FROM salary_grant ORDER BY created_at DESC", grantMapper);
    }

    public SalaryGrant findGrant(Integer id) {
        List<SalaryGrant> list = jdbcTemplate.query("SELECT * FROM salary_grant WHERE id=?", grantMapper, id);
        return list.isEmpty() ? null : list.get(0);
    }

    public void createSalaryGrant(SalaryGrant grant) {
        jdbcTemplate.update("INSERT INTO salary_grant(grant_no, employee_id, standard_id, reward, deduction, final_amount, status, review_comment) VALUES (?,?,?,?,?,?,?,?)",
                grant.getGrantNo(), grant.getEmployeeId(), grant.getStandardId(), grant.getReward(), grant.getDeduction(), grant.getFinalAmount(), grant.getStatus(), grant.getReviewComment());
    }

    public void reviewSalaryGrant(Integer id, BigDecimal reward, BigDecimal deduction, String status, String comment) {
        jdbcTemplate.update(
                "UPDATE salary_grant SET reward=?, deduction=?, final_amount=(SELECT total_payable FROM salary_standard WHERE id=standard_id)+? - ?, status=?, review_comment=? WHERE id=?",
                reward, deduction, reward, deduction, status, comment, id);
    }

    public BigDecimal calculatePension(BigDecimal base) {
        return base.multiply(new BigDecimal("0.08"));
    }

    public BigDecimal calculateMedical(BigDecimal base) {
        return base.multiply(new BigDecimal("0.02")).add(new BigDecimal("3"));
    }

    public BigDecimal calculateUnemployment(BigDecimal base) {
        return base.multiply(new BigDecimal("0.005"));
    }

    public BigDecimal calculateHousing(BigDecimal base) {
        return base.multiply(new BigDecimal("0.08"));
    }

    public BigDecimal calculateTotal(BigDecimal base) {
        return base.add(calculatePension(base)).add(calculateMedical(base)).add(calculateUnemployment(base)).add(calculateHousing(base));
    }

    public String generateStandardNo(Integer employeeId) {
        String prefix = "STD" + LocalDate.now().getYear();
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM salary_standard", Integer.class);
        int next = count == null ? 1 : count + 1;
        return prefix + String.format("%04d", next) + "E" + employeeId;
    }

    public String generateGrantNo(Integer employeeId) {
        String prefix = "PAY" + LocalDate.now().getYear();
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM salary_grant", Integer.class);
        int next = count == null ? 1 : count + 1;
        return prefix + String.format("%04d", next) + "E" + employeeId;
    }
}
