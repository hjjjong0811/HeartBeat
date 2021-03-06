package comjungwon7769heartbeat.github.heartbeat;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class BTSignalThread implements Runnable {
	public BlueToothCommunication btComu;
	public ServerCommunication svComu;
	public Context mContext;
	BlueToothHandler btHandler = new BlueToothHandler(null);

	public BTSignalThread(Context c) {
		this.mContext = c;
	}

	@Override
	public void run() {
		SharedPreferences preference = mContext.getSharedPreferences("user_info", Activity.MODE_PRIVATE);
		int count;
		btComu = new BlueToothCommunication(preference.getString("btAddr", ""), this.btHandler);
		btComu.setUseMode(btComu.CODE_RECEIVE);

		while(true) {
			count = 10;
			if(btSignalReceive()) {  //if Receive Bluetooth Signal
				//Bzz Friend ID Load From PreferenceData
				String bzz_id = preference.getString("bzz_id", null);
				//Log.d()
				if(bzz_id != null && !bzz_id.equals("")) {         //Trans To Server
					while(count > 0) {          //10번까지 시도
						if(svTrans(bzz_id)) break;  //전송성공시 그만시도
						count--;
					}
				} //try trans

			} //if(receive)
		} //while
	}

	private boolean btSignalReceive() {
		try {
			Thread thread = new Thread(btComu);
			thread.start();
			thread.join();  //wait
		} catch(InterruptedException e) {
			e.printStackTrace();
		}

		return btComu.isRead;   //bzzSignal Read한 경우 true
	}

	private boolean svTrans(String id) {
		svComu = new ServerCommunication();
		SharedPreferences preference = mContext.getSharedPreferences("user_info", Activity.MODE_PRIVATE);
		svComu.makeMsg(preference.getString("my_id", "0"), id, null, null, 2, null, null, 0);   //msg 만듬
		svComu.start();
		try {
			svComu.join(Constants.ServerWaitTime);  //thread wait
			if(svComu.chkError) {   //진동전송 실패시
				return false;
			} else {
				if(svComu.final_data == null) {
					return false;
				}
				if(!(boolean) svComu.final_data) {  //진동전송 실패시
					return false;
				}
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		return true;    //진동전송 성공
	}

	public void stopThread() {
		btComu.closeSock();
	}

}
