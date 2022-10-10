package net.wolfboy.tfml;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class TurAIAPI {

    public static String IOHandler(String input, boolean useSimpleAlgorithm, Boolean debug, int initialTolerance) throws IOException {
                if (useSimpleAlgorithm) {
                    FindAndStoreCharacterCount.MakeCharacterCount(input, debug);
                }
                return TheAlgorithm.ChoosingResponse(debug, useSimpleAlgorithm, input, initialTolerance);
            }
        }
