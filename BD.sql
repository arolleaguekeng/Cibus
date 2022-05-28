CREATE TABLE IF NOT EXISTS Localisation
(
	localisation_id SERIAL PRIMARY KEY NOT NULL,
	ville VARCHAR(50) NOT NULL,
	quartier VARCHAR(50) NOT NULL
)

CREATE TABLE IF NOT EXISTS Category
(
	category_id SERIAl PRIMARY KEY NOT NULL,
	image VARCHAR(255) NOT NULL,
	nom VARCHAR(50) NOT NULL
)
 
CREATE TABLE IF NOT EXISTS Restaurant
(
	restaurant_id SERIAL PRIMARY KEY NOT NULL,
	localisation_id INTEGER NOT NULL,
	image VARCHAR(255) NOT NULL,
	nom VARCHAR(50) NOT NULL,
	description TEXT NOT NULL,	
	rating INTEGER,	
	CONSTRAINT rest_fk FOREIGN KEY (localisation_id)
        REFERENCES Localisation (localisation_id)
)

CREATE TABLE IF NOT EXISTS Repas
(
	repas_id SERIAl PRIMARY KEY NOT NULL,
	restaurant_id INTEGER NOT NULL,
    category_id INTEGER NOT NULL,
	nom VARCHAR(50) NOT NULL,
	image VARCHAR(255) NOT NULL,
	description TEXT NOT NULL,
	price DECIMAL NOT NULL,
	rating INTEGER,
	CONSTRAINT plat_fk FOREIGN KEY (restaurant_id)
        REFERENCES Restaurant (restaurant_id),
	CONSTRAINT plat_cat_fk FOREIGN KEY (category_id)
        REFERENCES Category (category_id)
)

CREATE TABLE IF NOT EXISTS ProduitPanier
(
    produit_panier_id SERIAL PRIMARY KEY NOT NULL,
	repas_id INTEGER NOT NULL,
	quantite INTEGER NOT NULL,
	CONSTRAINT pan_fk FOREIGN KEY (repas_id)
        REFERENCES Repas (repas_id)
)

CREATE TABLE IF NOT EXISTS Panier
(
	panier_id SERIAL PRIMARY KEY NOT NULL,
	produit_panier_id INTEGER NOT NULL,
	CONSTRAINT pro_fk FOREIGN KEY (produit_panier_id)
        REFERENCES ProduitPanier (produit_panier_id)	
)

CREATE TABLE IF NOT EXISTS User
(
	user_id SERIAL PRIMARY KEY NOT NULl,
	localisation_id INTEGER NOT NULL,
	nom VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	phone NUMERIC NOT NULL,
	image VARCHAR(255),
	 CONSTRAINT user_loc_fk FOREIGN KEY (localisation_id)
        REFERENCES Localisation (localisation_id)	
)



-------test commit-----------------!>