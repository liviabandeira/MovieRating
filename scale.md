# Scaling

> The solution was developed thinking of a small traffic service, although this file can be used as a guideline to make it 
> more resilient to heavy load.

## 1. Caching layer
> A caching layer implementation can result in better performance, specially when the data is not changing.
> 
> The following traffic layers are good places for a caching mechanism:
> 
> - On requests that search the table academy_awards, which has data that doesn't change
> - On requests that fetches data from the omdbapi

## 2. Database
> The application performs an AVG operation on the fly everytime a request is made to fetch the top 10 nominees.
> 
> A great deal of latency can be saved if the database have a dynamic table view, which calls a procedure for updating 
> everytime a new rate is inserted.
> Then the application can return information by only checking this view.
