package com.rb.login.service.impl;

import com.rb.login.mapper.ResourceMapper;
import com.rb.login.model.entity.Resource;
import com.rb.login.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public int save(Resource resource) {
        int id = resourceMapper.save(resource);
        return id;
    }

    @Override
    public Resource findById(Integer id) {
        Resource resource = resourceMapper.findById(id);
        return resource;
    }

    @Override
    public List<Resource> findAll() {
        List<Resource> resources = resourceMapper.findAll();
        return resources;
    }

    @Override
    public void delete(Integer id) {
        resourceMapper.delete(id);
    }
}
