package com.zs.dao;

import com.zs.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LabelDao extends JpaRepository<Label,String>, JpaSpecificationExecutor<Label> {

}
