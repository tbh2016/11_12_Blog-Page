/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import com.sg.capstoneblog.model.Category;
import java.util.List;

/**
 *
 * @author yingy
 */
public interface CategoryDao {
    
    public Category addCategory(Category category);
    
    public void removeCategory(int categoryId);
    
    public void updateContact(Category category);
    
    public List<Category> getAllCategories();
    
    public Category getCategoryById(int categoryId);
    
}
