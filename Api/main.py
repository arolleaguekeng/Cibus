import secrets
from typing import TypeVar, Generic

from fastapi import FastAPI, HTTPException, UploadFile, File, Depends
from fastapi.security import OAuth2PasswordBearer, OAuth2PasswordRequestForm
from starlette import status
from tortoise import Tortoise
import shutil
from fastapi.staticfiles import StaticFiles
from PIL import Image
from models import *
from pydantic.typing import List
from tortoise.contrib.fastapi import HTTPNotFoundError, register_tortoise
from pydantic import BaseModel
import uvicorn


class Message(BaseModel):
    message: str


app = FastAPI(
    title="Cibus",
    description="Api pour la gestion de la base de donnée de l'application des restaurants",
    version="0.0.1",
    terms_of_service="http://example.com/terms/",
    contact={
        "name": "Deadpoolio the Amazing",
        "url": "http://x-force.example.com/contact/",
        "email": "dp@x-force.example.com",
    },
    license_info={
        "name": "Apache 2.0",
        "url": "https://www.apache.org/licenses/LICENSE-2.0.html",
    },
)
list_restau = []
print(list_restau)

app.mount("/static", StaticFiles(directory="static"), name="static")


# upload image from source
@app.post("/upload/profile")
async def create_upload_file(file: UploadFile = File(...)):
    filepath = "./static/images"
    filename = file.filename
    extension = filename.split(".")[1]
    if extension not in ["png", "jpg"]:
        return {"status": "error", "details": "File extension not allowed"}

    token_name = secrets.token_hex(10) + "." + extension
    generate_name = filepath + token_name
    file_content = await  file.read()
    with open(generate_name, "wb") as file:
        file.write(file_content)

        # PILLOW
        img = Image.open(generate_name)
        img.save(generate_name)
        file.close()
        # business = await Business.get(owner)
    file_url = generate_name[1:]
    return {"statut": "ok", "filename": file_url}


#
# Region Restaurant
#
@app.get("/")
async def hello():
    return {"Wellcom Message": "Bienvenue sur L'Api de Cibus"}


# Add resquests for restaurant
@app.post("/Restaurant", response_model=RestaurantIn_Pydantic)
async def create_restaurant(restaurant: RestaurantIn_Pydantic):
    obj = await Restaurant.create(**restaurant.dict(exclude_unset=True))
    return await RestaurantIn_Pydantic.from_tortoise_orm(obj)


# get restaurant by id
@app.get("/Restaurant/{id}", response_model=RestaurantIn_Pydantic, responses={404: {"model": HTTPNotFoundError}})
async def get_one_restaurant(restaurant_id: int):
    return await RestaurantIn_Pydantic.from_queryset_single(Restaurant.get(restaurant_id=restaurant_id))


# get all restaurant
@app.get("/Restaurants/", response_model=List[Restaurant_Pydantic])
async def get_all_restaurants():
    repas = get_all_repas()
    var = Restaurant()
    var.repas = repas
    return await Restaurant_Pydantic.from_queryset(var.all())


# update restaurant
@app.put("/Restaurant/{restaurant_id}", response_model=Restaurant_Pydantic,
         responses={404: {"model": HTTPNotFoundError}})
async def update_restaurant(restaurant_id: int, restaurant: RestaurantIn_Pydantic):
    await Restaurant.filter(restaurant_id=restaurant_id).update(**restaurant.dict(exclude_unset=True))
    return await Restaurant_Pydantic.from_queryset_single(Restaurant.get(restaurant_id=restaurant_id))


# delete restaurant
@app.delete("/Restaurant/{restaurant_id}", response_model=Message, responses={404: {"model": HTTPNotFoundError}})
async def delete_restaurant(restaurant_id: int):
    delete_obj = await Restaurant.filter(restaurant_id=restaurant_id).delete()
    if not delete_obj:
        raise HTTPException(status_code=404, detail="this restaurant, is not found")
    return Message(message="Succesfully deleted")


#
# Requests for Repas
#


# Add new Repas
@app.post("/repas", response_model=RepasIn_Pydantic)
async def create_repas(repas: RepasIn_Pydantic):
    obj = await Repas.create(**repas.dict(exclude_unset=True))
    if get_one_restaurant(obj.restaurant_id) is None:
        return {"Error": "This retaurant do not exist"}
    return await RepasIn_Pydantic.from_tortoise_orm(obj)


# get repas by Id
@app.get("/repas/{repas_id}", response_model=RepasIn_Pydantic, responses={404: {"model": HTTPNotFoundError}})
async def get_one_repas(repas_id: int):
    return await RepasIn_Pydantic.from_queryset_single(Repas.get(repas_id=repas_id))


# get all repas
@app.get("/repas/", response_model=List[Repas_Pydantic])
async def get_all_repas():
    return await Repas_Pydantic.from_queryset(Repas.all())


