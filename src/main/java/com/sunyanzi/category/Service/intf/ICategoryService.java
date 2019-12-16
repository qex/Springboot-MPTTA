package com.sunyanzi.category.Service.intf;

import com.sunyanzi.category.Entity.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> getAll();

    List<Category> getAllWithDepth();

    List<Category> getChildren(short cid);

    void createRoot(Category c);

    void createSibling(Category c, short cid);

    void createChild(Category c, short cid);

    void update(Category c, short cid);

    void delete(short cid);

}
