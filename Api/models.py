
from tortoise import fields, models
from typing import Union

from tortoise.contrib import pydantic
from tortoise.contrib.pydantic import pydantic_model_creator


# Create Class Repas
class Repas(models.Model):
    repas_id = fields.IntField(pk=True)
    restaurant_id = fields.IntField()
    nom = fields.CharField(max_length=250)
    image = fields.CharField(max_length=500)
    description = fields.CharField(max_length=300)
    prix = fields.FloatField()
    rating = fields.FloatField()

    class Meta:
        table: str = 'Repas'


Repas_Pydantic = pydantic_model_creator(Repas, name="Repas")
RepasIn_Pydantic = pydantic_model_creator(Repas, name="RepasIn", exclude_readonly=True)


# Create class Restaurant
class Restaurant(models.Model):
    restaurant_id = fields.IntField(pk=True)
    image = fields.CharField(500,default="images0134519cf6628c4f7e56.jpg")
    nom = fields.CharField(max_length=250, default="Undefinied")
    description = fields.CharField(max_length=250, default="Base description")
    rating = fields.FloatField()

    class PydanticMeta:
        pass

    class Meta:
        table: str = 'Restaurant'


Restaurant_Pydantic = pydantic_model_creator(Restaurant, name="Restorant")
RestaurantIn_Pydantic = pydantic_model_creator(Restaurant, name="RestorantIn", exclude_readonly=True)


# Create Class User
class User(models.Model):
    user_id = fields.IntField(pk=True)
    localisation_id = fields.IntField()
    nom = fields.CharField(null=False, max_length=255, unique=True)
    email = fields.CharField(null=False, max_length=255, unique=True)
    password = fields.CharField(null=True, max_length=255)
    phone = fields.IntField()
    imgae = fields.CharField(500)


    class Meta:
        table: str = 'Users'


User_Pydantic = pydantic_model_creator(User, name="User")
UserIn_Pydantic = pydantic_model_creator(User, name="UserIn", exclude_readonly=True,
                                         exclude=('hashed_password', 'confirmation'))


class Panier(models.Model):
    panier_id = fields.IntField(pk=True)
    produit_panier_id = fields.IntField()

    class Meta:
        table: str = 'Panier'


Panier_Pydantic = pydantic_model_creator(Panier, name="Panier")
PanierIn_Pydantic = pydantic_model_creator(Panier, name="PanierIn", exclude_readonly=True)


class ProduitPanier(models.Model):
    produit_panier_id = fields.IntField(pk=True)
    repas_id = fields.IntField()
    quantite = fields.IntField()

    class Meta:
        table: str = 'ProduitPanier'


ProduitPanier_Pydantic = pydantic_model_creator(ProduitPanier, name="ProduitPanier")
ProduitPanierIn_Pydantic = pydantic_model_creator(ProduitPanier, name="ProduitPanierIn", exclude_readonly=True)


class Categrory(models.Model):
    category_id = fields.IntField()
    image = fields.CharField(max_length=500)
    name = fields.CharField(max_length=50)

    class Meta:
        table: str = 'Category'


Categrory_Pydantic = pydantic_model_creator(Categrory, name="Categrory")
CategroryIn_Pydantic = pydantic_model_creator(Categrory, name="CategroryIn", exclude_readonly=True)


class Localisation(models.Model):
    localisation_id = fields.IntField()
    ville = fields.CharField(max_length=50)
    quartier = fields.CharField(max_length=50)
    class Meta:
        table: str = 'Localisation'
Localisation_Pydantic = pydantic_model_creator(Localisation, name="Localisation")
LocalisationIn_Pydantic = pydantic_model_creator(Localisation, name="LocalisationIn", exclude_readonly=True)
