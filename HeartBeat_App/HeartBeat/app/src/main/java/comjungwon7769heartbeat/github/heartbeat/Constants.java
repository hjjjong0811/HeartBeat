package comjungwon7769heartbeat.github.heartbeat;

public class Constants {
	public static final String defaultDeviceName = "BK8000L";

	public static final int maxString = 16;
	public static final int minString = 4;

	public static final int friendLoad_Interval = 300000;    //millsec -> 5min 300000
	public static final int RequestMsg_Interval = 1000;
	public static final int ServerWaitTime = 10000;

	//설정
	public static final boolean set_btBzz_ok = true, set_btBzz_no = false;
	public static final int set_push_no = 0, set_push_sound = 1, set_push_bzz = 2, set_push_both = 3;

	//서버주소
	public static final String SERVERURL =  "223.195.2.80";
	/*
	PopupActivity 의 Type 정의
	 */
	//PopupActivity Type에 따른, Intent를 이용해 (팝업으로 보낼 Data), (팝업에서 받아올 Data)
	public static final int popup_ok = 0;   //Popup = 0, To PopupActivity(Message = String), From PopupActivity()
	public static final int popup_re = 1;   //Popup = 1, To(Message = String), From(select = boolean)

	public static final int popup_pickEmotion = 10;  //Popup = 10, To (), From (selectedEmotion = int)

	public static final int popup_pickColor = 11;    //Popup = 11, To (), From (selectedColor = String)
	public static final int popup_recordVoice = 12;     //Popup = 12, To(), From(voicePath = String)

	public static final int popup_msgFriend = 20;   //Popup = 20, To(ID = String, Time = long), From(ID = String, Time = long, select = boolean)
	public static final int popup_msgVoice = 21;    //Popup = 21, To(ID = String, Nick = String, Time = long, Path = String), From(ID = String, Time = long, select = boolean)
	public static final int popup_msgEmotion = 22;  //Popup = 22, To(ID = String, Nick = String, Emotion = int, Time = long), From(ID = String, Time = long, select = boolean)
	public static final int popup_msgBzz = 23;      //Popup = 23, To(ID = String, Nick = String, Count = int, Time = long), From(ID = String, Time = long, select = boolean)

	/*
	Message Flag 정의
	 */
	public static final int msgFlag_Voice = 0;
	public static final int msgFlag_Emotion = 1;
	public static final int msgFlag_Bzz = 2;
	public static final int msgFlag_Friend = 3;
	public static final int msgFlag_any_id = 4;


	public static final int Emotion_sound[] = {R.raw.emotion_00_smile, R.raw.emotion_01_laugh, R.raw.emotion_02_sad, R.raw.emotion_03_annoy, R.raw.emotion_04_angry,
			R.raw.emotion_05_wink, R.raw.emotion_06_love, R.raw.emotion_07_wow, R.raw.emotion_08_overeat, R.raw.emotion_09_sleep};

	public static final int Emotion_content[] = {R.string.msg_content_e_smile, R.string.msg_content_e_laugh, R.string.msg_content_e_sad, R.string.msg_content_e_annoy,
			R.string.msg_content_e_angry, R.string.msg_content_e_wink, R.string.msg_content_e_love, R.string.msg_content_e_wow, R.string.msg_content_e_overeat, R.string.msg_content_e_sleep};

	public static enum Emotion {
		smile(0, "59BE7F"), laugh(1, "FFFF00"), sad(2, "0000FF"), annoy(3, "FF9900"), angry(4, "FF0000"),
		wink(5, "8041D9"), love(6, "FF93B2"), wow(7, "7393D5"), overeat(8, "D9CA74"), sleep(9, "FFFFFF");

		private int mode;
		private String color;

		Emotion(int value, String colorValue) {
			this.mode = value;
			this.color = colorValue;
		}

		public int getMode() {
			return mode;
		}
		public String getColor() {
			return color;
		}
	} //enum Emotion

}
