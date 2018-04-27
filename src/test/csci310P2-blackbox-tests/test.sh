#!/bin/bash
sh resetdb.sh
cucumber features/register.feature
cucumber features/login.feature
cucumber features/collageGeneration.feature
cucumber features/loadingImage.feature
cucumber features/exportFunctionality.feature
cucumber features/collageHistory.feature
cucumber features/collage_fiter_options.feature
cucumber features/collage_main_section.feature
cucumber features/delete_collage.feature
cucumber features/display_elements.feature
cucumber features/imageRequirement.feature
