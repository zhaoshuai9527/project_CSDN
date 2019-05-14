package com.zs.service;


import com.zs.dao.LabelDao;
import com.zs.entity.Label;
import com.zs.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LabelService {
    //@Autowired标注的变量可以直接使用，而不用像原来现实例化
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    //查询全部
    public List<Label> findAll() {

        return labelDao.findAll();//JPA提供的
    }

    //根据id查询
    public Label findLabelById(String id) {
        return labelDao.findById(id).get();
    }

    //增加label
    public void addSaveLabel(Label label) {
        label.setId(idWorker.nextId() + "");//雪花算法
        labelDao.save(label);
    }

    //删除
    public void deleteLabelById(String id) {
        labelDao.deleteById(id);
    }

    //更新label
    public void updateLabel(Label label) {
        labelDao.save(label);
    }

    //=====================================================================
    //构建查询条件
    public Specification<Label> createSpecification(Map findMap) {
        //Specification是一个接口，new 接口需要实现它的所有方法
        return new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //创建一个List<Predicate>存放 查询条件p1,p2....
                //因为List是可变长度
                List<Predicate> predicates = new ArrayList<>();
                if (findMap.get("labelname") != null && findMap.get("labelname") != "") {
                    Predicate p1 = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + (String) findMap.get("labelname").toString() + "%");
                    //将p1增加到list<predicates>
                    predicates.add(p1);
                }
                if (findMap.get("recommend") != null && findMap.get("recommend") != "") {
                    Predicate p2 = criteriaBuilder.like(root.get("recommend").as(String.class), "%" + (String) findMap.get("recommend").toString() + "%");
                    predicates.add(p2);
                }
                //
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    //根据条件查询
    public List<Label> findLabels(Map findMap){
        //先得到需要查的条件list<>
        Specification<Label> specification = createSpecification(findMap);


        return labelDao.findAll(specification);
    }

    //条件查询并分页
    public Page findLabelsAndPage(Map findMap  , int start , int pageSize  ){
        Specification<Label> specification = createSpecification(findMap);
        PageRequest pageRequest = PageRequest.of(start - 1, pageSize);
        return labelDao.findAll(specification, pageRequest);
    }

}
