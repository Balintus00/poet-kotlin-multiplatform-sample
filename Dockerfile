FROM gradle:7.5.1-jdk11-alpine

WORKDIR /usr/src/app

ENV USE_PREINSTALLED_NODE_JS=FALSE

COPY . .

EXPOSE 8080

RUN apk add nodejs npm

CMD ["gradle", "backend:nodeRun"]