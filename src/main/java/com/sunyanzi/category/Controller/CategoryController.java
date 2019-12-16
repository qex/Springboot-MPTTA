package com.sunyanzi.category.Controller;

import com.sunyanzi.category.Entity.Category;
import com.sunyanzi.category.Service.intf.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    ICategoryService cSvc;

    /* List ALL categories ... */
    @GetMapping("/category")
    public List<Category> getAll(@RequestParam(value = "display-depth", required = false) Byte dd) {
        return null == dd ? cSvc.getAll() : cSvc.getAllWithDepth();
    }

    /* Get all sub-categories of the specified parent category ... */
    @GetMapping("/category/{cid}")
    public List<Category> getChildren(@PathVariable short cid) {
        return cSvc.getChildren(cid);
    }

    /* Auto-create root category ... new category always appears at the far right ...  */
    @PostMapping("/category")
    public Category createRoot(@Valid Category c) {
        cSvc.createRoot(c);
        return c;
    }

    /* Create sub-category of specified parent ... new category always becomes the first child ... */
    @PostMapping("/category/{cid}")
    public Category createChild(@PathVariable short cid, @Valid Category c) {
        cSvc.createChild(c, cid);
        return c;
    }

    /* Update an exist category ... */
    @PutMapping("/category/{cid}")
    public Category update(@PathVariable short cid, @Valid Category c) {
        cSvc.update(c, cid);
        return c;
    }

    /* Just delete ... */
    @DeleteMapping("/category/{cid}")
    public void delete(@PathVariable short cid) {
        cSvc.delete(cid);
    }

}
