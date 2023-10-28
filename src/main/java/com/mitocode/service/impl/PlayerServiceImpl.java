package com.mitocode.service.impl;

import com.mitocode.model.Player;
import com.mitocode.model.Shirt;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IPlayerRpo;
import com.mitocode.repo.IShirtRepo;
import com.mitocode.service.IPlayerService;
import com.mitocode.service.IShirtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl extends CRUDImpl<Player, Integer> implements IPlayerService {

    @Autowired
    private IPlayerRpo repo;

    @Override
    protected IGenericRepo<Player, Integer> getRepo() {
        return repo;
    }
}
