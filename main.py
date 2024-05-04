from fastapi import FastAPI, HTTPException
from fastapi.encoders import jsonable_encoder
from pydantic import BaseModel,ValidationError
import httpx
import os
import json

class Item(BaseModel):
    instanceId: int
    name: str
    repositoryPath: str
    version: str

app = FastAPI()
@app.get("/build")
async def general():
    return {"code":200,"reposnseText":"Lo has llamado"}

@app.post("/build")
async def create_item(item: Item):
    
    url = os.getenv("URLBUILDER") if os.getenv("URLBUILDER") else"http://localhost:8000/build"  # Reemplaza con la URL de tu endpoint
    headers={"headers":"application/json"}
    DATA = jsonable_encoder(item)
    
    async with httpx.AsyncClient() as client:
        #response = await client.post(url, headers=headers, json=data)
        response = await client.get(url, headers=headers)
    
    if response.status_code==200:
        PATH=os.getenv("DATAPATH")if os.getenv("DATAPATH") else "C:\windows\temp\\" 
        FILENAME=PATH+str(item.instanceId)+".json"
        
        with open(FILENAME,'w') as f:
            json.dump(DATA,f)
        
        return  item
    else:
        return response.status_code