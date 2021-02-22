package com.example.azureretrofit;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Dictor {
    String Name;
    String DisplayName;
    String LocalName;
    String ShortName;
    String Gender;
    String Locale;
    String SampleRateHertz;
    String VoiceType;
    String Status;

    @NonNull
    @Override
    public String toString(){
        String res = DisplayName+", "+Gender+", "+Locale;
        return res;
    }
        // https://docs.microsoft.com/ru-ru/azure/cognitive-services/speech-service/rest-text-to-speech
}
