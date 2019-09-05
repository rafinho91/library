# Endpoints
## Book by ISBN
Will return a book identified by the given ISBN number in the form of a JSON document or return a 404 if the book
does not exists in the data set.
- Endpoint url : /api/book/{isbn}

## Book by category
Lists all books that are assigned to the requested category (empty list if no books belong to the category).
- Endpoint url : /api/category/{categoryName}/books

## Book by query
Lists all books that:) phrase (in title, authors, description, etc) (empty list if no books contains given phrase).
- Endpoint url : /api/search?q={phrase}

## Author rating
Lists all authors and their rating in descending order of the average rating of their books.
If a book is not rated, it should be not taken into account in the calculation of its author’s rating.
- Endpoint url : /api/rating
