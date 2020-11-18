package asr.proyectoFinal.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.language_translator.v3.LanguageTranslator;
import com.ibm.watson.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.language_translator.v3.model.TranslationResult;

public class Traductor {
	public static String translate(String palabra, String sourceModel, String destModel, boolean conversational)
			{
			String model;
			if(sourceModel.equals("en") || sourceModel.equals("es") || destModel.equals("en") || destModel.equals("es"))
			{
				model=sourceModel+"-"+destModel;
				if(conversational)
					model+="-conversational";
			}
			else
				model="en-es";
			
			Authenticator authenticator = new IamAuthenticator("VNkI-bsfWGZ0B6V14m6mY5I05mh6I2s2GJ6rEOAW3MfA");
			LanguageTranslator languageTranslator = new LanguageTranslator("2018-05-01", authenticator);
			
			
			languageTranslator.setServiceUrl("https://api.eu-gb.language-translator.watson.cloud.ibm.com/instances/9e206e37-5fae-444b-ae67-c7a0a296a5c6");
			
			TranslateOptions translateOptions = new TranslateOptions.Builder()
					.addText(palabra)
					.modelId(model)
					.build();
			TranslationResult translationResult = languageTranslator.translate(translateOptions).execute().getResult();
			
			System.out.println(translationResult);
			
			String traduccionJSON = translationResult.toString();
			JsonParser jp = new JsonParser() ;
			JsonObject rootObj =  jp.parse(traduccionJSON).getAsJsonObject();
			JsonArray traducciones = rootObj.getAsJsonArray("translations");
			String traduccionPrimera = palabra;
			if(traducciones.size()>0)
				traduccionPrimera = traducciones.get(0).getAsJsonObject().get("translation").getAsString();
			return traduccionPrimera;
	}
}
