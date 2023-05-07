package com.dgomezt.practicaintegradora.entities.dtos;

import com.dgomezt.practicaintegradora.entities.Category;
import com.dgomezt.practicaintegradora.entities.embeddables.Auditory;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CategoryDetailsDTO {
    Long id;
    String code;
    String description;
    String parentCategory;
    String childCategories;
    LocalDate entryDate;
    Long entryUser;
    LocalDate lastModificationDate;
    Long lastModificationUser;
    LocalDate removedDate;
    Long removedUser;

    public static CategoryDetailsDTO fromCategory(Category category) {
        CategoryDetailsDTO categoryDetailsDTO = new CategoryDetailsDTO();

        categoryDetailsDTO.id = category.getId();
        categoryDetailsDTO.code = category.getCode();
        categoryDetailsDTO.description = category.getDescription();

        if (category.getParentCategory() != null)
            categoryDetailsDTO.parentCategory = category.getParentCategory().getCode();

        if(category.getChildCategories().isEmpty())        
            categoryDetailsDTO.childCategories = "";
        else {
            categoryDetailsDTO.childCategories = "";
            for (Category childCategory : category.getChildCategories()) {
                    categoryDetailsDTO.childCategories += childCategory.getCode() + " ";
            }
        }

        if(category.getAuditory() == null) category.setAuditory(new Auditory());

        categoryDetailsDTO.entryDate = category.getAuditory().getEntryDate();

        if (category.getAuditory().getEntryUser() != null)
            categoryDetailsDTO.entryUser = category.getAuditory().getEntryUser().getId();

        categoryDetailsDTO.lastModificationDate = category.getAuditory().getLastModificationDate();

        if (category.getAuditory().getLastModificationUser() != null)
            categoryDetailsDTO.lastModificationUser = category.getAuditory().getLastModificationUser().getId();

        categoryDetailsDTO.removedDate = category.getAuditory().getRemovedDate();

        if (category.getAuditory().getRemovedUser() != null)
            categoryDetailsDTO.removedUser = category.getAuditory().getRemovedUser().getId();

        return categoryDetailsDTO;
    }
}
