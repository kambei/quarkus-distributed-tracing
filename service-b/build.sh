#!/bin/bash

./gradlew build -Dquarkus.container-image.build=true -Dquarkus.container-image.builder=docker -x test