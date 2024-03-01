package com.pivinadanang.services.impl;

import com.pivinadanang.dto.CategoryDTO;
import com.pivinadanang.entity.CategoryEntity;
import com.pivinadanang.exception.NotFoundException;
import com.pivinadanang.repository.CategoryRepository;
import com.pivinadanang.services.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryDTO> getIdAndNameCategory() {
        List<CategoryDTO> categories = categoryRepository.getIdAndNameCategory();
        return categories;
    }

    @Override
    public Page<CategoryDTO> searchCategoryPaginged(String name, Pageable pageable) {
        Page<CategoryDTO> CategoryDTOPage = categoryRepository.searchCategoryPaginged(name, pageable);
        return CategoryDTOPage;
    }

    @Override
    public Page<CategoryDTO> findAllPaginged(Pageable pageable) {
        Page<CategoryDTO> CategoryDTOPage = categoryRepository.findAllPaginged( pageable);
        return CategoryDTOPage;
    }

    @Override
    public CategoryDTO findByCategoryId(Long id) {
        CategoryDTO categoryDTO = this.categoryRepository.findByCategoryId(id)
                .orElseThrow(()->new NotFoundException("Category not found"));
        return categoryDTO;
    }

    @Override
    public CategoryDTO findByCategoryName(String name) {
        return categoryRepository.findByCategoryName(name);
    }

    @Override
    public CategoryDTO getIdAndNameCategoryById(Long id) {
        CategoryDTO categoryDTO = categoryRepository.getIdAndNameCategoryById(id);
        return categoryDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryDTO.getId());
        categoryEntity.setName(categoryDTO.getName());
        categoryEntity.generateCategorySlug();
        CategoryEntity categorySave = categoryRepository.save(categoryEntity);
        return modelMapper.map(categorySave, CategoryDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        CategoryEntity categoryOptional = categoryRepository.findByCategoryIdForUpdate(categoryDTO.getId())
                .orElseThrow(()->new NotFoundException("Category not found "));
        categoryOptional.setName(categoryDTO.getName());
        categoryOptional.generateCategorySlug();
        CategoryEntity categorySave = categoryRepository.save(categoryOptional);
        return modelMapper.map(categorySave, CategoryDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCategoryById(Long id) {
        CategoryDTO categoryDTO = this.categoryRepository.findByCategoryId(id)
                .orElseThrow(() -> new NotFoundException("cannot find category with id :" + id ));
        categoryRepository.deleteCategoryById(id);
    }
}
