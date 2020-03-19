CREATE TABLE games(codi VARCHAR(100) PRIMARY KEY NOT NULL,
                  name VARCHAR(50) NOT NULL,
                  review VARCHAR(100) NOT NULL,
                  rating  VARCHAR(50) NOT NULL,
                  genere VARCHAR(50) NOT NULL);

INSERT INTO games (codi, name, review, rating, genere) VALUES ('010', 'Call of Dutty','Great Game', 1, 'Accio');
INSERT INTO games (codi, name, review, rating, genere) VALUES ('020', 'Witcher','Great Game', 3, 'Aventura');
INSERT INTO games (codi, name, review, rating, genere) VALUES ('030', 'Mario Bross','Great Game', 4, 'Accio');
