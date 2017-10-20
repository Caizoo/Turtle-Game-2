package audio;

import java.util.ArrayList;

import org.lwjgl.openal.AL10;

public class Source {
	
	private int sourceId;
	public static ArrayList<Integer> sources = new ArrayList<Integer>();
	public static final int sourceBufferSize = 100;
	
	public Source() {
		sourceId = AL10.alGenSources();
		sources.add(sourceId);
		AL10.alSourcef(sourceId, AL10.AL_GAIN, AudioMaster.MASTER_VOLUME);
		AL10.alSourcef(sourceId, AL10.AL_PITCH, 1);
		AL10.alSource3f(sourceId, AL10.AL_POSITION, 0, 0, 0);
	}

	public static void update() {
		while(sources.size()>sourceBufferSize) {
			delete(sources.get(0));
			sources.remove(0);
		}
		for(int i = 0;i<sources.size();i++) {
			if(AL10.alGetSourcei(sources.get(i),AL10.AL_SOURCE_STATE) != AL10.AL_PLAYING) {
				delete(sources.get(i));
				sources.remove(i);
			}
		}
	}
	
	public void play(int buffer, float volumeFactor) {
		AL10.alSourcef(sourceId, AL10.AL_GAIN, (AudioMaster.MASTER_VOLUME) * volumeFactor);
		AL10.alSourcei(sourceId, AL10.AL_BUFFER, buffer);
		AL10.alSourcePlay(sourceId);
	}
	
	public static void delete(int sourceId) { AL10.alDeleteSources(sourceId); }
	public static boolean isPlaying(int sourceId) { return AL10.alGetSourcei(sourceId, AL10.AL_SOURCE_STATE)==AL10.AL_PLAYING; }
	
}
