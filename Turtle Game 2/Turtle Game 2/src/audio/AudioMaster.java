package audio;

import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;

public class AudioMaster {
	
	public static float MASTER_VOLUME = 1;
	public static float SOUND_EFFECT_VOLUME = 1;
	public static float GAME_VOLUME = 1;
	
	private static ArrayList<Integer> buffers = new ArrayList<Integer>();
	private static HashMap<String, Integer> bufferMap = new HashMap<String, Integer>();
	
	public static void init() {
		try{
			AL.create();
		}catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public static void setListenerData() {
		AL10.alListener3f(AL10.AL_POSITION, 0, 0, 0);
		AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);
	}
	
	public static void cleanUp() {
		for(int i:buffers) {
			AL10.alDeleteBuffers(i);
		}
		for(int s:Source.sources) {
			AL10.alDeleteSources(s);
		}
		AL.destroy();
	}
	
	public static int getSound(String soundName) {
		if(bufferMap.get(soundName)==null) {
			try {
				String file = ("audio/"+soundName+".wav");
				int buffer = AL10.alGenBuffers();
				buffers.add(buffer);
				WaveData waveFile = WaveData.create(file);
				AL10.alBufferData(buffer, waveFile.format, waveFile.data, waveFile.samplerate);
				waveFile.dispose();
				
				bufferMap.put(soundName, buffer);
				return buffer;
			}catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}
		return(bufferMap.get(soundName));
	}

}
