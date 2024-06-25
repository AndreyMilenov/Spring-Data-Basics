package com.example.springintro.repository;
import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.EditionType;
import java.math.BigDecimal;

public interface BookInfo {
    String getTitle();
    EditionType getEditionType();
    AgeRestriction getAgeRestriction();
    BigDecimal getPrice();

}
