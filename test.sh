#!/bin/bash

java -jar "\"$(pwd)/bundle/aiur-processor-thymeleaf-all.jar\" --prefix \"$(pwd)/test/\" test.html"
