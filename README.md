# spring-integration-test
	                                                                                             BaristaService
	                                                                     hotDrinks             ____________________
	                                                                    |==========| -Bridge->|                    |
	                     orders                   drinks               /                      | prepareHotDrink()  | -- \      preparedDrinks                        deliveries
	Place Order ->Cafe->|======|->OrderSplitter->|======|->DrinkRouter                        |                    |      --> |==============| ->WaiterAggregator-> |==========|
	                                                                   \ coldDrinks           | prepareColdDrink() | -- /
	                                                                    |==========| -Bridge->|                    |
	                                                                                          |____________________|
	
	                                                Legend: |====| - channels
