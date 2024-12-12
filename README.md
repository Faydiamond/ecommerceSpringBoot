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
- - **Get** : http://localhost:8082/api/v1/order/3
- **Post** : http://localhost:8082/api/v1/order | **Body** {"total_amount":120.00,"status":null}
- **Delete** : http://localhost:8082/api/v1/order/3
  

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



