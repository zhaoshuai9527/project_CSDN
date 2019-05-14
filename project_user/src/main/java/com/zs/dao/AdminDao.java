package com.zs.dao;

import com.zs.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdminDao extends JpaRepository<Admin,String>, JpaSpecificationExecutor<Admin> {
    //根据名字找admin
    public Admin findByLoginName(String loginName) ;
}
