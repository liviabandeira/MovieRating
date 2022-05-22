# Movie Rating API

> The solution used allows the voting of a movie by category 
with the rating between 1 to 5, where 1 is bad and 5 is 
excellent. A new table called movie_rating was created 
to save the vote. Containing only the vote and the reference 
to the academy_awards table.

> To return the top 10 of the best rated movies, a search 
is made in the movie_rating table to get the average of 
the best rated movies and to sort by box office, 10 
asynchronous requests are made in the omdb API.
