package com.codegym.model;

import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "nhanvien")
@Component
public class NhanVien implements Validator{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String hoTen;
    private boolean gioiTinh;
    @Size(min = 10, max = 10)
    private String sdt;
    @Size(min = 13,max = 13)
    private String cmnd;
    @Pattern(regexp = "^[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z0-9]+$")
    private String email;
    @NotEmpty
    private String diaChi;

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public NhanVien() {
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return NhanVien.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NhanVien nv = (NhanVien) target;
        String phoneNumber = nv.getSdt();
        ValidationUtils.rejectIfEmpty(errors,"sdt","sdt.empty");
        if (phoneNumber.length()>11 || phoneNumber.length()<10) {
            errors.rejectValue("sdt","sdt.length");
        }
        if (!phoneNumber.startsWith("0")){
            errors.rejectValue("sdt", "sdt.startsWith");
        }
        if (!phoneNumber.matches("(^$|[0-9]*$)")){
            errors.rejectValue("sdt", "sdt.matches");
        }
    }
}
