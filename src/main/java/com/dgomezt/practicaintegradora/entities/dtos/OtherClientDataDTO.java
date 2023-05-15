package com.dgomezt.practicaintegradora.entities.dtos;

import com.dgomezt.practicaintegradora.entities.helpers.Address;
import com.dgomezt.practicaintegradora.entities.helpers.CreditCart;

import java.util.ArrayList;

public class OtherClientDataDTO {
    Integer interactions;
    ArrayList<Address> deliveryAddress;
    ArrayList<CreditCart> creditCarts;
    ArrayList<String> interestedCategories;
    String comments;
    Boolean license;
}
