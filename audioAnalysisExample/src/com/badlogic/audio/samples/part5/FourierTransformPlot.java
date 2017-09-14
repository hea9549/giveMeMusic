package com.badlogic.audio.samples.part5;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.badlogic.audio.analysis.FFT;
import com.badlogic.audio.io.MP3Decoder;
import com.badlogic.audio.visualization.Plot;

/**
 * Simple example that generates a 1024 samples sine wave at 440Hz
 * and plots the resulting spectrum. 
 * 
 * @author mzechner
 *
 */
public class FourierTransformPlot
{
	public static void main( String[] argv )throws FileNotFoundException, Exception
	{
		final float frequency = 440; // Note A		
		float increment = (float)(2*Math.PI) * frequency / 44100;		
		float angle = 0;		
		float samples[] = new float[1024];
		MP3Decoder decoder = new MP3Decoder( new FileInputStream( "samples/muttyu.mp3" ) );
		while( decoder.readSamples( samples ) > 0 );
		FFT fft = new FFT( 1024, 44100 );
		
		/*for( int i = 0; i < samples.length; i++ )
		{
			samples[i] = (float)Math.sin( angle );
			angle += increment;		
		}*/

		fft.forward( samples );
		for(int i = 0;i<fft.getSpectrum().length;i++){
			if (fft.getSpectrum()[i]<0.002f)continue;
			System.out.println("line "+i+" : " + fft.getSpectrum()[i]);
		}
		Plot plot = new Plot( "Note A Spectrum", 512, 512);
		plot.plot(fft.getSpectrum(), 1, Color.red );
	}
}