# Update repas
@app.put("/repas/{repas_id}", response_model=Repas_Pydantic, responses={404: {"model": HTTPNotFoundError}})
async def update_repas(repas_id: int, repas: RepasIn_Pydantic):
    await Repas.filter(repas_id=repas_id).update(**repas.dict(exclude_unset=True))
    return await Repas_Pydantic.from_queryset_single(Repas.get(repas_id=repas_id))


# delete repas
@app.delete("/repas/{repas_id}", response_model=Message, responses={404: {"model": HTTPNotFoundError}})
async def delete_repas(repas_id: int):
    delete_obj = await Repas.filter(repas_id=repas_id).delete()
    if not delete_obj:
        raise HTTPException(status_code=404, detail="this repas, is not found")
    return Message(message="Succesfully deleted")


#
# Requests For Users
#

JWT_SECRET = 'myjwtsecret'


# Add new  User
@app.post("/user", response_model=UserIn_Pydantic)
async def create_user(user: UserIn_Pydantic):
    obj = await User.create(**user.dict(exclude_unset=True))
    return await UserIn_Pydantic.from_tortoise_orm(obj)


# get repas by Id
@app.get("/user/{user_id}", response_model=UserIn_Pydantic, responses={404: {"model": HTTPNotFoundError}})
async def get_one_user(user_id: int):
    return await UserIn_Pydantic.from_queryset_single(User.get(user_id=user_id))


# get all user
@app.get("/user", response_model=List[UserIn_Pydantic])
async def get_all_users():
    return await UserIn_Pydantic.from_queryset(User.all())


# Update user
@app.put("/user/{user_id}", response_model=User_Pydantic, responses={404: {"model": HTTPNotFoundError}})
async def update_user(user_id: int, user: UserIn_Pydantic):
    await User.filter(user_id=user_id).update(**user.dict(exclude_unset=True))
    return await User_Pydantic.from_queryset_single(User.get(user_id=user_id))


# delete user
@app.delete("/user/{user_id}", response_model=Message, responses={404: {"model": HTTPNotFoundError}})
async def delete_repas(user_id: int):
    delete_obj = await User.filter(user_id=user_id).delete()
    if not delete_obj:
        raise HTTPException(status_code=404, detail="this repas, is not found")
    return Message(message="Succesfully deleted")


# Login User


oauth2_scheme = OAuth2PasswordBearer(tokenUrl='token')


@app.get("/login")
async def authenticate_user(email: str, password: str):
    user = await User.get(email=email, password=password)
    if not user:
        return False
    return user


# @app.post('/token')
# async def generate_token(form_data: OAuth2PasswordRequestForm = Depends()):
#     user = await authenticate_user(form_data.username, form_data.password)
#
#     if not user:
#         raise HTTPException(
#             status_code=status.HTTP_401_UNAUTHORIZED,
#             detail='Invalid username or password'
#         )
#
#     user_obj = await User_Pydantic.from_tortoise_orm(user)
#
#     token = jwt.encode(user_obj.dict(), JWT_SECRET)
#
#     return {'access_token': token, 'token_type': 'bearer'}


# async def get_current_user(token: str = Depends(oauth2_scheme)):
#     try:
#         payload = jwt.decode(token, JWT_SECRET, algorithms=['HS256'])
#         user = await User.get(user_id=payload.get('id'))
#     except:
#         raise HTTPException(
#             status_code=status.HTTP_401_UNAUTHORIZED,
#             detail='Invalid username or password'
#         )
#
#     return await User_Pydantic.from_tortoise_orm(user)


@app.post('/users', response_model=User_Pydantic)
async def create_user(user: UserIn_Pydantic):
    user_obj = User(email=user.email)
    await user_obj.save()
    return await User_Pydantic.from_tortoise_orm(user_obj)


# @app.get('/users/me', response_model=User_Pydantic)
# async def get_user(user: User_Pydantic = Depends(get_current_user)):
#     return user


###localisation Region

# get localisation

@app.get("/localisations", response_model=List[Localisation_Pydantic], status_code=200)
async def return_all_localisation():
    return await Localisation_Pydantic.from_queryset(Localisation.all())


# post localisation

@app.post("/localisations", response_model=LocalisationIn_Pydantic, status_code=status.HTTP_201_CREATED)
async def add_localisation(localisation: LocalisationIn_Pydantic):
    obj = await Localisation.create(**localisation.dict(exclude_unset=True))
    return await Localisation_Pydantic.from_tortoise_orm(obj)


# delete localisation

@app.delete("/localisations/{localisationId}")
async def delete_localisation(localisationId: int):
    delete_obj = await Localisation.filter(localisation_id=localisationId).delete()
    if not delete_obj:
        raise HTTPException(status_code=404, detail="this localisation, is not found")
    return Message(message="Succesfully delete")


#
# Region Configurations
#


# configurations of server
if __name__ == "__main__":
    uvicorn.run("main:app", host="192.168.1.107", port=8000, log_level="info")

# Connect to database
register_tortoise(
    app,
    db_url="postgres://postgres:root@localhost/restaurant",
    modules={"models": ["models"]},
    generate_schemas=True,
    add_exception_handlers=True
)
