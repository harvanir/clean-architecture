FROM golang:1.16.3-alpine3.13 as multistage
RUN apk update && apk add --no-cache git

WORKDIR /go/src/app
COPY . .

RUN go build -o bin/main -v cmd/*
RUN pwd
RUN ls

## Optimize size image
FROM alpine:3.13.5

COPY --from=multistage /go/src/app/bin/main /app
CMD ["/app"]