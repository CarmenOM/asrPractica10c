package asr.proyectoFinal.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.text_to_speech.v1.util.WaveUtils;
import com.ibm.watson.text_to_speech.v1.TextToSpeech;

import com.ibm.watson.text_to_speech.v1.model.SynthesizeOptions;


public class TextToSpeechService {

	public static void createSpeech(String audio)
	{
		IamAuthenticator authenticator = new IamAuthenticator("5nD0oxjkI9s0Hgb50zeXyM4-5glgwAys2c9CT-r9NmcR");
		TextToSpeech textToSpeech = new TextToSpeech(authenticator);
		textToSpeech.setServiceUrl("https://api.us-south.text-to-speech.watson.cloud.ibm.com/instances/b431d35f-bf8b-430f-933c-6518f161d646");
	
		try {
		  SynthesizeOptions synthesizeOptions =
		    new SynthesizeOptions.Builder()
		      .text(audio)
		      .accept("audio/wav")
		      .voice("en-US_AllisonV3Voice")
		      .build();
	
		  InputStream inputStream =
		    textToSpeech.synthesize(synthesizeOptions).execute().getResult();
		  InputStream in = WaveUtils.reWriteWaveHeader(inputStream);
	
		  OutputStream out = new FileOutputStream(audio + ".wav");
		  byte[] buffer = new byte[1024];
		  int length;
		  while ((length = in.read(buffer)) > 0) {
		    out.write(buffer, 0, length);
		  }
	
		  out.close();
		  in.close();
		  inputStream.close();
		} catch (IOException e) {
		  e.printStackTrace();
		}
	}
	
	 public static void reproducirSonido(String audio){
	       try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(audio).getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	       } catch(Exception ex) {
	         System.out.println("Error al reproducir el sonido.");
	       }
	     }
}
