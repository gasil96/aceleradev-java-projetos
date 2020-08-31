package br.com.codenation.service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

public class OrderServiceImpl implements OrderService {

    private ProductRepository productRepository = new ProductRepositoryImpl();

    @Override
    public Double calculateOrderValue(List<OrderItem> items) {
        return items.stream().map(v -> this.calculatedValueProduct(v))
                .collect(Collectors.toList()).stream().mapToDouble(Double::doubleValue).sum();
    }

    @Override
    public Set<Product> findProductsById(List<Long> ids) {
        Set<Product> productsMap = ids.stream().filter(p -> productRepository.findById(p).isPresent())
                .map(p -> productRepository.findById(p).get()).collect(Collectors.toSet());
        return productsMap;
    }

    @Override
    public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
        return orders.stream().map(o -> this.calculateOrderValue(o)).mapToDouble(Double::doubleValue).sum();
    }

    @Override
    public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {
        return productIds.stream().filter(id -> id > 0 && id != null).map(id -> productRepository.findById(id).get())
                .collect(Collectors.toList()).stream().collect(Collectors.groupingBy(Product::getIsSale));
    }

    private Double calculatedValueProduct(OrderItem orderItem) {
        Optional<Product> productFind = productRepository.findById(orderItem.getProductId());
        System.err.println(productFind.get().getName());
        if (productFind.isPresent()) {
            Double valueFinal = productFind.get().getValue() * orderItem.getQuantity();
            if (productFind.get().getIsSale()) {
                Double desconto = valueFinal *  0.20;
                valueFinal = valueFinal - desconto;
            }
            return valueFinal;
        } else {
            return 0.0;
        }

    }

    public static void main(String[] args) {
        OrderItem orderItem = new OrderItem(17L, 1L);
        OrderService service = new OrderServiceImpl();
        List<OrderItem> listaOrder = new ArrayList<>();
        listaOrder.add(orderItem);

        System.err.println(service.calculateOrderValue(listaOrder));

    }

}