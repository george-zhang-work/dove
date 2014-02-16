package com.sina.tianqitong.appwidget;

import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;

import com.sina.tianqitong.lib.utility.AppDirUtility;
import com.sina.tianqitong.utility.DataStorageUtility;

import sina.mobile.tianqitong.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class AppDownloadTask extends AsyncTask<Void, Object, Boolean> {

	private static final String TAG = "AppDownloadTask";
	private static final String DEFAULT_DOWNLOAD_FILE_NAME = "tqt_downloaded_third_app.apk";

	private static final int DOWNLOAD_NOTIFY_ID = 0Xff01;

	private static final int INVALIDE_VALUE = -1;
	private static final int BUFFER_SIZE = 1024 * 2;
	private static final long UPDATE_TIME_INTERVAL = 1000;

	public final static int NOTIFICATION_STATUS_FLAG_NORMAL = 0x00;
	public final static int NOTIFICATION_STATUS_FLAG_ERROR = 0x01;

	public final static int ERROR_INVALID_DOWNLOAD_URL = 0X0101;
	public final static int ERROR_FAILED_OPEN_DOWNLOAD_URL = 0X0102;
	public final static int ERROR_FAILED_NO_SD_CARD = 0X0102;

	private String mFileUrl;
	private String mFileName;
	private int mFileSize;
	private String mFileSizeM;

	private Builder mBuilder;
	private Context mContext;
	private boolean mShowNotification;
	private NotificationManager mNM;

	public AppDownloadTask(Context context, String fileUrl, String fileName,
			int fileSize, String fileSizeM, boolean showNotfication) {
		mFileUrl = fileUrl;
		mFileName = fileName;
		mShowNotification = showNotfication;

		fileSizeM = removeUnit(fileSizeM);
		if (fileSize <= 0 && !TextUtils.isEmpty(fileSizeM)) {
			fileSize = (int) (Float.parseFloat(fileSizeM) * 1024 * 1024);
		} else if (TextUtils.isEmpty(fileSizeM) && fileSize > 0) {
			fileSizeM = formatSizeM(fileSize);
		}

		DownloadManager
		mContext = context;
		mNM = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);

		if (showNotfication) {
			mBuilder = new Builder(context)
					.setContentTitle(
							context.getString(R.string.download_notification_content_title))
					.setContentText(
							context.getString(R.string.download_notification_content_text))
					.setLargeIcon(
							BitmapFactory.decodeResource(
									context.getResources(),
									R.drawable.app_ic_launcher))
					.setSmallIcon(R.drawable.app_ic_dialog);
			notify(mBuilder.build());
		}
	}

	private void notify(Notification notification) {
		mNM.notify(DOWNLOAD_NOTIFY_ID, notification);

	}

	/**
	 * Values at least contains two values. <br/>
	 * 
	 * @param type
	 *            Downloaded result type as defined above.
	 * @param current
	 *            second argument, indicates current loaded bytes length.
	 * @param speed
	 *            third argument, indicates current loaded speed.
	 * @param others
	 *            come with specific type.
	 */
	@Override
	protected void onProgressUpdate(Object... values) {
		super.onProgressUpdate(values);
		if (mShowNotification && values != null) {
			final int type = (Integer) values[0];
			switch (type) {
			case NOTIFICATION_STATUS_FLAG_ERROR:
				if (values.length > 4) {
					final int what = (Integer) values[3];
					onErrorStatus(what);
				}
				break;
			case NOTIFICATION_STATUS_FLAG_NORMAL:
				if (values.length > 3) {
					final int current = (Integer) values[0];
					final int speed = (Integer) values[1];
					final int percent = current * 100 / mFileSize;
					onNormalStatus(current, speed, percent);
				}
				break;
			}
		}
	}

	protected void onErrorStatus(final int what) {

	}

	protected void onNormalStatus(int current, int speed, int percent) {
		mBuilder.setProgress(mFileSize, current, false);
		notify(mBuilder.build());
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if (TextUtils.isEmpty(mFileName)) {
			mFileName = getDownloadFileName(mFileUrl);
		}
	}

	@Override
	protected void onPostExecute(Boolean result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		disableConnectionReuseIfNecessary();
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		try {
			final URL url = new URL(mFileUrl);
			final HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			urlConnection.connect();
			int fileSize = urlConnection.getContentLength();
			if (fileSize != INVALIDE_VALUE) {
				mFileSize = fileSize;
				mFileSizeM = formatSizeM(fileSize);
			}
			bis = new BufferedInputStream(urlConnection.getInputStream());
			File saveFile = getDownloadFile(mFileUrl, mFileName);
			if (saveFile == null) {
				// Failed to create save file.
				publishProgress(NOTIFICATION_STATUS_FLAG_ERROR, 0, 0,
						ERROR_FAILED_NO_SD_CARD);
				// Throw ignored exception to close the file.
				throw new Exception("ignored exception");
			}
			fos = new FileOutputStream(saveFile);
			download(bis, fos);
			return true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception ignoredException) {
			ignoredException.printStackTrace();
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Do download work from Inputstream in to OutputStream out.
	 * 
	 * @param in
	 *            The file input stream.
	 * @param out
	 *            The file output stream.
	 * @return true if file successfully downloaded, otherwise false.
	 * @throws IOException
	 */
	private boolean download(InputStream in, OutputStream out)
			throws IOException {
		int len = 0;
		int current = 0;
		int bytesInThreshold = 0;
		long updateDelta = 0;
		long updateStart = System.currentTimeMillis();

		byte[] buffer = new byte[BUFFER_SIZE];
		while ((len = in.read(buffer)) != INVALIDE_VALUE) {
			out.write(buffer, 0, len);
			out.flush();
			current += len;
			bytesInThreshold += len;
			if (updateDelta > UPDATE_TIME_INTERVAL) {
				long speed = bytesInThreshold / updateDelta;
				publishProgress(current, speed);
				updateStart = System.currentTimeMillis();
				bytesInThreshold = 0;
			}
			updateDelta = System.currentTimeMillis() - updateStart;

			// If the task is cancelled, break the downloading progress.
			if (isCancelled()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Workaround for bug pre-Froyo, see here for more info:
	 * http://android-developers.blogspot.com/2011/09/androids-http-clients.html
	 */
	private static void disableConnectionReuseIfNecessary() {
		// HTTP connection reuse which was buggy pre-froyo
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
			System.setProperty("http.keepAlive", "false");
		}
	}

	/**
	 * Used to generate a file name from downloaded url.
	 * 
	 * @param fileUrl
	 *            The downloaded url.
	 * @return A file name.
	 */
	private static String getDownloadFileName(String fileUrl) {
		final String fileName;
		final int index = fileUrl.lastIndexOf('/');
		if (index != -1) {
			fileName = fileUrl.substring(index + 1);
		} else {
			fileName = DEFAULT_DOWNLOAD_FILE_NAME;
		}
		return fileName;
	}

	/**
	 * Try to remove the last unit char.
	 * 
	 * @param fileSizeM
	 *            as 3.6M, 3.6 or others format.
	 * @return as 3.6 without unit M or m.
	 */
	private static String removeUnit(String fileSizeM) {
		if (!TextUtils.isEmpty(fileSizeM)) {
			if (fileSizeM.endsWith("M") || fileSizeM.endsWith("m")) {
				fileSizeM = fileSizeM.substring(0, fileSizeM.length() - 1);
			}
		}
		return fileSizeM;
	}

	/**
	 * Change fileSize to size with Million byte unit.
	 * 
	 * @param fileSize
	 *            The file size with decimal unit.
	 * @return file size with million byte unit.
	 */
	private static String formatSizeM(int fileSize) {
		return String.format(Locale.CHINA, "%.2lf",
				(float) fileSize / 1024 / 1024);
	}

	private static File getDownloadFile(String fileUrl, String fileName) {
		return DataStorageUtility.getLocalFileFromHttpUrl(AppDirUtility
				.getDownloadDir(), TextUtils.isEmpty(fileName) ? fileUrl
				: fileName);
	}
}



