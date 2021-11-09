#!/bin/bash

mkdir bundle;
echo "build: ";
./gradlew shadowjar \
  && echo "copy: " \
  && cp -vf ./app/build/libs/aiur-processor-thymeleaf-all.jar ./bundle/aiur-processor-thymeleaf-all.jar \
  ;
