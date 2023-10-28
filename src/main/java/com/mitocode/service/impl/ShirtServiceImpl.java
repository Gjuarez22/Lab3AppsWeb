package com.mitocode.service.impl;

import com.mitocode.model.Exam;
import com.mitocode.model.Shirt;
import com.mitocode.repo.IExamRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IShirtRepo;
import com.mitocode.service.IExamService;
import com.mitocode.service.IShirtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShirtServiceImpl extends CRUDImpl<Shirt, Integer> implements IShirtService {

    @Autowired
    private IShirtRepo repo;

    @Override
    protected IGenericRepo<Shirt, Integer> getRepo() {
        return repo;
    }
}
