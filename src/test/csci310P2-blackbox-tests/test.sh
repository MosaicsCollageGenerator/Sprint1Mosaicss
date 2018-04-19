#!/bin/bash
sh resetdb.sh
cucumber features/register.feature
cucumber features/login.feature
cucumber features/collageGeneration.feature
cucumber features/loadingImage.feature
cucumber features/exportFunctionality.feature
cucumber features/collageHistory.feature
