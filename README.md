# Ecommerce Backend Spring/Java

![<texto-alt>](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQJyPvsrm6Jkz9BC-FLWxttZEUaAYLx2ZZuxw&s)

```bash
ENDPOINTS:
```
## Cart
- **Get** : http://localhost:8082/api/v1/cart/1
- **Put** : http://localhost:8082/api/v1/cart/1
- **Post** : [http://localhost:8082/api/v1/cart/1](http://localhost:8082/api/v1/cart)  | **Body**  {"user_id": 9}
- **Delete** : http://localhost:8082/api/v1/cart/1
## cart_items
- **Get** : http://localhost:8082/api/v1/cartsitems
- **Put** : http://localhost:8082/api/v1/cartsitem/5 | **Body** {"cart_id": 7,"product_id": 7,"quantity" : 3}
- **Post** : http://localhost:8082/api/v1/cartsitem | **Body** {"cart_id": 7,"product_id": 7,"quantity" : 3}
- **Delete** : http://localhost:8082/api/v1/cartsitem/6
## disscounts
- **Get** : http://localhost:8082/api/v1/disccounts
- **Get**: http://localhost:8082/api/v1/disccount/1
- **Put** : http://localhost:8082/api/v1/disccounts/1 | **Body** {"description": null,"discount_code": "Codee","discount_percentage": 10.00,"start_date":"2024-01-10","end_date":"2024-01-18","min_purchase": null}
- **Post** : http://localhost:8082/api/v1/disccount | **Body** {"cart_id": 7,"product_id": 7,"quantity" : 3}
- **Delete** : http://localhost:8082/api/v1/disccount/2  | **Body** {"description": null,"discount_code": "Codee","discount_percentage": 10.00,"start_date":"2024-01-10","end_date":"2024-01-18","min_purchase": null}
## orders
- **Get** : http://localhost:8082/api/v1/orders
- **Get** : http://localhost:8082/api/v1/order/1
- **Post** : http://localhost:8082/api/v1/order | **Body** {"total_amount":120.00,"status":null}
- **Delete** : http://localhost:8082/api/v1/order/3
## payments
- **Get** : http://localhost:8082/api/v1/payments  
- **Post** : http://localhost:8082/api/v1/cartsitem | **Body** {"payment_method":"efectivo","amount":125,"status":"pendiente","order_id": 5}
## users
- **Get** : http://localhost:8082/api/v1/users
- **Get** : http://localhost:8082/api/v1/user/2
- **Post** : http://localhost:8082/api/v1/login | **Body**  {"usernameOrEmail": "angievivi@gmail.com","password": "2015"}
- **Post** : http://localhost:8082/api/v1/user/ | **Body**  {"username": "SaraCa","email": "SaraCa@gmail.com","password": "123456","first_name": "Sara","last_name": "Cat","address": "calle 123","city": "bogota","postal_code": "1221","country": "Colombia","rol_id": 1}
- **Delete** : http://localhost:8082/api/v1/user/2
## rols
- http://localhost:8082/api/v1/rols
## Products
- **Get** : http://localhost:8082/api/v1/cartsitems
- **Put** : http://localhost:8082/api/v1/cartsitem/5 | **Body** {"cart_id": 7,"product_id": 7,"quantity" : 3}
- **Post** : http://localhost:8082/api/v1/cartsitem | **Body** {"cart_id": 7,"product_id": 7,"quantity" : 3}
- **Delete** : http://localhost:8082/api/v1/product/10
## Categories
- **Get** : http://localhost:8082/api/v1/categories
- **Get** : http://localhost:8082/api/v1/categories/1
- **Post** : http://localhost:8082/api/v1/categories | **Body** { "categoty": "hogar"}
- **Delete** : http://localhost:8082/api/v1/categories/3
## Categories
- **Get** : http://localhost:8082/api/v1/products
- **Get** : http://localhost:8082/api/v1/products/1
- **Post** : http://localhost:8082/api/v1/products | **Body** {"productName": "Vajilla para loza","description": "Marca corona","price": 42000.00,"category_id":10}
- **Delete** : http://localhost:8082/api/v1/products/3
  


## CONSIDERATION
- you should installed **java** in your laptop
- java v.17
- this project runs on the port 8082
- http://localhost:8082

## Technologies used in the project
- Mysql
- Java

## Data base
- ecommerce: script in this repo



