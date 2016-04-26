/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSconnect;

/**
 *
 * @author anuj
 */
import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	public static final AudioClip HIT = Applet.newAudioClip(Sound.class.getResource("hit.wav"));
        public static final AudioClip BACK = Applet.newAudioClip(Sound.class.getResource("back.wav"));
	
}
