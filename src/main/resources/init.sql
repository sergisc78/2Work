CREATE TABLE series(codi VARCHAR(100) PRIMARY KEY NOT NULL,
                  name VARCHAR(50) NOT NULL,
                  review VARCHAR(100) NOT NULL,
                  numCap VARCHAR(100) NOT NULL,
                  rating  VARCHAR(50) NOT NULL,
                  genere VARCHAR(50) NOT NULL);

INSERT INTO series (codi, name, review,numCap, rating, genere) VALUES ('010', 'Doctor Who','Great Serie','30', '1', 'Accio');
INSERT INTO series (codi, name, review,numCap, rating, genere) VALUES ('020', 'La casa de Papel','Interesante','20', '3', 'Aventura');
INSERT INTO series (codi, name, review,numCap, rating, genere) VALUES ('030', 'The Simpsons','Antigua','500', '4', 'Dibujos');
