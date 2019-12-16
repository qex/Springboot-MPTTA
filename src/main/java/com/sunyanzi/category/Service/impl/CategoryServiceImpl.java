package com.sunyanzi.category.Service.impl;

import com.sunyanzi.category.Entity.Category;
import com.sunyanzi.category.Mapper.CategoryMapper;
import com.sunyanzi.category.Service.intf.ICategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Resource
    private CategoryMapper _cm;

    @Override
    public List<Category> getAll() {
        return _cm.getAll();
    }

    @Override
    public List<Category> getAllWithDepth() {
        return _cm.calculateDepth();
    }

    @Override
    public List<Category> getChildren(short cid) {
        return _cm.getChild(cid);
    }

    @Override
    public void createRoot(Category c) {
        _cm.createRoot(c);
    }

    @Override
    @Transactional
    public void createSibling(Category c, short cid) {
        _create(c, _cm.getRight(cid));
    }

    @Override
    @Transactional
    public void createChild(Category c, short cid) {
        _create(c, _cm.getLeft(cid));
    }

    @Override
    public void update(Category c, short cid) {
        _cm.updateNode(c, cid);
    }

    @Override
    @Transactional
    public void delete(short cid) {
        Category c = _cm.getPosition(cid);
        _cm.deleteNode(c.getLeft(), c.getRight());

        _cm.shrinkRight(c.getRight(), c.getWidth());
        _cm.shrinkLeft(c.getRight(), c.getWidth());
    }

    private void _create(Category c, short v) {
        _cm.expandRight(v);
        _cm.expandLeft(v);
        _cm.createNode(c, v);
    }

}
