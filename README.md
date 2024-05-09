

Endpoints 

Return all orders:

```http
GET /orders
```
<br> <br>

Return one order:

```http
GET /orders/77
```

| Pathvariable | Type  |
|:-------------| :--- | 
| `order id`   | `string` |
<br> <br>

Creates a new order:

```http
GET /orders/new?customer_id=2&member=true
```

| Parameter     | Type  | Required | Description                                            |
|:--------------| :--- |:---------|:-------------------------------------------------------|
| `customer id` | `string` | `false`  | `Creates new customer or add order to stored customer` |

| Parameter      | Type  | Required   | Description                                           | 
|:---------------| :--- |:--------|:------------------------------------------------------|
| `member`       | `string` | `false` | `Adds or remove membership to new or stored customer` |

| Request body | Type                            | Required | Description           | 
|:-------------|:--------------------------------|:---------|:----------------------|
|     | `non empty list of order items` | `true`   | `List of order items` |

<br>
A JSON request body containing a non-empty list of order items is required.
A food order item must contain two recuired properties: `type` and `foodFlavour`. 
A furniture order item contains one property : `itemNumber`

```http
Example body: 
[{"itemNumber": 2000},
{"type": "HOT_DOG", "foodFlavour" : "CHILI_FLAVORED"}]
```

EXAMPLES CURL: 

curl -X POST --location "http://localhost:8080/orders/new?customer_id=2&member=1" \ -H "Content-Type: application/json" \ -d "[{\"itemNumber\": 2000}]"

curl -X POST --location "http://localhost:8080/orders/new?customer_id=2" \
-H "Content-Type: application/json" \
-d "[{\"type\": \"HOT_DOG\", \"foodFlavour\" : \"CHILI_FLAVORED\"},
{\"type\": \"HOT_DOG\", \"foodFlavour\" : \"CHILI_FLAVORED\"}]"

curl -X GET --location "http://127.0.0.1:8080/orders"


