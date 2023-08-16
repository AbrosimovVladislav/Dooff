INSERT INTO ingredient_entity (ingredient_id, calories, carbohydrates, fats, name, proteins)
VALUES
    (1, 157, 0.7, 10.9, 'Яйцо', 12.7),
    (2, 356, 2, 27, 'Сыр Гауда', 25),
    (3, 44, 4.7, 1.5, 'Молоко Безлактозное', 3);

INSERT INTO dish_entity (dish_id, name)
VALUES (1, 'Омлет с сыром');

INSERT INTO dish_ingredient_entity (dish_ingredient_ud, dish_id, ingredient_id, weight)
VALUES
    (1,1,1,240),
    (2,1,2,62),
    (3,1,3,200);