# matcha
Dating webapp

###Filtering
    GET /cars?color=red Returns a list of red cars
    GET /cars?seats<=2 Returns a list of cars with a maximum of 2 seats

###Sorting
    GET /cars?sort=-manufactorer,+model

###Field selection
    GET /cars?fields=manufacturer,model,id,color

###Paging
    GET /cars?offset=10&limit=5