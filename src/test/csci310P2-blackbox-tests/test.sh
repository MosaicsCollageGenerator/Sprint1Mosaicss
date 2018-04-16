#!/bin/bash
sh resetdb.sh
cucumber features/register.feature
cucumber features/login.feature
cucumber features/collageHistory.feature
cucumber features/mainPage.feature
cucumber features/exportFunctionality.feature
