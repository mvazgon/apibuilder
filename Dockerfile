FROM python:3.10

RUN  pip3 install fastapi 
RUN  adduser --no-create-home app 
RUN  mkdir /opt/apibuilder 
COPY src/ /opt/apibuilder 
RUN  chown app: /opt/apibuilder/ -R

EXPOSE 8000

USER app
WORKDIR /opt/apibuilder/

CMD uvicorn main:app --host 0.0.0.0 --port 8000