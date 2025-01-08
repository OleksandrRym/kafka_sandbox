package io.conduktor.kfk.service;

import io.conduktor.kfk.service.dto.CreateProductDTO;

import java.util.concurrent.ExecutionException;

public interface ProductService {
    String createProduct (CreateProductDTO createProductDTO) throws ExecutionException, InterruptedException;

}
